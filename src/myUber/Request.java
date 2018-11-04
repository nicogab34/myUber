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
		Driver driver = this.platform.search(this.customer, this.choice);
		driver.addRequest(customer);
	}
	
	
}
