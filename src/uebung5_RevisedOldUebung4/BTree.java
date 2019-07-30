package uebung5_RevisedOldUebung4;


public interface BTree<E extends Comparable<E>> {
	
	public boolean insert (E obj);
	public boolean insert (String filename);
	boolean contains (E obj);
	public void delete (E obj);
	public int height ();
	public int size ();
	public E getMax ();
	public E getMin ();
	public boolean isEmpty ();
	public void addAll (BTree<E> other);	
	public void printInOrder ();
	public void printPreOrder ();
	public void printPostOrder ();
	public void printLevelOrder ();

}
