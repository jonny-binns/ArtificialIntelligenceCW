import java.io.*;
import java.util.*;
import java.math.*;


public class RouteFinder {
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
		
		//gets connected nodes and adds them to the caves in cavesList
		caveNo = 1;
		for(int x=noOfCaves*2+1; x<cavesArr.length; x+=noOfCaves)
		{
			ArrayList<Integer> connectedCaves = new ArrayList<Integer>();
			for(int connectedNo=0; connectedNo<noOfCaves; connectedNo++)
			{
				if(cavesArr[x+connectedNo].equals("1"))
				{
					connectedCaves.add(connectedNo+1);
				}
			}
			Caves currentCave = cavesList.get(caveNo-1);
			currentCave.connectedCaves = connectedCaves;
			Replace(cavesList, (caveNo-1), currentCave);
			caveNo++;
		}
		
		//sets caves lengths to max values
		for(int x=0; x<cavesList.size(); x++)
		{
			Caves currentCave = cavesList.get(x);
			currentCave.length = Double.MAX_VALUE;
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
		startCave.length = 0.0;
		startCave.isLengthPerm = true;
		Replace(cavesList, 0, startCave);
		orderList.add(startCave);
		lengthList.remove(0);
		
		Caves endCave = cavesList.get(cavesList.size()-1);
		int cavesListPos = 0;
		
		while(endCave.isLengthPerm == false)
		{
				Caves currentCave = cavesList.get(cavesListPos);
				int connectedCavePos = 0;
				
				while(connectedCavePos < currentCave.connectedCaves.size())
				{
					Caves connectedCave = GetConnectedCave(currentCave, connectedCavePos, cavesList);
					if(orderList.contains(connectedCave))
					{
						connectedCavePos++;
					}
					else
					{
						double distance = CalculateDistance(currentCave, connectedCave);
						if(distance < connectedCave.length)
						{
							connectedCave.length = distance;
							connectedCavePos++;
						}
						else
						{
							connectedCavePos++;
						}
					}
					
					
				}
		}
		
	}

}
