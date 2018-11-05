package myUber;

public class RideFactory {
	
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
		if(Ridetype.equalsIgnoreCase("UberPool")) {
			return (new UberPool(customer,driver));
		}
		return(null);
	}

}
