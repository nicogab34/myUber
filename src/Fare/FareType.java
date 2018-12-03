package Fare;

import rides.UberBlack;
import rides.UberPool;
import rides.UberVan;
import rides.UberX;

public interface FareType {
	double fare(String rideType,double rideDistance,String trafficCondition);
	
	
	double fare(UberX UberX,double rideDistance,String trafficCondition);
	double fare(UberVan UberVan,double rideDistance,String trafficCondition);
	double fare(UberPool UberPool,double rideDistance,String trafficCondition);
	double fare(UberBlack UberBlack,double rideDistance,String trafficCondition); 

}
