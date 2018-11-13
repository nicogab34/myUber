package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import myUber.CarFactory;
import myUber.Customer;
import myUber.MyUber;
import myUber.Request;
import myUber.RideFactory;

class RequestTest {

	@Test
	void setPricesTest1() {
		Request r = new Request(new Customer("Baptiste", "Andrieu", new ArrayList<Double>(Arrays.asList(1.2, 4.3)), 125765894), new MyUber(new RideFactory(), new CarFactory()));
		r.setPrices(new ArrayList<Double>(Arrays.asList(1.2, 4.3)));
		assertEquals(r.getPrices(),new ArrayList<Double>(Arrays.asList(1.2, 4.3)));
	}
	@Test
	void setPricesTest2() {
		Request r = new Request(new Customer("Baptiste", "Andrieu", new ArrayList<Double>(Arrays.asList(1.2, 4.3)), 125765894), new MyUber(new RideFactory(), new CarFactory()));
		r.setPrices(new ArrayList<Double>(Arrays.asList(1.2, 4.3)));
		r.setPrices(new ArrayList<Double>(Arrays.asList(0.0, 0.0)));
		assertEquals(r.getPrices(),new ArrayList<Double>(Arrays.asList(1.2, 4.3)));
	}
	@Test
	void setChoiceTest1() {
		Request r = new Request(new Customer("Baptiste", "Andrieu", new ArrayList<Double>(Arrays.asList(1.2, 4.3)), 125765894), new MyUber(new RideFactory(), new CarFactory()));
		r.setPrices(new ArrayList<Double>(Arrays.asList(1.2, 4.3)));
		r.setChoice("UberVan");
		assertEquals(r.getChoice(),"UberVan");
	}
	@Test
	void setChoiceTest2() {
		Request r = new Request(new Customer("Baptiste", "Andrieu", new ArrayList<Double>(Arrays.asList(1.2, 4.3)), 125765894), new MyUber(new RideFactory(), new CarFactory()));
		r.setPrices(new ArrayList<Double>(Arrays.asList(1.2, 4.3)));
		r.setChoice("UberVan");
		r.setChoice("UberX");
		assertEquals(r.getChoice(),"UberVan");
	}
	@Test
	void setChoiceTest3() {
		Request r = new Request(new Customer("Baptiste", "Andrieu", new ArrayList<Double>(Arrays.asList(1.2, 4.3)), 125765894), new MyUber(new RideFactory(), new CarFactory()));
		r.setPrices(new ArrayList<Double>(Arrays.asList(1.2, 4.3)));
		r.setChoice("UberMerco");
		assertNull(r.getChoice());
	}
}
