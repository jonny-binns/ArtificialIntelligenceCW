import java.util.*;
//comparator that compares lengths of 2 caves
class CompareLengths implements Comparator<Caves>{
	
	public int compare(Caves a, Caves b)
	{
		return Long.compare(a.length, b.length);
	}

}
