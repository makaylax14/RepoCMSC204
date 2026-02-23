import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderComparatorStudentTest {

	OrderComparator oc;
	
	@BeforeEach
	void setUp() throws Exception {
		oc=new OrderComparator();
	}

	@AfterEach
	void tearDown() throws Exception {
		oc=null;
	}

	@Test
	void testCompare() {
		Order order1 = new Order("A", 5);
		Order order2 = new Order("B", 2);
		Order order3 = new Order("C", 5);
		assertEquals(1, oc.compare(order1, order2));
		assertEquals(-1, oc.compare(order2, order1));
		order1.setArrivalMinute(1);
		order3.setArrivalMinute(2);
		assertEquals(-1, oc.compare(order1, order3));
		assertEquals(1, oc.compare(order3, order1));
		order3.setArrivalMinute(1);
		assertEquals(0, oc.compare(order3, order1));
	}

}
