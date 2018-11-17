
package myUber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

import Fare.*;

public class MyUber {
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private ArrayList<Driver> drivingDrivers = new ArrayList<Driver>();
	private ArrayList<Customer> Customers= new ArrayList<Customer>();
	private ArrayList<Ride> rides = new ArrayList<Ride>();
	private RideFactory rideFactory;
	private CarFactory carFactory;
	private ArrayList<Customer> poolRequests = new ArrayList<Customer>();
	
	/**
	 * @param rideFactory
	 * @param carFactory
	 * Creates the Uber platform
	 */
	public MyUber(RideFactory rideFactory, CarFactory carFactory) {
		super();
		this.rideFactory = rideFactory;
		this.carFactory = carFactory;
	}
	
	/**
	 * @param c
	 * @param destination
	 * Sets customer destination
	 * Creates a new request
	 * Sets prices
	 * Add the request to the customer
	 */
	private void setDestination(Customer c,ArrayList<Double> destination){
		c.setDestination(destination);
		Request r =new Request(c, this);
		r.setPrices(getPrice(c, "medium"));
		c.setRequest(r);
	}
	
	
	private static double distance(Customer customer,Driver driver) {
		ArrayList<Double> positionCustomer=customer.getCoordinates();
		ArrayList<Double> positionDriver=driver.getPosition();
		double x1=positionCustomer.get(0);
		double x2=positionDriver.get(0);
		double y1=positionCustomer.get(1);
		double y2=positionDriver.get(1);
		return(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)));
			
	}
	
	public static double distance(ArrayList<Double> destination,ArrayList<Double> position) {
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
		this.decideRequest(res, true, customer);
		return res;
			
			
		}
	
	public static ArrayList<Double> getPrice(Customer customer,String trafficCondition) {
		ArrayList<Double> cost= new ArrayList<Double>();
		String[] RideTypes= {"UberX","UberBlack","UberPool","UberVan"};
		ArrayList<Double> destination= customer.getDestination();
		ArrayList<Double> position= customer.getCoordinates();
		double rideDistance = distance(destination,position);
		for (String ridetype: RideTypes) {
			FareCalculation fc = new FareCalculation();
			cost.add(fc.fare(ridetype,rideDistance,trafficCondition));
		}
		return (cost);
	}
	
	public static double getPrice(Customer customer, String trafficCondition,String RideType) {
		ArrayList<Double> cost=getPrice(customer,trafficCondition);
		if(RideType=="UberX") {return(cost.get(0));}
		if(RideType=="UberBlack") {return(cost.get(1));}
		if(RideType=="UberPool") {return(cost.get(2));}
		if(RideType=="UberVan") {return(cost.get(3));}
		return(0);
	}
	
	/**
	 * @param driver
	 * Adds a new driver to the platform
	 */
	private void add(Driver driver) {
		this.drivers.add(driver);
	}
	
	/**
	 * @param customer
	 * Adds a customer to the platform
	 */
	private void add(Customer customer) {
		this.Customers.add(customer);
	}
	
	/**
	 * @param driver
	 * @param car
	 * If the driver is one of the car owners : the driver starts driving the car
	 */
	private void takeCar(Driver driver, Car car) {
		if (car.getOwners().contains(driver)) {
			driver.takeCar(car);
			this.drivingDrivers.add(driver);
		}
	}
	
	/**
	 * @param c
	 * @param rideType
	 * Sets the customer ride type decision
	 */
	private void chooseRideType(Customer c, String rideType) {
		if (rideType == "UberPool") {
			this.poolRequests.add(c);
			if (this.poolRequests.size()==3) {
				this.startRide();
			}
		}
		else {
			c.chooseRideType(rideType);
		}
	}
	
	private void startRide() {
		
	}
	
	/**
	 * @param driver
	 * @param b
	 * @param customer
	 * Sets the driver decision for the request of the customer
	 */
	private void decideRequest(Driver driver, boolean b, Customer customer) {
		if (!b) {
			customer.setRequest(null);
		}
		else if (customer.getRequest() != null){
			Ride r = this.rideFactory.createRide(customer.getRequest().getChoice(),customer, driver);
			this.rides.add(r);
			driver.setState("on-a-ride");
			this.completeRide(r);
		}
	}
	
	
	private void completeRide(Ride r) {
		String trafficstate=trafficState();
		Driver d = r.getDriver();
		Customer c = r.getCustomer();
		double cost=0;
		double speed=getSpeed(trafficstate);
		double distance=distance(c.getDestination(),c.getCoordinates());
		r.setRideDuration(distance/speed);
		c.setTotalTime(c.getTotalTime()+r.getRideDuration());
		d.setState("on-duty");
		d.setPosition(c.getDestination());
		c.setCoordinates(c.getDestination());
		c.setDestination(new  ArrayList<Double>());
		c.setNumberOfRides(c.getNumberOfRides()+1);
		d.setNumberOfRides(d.getNumberOfRides()+1);
		if (r instanceof UberX){
			cost= getPrice(c,trafficstate,"UberX");
		}
		if (r instanceof UberBlack){
			cost= getPrice(c,trafficstate,"UberBlack");			
		}
		if (r instanceof UberPool){
			cost= getPrice(c,trafficstate,"UberPool");
		}
		if (r instanceof UberVan){
			cost= getPrice(c,trafficstate,"UberVan");
		}
		c.setTotalCharge(c.getTotalCharge()+cost);
		d.setMoneyCashed(d.getMoneyCashed()+cost);
	}
	
	public static String trafficState() {
		java.util.GregorianCalendar calendar = new GregorianCalendar();
		int heure = calendar.get(java.util.Calendar.HOUR_OF_DAY);
		double a=Math.random();
		if ((heure<7)&(heure>=22)) {
			if (a<0.95) {return"low";}
			if ((a>=0.95)&(a<0.99)) {return("medium");}
			if (a>0.99) {return"heavy";}
			
		}
		if ((heure>=7)&(heure<11)) {
			if (a<0.05) {return"low";}
			if ((a>=0.05)&(a<0.25)) {return("medium");}
			if (a>0.25) {return"heavy";}
			
		}
		if ((heure>=11)&(heure<17)) {
			if (a<0.15) {return"low";}
			if ((a>=0.85)&(a<0.99)) {return("medium");}
			if (a>0.85) {return"heavy";}
			
		}
		if ((heure>=17)&(heure<22)) {
			if (a<0.01) {return"low";}
			if ((a>=0.05)&(a<0.95)) {return("medium");}
			if (a>0.95) {return"heavy";}
			
		}
		return(null);
	}
	
	public static double getSpeed(String trafficstate) {
		double speed=0;
		if (trafficstate=="low") {speed=15;}
		if(trafficstate=="medium") {speed=7.5;}
		if (trafficstate=="heavy") {speed=3;}
		return (speed);
	}

	/**
	 * @return the ride factory
	 */
	public RideFactory getRideFactory() {
		return rideFactory;
	}

	/**
	 * @param rideFactory
	 * Sets the platform ride factory
	 */
	public void setRideFactory(RideFactory rideFactory) {
		this.rideFactory = rideFactory;
	}
	
	public ArrayList<Double> CustomerBalance(Customer c){
		ArrayList<Double> balance=new ArrayList<Double>();
		balance.add(c.getNumberOfRides());
		balance.add(c.getTotalTime());
		balance.add(c.getTotalCharge());
		return(balance);
	}
	
	public ArrayList<Double> SystemBalance() {
		double totalride=0;
		double totalcost=0;
		ArrayList<Double> balance=new ArrayList<Double>();
		for (Driver d:drivingDrivers) {
			totalride=totalride+d.getNumberOfRides();
			totalcost=totalcost+d.getMoneyCashed();
		}
		balance.add(totalride);
		balance.add(totalcost);
		return(balance);
	}
	
	/*public ArrayList<Customer> FrequencySortingCustomer(){
		double m=CustomerBalance(c).get(0);
		for(Customer c:Customers) {
			
			
		}
	}*/


	public static void main(String[] args) {
		
		RideFactory rideFactory = new RideFactory();
		
		CarFactory carFactory = new CarFactory();
		
		MyUber platform = new MyUber(rideFactory, carFactory);
		
		Driver d1 = new Driver("Vincent", "Bouget", "on-duty",new ArrayList<Double>(Arrays.asList(1.,5.)));
		Driver d2 = new Driver("Daniel", "(Taxi)", "on-duty",new ArrayList<Double>(Arrays.asList(90.,50.)));
		Driver d3 = new Driver("Nicolas", "Gabrion", "on-duty",new ArrayList<Double>(Arrays.asList(10.,6.)));
		Driver d4 = new Driver("Jean-Alexandre", "Dumond", "on-duty",new ArrayList<Double>(Arrays.asList(0.,6.7)));
		Driver d5 = new Driver("Neil", "Armstrong", "on-duty",new ArrayList<Double>(Arrays.asList(100.,60.)));
		Driver d6 = new Driver("Chofeur", "Cithechampillon", "on-duty",new ArrayList<Double>(Arrays.asList(4.0,6.2)));
		Driver d7 = new Driver("Sep", "Arti", "on-duty",new ArrayList<Double>(Arrays.asList(-5.,1.)));
		
		Car v1 = platform.carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d1,d2)),"");
		Car v2 = platform.carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d1,d2)),"");
		Car v3 = platform.carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d1,d2, d3, d4)),"");
		Car s1 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d3)), "");
		Car s2 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d4,d5)), "");
		Car s3 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d5,d6,d7)), "");
		Car s4 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d6)), "");
		Car b1 = platform.carFactory.createCar("Berline", new ArrayList<Driver>(Arrays.asList(d7)),"");
		Car b2 = platform.carFactory.createCar("Berline", new ArrayList<Driver>(Arrays.asList(d3)),"");
		Car b3 = platform.carFactory.createCar("Berline", new ArrayList<Driver>(Arrays.asList(d1,d7)),"");
		Car b4 = platform.carFactory.createCar("Berline", new ArrayList<Driver>(Arrays.asList(d4)),"");
		
		Customer c1 = new Customer("Baptiste", "Andrieu", new ArrayList<Double>(Arrays.asList(1.2, 4.3)), 125765894);
		Customer c2 = new Customer("No�", "Mikati", new ArrayList<Double>(Arrays.asList(0.0, 6.3)), 125775894);
		Customer c3 = new Customer("Christophe", "Gallon", new ArrayList<Double>(Arrays.asList(0.0, 0.0)), 125775824);
		Customer c4 = new Customer("Her", "Hoi", new ArrayList<Double>(Arrays.asList(60.0, 20.0)), 125775823);
		Customer c5 = new Customer("Barack", "Obama", new ArrayList<Double>(Arrays.asList(2.0, 6.7)), 895775824);
		Customer c6 = new Customer("Lady", "Diana", new ArrayList<Double>(Arrays.asList(7.0, 3.1)), 125885824);
		Customer c7 = new Customer("Catherine", "Guillouard", new ArrayList<Double>(Arrays.asList(0.0, 0.0)), 125715824);
		
		platform.add(d1);
		platform.add(d2);
		platform.add(d3);
		platform.add(d4);
		platform.add(d5);
		platform.add(d6);
		platform.add(d7);
		
		platform.add(c1);
		platform.add(c2);
		platform.add(c3);
		platform.add(c4);
		platform.add(c5);
		platform.add(c6);
		platform.add(c7);

		platform.takeCar(d1, v1);
		platform.takeCar(d2, v2);
		platform.takeCar(d3, v3);
		platform.takeCar(d4, s2);
		platform.takeCar(d5, s3);
		platform.takeCar(d6, s4);
		platform.takeCar(d7, b3);
		
		//Destinations
		ArrayList<Double> dest1 = new ArrayList<Double>(Arrays.asList(3.5, 2.7));
		ArrayList<Double> dest2 = new ArrayList<Double>(Arrays.asList(1.0, 0.0));
		ArrayList<Double> dest3 = new ArrayList<Double>(Arrays.asList(2.0, 1.0));
		ArrayList<Double> dest4 = new ArrayList<Double>(Arrays.asList(0.0, 0.0));
		ArrayList<Double> dest5 = new ArrayList<Double>(Arrays.asList(5.0, 1.0));
		ArrayList<Double> dest6 = new ArrayList<Double>(Arrays.asList(2.0, 1.7));
		ArrayList<Double> dest7 = new ArrayList<Double>(Arrays.asList(3.0, 32.0));
		
		//Core
		
		platform.setDestination(c1,dest1);
		platform.chooseRideType(c1, "UberVan");
		platform.setDestination(c2,dest2);
		platform.chooseRideType(c1, "UberVan");
		platform.chooseRideType(c2, "UberBlack");
		platform.setDestination(c3,dest3);
		platform.chooseRideType(c3, "UberVan");
		

		for (Ride r :platform.rides) {
			System.out.println(r);
		}
		

		
	}

}
