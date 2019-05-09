package uebung3;

public class HashTable {

	private final static int DEFAULT_CAPACITY = 8; // Default capacity
	private int numberOfCollisions = 0; // statistics counter for collisions
	private int count; // Number of pairs
	private int size; // The size of the table
	private HashItem[] table; // The table itself
	private Probing kindOfProbing;
	

	// there are more private elements !!!

	public int getStat() { // get statistis data
		return numberOfCollisions + count + size;
	}

	// hash table with default size = 8, default probing linear
	public HashTable() {
		this(DEFAULT_CAPACITY, new LinearProbing());
	}

	// hash table with modifiable size;
	public HashTable(int size) {
		this(size, new LinearProbing());
	}

	public HashTable(int size, Probing p) {
		this.size = size;
		table = new HashItem[size];
		this.kindOfProbing = p;
	}

	public void clear() { // clear hash table
		this.table = new HashItem[this.size];
		count = 0;
		numberOfCollisions = 0;
	}

	public boolean isEmpty() {
		return (count == 0);
	}

	public void printHT() { // prints hash table values
		if (!isEmpty()) {
			for (int i = 0; i < table.length; i++) {
				if (table[i] != null) {
					System.out.println(i);
					System.out.println(table[i].getValue().toString());
				}
			}
		}
	}

	public int size() { // returns number of elements in hash table
		return count;
	}

	// only for reason of test to enforce rehashing
	public void reHash() {
		if (!isEmpty()) {
			int sizeOfNewHashtable = table.length * 2;
			HashItem[] oldTable = table;
			table = new HashItem[sizeOfNewHashtable];
			for (int i = 0; i < oldTable.length; i++) {
				put(oldTable[i].getKey(), oldTable[i].getValue());
			}
		}
	}

	// null if key isn't in hash table already, old value othetwise
	public Object put(Object key, Object value) {

		// if table is poluted, 70 % full or compleatly full
		if (count == size || (count / size) * 100  > 50) {
			int sizeOfNewHashtable = table.length * 2;
			HashItem[] oldTable = table;
			table = new HashItem[sizeOfNewHashtable];

			// reset of the statistical data
			numberOfCollisions = 0;
			this.size = sizeOfNewHashtable;
			this.count = 0;

			// Puts all elements acrding to the new order;
			for (int i = 0; i < oldTable.length; i++) {
				if (oldTable[i] != null) {
					putInternal(oldTable[i].getKey(), oldTable[i].getValue());
				}
			}
		}
		return putInternal(key, value);
		
	}

	private Object putInternal(Object key, Object value) {
		// Find start location;
		int hash = this.hashCode(key);
		Object returnObject = null;

		// if element null we place our Object here
		if (table[hash] == null) {
			table[hash] = new HashItem(key, value);
			count++;
			return returnObject;
		}
		
		// if object is to be deleted is found we delete and place our new node
		// here
		else if (table[hash].isDeleted()) {
			count++;
			table[hash] = new HashItem(key, value);
			return returnObject;
		}
		
		//else if value is to be overriden
		else if (table[hash].getKey().equals(key)) {
			returnObject = table[hash].getValue();
			table[hash] = new HashItem(key, value);
			return returnObject;
		}
		else {
			// Else we probe
			numberOfCollisions++;
			Probing probe;
			
			if (kindOfProbing instanceof QuadraticProbing) {
				probe = new QuadraticProbing(hash, table, key);
			}
			else {
				probe = new LinearProbing(hash, table, key);
			}

			probe.startProbing();
			// if probing has found us a spot with deletable Element
			if (table[probe.getNum()] == null) {
				// Else just incerease object number and store data
				count++;
				table[probe.getNum()] = new HashItem(key, value);
				return returnObject;
			}
			else if (table[probe.getNum()].isDeleted()) {
				table[probe.getNum()] = new HashItem(key, value);
				count++;
				return returnObject;
			}
			else if (table[probe.getNum()].getKey().equals(key)) {
				returnObject = table[probe.getNum()].getValue();
				table[probe.getNum()] = new HashItem(key, value);
				return returnObject;
			}
			return returnObject;
		}

	}

	public boolean remove(Object key) { // true, if key is in hash table, false
		if (!isEmpty()) { // otherwise
			for (int i = 0; i < table.length; i++) {
				if (table[i] != null && table[i].getKey().equals(key)) {
					// if key found set flag to deleted;
					table[i].setDeleted();
					count--;
					return true;
				}
			}
		}
		return false;
	}

	public Object get(Object key) { // returns value of (key, value)-pair, if in
		if (!isEmpty()) {
			for (int i = 0; i < table.length; i++) {
				if (table[i] != null && table[i].getKey().equals(key)) {
					return table[i].getValue();
				}
			}
		}
		return null;
	}

	public boolean containskey(Object key) { // true if key is in hash table
		boolean contains = false;
		if (!isEmpty()) {
			for (int i = 0; i < table.length; i++) {
				if (table[i] != null && table[i].getKey().equals(key)) {
					contains = true;
				}
			}
		}
		return contains;
	}

	public boolean contains(Object value) { // true if value is in hash table
		boolean contains = false;
		if (!isEmpty()) {
			for (int i = 0; i < table.length; i++) {
				if (table[i] != null && table[i].getValue().equals(value) && !table[i].isDeleted()) {
					contains = true;
				}
			}
		}
		return contains;
	}

	private int hashCode(Object key) {
		return Math.abs(((StringElement) key).hashCode()) % this.size;
	}

}
