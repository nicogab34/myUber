package myUber;

import java.util.ArrayList;
import java.util.Arrays;

public class MyUber {
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private ArrayList<Driver> drivingDrivers = new ArrayList<Driver>();
	
	public MyUber() {
		super();
	}
	
	private ArrayList<Driver> setDestination(Customer c,ArrayList<Double> destination){
		c.setDestination(destination);
		return this.search(c.getCoordinates(), c.getDestination());
	}
	
	
	private ArrayList<Driver> search(ArrayList<Double> position, ArrayList<Double> destination){
		ArrayList<Driver> res = new ArrayList<Driver>();
		
		return res;
	}
	private void add(Driver driver) {
		this.drivers.add(driver);
	}
	
	private void takeCar(Driver driver, Car car) {
		driver.takeCar(car);
		this.drivingDrivers.add(driver);
	}

	public static void main(String[] args) {
		MyUber platform = new MyUber();
		
		Driver d1 = new Driver("Vincent", "Bouget", "on-duty");
		Driver d2 = new Driver("Daniel", "(Taxi)", "on-duty");
		Driver d3 = new Driver("Nicolas", "Gabrion", "on-duty");
		
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);
		
		CarFactory carFactory = new CarFactory();
		
		Car v1 = carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d1,d2)),"");
		Car s1 = carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d2)), "UberX");
		Car b1 = carFactory.createCar("Berline", new ArrayList<Driver>(Arrays.asList(d3)),"");
		
		Customer c1 = new Customer("Baptiste", "Andrieu", new ArrayList<Double>(Arrays.asList(1.2, 4.3)), 125765894);
		
		System.out.println(c1);
		
		platform.add(d1);
		platform.add(d2);
		platform.add(d3);
		
		platform.takeCar(d1, v1);
		platform.takeCar(d2, s1);
		platform.takeCar(d3, b1);
		
		System.out.println(d1);
		
		ArrayList<Double> dest1 = new ArrayList<Double>(Arrays.asList(3.5, 2.7));
		
		platform.setDestination(c1,dest1);
		
		System.out.println(c1);
		
		/* platform.search(c1.coordinates, c1.destination) */
		
		
	}

}
