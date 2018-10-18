package myUber;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public abstract class Car implements Lock{
	private int seats;
	private String ID;
	private ArrayList<Driver> owners;
	
	
	public Car(int seats, String iD, ArrayList<Driver> owners) {
		super();
		this.seats = seats;
		ID = iD;
		this.owners = owners;
	}


	public ArrayList<Driver> getOwners() {
		return owners;
	}


	public void setOwners(ArrayList<Driver> owners) {
		this.owners = owners;
	}


	public int getSeats() {
		return seats;
	}


	public String getID() {
		return ID;
	}
	
	@Override
	public void lock() {
		this.lock();
		
	}
	@Override
	public void unlock() {
		this.unlock();
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
