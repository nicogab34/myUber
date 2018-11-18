package myUber;

import java.util.Comparator;

public class CustCashComparator   implements Comparator<Customer>{

	/**
	 * @param customer1
	 * @param customer2
	 * Define how to compare two customers based on the total amount they were charged
	 */
	@Override
	public int compare(Customer o1, Customer o2) {
		// TODO Auto-generated method stub
		if(o1.getTotalCharge()< o2.getTotalCharge()) {return(-1);}
		if(o1.getTotalCharge()> o2.getTotalCharge()) {return(1);}
		return(0);
	}
}
