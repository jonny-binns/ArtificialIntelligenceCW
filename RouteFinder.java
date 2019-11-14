import java.io.*;
import java.util.*;

public class RouteFinder {
	//reads in specified file and returns string of contents
	static String ReadFile(String fileName)
	{
		/** for running in cmd
		try{
	 			String cavesFile = args[0];
				File caves = new File(cavesFile);
				String cavesStr = "";
				Scanner myReader = new Scanner(caves);
				while(myReader.hasNextLine())
				{
					cavesStr = myReader.nextLine();
				}
				myReader.close();

				System.out.println(cavesStr);
		}
		catch (FileNotFoundException e)
		{
				System.out.println("Java did an o o p s i e.");
				e.printStackTrace();
		}
		 **/
		String str = "";
		try{
			File caves = new File(fileName);
			Scanner myReader = new Scanner(caves);
			while(myReader.hasNextLine())
			{
				str = myReader.nextLine();
			}
			myReader.close(); 
	}
	catch (FileNotFoundException e)
	{
			System.out.println("input file not found");
			e.printStackTrace();
	}
		return str;
	}
	
	//writes string to file
	static void WriteFile(String str)
	{
		try 
		{
			FileWriter writer = new FileWriter("output.txt");
			writer.write(str);
			writer.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Error: ");
			e.printStackTrace();
		}
	}
	
	//takes in list of caves and prints it
	static void PrintList(ArrayList<Caves> cavesList)
	{
		for(int x=0; x<cavesList.size(); x++)
		{
			Caves currentCave = cavesList.get(x);
			System.out.println(currentCave.toString());
		}
	}
	
	//replaces list
	static void Replace(ArrayList<Caves> cavesList, int pos, Caves currentCave)
	{
		cavesList.remove(pos);
		cavesList.add(pos, currentCave);
	}
	
	//gets the specified connected cave from the caves list
	static Caves GetConnectedCave(Caves parentCave, int connectedCaveNo, ArrayList<Caves> cavesList)
	{
		int returnCaveNo = parentCave.connectedCaves.get(connectedCaveNo);
		Caves connectedCave = new Caves();
		
		for(int x=0; x<cavesList.size(); x++)
		{
			Caves currentCave = cavesList.get(x);
			if(currentCave.number == returnCaveNo)
			{
				connectedCave = currentCave;
			}
		}
		
		return connectedCave;
	}
	
