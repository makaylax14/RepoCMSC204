import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyPriorityQueueStudentTest {
	
	MyPriorityQueue<Order> queue;
	MyPriorityQueue<Order> queue2;
	Order[] orders = new Order[4];
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		queue=null;
		queue2=null;
	}

	@Test
	void testMyPriorityQueueIntComparatorOfT() {
		orders[0]=new Order("A", 5);
		orders[1]=new Order("B", 6);
		orders[2]=new Order("C", 2);
		orders[3]=new Order ("D", 1);
		OrderComparator comp = new OrderComparator();
		queue=new MyPriorityQueue<>(orders.length, comp);
		assertEquals(0, queue.size());
		assertThrows(IllegalStateException.class,
				() -> queue2 = new MyPriorityQueue<>(2000, comp));
		queue.enqueue(orders[0]);
		queue.enqueue(orders[1]);
		assertEquals(2, queue.size());
		queue.enqueue(orders[2]);
		Order inst = queue.dequeue();
		assertEquals(2, inst.getDeadlineMinute());
	}

	@Test
	void testMyPriorityQueueComparatorOfT() {
		OrderComparator comp = new OrderComparator();
		queue=new MyPriorityQueue<>(comp);
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		assertThrows(IllegalStateException.class,
				() -> queue.enqueue(new Order("A", 5)));
	}

	@Test
	void testEnqueue() {
		OrderComparator comp = new OrderComparator();
		queue=new MyPriorityQueue<>(comp);
		queue.enqueue(new Order("A", 5));
		assertEquals(1, queue.size());
		Order inst = queue.dequeue();
		assertEquals(5, inst.getDeadlineMinute());
		
	}

	@Test
	void testDequeue() {
		OrderComparator comp = new OrderComparator();
		queue=new MyPriorityQueue<>(comp);
		queue.enqueue(new Order("A", 5));
		assertEquals(1, queue.size());
		Order inst = queue.dequeue();
		assertEquals(0, queue.size());
	}

	@Test
	void testPeek() {
		OrderComparator comp = new OrderComparator();
		queue=new MyPriorityQueue<>(comp);
		queue.enqueue(new Order("A", 5));
		assertEquals(1, queue.size());
		Order inst = queue.peek();
		assertEquals(5, inst.getDeadlineMinute());
		assertEquals("A", inst.getId());
	}

	@Test
	void testIsEmpty() {
		OrderComparator comp = new OrderComparator();
		queue=new MyPriorityQueue<>(comp);
		assertEquals(true, queue.isEmpty());
	}

	@Test
	void testSize() {
		OrderComparator comp = new OrderComparator();
		queue=new MyPriorityQueue<>(comp);
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("A", 5));
		assertEquals(3, queue.size());
	}

	@Test
	void testToArray() {
		OrderComparator comp = new OrderComparator();
		queue=new MyPriorityQueue<>(comp);
		queue.enqueue(new Order("A", 5));
		queue.enqueue(new Order("B", 2));
		queue.enqueue(new Order("C", 3));
		Object[] arr = queue.toArray();
		Order order1 = (Order)arr[0];
		Order order2 = (Order)arr[1];
		Order order3 = (Order)arr[2];
		assertEquals("B", order1.getId());
		assertEquals("C", order2.getId());
		assertEquals("A", order3.getId());
		assertEquals(2, order1.getDeadlineMinute());
		assertEquals(3, order2.getDeadlineMinute());
		assertEquals(5, order3.getDeadlineMinute());
		assertEquals(3, queue.size());
	}

}
