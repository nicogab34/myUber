package myUber;

import java.util.ArrayList;

public class Customer{
	private int ID;
	private String name;
	private String surname;
	private ArrayList<Double> coordinates;
	private int creditCardNumber;
	private Request request ;
	private ArrayList<Double> destination = new  ArrayList<Double>();
	private double numberOfRides=0;
	private double totalTime=0;
	private double totalCharge=0;
	
	private static int nextID = 1;

	public Customer(String name, String surname, ArrayList<Double> coordinates, int creditCardNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.coordinates = coordinates;
		this.creditCardNumber = creditCardNumber;
		this.ID = nextID;
		nextID++;
	}
	

	public double getTotalTime() {
		return totalTime;
	}


	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}


	public double getTotalCharge() {
		return totalCharge;
	}


	public void setTotalCharge(double totalCharge) {
		this.totalCharge = totalCharge;
	}


	public double getNumberOfRides() {
		return numberOfRides;
	}

	public void setNumberOfRides(double numberOfRides) {
		this.numberOfRides = numberOfRides;
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

	public ArrayList<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Double> coordinates) {
		this.coordinates = coordinates;
	}

	public int getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public ArrayList<Double> getDestination() {
		return destination;
	}

	public void setDestination(ArrayList<Double> destination) {
		this.destination = destination;
	}
	
	public void answer(String chosenRideType,Request request) {
		request.setChoice(chosenRideType);
	}
	
	public void notify(ArrayList<Double> prices, ArrayList<String> rideTypes) {
		for (int i=0;i<prices.size(); i++) {
			System.out.println(rideTypes.get(i)+" : "+prices.get(i));
		}
	}
	
	public void chooseRideType(String rideType) {
		this.request.setChoice(rideType);
	}
	
	public String toString() {
		String destinationString;
		if (this.destination.size() == 0) {
			destinationString = "Not defined yet";
		}
		else {
			destinationString = this.destination.toString();
		}
		return
		"## CUSTOMER ##\n"+
		"ID : "+this.ID+"\n"+
		"Name : "+this.name+"\n"+
		"Surname : "+this.surname+"\n"+
		"Coordinates : "+this.coordinates+"\n"+
		"Credit Card Number : "+this.creditCardNumber+"\n"+
		"Destination : "+destinationString+"\n";
	}
	
}
