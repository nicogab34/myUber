package myUber;

import java.util.ArrayList;
import java.util.Arrays;

public class MyUber {
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private ArrayList<Driver> drivingDrivers = new ArrayList<Driver>();
	private ArrayList<Ride> rides = new ArrayList<Ride>();
	private RideFactory rideFactory;
	private CarFactory carFactory;
	
	public MyUber(RideFactory rideFactory, CarFactory carFactory) {
		super();
		this.rideFactory = rideFactory;
		this.carFactory = carFactory;
	}
	
	
	private void setDestination(Customer c,ArrayList<Double> destination){
		c.setRequest(new Request(c, this));
		System.out.println(c.getRequest().getCustomer());
		c.setDestination(destination);
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
	
	private double distance(ArrayList<Double> destination,ArrayList<Double> position) {
		double x1=destination.get(0);
		double x2=position.get(0);
		double y1=destination.get(1);
		double y2=position.get(1);
		return(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)));
			
	}
	
	public Driver search(Customer customer, String Ridetype){
		Driver res = null;
		for (int i=0;i<drivingDrivers.size();i++){
			Car car=drivingDrivers.get(i).getCar();
			if (((car.getRideType()==Ridetype) & (drivingDrivers.get(i).getState() == "on-duty") & ((res == null) || distance(customer,drivingDrivers.get(i))< distance(customer,res)))){
				res = drivingDrivers.get(i);
			}
		}
		return(res);
			
			
		}
	
	public double[] getPrice(Customer customer,int trafficCondition) {
		double[] cost= {};
		String[] RideTypes= {"UberBlack","UberX","UberPool","UberVan"};
		ArrayList<Double> destination= customer.getDestination();
		ArrayList<Double> position= customer.getCoordinates();
		double rideDistance = distance(destination,position);
		for (String ridetype: RideTypes) {
			cost.add(fare(ridetype,rideDistance,trafficCondition))
		}
		return (cost);
	}

	private void add(Driver driver) {
		this.drivers.add(driver);
	}
	
	private void takeCar(Driver driver, Car car) {
		driver.takeCar(car);
		this.drivingDrivers.add(driver);
	}
	
	private void chooseRideType(Customer c, String rideType) {
		c.chooseRideType(rideType);
	}
	
	private void decideRequest(Driver driver, boolean b, Customer customer) {
		if (!b) {
			customer.setRequest(null);
		}
		else if (customer.getRequest() != null){
			System.out.print(customer.getRequest());
			this.rides.add(this.rideFactory.createRide(customer.getRequest().getChoice(),customer, driver));
			driver.setState("on-a-ride");
		}
	}

	public static void main(String[] args) {
		
		RideFactory rideFactory = new RideFactory();
		
		CarFactory carFactory = new CarFactory();
		
		MyUber platform = new MyUber(rideFactory, carFactory);
		
		Driver d1 = new Driver("Vincent", "Bouget", "off-duty",new ArrayList<Double>(Arrays.asList(1.,5.)));
		Driver d2 = new Driver("Daniel", "(Taxi)", "on-duty",new ArrayList<Double>(Arrays.asList(90.,50.)));
		Driver d3 = new Driver("Nicolas", "Gabrion", "on-duty",new ArrayList<Double>(Arrays.asList(10.,6.)));
		
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);
		
		Car v1 = platform.carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d1,d2)),"");
		Car s1 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d2)), "");
		Car b1 = platform.carFactory.createCar("Berline", new ArrayList<Driver>(Arrays.asList(d3)),"");
		
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
		
		platform.chooseRideType(c1, "UberVan");
		
		platform.decideRequest(d1,true,c1);
		
		for (Ride r :platform.rides) {
			System.out.println(r);
		}
	}

}
