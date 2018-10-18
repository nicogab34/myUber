package myUber;

import java.util.ArrayList;

public class Berline extends Car{
	private static int seats = 4;
	private static int nextID = 1;

	public Berline(ArrayList<Driver> owners) {
		super(seats, "Berline"+nextID, owners);
		nextID++;
	}
	

}
