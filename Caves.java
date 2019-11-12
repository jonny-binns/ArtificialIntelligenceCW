import java.util.*;

public class Caves {
	int number = 0;
	int xCoOrd = 0;
	int yCoOrd = 0;
	ArrayList<Integer> connectedCaves = new ArrayList<Integer>();
	long length = 0;
	boolean isLengthPerm = false;
	
	@Override
	public String toString()
	{
		return "Cave Number = " + number + " X = " + xCoOrd + " Y = " + yCoOrd + " Connected Caves = " + connectedCaves + " Length = " + length + " Is Length Perminant = " + isLengthPerm;
	}
}
