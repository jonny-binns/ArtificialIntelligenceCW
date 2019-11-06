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
import java.util.Collections;
import java.util.Scanner;

public class RouteFinder {

	public static void main(String[] args) {
		
		/**
		 * MAKE THIS INTO FUNCTION
		 */
		
		
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
		
		
		try{
			File caves = new File("input1.cav");
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
			System.out.println("input file not found");
			e.printStackTrace();
	}
	}

}
