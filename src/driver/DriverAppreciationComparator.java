package driver;

import java.util.Comparator;

public class DriverAppreciationComparator implements Comparator<Driver>{

	/**
	 * @param driver1
	 * @param driver2
	 * Define how to compare two drivers based on their appreciation rates
	 */
	@Override
	public int compare(Driver o1, Driver o2) {
		// TODO Auto-generated method stub
		if(o1.getAppreciationRate()< o2.getAppreciationRate()) {return(-1);}
		if(o1.getAppreciationRate()> o2.getAppreciationRate()) {return(1);}
		return(0);
	}


}
