package rides;

import java.util.ArrayList;

import customer.Customer;
import driver.Driver;

public abstract class Ride {
	private String state;
	private Customer customer;
	private ArrayList<Customer> customers=new ArrayList<Customer>();
	private int ID;
	private Driver driver;
	private static int nextID;
	private double rideDuration;
	public Ride(String state, Customer customer, Driver driver) {
		super();
		this.state = state;
		this.customer = customer;
		ID = nextID;
		this.driver = driver;
		nextID++;
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public Ride(String state, ArrayList<Customer> customers, Driver driver) {
		super();
		this.state = state;
		for (Customer c:customers) {
			
			this.customers.add(c);
		}
		ID = nextID;
		this.driver = driver;
		nextID++;
	}
	
	
	public double getRideDuration() {
		return rideDuration;
	}

	public void setRideDuration(double rideDuration) {
		this.rideDuration = rideDuration;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Customer getCustomer() {
		return customer;
	}
	public Driver getDriver() {
		return driver;
	}
	public String toString() {
		return "Customer : "+this.customer+"\n"+
	"Driver : "+this.driver+"\n";
	}
	
	
}
