import java.util.*;

class CompareLengths implements Comparator<Caves>{
	
	public int compare(Caves a, Caves b)
	{
		return Long.compare(a.length, b.length);
		//return Double.compare(a.length, b.length);
	}

}
