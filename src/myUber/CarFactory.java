package myUber;

import java.util.ArrayList;
import java.util.Optional;

public class CarFactory {
	public Car createCar(String carType, ArrayList<Driver> drivers, String rideType) {
		if (carType == "Berline") {
			return new Berline(drivers);
		}
		else if (carType == "Van") {
			return new Van(drivers);
		}
		else if (carType == "Standard") {
			return new Standard(drivers, rideType);
		}
		return null;
	}
}
