package uebung1Revised;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {

	private BinaryTree<Integer> myTree;

	@Before
	public void setUp() throws Exception {
		myTree = new MyBinaryTree<>();
		assertTrue(myTree.insert(1));
		assertTrue(myTree.insert(4));
		assertTrue(myTree.insert(3));
		assertTrue(myTree.insert(2));
		assertTrue(myTree.insert(6));
		assertTrue(myTree.insert(5));
	}

	@Test
	public void testInsert() {
		myTree = new MyBinaryTree<>();
		assertTrue(myTree.insert(1));
		assertTrue(myTree.insert(4));
		assertTrue(myTree.insert(3));
		assertTrue(myTree.insert(2));
		assertTrue(myTree.insert(6));
		assertTrue(myTree.insert(5));
		assertTrue(myTree.insert(-4));
	}
	
	@Test
	public void testInsertFromFile() throws IOException {
		myTree.saveToFile("Hello");
		myTree = new MyBinaryTree<Integer>();
		myTree.insert("Hello");
		System.out.println(((MyBinaryTree<Integer>)myTree).asList());
	}

	@Test
	public void testTreeWithIntegers() throws IOException {

		assertTrue(myTree.contains(6));
		assertTrue(myTree.contains(5));
		assertTrue(myTree.contains(4));
		assertTrue(myTree.contains(3));
		assertTrue(myTree.contains(2));
		assertTrue(myTree.contains(1));
		assertEquals(6, myTree.size());
		System.out.println(((MyBinaryTree<Integer>) myTree).asList());
		myTree.saveToFile("Hello");
		assertTrue(myTree.remove(1));
		assertTrue(myTree.remove(2));
		assertTrue(myTree.remove(3));
		assertTrue(myTree.remove(4));
		assertTrue(myTree.remove(5));
		assertTrue(myTree.remove(6));

	}

}
