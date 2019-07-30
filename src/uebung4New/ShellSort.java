package uebung4New;

public class ShellSort implements SortMethod {

	Sort insertionSort = new InsertionSort();


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void sort(Comparable[] toSort) {
		// we take an interval as big as possible
		int length = toSort.length;
		int gapSize = length / 3;
		
		//while our gap is bigger than 1 we continue shell sorting		
		while (gapSize > 1) {
		
			//we start at the first possible position we could swap;
			for (int i = gapSize; i < length; i++) {
				
				int j = i;
				
				//save out current Element
				Comparable currentElement = toSort[i];
				
				//while j - gap size is still a valid position and our current Element is still not at its place
				while ((j - gapSize) >= 0 && (currentElement.compareTo(toSort[j - gapSize]) > 0)) {
					
					toSort[j] = toSort[j - gapSize];
					//we check all positions by backtracking 
					j -= gapSize;
				
				}
				
				toSort[j] = currentElement;

			}
			
			gapSize = gapSize/3;
		}
		//the final part is the use of a simple insertion sort to put all elements into order
		insertionSort.sort(toSort);
		
	}

//		int inner, outer;
//
//		Comparable temp;
//
//		int interval = 1;
//
//		while (interval <= toCompare.length / 3) {
//			
//			interval = ((toCompare.length * 4) / 5) / interval;	
//		
//			while(interval > 0) {
//				
//				for(outer = interval; outer < toCompare.length; outer++) {
//					
//					temp = toCompare[outer];
//					
//					System.out.println("Store " + toCompare[outer] );
//					
//					inner = outer;
//					
//					while(inner > interval - 1 && toCompare[inner - interval].compareTo(temp) <= 0) {
//						
//						toCompare[inner] = toCompare[inner - interval];
//						
//						inner -= interval;
//						
//					}
//					
//					toCompare[inner] = temp;
//				}
//				
//				interval = (interval - 1) / 3;
//			}
//		}
//
//		return null;

	public void printArray(Comparable[] toPrint) {
		for (int i = 0; i < toPrint.length; i++) {
			System.out.print("| " + toPrint[i] + " ");
		}
		System.out.print("| \n");
	}

}
