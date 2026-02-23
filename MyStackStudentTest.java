import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyStackStudentTest {

	MyStack<Order> stack;
	MyStack<Order> stack2;
	Order[] orders = new Order[4];
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		stack=null;
		stack2=null;
	}

	@Test
	void testMyStackInt() {
		stack=new MyStack(5);
		assertEquals(0, stack.size());
		assertThrows(IllegalStateException.class,
				() -> stack2 = new MyStack<>(2000));
		orders[0]=new Order("A", 5);
		orders[1]=new Order("B", 6);
		orders[2]=new Order("C", 2);
		orders[3]=new Order ("D", 1);
		stack.push(orders[0]);
		stack.push(orders[1]);
		assertEquals(2, stack.size());
		stack.push(orders[2]);
		Order inst = stack.pop();
		assertEquals(2, inst.getDeadlineMinute());
	}

	@Test
	void testMyStack() {
		stack=new MyStack();
		assertEquals(0, stack.size());
		orders[0]=new Order("A", 5);
		orders[1]=new Order("B", 6);
		orders[2]=new Order("C", 2);
		orders[3]=new Order ("D", 1);
		stack.push(orders[0]);
		stack.push(orders[1]);
		assertEquals(2, stack.size());
		stack.push(orders[2]);
		stack.push(orders[2]);
		stack.push(orders[2]);
		stack.push(orders[2]);
		stack.push(orders[2]);
		stack.push(orders[2]);
		stack.push(orders[2]);
		stack.push(orders[2]);
		assertThrows(IllegalStateException.class,
				() -> stack.push(orders[2]));
	}

	@Test
	void testPush() {
		stack=new MyStack();
		assertEquals(0, stack.size());
		orders[0]=new Order("A", 5);
		orders[1]=new Order("B", 6);
		orders[2]=new Order("C", 2);
		stack.push(orders[0]);
		stack.push(orders[1]);
		stack.push(orders[2]);
		assertEquals(3, stack.size());
		assertEquals("C", stack.pop().getId());
		assertEquals("B", stack.pop().getId());
		assertEquals("A", stack.pop().getId());
	}

	@Test
	void testPop() {
		stack=new MyStack();
		assertEquals(0, stack.size());
		orders[0]=new Order("A", 5);
		orders[1]=new Order("B", 6);
		orders[2]=new Order("C", 2);
		stack.push(orders[0]);
		stack.push(orders[1]);
		stack.push(orders[2]);
		stack.pop();
		assertEquals(2, stack.size());
	}

	@Test
	void testPeek() {
		stack=new MyStack();
		assertEquals(0, stack.size());
		orders[0]=new Order("A", 5);
		orders[1]=new Order("B", 6);
		orders[2]=new Order("C", 2);
		stack.push(orders[0]);
		stack.push(orders[1]);
		stack.push(orders[2]);
		Order order = stack.peek();
		assertEquals(3, stack.size());
		assertEquals("C", order.getId());
		assertEquals(2, order.getDeadlineMinute());
	}

	@Test
	void testIsEmpty() {
		stack=new MyStack();
		assertEquals(true, stack.isEmpty());
		orders[0]=new Order("A", 5);
		orders[1]=new Order("B", 6);
		orders[2]=new Order("C", 2);
		stack.push(orders[0]);
		stack.push(orders[1]);
		stack.push(orders[2]);
		assertEquals(false, stack.isEmpty());
	}

	@Test
	void testSize() {
		stack=new MyStack();
		assertEquals(0, stack.size());
		orders[0]=new Order("A", 5);
		orders[1]=new Order("B", 6);
		orders[2]=new Order("C", 2);
		stack.push(orders[0]);
		stack.push(orders[1]);
		stack.push(orders[2]);
		assertEquals(3, stack.size());
	}

	@Test
	void testToArray() {
		stack=new MyStack();
		orders[0]=new Order("A", 5);
		orders[1]=new Order("B", 6);
		orders[2]=new Order("C", 2);
		stack.push(orders[0]);
		stack.push(orders[1]);
		stack.push(orders[2]);
		Object[] arr = stack.toArray();
		Order order1 = (Order)arr[0];
		Order order2 = (Order)arr[1];
		Order order3 = (Order)arr[2];
		assertEquals("A", order1.getId());
		assertEquals("B", order2.getId());
		assertEquals("C", order3.getId());
		assertEquals(5, order1.getDeadlineMinute());
		assertEquals(6, order2.getDeadlineMinute());
		assertEquals(2, order3.getDeadlineMinute());
		assertEquals(3, stack.size());
	}

}
