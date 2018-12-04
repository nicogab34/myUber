package gui;

import cars.CarFactory;
import myUber.MyUber;
import rides.RideFactory;

public class Main {

	public static void main(String[] args) {
		Controlleur c = new Controlleur(new MyUber(new RideFactory(), new CarFactory()));

	}

}
