package myUber;

import java.util.ArrayList;
import java.util.Arrays;

public class MyUber {

	public static void main(String[] args) {
		Driver d1 = new Driver("Vincent", "Bouget", "on-duty");
		Driver d2 = new Driver("Daniel", "(Taxi)", "on-duty");
		
		
		Van v1 = new Van(new ArrayList<Driver>(Arrays.asList(d1,d2)));
		Standard s1 = new Standard(new ArrayList<Driver>(Arrays.asList(d2)));
		Berline b1 = new Berline(new ArrayList<Driver>(Arrays.asList(d1)));
		
		Customer c1 = new Customer("Baptiste", "Andrieu", new ArrayList<Float>(Arrays.asList(3.0,4.0)), 125765894);
	}

}
