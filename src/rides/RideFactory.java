package rides;

import java.util.ArrayList;
import java.util.Arrays;

import customer.Customer;
import driver.Driver;

public class RideFactory {
	private ArrayList<String> rideTypes = new ArrayList<String>(Arrays.asList("UberX","UberBlack","UberPool","UberVan"));
	
	public Ride createRide(String Ridetype,Customer customer, Driver driver) {
		
		if(Ridetype.equalsIgnoreCase("UberX")) {
			return (new UberX(customer,driver));
		}
		if(Ridetype.equalsIgnoreCase("UberBlack")) {
			return (new UberVan(customer,driver));
		}
		if(Ridetype.equalsIgnoreCase("UberVan")) {
			return (new UberVan(customer,driver));
		}
		return(null);
	}
	
	public Ride createRide(String Ridetype,ArrayList<Customer> customers, Driver driver) {
		if(Ridetype.equalsIgnoreCase("UberPool")) {
			return (new UberPool(customers,driver));
		}
		return(null);		
	}

	public ArrayList<String> getRideTypes() {
		return rideTypes;
	}
	

}
