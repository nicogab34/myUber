package cars;

import java.util.ArrayList;

import driver.Driver;

public class Standard extends Car{
	
	private static int seats = 4;
	private static int nextID = 1;
	
	/**
	 * @param owners
	 * @param rideType
	 * Creates a new of type Standard
	 */
	public Standard(ArrayList<Driver> owners, String rideType) {
		super(seats, "Standard"+nextID, owners, rideType);
		// TODO Auto-generated constructor stub
		nextID++;
	}

}
