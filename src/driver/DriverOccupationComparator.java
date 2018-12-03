package driver;

import java.util.Comparator;

public class DriverOccupationComparator   implements Comparator<Driver>{

	
	/**
	 * @param driver1
	 * @param driver2
	 * Define how to compare two drivers based on their duty rates
	 */
	@Override
	public int compare(Driver o1, Driver o2) {
		// TODO Auto-generated method stub
		if(o1.onDutyRate()< o2.onDutyRate()) {return(-1);}
		if(o1.onDutyRate()> o2.onDutyRate()) {return(1);}
		return(0);
	}

}
