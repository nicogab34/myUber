package myUber;

import java.util.ArrayList;

import sun.misc.Lock;

public class Driver extends Lock{
	private int ID;
	private String name;
	private String surname;
	private String state;
	private Car car;
	private ArrayList<Double> position;
	private ArrayList<Customer> requests = new ArrayList<Customer>();
	private static int nextID = 1;

	public Driver(String name, String surname, String state,ArrayList<Double> position) {
		super();
		this.name = name;
		this.surname = surname;
		this.state = state;
		this.ID = nextID;
		nextID++;
		this.position=position;
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
	
	public Car getCar() {
		return car;
	}
	

	public ArrayList<Double> getPosition() {
		return position;
	}

	public void setPosition(ArrayList<Double> position) {
		this.position = position;
	}
	
	public void addRequest(Customer customer) {
		this.requests.add(customer);
	}
	
	public void decideRequest(boolean b,Customer c) {
		
	}

	public String toString() {
		String carString;
		if (this.car==null) {
			carString = "No car";
		}
		else {
			carString = this.car.getID();
		}
		return "## DRIVER ##\n"+
	"ID : "+this.ID+"\n"+
	"Name : "+this.name+"\n"+
	"Surname : "+this.surname+"\n"+
	"State : "+this.state+"\n"+
	"Car : "+carString+"\n";
	}

}
