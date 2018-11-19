package customer;

import java.util.Comparator;

public class CustRideComparator  implements Comparator<Customer>{

	/**
	 * @param customer1
	 * @param customer2
	 * Define how to compare two customers based on the number of rides they made
	 */
	@Override
	public int compare(Customer o1, Customer o2) {
		// TODO Auto-generated method stub
		if(o1.getNumberOfRides()< o2.getNumberOfRides()) {return(-1);}
		if(o1.getNumberOfRides()> o2.getNumberOfRides()) {return(1);}
		return(0);
	}

}
