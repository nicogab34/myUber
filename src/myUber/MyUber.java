
package myUber;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import Fare.*;
import cars.Car;
import cars.CarFactory;
import customer.CustCashComparator;
import customer.CustRideComparator;
import customer.Customer;
import driver.Driver;
import driver.DriverAppreciationComparator;
import driver.DriverOccupationComparator;
import jdk.nashorn.internal.runtime.options.Options;
import rides.Ride;
import rides.RideFactory;
import rides.UberBlack;
import rides.UberPool;
import rides.UberVan;
import rides.UberX;

public class MyUber{
	private ArrayList<Driver> drivers = new ArrayList<Driver>();
	private ArrayList<Driver> drivingDrivers = new ArrayList<Driver>();
	private ArrayList<Car> cars = new ArrayList<Car>();
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
	
	private Car createCar(String carType, ArrayList<Driver> drivers, String rideType) {
		Car c = this.carFactory.createCar(carType, drivers, rideType);
		cars.add(c);
		return c;
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
		ArrayList<Double> prices = getPrice(c, "medium");
		r.setPrices(prices);
		System.out.println("\n" + c.getName()+ "\nHere are the prices for the different types of ride, choose yours !");
		for (int i=0;i<this.getRideFactory().getRideTypes().size();i++) {
			System.out.println(this.getRideFactory().getRideTypes().get(i) + " : " + prices.get(i));
		}
		c.setRequest(r);
	}
	
