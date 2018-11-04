package myUber;

import java.util.ArrayList;
import java.util.Arrays;

public class MyUber {
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private ArrayList<Driver> drivingDrivers = new ArrayList<Driver>();
	
	public MyUber() {
		super();
	}
	
	private void setDestination(Customer c,ArrayList<Double> destination){
		c.setDestination(destination);
		c.setRequest(new Request(c, this));
	}
	
	private double distance(Customer customer,Driver driver) {
		ArrayList<Double> positionCustomer=customer.getCoordinates();
		ArrayList<Double> positionDriver=driver.getPosition();
		double x1=positionCustomer.get(0);
		double x2=positionDriver.get(0);
		double y1=positionCustomer.get(1);
		double y2=positionDriver.get(1);
		return(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)));
			
	}
	
	private Driver search(Customer customer, String Ridetype){
		ArrayList<Driver> Cond = new ArrayList<Driver>();

		for (int i=0;i<drivingDrivers.size();i++){
			Car car=drivingDrivers.get(i).getCar();
			if (car.getRideType()==Ridetype){
				Cond.add(drivingDrivers.get(i));}
		}
		Driver res=(Cond.get(0));
		for (int j=1;j<Cond.size();j++){
			if(distance(customer,Cond.get(j))<distance(customer,res)) {
				res=Cond.get(j);}
			}
		return(res);
			
			
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
		
		Driver d1 = new Driver("Vincent", "Bouget", "on-duty",new ArrayList<Double>(Arrays.asList(1.,5.)));
		Driver d2 = new Driver("Daniel", "(Taxi)", "on-duty",new ArrayList<Double>(Arrays.asList(90.,50.)));
		Driver d3 = new Driver("Nicolas", "Gabrion", "on-duty",new ArrayList<Double>(Arrays.asList(10.,6.)));
		
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);
		
		CarFactory carFactory = new CarFactory();
		
		Car v1 = carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d1,d2)),"");
		Car s1 = carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d2)), "");
		Car b1 = carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d3)),"");
		
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
		Driver a=platform.search(c1,"UberVan");
		System.out.println(a);
		
		
	}

}
