package uebung3;

public class LinearProbing implements Probing {

	private int currentNumber;
	private HashItem[] table;
	private Object key;
	public LinearProbing (){
		
	}
	public LinearProbing(int currentNumber, HashItem[] table, Object key) {
		this.currentNumber = currentNumber;
		this.table = table;
		this.key = key;
	}

	@Override
	public int nextNum() {
		if (currentNumber == table.length - 1) {
			currentNumber = 0;
			return currentNumber;
		}
		return currentNumber++;
	}

	@Override
	public int getNum() {
		return currentNumber;
	}

	@Override
	public void startProbing() {
		if (table.length == 2) {
			if (currentNumber == 0) {
				currentNumber = 1;
			}
			else {
				currentNumber = 0;
			}
		}
		else {
			// Starts at the expected position for the key
			int i = currentNumber;
			// we initiate the loop
			
			boolean placeFound = false;
			this.nextNum();
			while (currentNumber != i && !placeFound) {
				// if position is empty, spot found
				if (table[currentNumber] == null) {
					placeFound = true;
				}
				// if object is to be deleted, spot foun
				else if (table[currentNumber].isDeleted()) {
					placeFound = true;
				}
				else if(table[currentNumber].getKey().equals(key)){
					placeFound = true;
				}
				if(placeFound == false){
					this.nextNum();	
				}
				
			}
		}
	}
}