	//calculates distance between 2 caves
	static double CalculateDistance(Caves startCave, Caves endCave)
	{
		double xDifference = startCave.xCoOrd - endCave.xCoOrd;
		double xDifferenceSquared = xDifference * xDifference;
		
		double yDifference = startCave.yCoOrd - endCave.yCoOrd;
		double yDifferenceSquared = yDifference * yDifference;
		
		double distance = Math.sqrt((xDifferenceSquared + yDifferenceSquared));
		
		return distance;
	}
	

public static void main(String[] args) {
		//take in input file, set to cavesStr and convert cavesSrr to array
		String fileName = "input1.cav";
		String cavesStr = ReadFile(fileName);
		String[] cavesArr = cavesStr.split(",");
		
		//create cavesList and stores the number of caves in the input file
		ArrayList<Caves> cavesList = new ArrayList<Caves>();
		int noOfCaves = Integer.parseInt(cavesArr[0]);
		
		//creates cave objects gives their co-ordinates/number and adds to cavesList
		int caveNo = 1;
		for(int x=1; x<=noOfCaves*2; x+=2)
		{	
			Caves cave = new Caves();
			cave.xCoOrd = Integer.parseInt(cavesArr[x]);
			cave.yCoOrd = Integer.parseInt(cavesArr[x+1]);
			cave.number = caveNo;

			cavesList.add(cave);
			caveNo++;
		}
		
		//gets connected caves and adds them to the caves in cavesList
		for(int x=0; x<noOfCaves; x++)
		{	
			int counter = x;
			ArrayList<Integer> connectedCaves = new ArrayList<Integer>();
			for(int y=0; y<noOfCaves; y++)
			{
				if(cavesArr[(noOfCaves*2+1)+counter].contentEquals("1"))
				{
					connectedCaves.add(y+1);
				}
				counter = counter + noOfCaves;
			}
			Caves currentCave = cavesList.get(x);
			currentCave.connectedCaves = connectedCaves;
			Replace(cavesList, x, currentCave);
		}
		
			
		//sets caves lengths to max values
		for(int x=0; x<cavesList.size(); x++)
		{
			Caves currentCave = cavesList.get(x);
			//currentCave.length = Double.MAX_VALUE;
			currentCave.length = Long.MAX_VALUE;
			Replace(cavesList, x, currentCave);
		}
		
		
		//create length list
		ArrayList<Caves> lengthList = new ArrayList<Caves>();
		
		//Initiate lengthList
		for(int x=0; x<cavesList.size(); x++)
		{
			Caves currentCave = cavesList.get(x);
			lengthList.add(x, currentCave);
		}
		
		//create route list
		ArrayList<Caves> routeList = new ArrayList<Caves>();
		
		//create order list
		ArrayList<Caves> orderList = new ArrayList<Caves>();		
		
	
		//create start cave, set length 5 to 0, make length permanent, update cavesList with new cave, add to orderList and remove from lengthList 
		Caves startCave = cavesList.get(0);
		startCave.length = 0;
		startCave.isLengthPerm = true;
		Replace(cavesList, 0, startCave);
		orderList.add(startCave);
		lengthList.remove(0);
		
		//specifies the endCave and initialize the cavesList position counter
		Caves endCave = cavesList.get(cavesList.size()-1);
		int cavesListPos = 0;

		//terminates when the last cave has a permanent length
		while(endCave.isLengthPerm == false)
		{
				//gets a current cave and sets the counter for the connected caves to 0
				Caves currentCave = cavesList.get(cavesListPos);
				int connectedCavePos = 0;
				//while the connected cave list has elements
				while(connectedCavePos < currentCave.connectedCaves.size())
				{
					//gets the connected cave at the specified point
					Caves connectedCave = GetConnectedCave(currentCave, connectedCavePos, cavesList);
					//skips if the order list already contains the connected cave
					if(orderList.contains(connectedCave))
					{
						connectedCavePos++;
					}
					else
					{
						//calculates the distance and multiplies it by 100 to keep 3dp of accuracy
						double currentDistance = CalculateDistance(currentCave, connectedCave);
						double distanceMultiple = (currentDistance * 1000) + currentCave.length;
						Long distance = (long) distanceMultiple;
						//checks if distance is the shorter than current
						if(distance < connectedCave.length)
						{
							//updates distance if shorter and moves on to next cave in list
							connectedCave.length = distance;
							connectedCavePos++;
						}
						else
						{
							//moves to next cave in list
							connectedCavePos++;
						}
					}
				}
				//sorts the length list by cave.length
				Collections.sort(lengthList, new CompareLengths());
				//makes the shortest length true (will always be the current cave
				lengthList.get(0).isLengthPerm = true;
				//moves to the next cave in the list
				cavesListPos = (lengthList.get(0).number - 1);
				//adds the cave with the shortest length to the order list
				orderList.add(lengthList.get(0));
				//removes the cave with the shortest length from the length list
				lengthList.remove(0);
		}
		
		//sets the order list position to the end of the list
		int orderListPos = orderList.size()-1;
		//sets x to be one less than the end of the list
		int x = orderListPos - 1;
		//adds the final cave to the route list
		routeList.add(orderList.get(orderListPos));
		//initializes the output string
		String output="";
		//if the final cave's length wasnt changed then no route has been found
		if(routeList.get(0).length == Long.MAX_VALUE)
		{
			//if no route found sets output to 0
			output = "0";
		}
		else
		{
			//breaks when we finish checking the orderList
			while(orderListPos >= 0 && x>=0)
			{
				//calculates distance and converts to long
				double currentDistance = CalculateDistance(orderList.get(orderListPos), orderList.get(x));
				double distanceMultiple = (currentDistance * 1000);
				Long distance = (long) distanceMultiple;
				//if the current cave - the distance between that and cav x is true
				if(orderList.get(orderListPos).length - distance== orderList.get(x).length)
				{
					//caves are connected and adds the cave x to the route list
					routeList.add(orderList.get(x));
					//sets the orderList position to x
					orderListPos = x;
				}
				//otherwise decrease x
				x--;
			}
			//reverse the routeList to start from the beginning
			Collections.reverse(routeList);
			
			//loops through the final routeList, gets the caves numbers and adds them to the output string
			for(int y=0; y<routeList.size(); y++)
			{
				output = output + routeList.get(y).number + " ";
			}
		}
		//writes the output string to output.txt
		WriteFile(output);
		//System.out.println(output);
		
	}

}
