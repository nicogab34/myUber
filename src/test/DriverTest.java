package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import cars.Car;
import cars.CarFactory;
import driver.Driver;

class DriverTest {
	@Test
	void driverConstructorTest() {
		Driver d3 = new Driver("Nicolas", "Gabrion", "on-duty",new ArrayList<Double>(Arrays.asList(10.,6.)));
		assertFalse(d3==null);
	}
	
	@Test
	void takeRightCarTest() {
		Driver d3 = new Driver("Nicolas", "Gabrion", "on-duty",new ArrayList<Double>(Arrays.asList(10.,6.)));
		Car s1 = new CarFactory().createCar("Standard", new ArrayList<Driver>(Arrays.asList(d3)), "");
		d3.takeCar(s1);
		assertTrue("takeRightCarTest", d3.getCar() == s1);
	}
	
	@Test
	void takeWrongCarTest() {
		Driver d3 = new Driver("Nicolas", "Gabrion", "on-duty",new ArrayList<Double>(Arrays.asList(10.,6.)));
		Car s1 = new CarFactory().createCar("Standard", new ArrayList<Driver>(Arrays.asList()), "");
		d3.takeCar(s1);
		assertTrue("takeWrongCarTest", d3.getCar() == null);
	}

}
