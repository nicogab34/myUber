package myUber;

import java.util.ArrayList;

import customer.Customer;
import driver.Driver;

public class Request{
	private Customer customer;
	private MyUber platform;
	private ArrayList<Double> prices;
	private String choice;
	private boolean state;
	private int step;
	
	public Request(Customer c, MyUber platform) {
		super();
		this.customer=c;
		this.platform = platform;
	}
	
	public ArrayList<Double> getPrices() {
		return prices;
	}
	public void setPrices(ArrayList<Double> prices) {
		if (this.step == 0) {
			this.prices = prices;
			this.step++;
		}
		else {
			System.out.println("Meaningless command");
		}
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		if ((step == 1) && (this.platform.getRideFactory().getRideTypes().contains(choice))){
			this.choice = choice;
			Driver driver = this.platform.search(this.customer, choice);
			if (driver == null) {
				this.customer.setRequest(null);
			}
			else {
				driver.addRequest(this.customer);
			}
			this.step++;
		}
		else {
			System.out.println("Meaningless command");
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public MyUber getPlatform() {
		return platform;
	}
	
	
	
	
}
