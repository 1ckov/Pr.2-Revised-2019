package uebung3Revised;

public interface HashTable <K,V> {
	/**
	 * Put's a key, value pair in the table;
	 * @param key - contains the key which is going to be used for hashing
	 * @param value - the value to be stored
	 * @return - Null if value got put into table;
	 * 		   - The value that got taken our or replaced;
	 */
	V put(K key,V value);
	/**
	 * Gets the vlaue corresponding to this key;
	 * @param key - The key to be searched for.
	 * @return - the corresponding value;
	 * 		   - if not found Null;
	 */
	V get(K key);
	/**
	 * Checks the table for a value;
	 * @param value - value to be searched for;
	 * @return - the value if found;
	 * 		   - Null if not;
	 */
	boolean contains(V value);
	/**
	 * Checks the table for a value, using a key;
	 * @param key - the key who corresponds to the value;
	 * @return - the value if found;
	 * 		   - Null if not;	 
	 * */
	boolean containsKey(K key);
	/**
	 * Checks the table if empty;
	 * @return - true if is/false otherwise
	 */
	boolean isEmpty();
	/**
	 * The size of the current table;
	 * @return - the number of elements stored in the table;
	 */
	int size();
	/**
	 * Returns an empty Hash table;
	 * @return an empty Hash table;
	 */
	HashTable<K,V> clear(HashTable<K,V> thisTable);
	/**
	 * Removes a value corresponding to a key;
	 * @value key - the key;
	 * @return True if successful/false if not;
	 */
	boolean remove(K key);
	
	public Probing getProbing();
	public int getStat();
	

}
