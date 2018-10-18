package myUber;

import java.util.ArrayList;
import java.util.Arrays;

public class MyUber {

	public static void main(String[] args) {
		Driver d1 = new Driver("Vincent", "Bouget", "on-duty");
		Driver d2 = new Driver("Daniel", "(Taxi)", "on-duty");
		Driver d3 = new Driver("Nicolas", "Gabrion", "on-duty");
		
		Van v1 = new Van(new ArrayList<Driver>(Arrays.asList(d1,d2)));
		Standard s1 = new Standard(new ArrayList<Driver>(Arrays.asList(d2)));
		Berline b1 = new Berline(new ArrayList<Driver>(Arrays.asList(d3)));
		
		Customer c1 = new Customer("Baptiste", "Andrieu", new ArrayList<Double>(Arrays.asList(1.2, 4.3)), 125765894);
		
		d1.takeCar(v1);
		d2.takeCar(s1);
		d3.takeCar(b1);
	}

}
