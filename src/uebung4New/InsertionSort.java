package uebung4New;

public class InsertionSort implements Sort{

	@Override
	public Comparable[] sort(Comparable[] toSort) {
		// start at position 1
		for(int i = 1; i < toSort.length; i++) {
			int j = i;
			// we save our current Element
			Comparable toInsert = toSort[i];
			//if we find a position fitting our current element or end up at the beggining we swap
			while((j > 0) && toSort[j-1].compareTo(toInsert) >= 0 ) {
				swapValue(j,j-1,toSort);
				j--;
				
			}
			
			toSort[j] = toInsert;
		}
		
		return toSort;
	}

	@Override
	public void printArray(Comparable[] toPrint) {
		for(int i = 0; i < toPrint.length; i++) {
			System.out.print("| " + toPrint[i] + " ");
		}
		System.out.print("| \n");
	}

	@Override
	public void swapValue(int positionA, int positionB, Comparable[] array) {
		array[positionA] = array[positionB];
	}
	
	

}
