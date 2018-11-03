package myUber;

public class RideFactory {
	
	public Ride createRide(String Ridetype,String state,Customer customer,int ID, Driver driver) {
		
		if(Ridetype.equalsIgnoreCase("UberX")) {
			return (new UberX(customer,ID,driver));
		}
		if(Ridetype.equalsIgnoreCase("UberBlack")) {
			return (new UberVan(customer,ID,driver));
		}
		if(Ridetype.equalsIgnoreCase("UberVan")) {
			return (new UberVan(customer,ID,driver));
		}
		if(Ridetype.equalsIgnoreCase("UberPool")) {
			return (new UberPool(customer,ID,driver));
		}
		return(null);
	}

}
