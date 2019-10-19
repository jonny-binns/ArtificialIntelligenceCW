import java.io.*;
import java.util.Collections;
import java.util.Scanner;

public class RouteFinder {

	public static void main(String[] args) {
		try{
	 			String cavesFile = args[0];
				//System.out.println(file);
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

	}

}
