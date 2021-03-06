package cars;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import driver.Driver;

public abstract class Car{
	private int seats;
	private String ID;
	private ArrayList<Driver> owners;
	private String rideType;
	
	
	public Car(int seats, String iD, ArrayList<Driver> owners, String rideType) {
		super();
		this.seats = seats;
		ID = iD;
		this.owners = owners;
		this.rideType = rideType;
	}


	public ArrayList<Driver> getOwners() {
		return owners;
	}


	public void setOwners(ArrayList<Driver> owners) {
		this.owners = owners;
	}


	public int getSeats() {
		return seats;
	}


	public String getID() {
		return ID;
	}


	public String getRideType() {
		return rideType;
	}


	public void setRideType(String rideType) {
		this.rideType = rideType;
	}
	
	
	

}
