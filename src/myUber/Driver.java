package myUber;

import sun.misc.Lock;

public class Driver extends Lock{
	private int ID;
	private String name;
	private String surname;
	private String state;
	private Car car;
	
	private static int nextID = 1;

	public Driver(String name, String surname, String state) {
		super();
		this.name = name;
		this.surname = surname;
		this.state = state;
		this.ID = nextID;
		nextID++;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void takeCar(Car car) {
		this.car = car;
	}

}
