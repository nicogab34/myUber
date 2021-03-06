package cars;

import java.util.ArrayList;
import java.util.Optional;

import driver.Driver;

public class CarFactory {
	
	/**
	 * @param carType
	 * @param drivers
	 * @param rideType
	 * @return a car created in the car factory
	 */
	public Car createCar(String carType, ArrayList<Driver> drivers, String rideType) {
		if (carType.equals("Berline")) {
			return new Berline(drivers);
		}
		else if (carType.equals("Van")) {
			return new Van(drivers);
		}
		else if (carType.equals("Standard")) {
			return new Standard(drivers, rideType);
		}
		return null;
	}
}
