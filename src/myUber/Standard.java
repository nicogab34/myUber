package myUber;

import java.util.ArrayList;

public class Standard extends Car{
	
	private static int seats = 4;
	private static int nextID = 1;
	
	public Standard(ArrayList<Driver> owners) {
		super(seats, "Standard"+nextID, owners);
		// TODO Auto-generated constructor stub
		nextID++;
	}

}
