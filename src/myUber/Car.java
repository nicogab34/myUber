package myUber;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public abstract class Car{
	private int seats;
	private String ID;
	private ArrayList<Driver> owners;
	
	
	public Car(int seats, String iD, ArrayList<Driver> owners) {
		super();
		this.seats = seats;
		ID = iD;
		this.owners = owners;
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
	
	
	

}
