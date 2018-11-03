package myUber;

public abstract class Ride {
	private String state;
	private Customer customer;
	private int ID;
	private Driver driver;
	public Ride(String state, Customer customer, int iD, Driver driver) {
		super();
		this.state = state;
		this.customer = customer;
		ID = iD;
		this.driver = driver;
	}
	
	
	
}
