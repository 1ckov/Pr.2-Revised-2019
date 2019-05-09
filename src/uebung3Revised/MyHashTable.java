package uebung3Revised;

public class MyHashTable<K extends Element, V extends Object> implements HashTable<K, V> {

	private static final int DEFAULT_SIZE = 8;
	private int size;
	private int numberOfCollisions;
	/**
	 * Specifies the probing type;
	 */
	private Probing probing;
	/**
	 * The table to hold our values;
	 */
	private HashItem<K, V> table[];
	private int numberOfElements;

	/**
	 * Calculates the fill Grade of the table;
	 * 
	 * @return an integer value
	 */
	public int calculateFilling() {
		int result = ((100 * numberOfElements) / (size));
		return result;
	}

	/**
	 * Returns the number of collisions;
	 */
	public int getStat() {
		return numberOfCollisions;
	}

	/**
	 * The main constructor
	 * 
	 * @param size    - the size of the table
	 * @param probing - the type of traversal
	 */
	@SuppressWarnings("unchecked")
	public MyHashTable(int size, Probing probing) {
		// Avoiding the quadratic probings discrepancy's when using values divisible by
		// 2
		if (probing instanceof QuadraticProbing && size % 2 == 0) {
			this.size = size + 1;
		}

		else {
			this.size = size;
		}

		// Initialazing the table;
		this.table = new HashItem[this.size];
		this.probing = probing;
		numberOfElements = 0;
		numberOfCollisions = 0;
	}

	public MyHashTable(int size) {
		this(size, new LinearProbing());
	}

	public MyHashTable() {
		this(DEFAULT_SIZE, new LinearProbing());
	}

	@Override
	public V put(K key, V value) {

		if (numberOfElements == size() || calculateFilling() >= 75) {
			reHash();
		}

		HashItem<K, V> newItem = new HashItem<>(key, value);
		int hash = newItem.hashCode();
		int index = hash % size;

		startProbing(index);

		return put(newItem, index);

	}

	private V put(HashItem<K, V> newItem) {

		int hash = newItem.hashCode();
		int index = hash % size;

		startProbing(index);

		return put(newItem, index);
	}

	private V put(HashItem<K, V> newItem, int index) {

		if (table[index] == null) {
			table[index] = newItem;
			numberOfElements++;
			return null;
		}

		else if (table[index].isDeleted()) {
			V toReturn = table[index].getValue();
			table[index] = newItem;
			numberOfElements++;
			return toReturn;
		}

		else if (table[index].getKey().equals(newItem.getKey())) {
			V toReturn = table[index].getValue();
			table[index] = newItem;
			return toReturn;
		}
		// When probing gets initialized it already has gone to the first possible
		// position;
		numberOfCollisions++;
		int num = probing.getNum();
		probing.nextNum();

		return put(newItem, num);

	}

	@SuppressWarnings("unchecked")
	private void reHash() {

		int oldSize = size();
		HashItem<K, V>[] oldTable = this.table;

		if (probing instanceof QuadraticProbing) {
			this.size = oldSize * 3;
		}

		else {
			// another Measure taken to avoid problems with quadratic probing.
			this.size = oldSize * 2;
		}
		// Reseting the statistic values of our table;
		this.table = new HashItem[this.size];
		numberOfElements = 0;
		numberOfCollisions = 0;

		for (int i = 0; i < oldSize; i++) {
			if (oldTable[i] != null) {
				put(oldTable[i]);
			}
		}

	}

	@Override
	public V get(K key) {

		HashItem<K, V> toBeGoten = new HashItem<>(key, null);
		int hashCode = toBeGoten.hashCode();
		int index = hashCode % size();

		startProbing(index);

		for (int i = 0; i < size(); i++) {

			if (table[index].getKey().equals(key)) {
				return table[index].getValue();

			}

			index = probing.getNum();
			probing.nextNum();

		}

		return null;
	}

	@Override
	public boolean contains(V value) {

		if (isEmpty()) {
			return false;
		}

		for (int i = 0; i < size(); i++) {
			if (table[i] != null) {
				if (value.equals(table[i].getValue())) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean containsKey(K key) {
		HashItem<K, V> toBeFound = new HashItem<>(key, null);
		int pos = toBeFound.hashCode() % size();
		startProbing(pos);

		for (int i = 0; i < size(); i++) {

			if (key.equals(table[pos].getKey())) {
				return true;
			}

			pos = probing.getNum();
			probing.nextNum();

		}

		return false;
	}

	@Override
	public boolean isEmpty() {
		return numberOfElements == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public HashTable<K, V> clear(HashTable<K, V> thisTable) {
		
		thisTable = new MyHashTable<K, V>(thisTable.size(), thisTable.getProbing());
	
		return thisTable;
	
	}

	@Override
	public boolean remove(K key) {
		
		if (containsKey(key) && !isEmpty()) {
		
			HashItem<K, V> newItem = new HashItem<>(key, null);
			int hashCode = newItem.hashCode();
			int pos = hashCode % size();
			startProbing(pos);
			
			for (int i = 0; i < size(); i++) {
		
				if (table[pos].getKey().equals(key)) {
				
					table[pos].delete();
					numberOfElements--;
					
					return true;
				}
				
				pos = probing.getNum();
				probing.nextNum();
			}

		}

		return false;
	}
	/**
	 * Initializes the probing giving it the values 
	 * @param pos
	 */
	private void startProbing(int pos) {
		
		if (this.probing instanceof LinearProbing) {
		
			this.probing = new LinearProbing(size, pos);
		} 
		
		else if (this.probing instanceof QuadraticProbing) {
		
			this.probing = new QuadraticProbing(size, pos);
		}

		probing.startProbing();

	}

	@Override
	public Probing getProbing() {
		
		return this.probing;
	
	}

}