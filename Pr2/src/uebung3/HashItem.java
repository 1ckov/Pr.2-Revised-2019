package uebung3;

public class HashItem {
	private Object key;
	private Object value;
	private boolean deleted;
	/**
	 * This hash item can be marked deletable and can store 1 value and 1 keys;
	 * @param key - The key to search with;
	 * @param value - The value to be stored alongside the key;
	 */
	public HashItem(Object  key, Object value) {
		this.key = key;
		this.value = value;
		this.deleted = false;
	}
	public void setDeleted(){
		this.deleted = true;
	}
	
	public boolean isDeleted(){
		return deleted;
	}

	public Object getKey() {
		return this.key;
	}

	public Object getValue() {
		return this.value;
	}
}
