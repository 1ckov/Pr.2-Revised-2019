package uebung3;

public class QuadraticProbing implements Probing {
	private int position;
	private int currentSign = 1;
	private int currentNumber = 0;
	private int counter = 1;
	private HashItem[] table;
	private Object key;
	
	public QuadraticProbing(){
		
	}

	public QuadraticProbing(int position, HashItem[] table, Object key) {
		this.position = position;
		this.currentSign = 1;
		this.table = table;
		this.key = key;
	}

	@Override
	public int nextNum() {
		if (currentSign > 0) {
			int result = currentSign * ((int) Math.pow(counter, 2));
			currentNumber = result;
			currentSign = -1;
			return result;
		} else {
			int result = currentSign * ((int) Math.pow(counter, 2));
			currentSign = 1;
			currentNumber = result;
			counter++;
			return result;

		}
	}

	@Override
	public int getNum() {
		int num = position + currentNumber % table.length;
		if(num < 0){
			return 0;
		}
		else return num;
	}

	@Override
	public void startProbing() {

		// Starts at the expected position for the key
		
		// we initiate the loot

		boolean placeFound = false;
		while (!placeFound) {
			// if position is empty, spot found
			if (table[getNum()] == null) {
				placeFound = true;
			}
			// if object is to be deleted, spot foun
			else if (table[getNum()].isDeleted()) {
				placeFound = true;
			} else if (table[getNum()].getKey().equals(key)) {
				placeFound = true;
			}
			if (placeFound == false) {
				this.nextNum();
			}

		}
	}

}
