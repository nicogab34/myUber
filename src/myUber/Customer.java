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

	/**
	 * @param name
	 * @param surname
	 * @param coordinates
	 * @param creditCardNumber
	 * Creates a new Customer
	 */
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

	/**
	 * @return the ID of the customer
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @return the name of the customer
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * Sets the name of the customer
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname of the customer
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 * Sets the surname of the customer
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the coordinates of the customer
	 */
	public ArrayList<Double> getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates
	 * Sets the customer's coordinate
	 */
	public void setCoordinates(ArrayList<Double> coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the credit card number of the customer
	 */
	public int getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * @param creditCardNumber
	 * Sets the credit card number of the customer
	 */
	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**
	 * @return the running request of the customer
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * @param request
	 * Sets the running request of the customer
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * @return the destination of the customer
	 */
	public ArrayList<Double> getDestination() {
		return destination;
	}

	/**
	 * @param destination
	 * Sets the destination of the customer
	 */
	public void setDestination(ArrayList<Double> destination) {
		this.destination = destination;
	}
	
	/**
	 * @param rideType
	 * Sets the ride type of the request
	 */
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
