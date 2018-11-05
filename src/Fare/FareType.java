package Fare;

import myUber.UberBlack;
import myUber.UberPool;
import myUber.UberVan;
import myUber.UberX;

public interface FareType {
	double fare(UberX UberX,double rideDistance,int trafficCondition);
	double fare(UberVan UberVan,double rideDistance,int trafficCondition);
	double fare(UberPool UberPool,double rideDistance,int trafficCondition);
	double fare(UberBlack UberBlack,double rideDistance,int trafficCondition);

}
