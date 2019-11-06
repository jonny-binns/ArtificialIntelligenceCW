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
 * 			distance = calculateDistance(currentNode, connectedNode)
 * 			if(distance < connectedNode.length)
 * 			{
 * 				connectedNode.length = distance
 * 				connectedNodepos++
 * 			}
 * 			else
 * 			{
 * 				connectedNodepos++
 * 			}
 * 		}
 * 		updateLengthsList()
 * 		lengthsList(0).isLengthPerm = True
 * 		caveListPos = lengthList(0).number
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
import java.util.Scanner;


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
		String fileName = "input1.cav";
		String cavesStr = ReadFile(fileName);
		System.out.println(cavesStr);
		
		
		
		WriteFile(cavesStr);
		
	}

}
