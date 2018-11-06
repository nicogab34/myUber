package Fare;

import myUber.UberBlack;
import myUber.UberPool;
import myUber.UberVan;
import myUber.UberX;

public interface FareType {
	double fare(String rideType,double rideDistance,String trafficCondition);
	
	
	double fare(UberX UberX,double rideDistance,String trafficCondition);
	double fare(UberVan UberVan,double rideDistance,String trafficCondition);
	double fare(UberPool UberPool,double rideDistance,String trafficCondition);
	double fare(UberBlack UberBlack,double rideDistance,String trafficCondition); 

}
