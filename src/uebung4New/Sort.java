package uebung4New;

public interface Sort {

	public Comparable[] sort(Comparable[] toCompare);
	
	public void printArray(Comparable[] toPrint);
	
	public void swapValue(int positionA, int positionB, Comparable[] array);
	
}
