import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenericLinkedListStudentTest {

	private GenericLinkedList<String> list;
	
	@BeforeEach
	void setUp() throws Exception {
		list = new GenericLinkedList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		list=null;
	}

	@Test
	void testAdds() {
		list.addFirst("Dog");
		list.addFirst("Bird");
		assertEquals(2, list.size());
		list.addLast("Cat");
		assertEquals(3, list.size());
		assertEquals("Bird", list.get(0));
	}
	
	@Test
	void testIsEmptyAndSize() {
		assertEquals(true, list.isEmpty());
		assertEquals(0, list.size());
		list.addFirst("Dog");
		assertEquals(false, list.isEmpty());
		assertEquals(1, list.size());
	}
	
	@Test
	void testContainsAndGets() {
		list.addLast("Dog");
		list.addLast("Bird");
		list.addLast("Cat");
		assertEquals(false, list.contains("Elephant"));
		assertEquals(true, list.contains("Dog"));
		assertEquals("Dog", list.get(0));
		assertEquals("Dog", list.getFirst());
		assertEquals("Cat", list.getLast());
	}
	
	@Test
	void testRemovesAndClear() {
		list.addLast("Dog");
		list.addLast("Bird");
		list.addLast("Cat");
		assertEquals("Bird", list.remove(1));
		assertEquals("Dog", list.removeFirst());
		list.addFirst("Fish");
		assertEquals("Cat", list.removeLast());
		list.addFirst("Cow");
		assertEquals(true, list.remove("Fish"));
		assertEquals(false, list.remove("Elephant"));
		list.clear();
		assertEquals(0, list.size());
	}
	
	@Test
	void testToArray() {
		list.addLast("Dog");
		list.addLast("Bird");
		list.addLast("Cat");
		Object[] arr = list.toArray();
		assertEquals("Dog", arr[0]);
		assertEquals("Bird", arr[1]);
		assertEquals("Cat", arr[2]);
		assertEquals(3, arr.length);
	}

}
