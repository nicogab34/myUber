package myUber;

import java.util.ArrayList;

public class Van extends Car{
	private static int seats = 6;
	private static int nextID = 1;
	private static String rideType = "UberVan";

	public Van(ArrayList<Driver> owners) {
		super(seats, "Van"+nextID, owners, rideType);
		nextID++;
	}

}