	/**
	 *
	 * @param customer
	 * @param driver
	 * @return double
	 * Calculate the distance betwenn a customer and a driver
	 */
	private static double distance(Customer customer,Driver driver) {
		ArrayList<Double> positionCustomer=customer.getCoordinates();
		ArrayList<Double> positionDriver=driver.getPosition();
		double x1=positionCustomer.get(0);
		double x2=positionDriver.get(0);
		double y1=positionCustomer.get(1);
		double y2=positionDriver.get(1);
		return(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)));
			
	}
	
	/**
	 * 
	 * @param destination
	 * @param position
	 * @return double
	 * Calculate the distance between two points of the map
	 */
	public static double distance(ArrayList<Double> destination,ArrayList<Double> position) {
		double x1=destination.get(0);
		double x2=position.get(0);
		double y1=destination.get(1);
		double y2=position.get(1);
		return(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)));
			
	}
	
	/**
	 * 
	 * @param customer
	 * @param Ridetype
	 * @return void
	 * Find the nearest driver from a given customer
	 */
	public Driver search(Customer customer, String Ridetype){
		Driver res = null;
		boolean found = false;
		for (int i=0;i<drivingDrivers.size();i++){
			Car car=drivingDrivers.get(i).getCar();
			if (((car.getRideType()==Ridetype) & (drivingDrivers.get(i).getState() == "on-duty") & ((res == null) || distance(customer,drivingDrivers.get(i))< distance(customer,res)))){
				res = drivingDrivers.get(i);
				found = true;
			}
		}
		if (found){
			this.decideRequest(res, true, customer);
		}
		else {
			System.out.println("We can't find any driver matching with your request.");
		}
		
		return res;
			
			
		}
	
	/**
	 * 
	 * @param customer
	 * @param trafficCondition
	 * @return
	 * Calculate the prices of the different rides possible(X,Black,Pool,Van) with given traffic state
	 */
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
	
	/**
	 * 
	 * @param customer
	 * @param trafficCondition
	 * @param RideType
	 * @return double
	 * Calculate the price of a ride with given ridetype and given traffic state
	 */
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
	public void add(Driver driver) {
		this.drivers.add(driver);
	}
	
	/**
	 * @param customer
	 * Adds a customer to the platform
	 */
	public void add(Customer customer) {
		this.Customers.add(customer);
	}
	
	/**
	 * @param driver
	 * @param car
	 * If the driver is one of the car owners : the driver starts driving the car
	 */
	private void takeCar(Driver driver, Car car) {
		if (car.getOwners().contains(driver)) {
			this.drivingDrivers.add(driver);
			for (Driver d : car.getOwners()) {
				if (d.getCar() == car) {
					d.resetCar();
				}
			}
			driver.takeCar(car);
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
	
	/**
	 * This method starts the uberPool ride
	 */
	private void startRide() {
		System.out.println("UberPool ride started");
		for (Driver d : this.uberPoolDrivers()) {
			
			Ride r = this.rideFactory.createRide("UberPool",this.poolRequests, d);
			this.rides.add(r);
			d.setState("on-a-ride");
			completeRide(r);
			this.poolRequests = new ArrayList<Customer>();
			return;
		}
	}
	
	/**
	 * @return the list of "on-duty" uberPool drivers ordered by distance to be traveled to realize the current uberPool request
	 */
	private ArrayList<Driver> uberPoolDrivers() {
		ArrayList<Driver> uberPoolDrivers= new ArrayList<Driver>();
		ArrayList<Double> costs= new ArrayList<Double>();
		for (int i=0;i<this.drivingDrivers.size();i++) {
			if (this.drivingDrivers.get(i).getState() == "on-duty" && this.drivingDrivers.get(i).getCar().getRideType() == "UberPool") {
				if (uberPoolDrivers.size() == 0){
					uberPoolDrivers.add(this.drivingDrivers.get(i));
					costs.add(uberPoolCost(this.drivingDrivers.get(i)));
				}
				else {
					if (uberPoolCost(this.drivingDrivers.get(i)) > costs.get(costs.size()-1)) {
						uberPoolDrivers.add(this.drivingDrivers.get(i));
						costs.add(uberPoolCost(this.drivingDrivers.get(i)));
					}
					else {
						int size = uberPoolDrivers.size();
						for (int j=0;j<size;j++) {
							if (uberPoolCost(this.drivingDrivers.get(i)) <= costs.get(j)) {
								uberPoolDrivers.add(j, this.drivingDrivers.get(i));
								costs.add(j, uberPoolCost(this.drivingDrivers.get(i)));
							}
						}
					}
				}
			}
		}
		return uberPoolDrivers;
	}
	
	
	/**
	 * @param driver
	 * @return
	 * This method tests every way to serve every customer of the pool request and returns the lowest distance to be travelled if the driver in argument was taking the ride
	 */
	private double uberPoolCost(Driver driver) {
		double cost = -1;
		ArrayList<ArrayList<Double>> globalOrderPos = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> globalOrderDest = new ArrayList<ArrayList<Double>>();
		int N = (int) Math.pow(factorial(this.poolRequests.size()),2);
		for (int i=0;i<N;i++) {
			int n = i;
			ArrayList<ArrayList<Double>> pos = new ArrayList<ArrayList<Double>>();
			ArrayList<ArrayList<Double>> dest = new ArrayList<ArrayList<Double>>();
			for (int j=0; j<this.poolRequests.size();j++) {
				pos.add(this.poolRequests.get(j).getCoordinates());
				dest.add(this.poolRequests.get(j).getDestination());
			}
			ArrayList<ArrayList<Double>> orderDest = new ArrayList<ArrayList<Double>>();
			ArrayList<ArrayList<Double>> orderPos = new ArrayList<ArrayList<Double>>();
			int q = N;
			for (int j=0;j<this.poolRequests.size();j++) {
				q = q/(this.poolRequests.size()-j);
				orderPos.add(pos.remove(n/q));
				n = n%q;
			}
			for (int j=0;j<this.poolRequests.size();j++) {
				q = q/(this.poolRequests.size()-j);
				orderDest.add(dest.remove(n/q));
				n = n%q;
			}
			double auxCost = this.distance(driver.getPosition(), orderPos.get(0)) + this.distance(orderPos.get(this.poolRequests.size()-1), orderDest.get(0));
			for (int j=0; j<this.poolRequests.size()-1;j++) {
				auxCost += (this.distance(orderPos.get(j), orderPos.get(j+1))+this.distance(orderDest.get(j), orderDest.get(j+1)));
			}
			if (cost == -1 || auxCost < cost) {
				cost = auxCost;
				globalOrderPos = orderPos;
				globalOrderDest = orderDest;
			}
		}
		return cost;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 * factorial of n
	 */
	public int factorial(int n) {
		if (n<2) {
			return 1;
		}
		else {
			return n*factorial(n-1);
		}
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
	
	/**
	 * 
	 * @param r
	 * Accomplish the ride r by displacing driver ad costumers at their destination and update the numbers of rides and amount of cash for drivers and costumers
	 *the costumers has the possibility to give a note to the driver
	 */
	private void completeRide(Ride r) {
		String trafficstate=trafficState();
		double cost=0;
		double speed=getSpeed(trafficstate);
		Scanner scan= new Scanner(System.in);
		if (r instanceof UberX){
			Driver d = r.getDriver();
			Customer c = r.getCustomer();
			cost= getPrice(c,trafficstate,"UberX");
			double distance=distance(c.getDestination(),c.getCoordinates());
			r.setRideDuration(distance/speed);
			c.setTotalTime(c.getTotalTime()+r.getRideDuration());
			d.setState("on-duty");
			d.setPosition(c.getDestination());
			c.setCoordinates(c.getDestination());
			c.setDestination(new  ArrayList<Double>());
			c.setNumberOfRides(c.getNumberOfRides()+1);
			d.setNumberOfRides(d.getNumberOfRides()+1);
			c.setTotalCharge(c.getTotalCharge()+cost);
			d.setMoneyCashed(d.getMoneyCashed()+cost);
			System.out.println("Veuillez notez le chauffeur entre 0 et 5");	
			try {
				int note=scan.nextInt();
				r.getDriver().setAppreciationRate(note);
				System.out.println(c.getName()+" a donné la note "+note);
							}
			catch(InputMismatchException e) {
				
			}
			catch(NoSuchElementException e) {
				
			}
		}
		if (r instanceof UberBlack){
			Driver d = r.getDriver();
			Customer c = r.getCustomer();
			cost= getPrice(c,trafficstate,"UberBlack");		
			double distance=distance(c.getDestination(),c.getCoordinates());
			r.setRideDuration(distance/speed);
			c.setTotalTime(c.getTotalTime()+r.getRideDuration());
			d.setState("on-duty");
			d.setPosition(c.getDestination());
			c.setCoordinates(c.getDestination());
			c.setDestination(new  ArrayList<Double>());
			c.setNumberOfRides(c.getNumberOfRides()+1);
			d.setNumberOfRides(d.getNumberOfRides()+1);
			c.setTotalCharge(c.getTotalCharge()+cost);
			d.setMoneyCashed(d.getMoneyCashed()+cost);
			System.out.println("Veuillez notez le chauffeur entre 0 et 5");	
			try {
				int note=scan.nextInt();
				r.getDriver().setAppreciationRate(note);
				System.out.println(c.getName()+" a donné la note "+note);
						}
			catch(InputMismatchException e) {
				
			}
			catch(NoSuchElementException e) {
				
			}
		}
		if (r instanceof UberPool){
			Driver driver = r.getDriver();
			ArrayList<Customer> customers= r.getCustomers();
			for(Customer c:customers) {
				double dist=distance(c.getDestination(),c.getCoordinates());
				cost=getPrice(c,trafficstate,"UberPool");
				r.setRideDuration(dist/speed);
				c.setTotalTime(c.getTotalTime()+r.getRideDuration());
				c.setCoordinates(c.getDestination());
				driver.setPosition(c.getDestination());
				c.setDestination(new  ArrayList<Double>());
				c.setNumberOfRides(c.getNumberOfRides()+1);
				c.setTotalCharge(c.getTotalCharge()+cost);
				driver.setMoneyCashed(driver.getMoneyCashed()+cost);
				System.out.println("Veuillez notez le chauffeur entre 0 et 5");	
				try {
					int note=scan.nextInt();
					r.getDriver().setAppreciationRate(note);
					System.out.println(c.getName()+" a donné la note "+note);
									}
				catch(InputMismatchException e) {
					
				}
				catch(NoSuchElementException e) {
					
				}
				}
			driver.setState("on-duty");
			driver.setNumberOfRides(driver.getNumberOfRides()+1);
		}
		if (r instanceof UberVan){
			Driver d = r.getDriver();
			Customer c = r.getCustomer();
			cost= getPrice(c,trafficstate,"UberVan");
			double distance=distance(c.getDestination(),c.getCoordinates());
			r.setRideDuration(distance/speed);
			c.setTotalTime(c.getTotalTime()+r.getRideDuration());
			d.setState("on-duty");
			d.setPosition(c.getDestination());
			c.setCoordinates(c.getDestination());
			c.setDestination(new  ArrayList<Double>());
			c.setNumberOfRides(c.getNumberOfRides()+1);
			d.setNumberOfRides(d.getNumberOfRides()+1);
			c.setTotalCharge(c.getTotalCharge()+cost);
			d.setMoneyCashed(d.getMoneyCashed()+cost);
			System.out.println("Veuillez notez le chauffeur entre 0 et 5");	
			try {
				int note=scan.nextInt();
				r.getDriver().setAppreciationRate(note);
				System.out.println(c.getName()+" a donné la note "+note);
							}
			catch(InputMismatchException e) {
				
			}
			catch(NoSuchElementException e) {
				
			}
		}

	}
	

	
	/**
	 * 
	 * @return
	 * Give the traffic state at the current time
	 */
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
	
	public static String trafficState(int heure) {
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
	
	/**
	 * 
	 * @param trafficstate
	 * @return
	 * For a given traffic state, calculate how fast a car is moving
	 */
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
	
	public CarFactory getCarFactory() {
		return carFactory;
	}

	public void setCarFactory(CarFactory carFactory) {
		this.carFactory = carFactory;
	}

	/**
	 * 
	 * @param c
	 * @return
	 * Return a list of statistics concerning costumers: the number of rides they took, total time they spent in a uber and the total charge they paid
	 */
	public ArrayList<Double> CustomerBalance(Customer c){
		ArrayList<Double> balance=new ArrayList<Double>();
		balance.add(c.getNumberOfRides());
		balance.add(c.getTotalTime());
		balance.add(c.getTotalCharge());
		return(balance);
	}
	
	/**
	 * 
	 * @return
	 * Return a list of statistics concerning drivers: the number of rides they made and the total cash they charged
	 */
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
	
	/**
	 * 
	 * @return
	 * Sort the costumers by the frequency they use the application
	 */
	public ArrayList<Customer> FrequencySortingCustomer(){
		ArrayList<Customer> ListeTrie = (ArrayList<Customer>) Customers.clone();
		CustRideComparator c1=new CustRideComparator();
		Collections.sort(ListeTrie, c1);
		return(ListeTrie);
	}
	
	/**
	 * 
	 * @return
	 * Sort the costumers by the amount of cash they spent in rides 
	 */
	public ArrayList<Customer> ChargeSortingCustomer(){
		ArrayList<Customer> ListeTrie = (ArrayList<Customer>) Customers.clone();
		CustCashComparator c1=new CustCashComparator();
		Collections.sort(ListeTrie, c1);
		return(ListeTrie);
	}
	
	/**
	 * 
	 * @return
	 * Sort the drivers by their occupation rate
	 */
	public ArrayList<Driver> OccupationSortingDriver(){
		ArrayList<Driver> ListeTrie = (ArrayList<Driver>) drivingDrivers.clone();
		DriverOccupationComparator c1=new DriverOccupationComparator();
		Collections.sort(ListeTrie, c1);
		return(ListeTrie);
	}
	
	/**
	 * 
	 * @return
	 * Sort the drivers by how much clients appreciated them
	 */
	public ArrayList<Driver> AppreciationSortingDriver(){
		ArrayList<Driver> ListeTrie = (ArrayList<Driver>) drivingDrivers.clone();
		DriverAppreciationComparator c1=new DriverAppreciationComparator();
		Collections.sort(ListeTrie, c1);
		return(ListeTrie);
		}	
	
	public void setup(String a, String b, String c, String d) {
		int nStandard=Integer.parseInt(a);
		int nBerline=Integer.parseInt(b);
		int nVan=Integer.parseInt(c);
		int nCustomer=Integer.parseInt(d);
		
		for (int i=0;i<nStandard;i++) {
			Driver driver = new Driver("Drivername"+this.drivers.size(), "Driversurname"+this.drivers.size(), "off-duty",new ArrayList<Double>(Arrays.asList(0.,0.)));
			this.add(driver);
			Car car = this.createCar("Standard", new ArrayList<Driver>(Arrays.asList(driver)), "UberX");
			this.takeCar(driver, car);

		}
		for (int i=0;i<nBerline;i++) {
			Driver driver = new Driver("Drivername"+this.drivers.size(), "Driversurname"+this.drivers.size(), "off-duty",new ArrayList<Double>(Arrays.asList(0.,0.)));
			this.add(driver);
			Car car = this.createCar("Berline", new ArrayList<Driver>(Arrays.asList(driver)), "");
			this.takeCar(driver, car);
			
		}
		for (int i=0;i<nVan;i++) {
			Driver driver = new Driver("Drivername"+this.drivers.size(), "Driversurname"+this.drivers.size(), "off-duty",new ArrayList<Double>(Arrays.asList(0.,0.)));
			this.add(driver);
			Car car = this.createCar("Van", new ArrayList<Driver>(Arrays.asList(driver)), "");
			this.takeCar(driver, car);
			
		}
		for (int i=0;i<nCustomer;i++) {
			Customer customer = new Customer("CustomerName"+this.Customers.size(), "CustomerSurname"+this.Customers.size(), new ArrayList<Double>(Arrays.asList(1.2, 4.3)), 000000000);
			this.add(customer);						
		}
		System.out.println(this.Customers);
		System.out.println(this.drivingDrivers);
		
	}
	
	public void addCustomer(String name, String surname) {
			Random r = new Random();
			double x = 100*r.nextDouble()-50;
			double y = 100*r.nextDouble()-50;
			ArrayList<Double> position = new ArrayList<Double>(Arrays.asList(x, y));
			Customer customer = new Customer(name,surname,position,00);
			this.add(customer);
			System.out.println(this.Customers);	
	}
	
	public void addCarDriver(String name, String surname, String carType) {
		Driver driver = new Driver(name,surname,"off-duty",new ArrayList<Double>(Arrays.asList(0.,0.)));
		String rideType = "";
		Random r = new Random();
		if (carType.equals("Standard")) {
			if (r.nextBoolean()){
				rideType = "UberX";
			}
			else {
				rideType = "UberPool";
			}
		}
		System.out.println(driver);
		ArrayList<Driver> drivers = new ArrayList<Driver>(Arrays.asList(driver));
		Car car = this.createCar(carType,drivers,rideType);
		this.add(driver);
		System.out.println(car);
		this.takeCar(driver,car);
		System.out.println(this.drivers);
		System.out.println(this.cars);	
	}
	
	public void addDriver(String name, String surname, String carID) {
		Driver driver = new Driver(name,surname,"off-duty",new ArrayList<Double>(Arrays.asList(0.,0.)));
		this.add(driver);
		boolean found=false;
		for (Car c : this.cars) {
			if (c.getID().equals(carID)){
				c.getOwners().add(driver);
				this.takeCar(driver, c);
				found = true;
			}
		}
		System.out.println(this.cars);
		System.out.println(this.drivers);
		if (!found){
			System.out.println("We can't find a car with this ID");
		}
	}
	
	public void setDriverStatus(String name, String surname, String status) {
		boolean found=false;
		for (Driver d : this.drivers) {
			if ((d.getName().equals(name))&&(d.getSurname().equals(surname))) {
				found=true;
				d.setState(status);
			}
		}
		System.out.println(this.drivers);
		if (!found){
			System.out.println("We can't find a driver with this name and surname");
		}
	}
	
	public void moveCar(String carID, String x, String y) {
		boolean found=false;
		for (Car c : this.cars) {
			if (c.getID().equals(carID)){
				found = true;
				for (Driver d : c.getOwners()) {
					if (d.getCar() == c){
						ArrayList<Double> pos = new ArrayList<Double>();
						pos.add(Double.parseDouble(x));
						pos.add(Double.parseDouble(y));
						d.setPosition(pos);
					}
				}
			}
		}
		System.out.println(this.cars);
		System.out.println(this.drivers);
		if (!found){
			System.out.println("We can't find a car with this ID");
		}
	}
	
	public void moveCustomer(String sid, String x, String y) {
		boolean found=false;
		for (Customer c : this.Customers) {
			int id=(int) Integer.parseInt(sid);
			if (c.getID()==id) {
				found = true;															
				ArrayList<Double> pos = new ArrayList<Double>();
				pos.add(Double.parseDouble(x));
				pos.add(Double.parseDouble(y));
				c.setDestination(pos);
				}	
			}
		System.out.println(this.cars);
		System.out.println(this.drivers);
		if (!found){
			System.out.println("We can't find a customer with this ID");
		}
	}
	
	public void displayState() {
		System.out.println(this.cars);
		System.out.println(this.drivers);
		System.out.println(this.Customers);
	}
	
	public void ask4price(String cId,String x,String y,String time) {
		int t=Integer.parseInt(time);
		int ID=Integer.parseInt(cId);
		double X=Double.parseDouble(x);
		double Y=Double.parseDouble(y);
		ArrayList<Double> destination=new ArrayList<Double>();
		destination.add(X);
		destination.add(Y);	
		Customer customer=null;
		for (Customer c: Customers) {
			if(c.getID()==ID) {customer=c;}
			else{System.out.println("customer not found");}					
		}
		String trafficstate=trafficState(t);
		customer.setDestination(destination);		
		System.out.println(getPrice(customer,trafficstate));
	}
	public void simRide(String c, String x, String y, String time, String rideType, String driverMark) {
		double X=Double.parseDouble(x);
		double Y=Double.parseDouble(y);
		Customer C = null;
		for (Customer e : this.Customers) {
			if (e.getID()==Integer.parseInt(c)) {
				C=e;
			}
		}
		if (C.equals(null)) {
			System.out.println("We can't find a customer with this ID");
		}
		else {
			this.setDestination(C,new ArrayList<Double>(Arrays.asList(X, Y)));
			this.chooseRideType(C, rideType);
		}
		
	}
	
	public void displayDrivers(String sortpolicy){
		if (sortpolicy.equalsIgnoreCase("mostoccupied")) {
			System.out.println(OccupationSortingDriver());
		}
		if (sortpolicy.equalsIgnoreCase("mostappreciated")) {
			System.out.println(AppreciationSortingDriver());			
		}
		else {System.out.println("unknown command");}
	}
	
	public void displayCustomers(String sortpolicy){
		if (sortpolicy.equalsIgnoreCase("mostfrequent")) {
			System.out.println(FrequencySortingCustomer());
		}
		if (sortpolicy.equalsIgnoreCase("mostcharged")) {
			System.out.println(ChargeSortingCustomer());
		}
		else {System.out.println("unknown command");}
	}
	
	public void totalCashed() {
		double total=0;
		for(Driver d:drivingDrivers) {
			total+=d.getMoneyCashed();			
		}
		System.out.println(total);
	}

	
	public static void main(String[] args) {
		
		System.out.println("Welcome on myUber !");
		
		CarFactory carFactory = new CarFactory();
		RideFactory rideFactory = new RideFactory();
		MyUber platform = new MyUber(rideFactory, carFactory);
		
		/*Use Case scenario number 1:
		 * The platform and the factories are initialized 
		 * as well as drivers, customers and cars.
		 * Destinations are given to each customer.
		 */
		boolean app = true;
		Scanner scan = new Scanner(System.in);
		while (app){
			if (scan.hasNext()) {
				String a = scan.nextLine();
				if (a.equals("stop")) {
					app=false;
				}
				String[] l=a.split(" ");
				/*Commande setup  <nStandardCars> <nBerlinCars> <nVanCars> <nCustomers> */
				try {
					if (l[0].equalsIgnoreCase("setup")) {
						platform.setup(l[1],l[2],l[3],l[4]);
					}
					
					else if (l[0].equals("addCustomer")){
						platform.addCustomer(l[1], l[2]);
					}
					else if (l[0].equals("addCarDriver")){
						platform.addCarDriver(l[1],l[2],l[3]);
					}
					
					else if (l[0].equals("addDriver")){
						platform.addDriver(l[1],l[2],l[3]);
					}
					
					else if (l[0].equals("setDriverStatus")){
						platform.setDriverStatus(l[1],l[2],l[3]);
					}
					
					else if (l[0].equals("moveCar")){
						platform.moveCar(l[1], l[2], l[3]);
					}
					else if (l[0].equals("moveCustomer")){
						platform.moveCustomer(l[1],l[2],l[3]);
					}
					
					else if (l[0].equals("displayState")){
						platform.displayState();
					}
					
					else if (l[0].equals("ask4price")){
							platform.ask4price(l[1],l[2],l[3],l[4]);
					}
					else if (l[0].equals("simRide")){
						platform.simRide(l[1],l[2],l[3],l[4],l[5],l[6]);
					}
					/*else if (l[0].equals("simRide_i")){
						platform.simRide_i(l[1],l[2],l[3],l[4]);
					}*/
					else if (l[0].equals("displayDrivers")){
						platform.displayDrivers(l[1]);
					}
					else if (l[0].equals("displayDrivers")) {
						platform.displayCustomers(l[1]);
					}
					else if (l[0].equals("totalCashed")) {
						platform.totalCashed();
					}
					else {
						System.out.println("Unkown command");
					}
				}
				catch(NumberFormatException e) {
					System.out.println("Make sure you filled every field with relevant content");
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Not enough arguments");
				}
				
			}
		}
		scan.close();
		

		


		
		
/*		
		
		

		
		Driver d1 = new Driver("Vincent", "Bouget", "on-duty",new ArrayList<Double>(Arrays.asList(1.,5.)));
		Driver d2 = new Driver("Daniel", "(Taxi)", "on-duty",new ArrayList<Double>(Arrays.asList(90.,50.)));
		Driver d3 = new Driver("Nicolas", "Gabrion", "on-duty",new ArrayList<Double>(Arrays.asList(10.,6.)));
		Driver d4 = new Driver("Jean-Alexandre", "Dumond", "on-duty",new ArrayList<Double>(Arrays.asList(0.,6.7)));
		Driver d5 = new Driver("Neil", "Armstrong", "on-duty",new ArrayList<Double>(Arrays.asList(100.,60.)));
		Driver d6 = new Driver("Chofeur", "Cithechampillon", "on-duty",new ArrayList<Double>(Arrays.asList(4.0,6.2)));
		Driver d7 = new Driver("Sep", "Arti", "on-duty",new ArrayList<Double>(Arrays.asList(-5.,1.)));
		Driver d8 = new Driver("d", "8", "on-duty",new ArrayList<Double>(Arrays.asList(0.,1.)));
		Driver d9 = new Driver("d", "9", "on-duty",new ArrayList<Double>(Arrays.asList(-5.,1.6)));
		Driver d10 = new Driver("d", "10", "on-duty",new ArrayList<Double>(Arrays.asList(-5.,12.)));
		Driver d11 = new Driver("Sdep", "11", "on-duty",new ArrayList<Double>(Arrays.asList(6.,2.3)));
		
		
		Car v1 = platform.carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d1,d2)),"");
		Car v2 = platform.carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d1,d2)),"");
		Car v3 = platform.carFactory.createCar("Van", new ArrayList<Driver>(Arrays.asList(d1,d2, d3, d4)),"");
		Car s1 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d3)), "UberX");
		Car s2 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d4,d5)), "UberX");
		Car s3 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d5,d6,d7)), "UberX");
		Car s4 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d6)), "UberX");
		Car s5 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d8)), "UberPool");
		Car s6 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d9)), "UberPool");
		Car s7 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d10)), "UberPool");
		Car s8 = platform.carFactory.createCar("Standard", new ArrayList<Driver>(Arrays.asList(d11)), "UberPool");
		Car b1 = platform.carFactory.createCar("Berline", new ArrayList<Driver>(Arrays.asList(d7)),"");
		Car b2 = platform.carFactory.createCar("Berline", new ArrayList<Driver>(Arrays.asList(d3)),"");
		Car b3 = platform.carFactory.createCar("Berline", new ArrayList<Driver>(Arrays.asList(d1,d7)),"");
		Car b4 = platform.carFactory.createCar("Berline", new ArrayList<Driver>(Arrays.asList(d4)),"");
		
		Customer c1 = new Customer("Baptiste", "Andrieu", new ArrayList<Double>(Arrays.asList(1.2, 4.3)), 125765894);
		Customer c2 = new Customer("Noé", "Mikati", new ArrayList<Double>(Arrays.asList(0.0, 6.3)), 125775894);
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
		platform.add(d8);
		platform.add(d9);
		platform.add(d10);
		platform.add(d11);
		
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
		platform.takeCar(d8, s5);
		platform.takeCar(d9, s6);
		platform.takeCar(d10, s7);
		platform.takeCar(d11, s8);
		
		//Destinations
		ArrayList<Double> dest1 = new ArrayList<Double>(Arrays.asList(3.5, 2.7));
		ArrayList<Double> dest2 = new ArrayList<Double>(Arrays.asList(1.0, 0.0));
		ArrayList<Double> dest3 = new ArrayList<Double>(Arrays.asList(2.0, 1.0));
		ArrayList<Double> dest4 = new ArrayList<Double>(Arrays.asList(0.0, 0.0));
		ArrayList<Double> dest5 = new ArrayList<Double>(Arrays.asList(5.0, 1.0));
		ArrayList<Double> dest6 = new ArrayList<Double>(Arrays.asList(2.0, 1.7));
		ArrayList<Double> dest7 = new ArrayList<Double>(Arrays.asList(3.0, 32.0));
		
		/*End of Use Case scenario number 1 
		 */
		
		
		/*Use case scenario number 2:
		 * The customer c1 (Baptiste Andrieu) wants to go to the destination dest1.
		 * The four type of rides are proposed and he chooses the UberVan type.
		 * The ride is completed and the customer can note the driver.
		 */
		
/*		platform.setDestination(c1,dest1);
		platform.chooseRideType(c1, "UberVan");

		/*End of use case scenario number 2
		 */

		
		/*Use case scenario number 3:
		 * Customer c1 (Baptiste Andrieu), c2 (Noé Mikati) and c3 (Christophe Gallon)
		 * want to go respectively to destinations dest 6, dest7 and dest3.
		 * The four type of rides are proposed and they all choose the UberPool type.
		 * The ride is completed and customers can note the driver.
		 */
		
/*		platform.setDestination(c1,dest6);
		platform.chooseRideType(c1, "UberPool");
		platform.setDestination(c2,dest7);
		platform.chooseRideType(c2, "UberPool");
		platform.setDestination(c3,dest3);
		platform.chooseRideType(c3, "UberPool");

		
		/*End of use case scenario number 3
		 */

		
		/* Use case scenario number 4:
		 * Statistics about two rides in addition to the precedent rides are collected.
		 * Then driver and customers are sorted in order to print:
		 * the most charged customer
		 * the customer the most using the application
		 * the most appreciated driver
		 * the most occupied driver
		 */
		
/*		platform.setDestination(c2,dest2);
		platform.chooseRideType(c2, "UberBlack");
		platform.setDestination(c3,dest3);
		platform.chooseRideType(c3, "UberVan");
		
		System.out.println("The most charged customer is");
		System.out.println(platform.ChargeSortingCustomer().get(0));
		System.out.println("The customer the most using the application is");
		System.out.println(platform.FrequencySortingCustomer().get(0));
		System.out.println("The most liked driver is");
		System.out.println(platform.AppreciationSortingDriver().get(0));
		System.out.println("The most occupied driver is");
		System.out.println(platform.OccupationSortingDriver().get(0));
		System.out.println(d1.getAppreciationRate());
		System.out.println(d2.getAppreciationRate());
		System.out.println(d3.getAppreciationRate());
		System.out.println(d4.getAppreciationRate());
		System.out.println(d5.getAppreciationRate());
		System.out.println(d6.getAppreciationRate());
		System.out.println(d7.getAppreciationRate());
		
		/*End of use case scenario number 4
		 */	
		

	
	}
}
