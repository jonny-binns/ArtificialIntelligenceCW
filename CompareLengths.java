import java.util.*;

class CompareLengths implements Comparator<Caves>{
	
	public int compare(Caves a, Caves b)
	{
		return Double.compare(a.length, b.length);
	}

}