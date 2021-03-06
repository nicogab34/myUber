package Fare;



import rides.UberBlack;
import rides.UberPool;
import rides.UberVan;
import rides.UberX;

public class FareCalculation implements FareType{
	
	public FareCalculation() {
		super();
	}
	
	/**
	 * @param rideType
	 * @param rideDistance
	 * @param trafficConditon
	 * @return
	 * Depending on the type of ride (X,Black,Van, Pool), traffic state and the distance of the ride, this function returns the price of the ride
	 */
	@Override
	public double fare(String rideType, double rideDistance, String trafficCondition) {
		// TODO Auto-generated method stub
		double cost=0;
		double trafficRate=0;
		double BasicRate=0;
		if (rideType=="UberX") {
			if (trafficCondition=="low") {trafficRate=1;}
			if (trafficCondition=="medium") {trafficRate=1.1;}
			if (trafficCondition=="high") {trafficRate=1.5;}
		
			//if rideDistance
			if (rideDistance<5) {BasicRate=3.3;}
			if ((rideDistance>=5) & (rideDistance<10)){BasicRate=4.2;}
			if ((rideDistance>=10) & (rideDistance<20)){BasicRate=1.91;}
			if (rideDistance>=20){BasicRate=1.5;}
			
		}
		if (rideType=="UberVan") {		// TODO Auto-generated method stub
			if (trafficCondition=="low") {trafficRate=1;}
			if (trafficCondition=="medium") {trafficRate=1.5;}
			if (trafficCondition=="high") {trafficRate=1.8;}
			
			//if rideDistance
			if (rideDistance<5) {BasicRate=6.2;}
			if ((rideDistance>=5) & (rideDistance<10)){BasicRate=7.7;}
			if ((rideDistance>=10) & (rideDistance<20)){BasicRate=3.25;}
			if (rideDistance>=20){BasicRate=2.6;}

			
		}
		if (rideType=="UberPool") {
			if (trafficCondition=="low") {trafficRate=1;}
			if (trafficCondition=="medium") {trafficRate=1.1;}
			if (trafficCondition=="high") {trafficRate=1.2;}
			
			//if rideDistance
			if (rideDistance<5) {BasicRate=2.4;}
			if ((rideDistance>=5) & (rideDistance<10)){BasicRate=3;}
			if ((rideDistance>=10) & (rideDistance<20)){BasicRate=1.3;}
			if (rideDistance>=20){BasicRate=1.1;}

			
		}
		if (rideType=="UberBlack") {
			if (trafficCondition=="low") {trafficRate=1;}
			if (trafficCondition=="medium") {trafficRate=1.3;}
			if (trafficCondition=="high") {trafficRate=1.6;}
			
			//if rideDistance
			if (rideDistance<5) {BasicRate=6.2;}
			if ((rideDistance>=5) & (rideDistance<10)){BasicRate=5.5;}
			if ((rideDistance>=10) & (rideDistance<20)){BasicRate=3.25;}
			if (rideDistance>=20){BasicRate=2.6;}
			
		}
		cost=rideDistance*BasicRate*trafficRate;
		return cost;
	}
	
	/**
	 * @param UberX
	 * @param rideDistance
	 * @param trafficConditon
	 * @return
	 * Depending traffic state and the distance of the ride, this function returns the price of an UberX ride
	 */
	@Override
	public double fare(UberX UberX, double rideDistance, String trafficCondition) {
		// TODO Auto-generated method stub
		double cost=0;
		double trafficRate=0;
		double BasicRate=0;
		if (trafficCondition=="low") {trafficRate=1;}
		if (trafficCondition=="medium") {trafficRate=1.1;}
		if (trafficCondition=="high") {trafficRate=1.5;}
		
		//if rideDistance
		if (rideDistance<5) {BasicRate=3.3;}
		if ((rideDistance>5) & (rideDistance<10)){BasicRate=4.2;}
		if ((rideDistance>10) & (rideDistance<20)){BasicRate=1.91;}
		if (rideDistance>20){BasicRate=1.5;}
		cost=rideDistance*BasicRate*trafficRate;

		return cost;
	}

	/**
	 * @param UberVan
	 * @param rideDistance
	 * @param trafficConditon
	 * @return
	 * Depending traffic state and the distance of the ride, this function returns the price of an UberVan ride
	 */
	@Override
	public double fare(UberVan UberVan, double rideDistance, String trafficCondition) {
		// TODO Auto-generated method stub
		double cost=0;
		double trafficRate=0;
		double BasicRate=0;
		if (trafficCondition=="low") {trafficRate=1;}
		if (trafficCondition=="medium") {trafficRate=1.5;}
		if (trafficCondition=="high") {trafficRate=1.8;}
		
		//if rideDistance
		if (rideDistance<5) {BasicRate=6.2;}
		if ((rideDistance>5) & (rideDistance<10)){BasicRate=7.7;}
		if ((rideDistance>10) & (rideDistance<20)){BasicRate=3.25;}
		if (rideDistance>20){BasicRate=2.6;}
		cost=rideDistance*BasicRate*trafficRate;

		return cost;
	}

	/**
	 * @param UberPool
	 * @param rideDistance
	 * @param trafficConditon
	 * @return
	 * Depending traffic state and the distance of the ride, this function returns the price of an UberPool ride
	 */
	@Override
	public double fare(UberPool UberPool, double rideDistance, String trafficCondition) {
		// TODO Auto-generated method stub
		double cost=0;
		double trafficRate=0;
		double BasicRate=0;
		if (trafficCondition=="low") {trafficRate=1;}
		if (trafficCondition=="medium") {trafficRate=1.1;}
		if (trafficCondition=="high") {trafficRate=1.2;}
		
		//if rideDistance
		if (rideDistance<5) {BasicRate=2.4;}
		if ((rideDistance>5) & (rideDistance<10)){BasicRate=3;}
		if ((rideDistance>10) & (rideDistance<20)){BasicRate=1.3;}
		if (rideDistance>20){BasicRate=1.1;}
		cost=rideDistance*BasicRate*trafficRate;

		return cost;
	}

	/**
	 * @param UberBlack
	 * @param rideDistance
	 * @param trafficConditon
	 * @return
	 * Depending traffic state and the distance of the ride, this function returns the price of an UberBlack ride
	 */
	@Override
	public double fare(UberBlack UberBlack, double rideDistance, String trafficCondition) {
		// TODO Auto-generated method stub
		double cost=0;
		double trafficRate=0;
		double BasicRate=0;
		if (trafficCondition=="low") {trafficRate=1;}
		if (trafficCondition=="medium") {trafficRate=1.3;}
		if (trafficCondition=="high") {trafficRate=1.6;}
		
		//if rideDistance
		if (rideDistance<5) {BasicRate=6.2;}
		if ((rideDistance>5) & (rideDistance<10)){BasicRate=5.5;}
		if ((rideDistance>10) & (rideDistance<20)){BasicRate=3.25;}
		if (rideDistance>20){BasicRate=2.6;}
		cost=rideDistance*BasicRate*trafficRate;

		return cost;
	} 

}
