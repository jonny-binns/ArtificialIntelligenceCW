import java.util.*;

public class Caves {
	int number = 0;
	int xCoOrd = 0;
	int yCoOrd = 0;
	ArrayList<Integer> connectedCaves = new ArrayList<Integer>();
	double length = 0.0;
	boolean isLengthPerm = false;
	
	@Override
	public String toString()
	{
		return "Cave Number = " + number + " X = " + xCoOrd + " Y = " + yCoOrd + " Connected Caves = " + connectedCaves + " Length = " + length + " Is Length Perminant = " + isLengthPerm;
	}
}
