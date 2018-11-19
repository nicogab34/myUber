package cars;

import java.util.ArrayList;

import driver.Driver;

public class Berline extends Car{
	private static int seats = 4;
	private static String rideType = "UberBlack";
	
	private static int nextID = 1;

	/**
	 * @param owners
	 * Creates a new car of type Berline
	 */
	public Berline(ArrayList<Driver> owners) {
		super(seats, "Berline"+nextID, owners, rideType);
		nextID++;
	}
	

}
