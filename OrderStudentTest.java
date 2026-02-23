import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderStudentTest {

	Order order;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		order=null;
	}

	@Test
	void testOrderStringInt() {
		order=new Order("A", 5);
		assertEquals("A", order.getId());
		assertEquals(5, order.getDeadlineMinute());
	}

	@Test
	void testOrder() {
		order=new Order();
		assertEquals(null, order.getId());
		assertEquals(0, order.getDeadlineMinute());
		assertEquals(0, order.getArrivalMinute());
	}

	@Test
	void testSetArrivalMinute() {
		order=new Order();
		order.setArrivalMinute(1);
		assertEquals(1, order.getArrivalMinute());
	}

	@Test
	void testGetArrivalMinute() {
		order=new Order();
		order.setArrivalMinute(3);
		assertEquals(3, order.getArrivalMinute());
	}

	@Test
	void testGetDeadlineMinute() {
		order=new Order("A", 5);
		assertEquals(5, order.getDeadlineMinute());
	}

	@Test
	void testGetId() {
		order=new Order("A", 5);
		assertEquals("A", order.getId());
	}

}
