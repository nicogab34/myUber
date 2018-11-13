package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import myUber.CarFactory;
import myUber.MyUber;
import myUber.RideFactory;

class MyUberTest {
	RideFactory rideFactory = new RideFactory();
	
	CarFactory carFactory = new CarFactory();
	
	MyUber platform = new MyUber(rideFactory, carFactory);

	@Test
	void distanceTest() {
		ArrayList<Double> dest1 = new ArrayList<Double>(Arrays.asList(3.5, 2.7));
		ArrayList<Double> dest2 = new ArrayList<Double>(Arrays.asList(1.0, 0.0));
		assertEquals(platform.distance(dest1,dest2), Math.sqrt(Math.pow(2.5,2)+Math.pow(2.7, 2)));
	}
	

}
