package myUber;

public abstract class Ride {
	private String state;
	private Customer customer;
	private int ID;
	private Driver driver;
	private static int nextID;
	public Ride(String state, Customer customer, Driver driver) {
		super();
		this.state = state;
		this.customer = customer;
		ID = nextID;
		this.driver = driver;
		nextID++;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String toString() {
		return "Customer : "+customer+"\n"+
	"Driver : "+driver+"\n";
	}
	
	
}
