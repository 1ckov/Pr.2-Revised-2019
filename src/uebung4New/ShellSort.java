package uebung4New;

public class ShellSort implements Sort {

	private int size;
	Sort insertionSort = new InsertionSort();

	public ShellSort(int size) {
		this.size = size;
	}

	@Override
	public Comparable[] sort(Comparable[] toCompare) {

		int inner, outer;

		Comparable temp;

		int interval = 1;

		while (interval <= toCompare.length / 3) {
			
			interval = ((toCompare.length * 4) / 5) / interval;	
		
			while(interval > 0) {
				
				for(outer = interval; outer < toCompare.length; outer++) {
					
					temp = toCompare[outer];
					
					System.out.println("Store " + toCompare[outer] );
					
					inner = outer;
					
					while(inner > interval - 1 && toCompare[inner - interval].compareTo(temp) <= 0) {
						
						toCompare[inner] = toCompare[inner - interval];
						
						inner -= interval;
						
					}
					
					toCompare[inner] = temp;
				}
				
				interval = (interval - 1) / 3;
			}
		}

		return null;
	}

	public void printArray(Comparable[] toPrint) {
		for (int i = 0; i < toPrint.length; i++) {
			System.out.print("| " + toPrint[i] + " ");
		}
		System.out.print("| \n");
	}

	@Override
	public void swapValue(int positionA, int positionB, Comparable[] array) {
		// TODO Auto-generated method stub

	}

}
