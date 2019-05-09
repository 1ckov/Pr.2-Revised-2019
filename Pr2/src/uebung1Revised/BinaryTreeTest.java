package uebung1Revised;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testTreeWithIntegers() throws IOException {
		BinaryTree<Integer> myTree = new MyBinaryTree<>();
		assertTrue(myTree.insert(1));
		assertTrue(myTree.insert(4));
		assertTrue(myTree.insert(3));
		assertTrue(myTree.insert(2));
		assertTrue(myTree.insert(6));
		assertTrue(myTree.insert(5));
		assertTrue(myTree.contains(6));
		assertTrue(myTree.contains(5));
		assertTrue(myTree.contains(4));
		assertTrue(myTree.contains(3));
		assertTrue(myTree.contains(2));
		assertTrue(myTree.contains(1));
		assertEquals(6, myTree.size());
		System.out.println(((MyBinaryTree<Integer>)myTree).asList());
		myTree.saveToFile("Hello");
		assertTrue(myTree.remove(1));
		assertTrue(myTree.remove(2));
		assertTrue(myTree.remove(3));
		assertTrue(myTree.remove(4));
		assertTrue(myTree.remove(5));
		assertTrue(myTree.remove(6));
		
		
		
	}

}
