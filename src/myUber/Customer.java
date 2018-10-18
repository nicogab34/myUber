package myUber;

import java.util.ArrayList;

public class Customer {
	private int ID;
	private String name;
	private String surname;
	private ArrayList<Float> coordinates;
	private int creditCardNumber;
	private ArrayList<Message> messages = ArrayList<Message>();
	
	private static int nextID = 1;

	public Customer(String name, String surname, ArrayList<Float> coordinates, int creditCardNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.coordinates = coordinates;
		this.creditCardNumber = creditCardNumber;
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

	public ArrayList<Float> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Float> coordinates) {
		this.coordinates = coordinates;
	}

	public int getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
}
