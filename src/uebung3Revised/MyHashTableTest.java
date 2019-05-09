package uebung3Revised;


import java.lang.invoke.MethodHandles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uebung3.Song;

public class MyHashTableTest {
	HashTable<Element,Song> table;
	@Before
	public void setUp() throws Exception {
		table = new MyHashTable<>();
	}

	@After
	public void tearDown() throws Exception {
		table = null;
	}
	

	@Test
	public void test() {
	}

}
