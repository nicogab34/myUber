package Fare;

import myUber.UberBlack;
import myUber.UberPool;
import myUber.UberVan;
import myUber.UberX;

public class FareCalculation implements FareType{

	@Override
	public double fare(UberX UberX, double rideDistance, String trafficCondition) {
		// TODO Auto-generated method stub
		double trafficRate=0;
		if (trafficCondition=="low") {trafficRate=1;}
		if (trafficCondition=="medium") {trafficRate=1.1;}
		if (trafficCondition=="high") {trafficRate=1.5;}
		
		if rideDistance

		return 0;
	}

	@Override
	public double fare(UberVan UberVan, double rideDistance, int trafficCondition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double fare(UberPool UberPool, double rideDistance, int trafficCondition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double fare(UberBlack UberBlack, double rideDistance, int trafficCondition) {
		// TODO Auto-generated method stub
		return 0;
	}

}
