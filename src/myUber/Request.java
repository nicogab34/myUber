package myUber;

import java.util.ArrayList;

public class Request{
	Customer customer;
	MyUber platform;
	ArrayList<Double> prices;
	String choice;
	
	public Request(Customer c, MyUber platform) {
		super();
		this.customer=c;
		this.platform = platform;
	}
	
	public ArrayList<Double> getPrices() {
		return prices;
	}
	public void setPrices(ArrayList<Double> prices) {
		this.prices = prices;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
		Driver driver = this.platform.search(this.customer, choice);
		if (driver == null) {
			this.customer.setRequest(null);
		}
		else {
			driver.addRequest(this.customer);
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public MyUber getPlatform() {
		return platform;
	}
	
	
	
	
}
