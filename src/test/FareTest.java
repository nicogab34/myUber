package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Fare.FareCalculation;
import customer.Customer;
import driver.Driver;
import rides.Ride;
import rides.RideFactory;

class FareTest {

	@Test
	void fareX1() {
		RideFactory rf = new RideFactory();
		FareCalculation fc = new FareCalculation();
		assert Math.abs(fc.fare("UberX", 1., "low")- 3.3) < 0.001;
	}
	@Test
	void fareX2() {
		RideFactory rf = new RideFactory();
		FareCalculation fc = new FareCalculation();
		assert Math.abs(fc.fare("UberX", 8., "medium")- 36.96) < 0.001;
	}
	@Test
	void fareBlack1() {
		RideFactory rf = new RideFactory();
		FareCalculation fc = new FareCalculation();
		System.out.println(fc.fare("UberBlack", 10., "high"));
		assert Math.abs(fc.fare("UberBlack", 10., "high")- 52.) < 0.001;
	}
	@Test
	void fareBlack2() {
		RideFactory rf = new RideFactory();
		FareCalculation fc = new FareCalculation();
		assert Math.abs(fc.fare("UberBlack", 10., "medium")- 42.25) < 0.001;
	}
	@Test
	void fareVan1() {
		RideFactory rf = new RideFactory();
		FareCalculation fc = new FareCalculation();
		assert Math.abs(fc.fare("UberVan", 5., "medium")- 57.75) < 0.001;
	}
	@Test
	void fareVan2() {
		RideFactory rf = new RideFactory();
		FareCalculation fc = new FareCalculation();
		assert Math.abs(fc.fare("UberVan", 7., "high")- 97.02) < 0.001;
	}

}
