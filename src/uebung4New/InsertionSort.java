package uebung4New;

public class InsertionSort implements Sort{

	@Override
	public Comparable[] sort(Comparable[] toSort) {
		for(int i = 1; i < toSort.length; i++) {
			int j = i;
			Comparable toInsert = toSort[i];
			
			while((j > 0) && toSort[j-1].compareTo(toInsert) < 0 ) {
				toSort[j] = toSort[j-1];
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
		
	}
	
	

}
