import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseCodeStudentTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTreeNode() {
		TreeNode<String> inst = new TreeNode<>("dog");
		assertEquals("dog", inst.getData());
		assertEquals(null, inst.getRightChild());
		assertEquals(null, inst.getLeftChild());
		TreeNode<String> inst2 = new TreeNode<>(inst);
		assertEquals("dog", inst2.getData());
		assertEquals(null, inst2.getRightChild());
		assertEquals(null, inst2.getLeftChild());
		inst.setData("cat");
		assertEquals("cat", inst.getData());
		TreeNode<String> left = new TreeNode<>("bird");
		TreeNode<String> right = new TreeNode<>("hamster");
		inst.setLeftChild(left);
		inst.setRightChild(right);
		assertEquals(true, inst.hasLeftChild());
		assertEquals(true, inst.hasRightChild());
		assertEquals(false, inst.isLeaf());
		assertEquals(true, left.isLeaf());
	}
	
	@Test
	void testMorseCodeConverterAndMorseCodeTree() throws FileNotFoundException {
		MorseCodeConverter inst = new MorseCodeConverter();
		File file = new File("src/howDoILoveThee.txt");
		File file2 = new File("fake.txt");
		assertEquals("how do i love thee let me count the ways", MorseCodeConverter.convertToEnglish(file));
		assertThrows(FileNotFoundException.class,
	               () ->  MorseCodeConverter.convertToEnglish(file2));
		assertEquals("i love sushi", MorseCodeConverter.convertToEnglish(".. / .-.. --- ...- . / ... ..- ... .... .."));
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", MorseCodeConverter.printTree());
		MorseCodeTree instTree = new MorseCodeTree();
		assertEquals("dogs are awesome", MorseCodeConverter.convertToEnglish("-.. --- --. ... / .- .-. . / .- .-- . ... --- -- ."));
		MorseCodeTree ex = new MorseCodeTree();
		TreeNode<String> empty = new TreeNode<>("");
		ex.setRoot(empty);
		assertEquals(empty, ex.getRoot());
	}
}
