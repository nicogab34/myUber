package rides;

import java.util.ArrayList;

import customer.Customer;
import driver.Driver;

public class UberPool extends Ride{
	
	public UberPool(ArrayList<Customer> customers,Driver driver) {
		super("unconfirmed", customers,driver);
		// TODO Auto-generated constructor stub
	}

}
