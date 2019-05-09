package uebung4;


public interface BTree {
	
	public boolean insert (Comparable obj);
	public boolean insert (String filename);
	boolean contains (Comparable obj);
	public void delete (Comparable obj);
	public int height ();
	public int size ();
	public Comparable getMax ();
	public Comparable getMin ();
	public boolean isEmpty ();
	public void addAll (BTreeImpl other);   // der Einfachheit halber
	
	public void printInOrder ();
	public void printPreOrder ();
	public void printPostOrder ();
	public void printLevelOrder ();

}
