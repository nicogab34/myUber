package cars;

import java.util.ArrayList;

import driver.Driver;

public class Van extends Car{
	private static int seats = 6;
	private static int nextID = 1;
	private static String rideType = "UberVan";

	/**
	 * @param owners
	 * Creates a new car of type Van
	 */
	public Van(ArrayList<Driver> owners) {
		super(seats, "Van"+nextID, owners, rideType);
		nextID++;
	}

}
