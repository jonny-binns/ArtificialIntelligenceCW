/* Pseudocode for caves
 * number (int)
 * xCoOrd (int)
 * yCoOrd (int)
 * connectedNodes (arraylist)
 * length (float)
 * isLengthPerm(bool)
 */


import java.util.*;

public class Caves {
	int number = 0;
	int xCoOrd = 0;
	int yCoOrd = 0;
	ArrayList<Integer> connectedNodes = new ArrayList<Integer>();
	double length = 0.0;
	boolean isLengthPerm = false;
	
	@Override
	public String toString()
	{
		return "Number = " + number + " X = " + xCoOrd + " Y = " + yCoOrd + " Connected Nodes = " + connectedNodes + " Length = " + length + " Is Length Perminant = " + isLengthPerm;
	}
}
