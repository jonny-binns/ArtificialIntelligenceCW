/* Pseudocode for project
 * readFile
 * caveList
 * initcaveList
 * lengthList
 * initLengthList
 * routeList
 * orderList
 * 
 * startNode = caveList(0)
 * startNode.length = 0
 * startNode.isLengthPerm = True
 * orderList.add(startNode)
 * lengthList.remove(startNode)
 * 
 * endNode = caveList(caveList.length)
 * caveListPos = 0
 * while(endNode.isLengthPerm = False)
 * {
 * 		currentNode = caveList(caveListPos)
 * 		connectedNodePos = 0
 * 		while(connectedNodePos < currentNode.connectedNodes.Length)
 * 		{
 *			connectedNode = currentnode.connectedNodes(connectedNodePos)
 * 			if(orderList.contains(connectedNode))
 *			{
 *				connectedNodePos++
 *			}
 * 			else
 * 			{	
 *  			distance = calculateDistance(currentNode, connectedNode)
 * 				if(distance < connectedNode.length)
 * 				{
 * 					connectedNode.length = distance
 * 					connectedNodePos++
 * 				}
 * 				else
 * 				{
 * 					connectedNodePos++
 * 				}
 * 			}
 * 		}
 * 		updateLengthsList()
 * 		lengthsList(0).isLengthPerm = True
 * 		caveListPos = lengthList(0).number - 1
 * 		orderList.add(lengthList(0))
 * 		lengthsList.remove(lengthList(0))
 * 		}
 * 
 * orderListPos = orderList.length
 * routeList.add(orderList(orderListPos))
 * 
 * while(orderListPos >= 0)
 * {
 * 		x = orderListPos - 1
 * 		if(orderList(orderListPos) - calculateDistance(orderList(orderListPos), orderList(x)) = orderList(x).length)
 * 		{
 * 			routes.add(orderList(x)
 * 			orderListPos = x
 * 		}
 * 		else
 * 		{
 * 			x--
 * 		}
 * }	
 * 
 * routeList.reverse
 * outputFile
 * 
 */

import java.io.*;
import java.util.*;


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
		String cavesStr = "";
		try{
			File caves = new File(fileName);
			Scanner myReader = new Scanner(caves);
			while(myReader.hasNextLine())
			{
				cavesStr = myReader.nextLine();
			}
			myReader.close(); 
	}
	catch (FileNotFoundException e)
	{
			System.out.println("input file not found");
			e.printStackTrace();
	}
		return cavesStr;
	}
	
	
	static void WriteFile(String cavesStr)
	{
		try 
		{
			FileWriter writer = new FileWriter("output.txt");
			writer.write(cavesStr);
			writer.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Error: ");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//take in input file and set to cavesStr
		String fileName = "input1.cav";
		String cavesStr = ReadFile(fileName);
		String[] cavesArr = cavesStr.split(",");
		//System.out.println(cavesStr);
		
		//create and populate caveList
		ArrayList<Caves> cavesList = new ArrayList<Caves>();
		int noOfCaves = Integer.parseInt(cavesArr[0]);
		
		//creates objects and adds co-ordinates/number and adds to list
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
		
		//gets connected nodes and adds them to the objects
		caveNo = 1;
		for(int x=noOfCaves*2+1; x<cavesArr.length; x+=7)
		{
			ArrayList<Integer> connectedNodes = new ArrayList<Integer>();
			for(int connectedNo=0; connectedNo<noOfCaves; connectedNo++)
			{
				if(cavesArr[x+connectedNo].equals("1"))
				{
					connectedNodes.add(connectedNo+1);
				}
			}
			Caves currentCave = cavesList.get(caveNo-1);
			currentCave.connectedNodes = connectedNodes;
			cavesList.remove(caveNo-1);
			cavesList.add((caveNo-1), currentCave);
			caveNo++;
		}
		
		//sets caves lengths to max values
		for(int x=0; x<cavesList.size(); x++)
		{
			Caves currentCave = cavesList.get(x);
			currentCave.length = Double.MAX_VALUE;
			cavesList.remove(x);
			cavesList.add(x, currentCave);
		}
		
		for(int x=0; x<cavesList.size(); x++)
		{
			Caves currentCave = cavesList.get(x);
			System.out.println(currentCave.toString());
		}
	}

}
