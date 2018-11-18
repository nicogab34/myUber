package myUber;

import java.util.ArrayList;

import sun.misc.Lock;

public class Driver extends Lock{
	private int ID;
	private String name;
	private String surname;
	private String state;
	private Car car;
	private ArrayList<Double> position;
	private ArrayList<Customer> requests = new ArrayList<Customer>();
	private double numberOfRides=0;
	private double MoneyCashed=0;
	private double TimeOnDuty=0;
	private double TimeOnRide=0;
	private double TimeOffDuty=0;
	private double TimeVar=0;
	private ArrayList<Double> AppreciationRate;
	private static int nextID = 1;

	/**
	 * 
	 * @param name
	 * @param surname
	 * @param state
	 * @param position
	 * Creates a new driver
	 */
	public Driver(String name, String surname, String state,ArrayList<Double> position) {
		super();
		this.name = name;
		this.surname = surname;
		this.state = state;
		this.ID = nextID;
		nextID++;
		this.position=position;
		this.AppreciationRate.add(5.);
		this.AppreciationRate.add(0.);
	}
		
	/**
	 * @return the time spent in state "on-duty"
	 */
	public double getTimeOnDuty() {
		return TimeOnDuty;
	}


	public void setTimeOnDuty(double timeOnDuty) {
		TimeOnDuty = timeOnDuty;
	}


	public double getTimeDrvAround() {
		return TimeOnRide;
	}


	public void setTimeDrvAround(double timeDrvAround) {
		TimeOnRide = timeDrvAround;
	}


	public double getNumberOfRides() {
		return numberOfRides;
	}

	public void setNumberOfRides(double numberOfRides) {
		this.numberOfRides = numberOfRides;
	}
	
	public double getMoneyCashed() {
		return MoneyCashed;
	}


	public void setMoneyCashed(double moneyCashed) {
		MoneyCashed = moneyCashed;
	}


	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		double t1=this.TimeVar;
		double t2=System.currentTimeMillis();
		double delta=t2-t1;
		this.TimeVar=t2;
		if(this.state=="on-duty"){
			this.TimeOnDuty=this.TimeOnDuty+delta;
		}
		if(this.state=="on-a-ride") {
			this.TimeOnRide=this.TimeOnRide+delta;
		}
		if(this.state=="off-duty") {
			this.TimeOffDuty=this.TimeOffDuty+delta;
		}
		this.state = state; 
	}
	
	public void takeCar(Car car) {
		if (car.getOwners().contains(this)) {
			this.car = car;
		}
		else {
			System.out.println("Meaningless command");
		}
	}
	
	public Car getCar() {
		return car;
	}
	

	public ArrayList<Double> getPosition() {
		return position;
	}

	public void setPosition(ArrayList<Double> position) {
		this.position = position;
	}
	
	public void addRequest(Customer customer) {
		this.requests.add(customer);
	}

	public String toString() {
		String carString;
		if (this.car==null) {
			carString = "No car";
		}
		else {
			carString = this.car.getID();
		}
		return "## DRIVER ##\n"+
	"ID : "+this.ID+"\n"+
	"Name : "+this.name+"\n"+
	"Surname : "+this.surname+"\n"+
	"State : "+this.state+"\n"+
	"Car : "+carString+"\n";
	}
	
	public double onDutyRate() {
		return(this.TimeOnDuty/this.TimeOnRide);
	}
	
	public double ActivityRate() {
		double t1=this.TimeOnDuty+this.TimeOnRide;
		double t2=t1+this.TimeOffDuty;
		return(t1/t2);
	}

	public double getAppreciationRate() {
		return AppreciationRate.get(0);
	}

	public void setAppreciationRate(double Rate) {
		double n=this.AppreciationRate.get(1);
		double m=(Rate+n*AppreciationRate.get(0))/(n+1);
		AppreciationRate.set(0, m);
		AppreciationRate.set(1,n+1);
	}
	

}
