import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuilderAndWordStudentTest {

	DictionaryBuilder db = new DictionaryBuilder(100);
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDbMethods() throws FileNotFoundException{
		assertEquals(0, db.getUniqueCount());
		assertEquals(0, db.getTotalCount());
		assertEquals(0.6, db.getLoadFactor());
		db.addWord("dog");
		assertEquals(1, db.getUniqueCount());
		assertEquals(1, db.getTotalCount());
		assertEquals(1, db.getFrequency("dog"));
		DictionaryBuilder db2 = new DictionaryBuilder("sample_input.txt");
		assertEquals(2, db2.getFrequency("quick"));
		assertEquals(18, db2.getTotalCount());
		assertEquals(12, db2.getUniqueCount());
		assertThrows(FileNotFoundException.class,
               () ->  new DictionaryBuilder("fake.txt"));
		db.addWord("cat");
		db.addWord("dog");
		List<String> list = db.getAllWords();
		Iterator<String> iter = list.iterator();
		assertEquals("cat", iter.next());
		assertEquals("dog", iter.next());
		db.removeWord("cat");
		assertEquals(1, db.getUniqueCount());
		assertEquals(2, db.getTotalCount());
		assertEquals(true, DictionaryBuilder.isPrime(13));
		// Removing word and checking if dictionary updated correctly
		db.removeWord("dog");
		assertEquals(0, db.getTotalCount());
		// Adding words with varying cases and punctuation
		db.addWord("Apple!");
		db.addWord("apple");
		assertEquals(2, db.getFrequency("apple"));
		// Checking duplicates are not re-added
		assertEquals(1, db.getUniqueCount());
		// Testing with empty dictionary
		DictionaryBuilder db4 = new DictionaryBuilder(0);
		List<String> list2 = db4.getAllWords();
		assertEquals(0, list2.size());
		// Testing delete on non-existent word
		assertThrows(DictionaryEntryNotFoundException.class,
	               () ->  db4.removeWord("dog"));
		// Testing search on non-existent word
		assertEquals(0, db4.getFrequency("bird"));
		// Searching known words and verifying the frequencies
		db4.addWord("man");
		db4.addWord("woman");
		db4.addWord("woman");
		assertEquals(1, db4.getFrequency("man"));
		assertEquals(2, db4.getFrequency("Woman"));
	}
	
	@Test
	void testWordMethods() {
		Word word = new Word("pizza", 3);
		assertEquals("pizza", word.getName());
		assertEquals(3, word.getCount());
		word.setName("yes");
		word.setCount(5);
		assertEquals("yes", word.getName());
		assertEquals(5, word.getCount());
	}

}
