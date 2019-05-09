package uebung1Revised;

import java.io.IOException;

public interface BinaryTree<T> {
	/**
	 * Inserts a value into MyBinaryTree.
	 * @param value - 
	 * @return
	 */
	public boolean insert(T value);		//Fugt val in den Baum ein.
	/**
	 * Insert values from a file into MyBinaryTree.
	 * @param filename
	 * @return
	 */
	public boolean insert (String filename);		//Fugt die int-Werte, die in der Datei stehen in den Baum ein.
	/** */
	boolean contains(T value);		//Testet, ob val im Baum vorhanden ist.
	/** */
	int size();		//Ermittelt die Anzahl der Knoten im Baum.
	/** */
	int height();		//Ermittelt die H¨ohe des Baums.
	/** */
	int getMax();		//Liefert das gr¨oßte Element im Baum.
	/** */
	int getMin();		//Liefert das kleinste Element im Baum
	/** */
	boolean remove (T value);		//Entfernt val aus dem Baum.
	/** */
	boolean isEmpty();		//true genau dann, wenn der Baum leer ist.
	/** */
	void addAll(BinaryTree<T> otherTree);		//Fugt alle Elemente des ¨ ubergebenen Baums ( ¨ otherTree) in
											//den aktuellen Baum ein.
	/** */
	void printInorder();		//Gibt Baum in Inorder aus.
	/** */
	void printPostorder();		//Gibt Baum in Postorder aus.
	/** */
	void printPreorder();		//Gibt Baum in Preorder aus.
	/** */
	void printLevelorder();		//Gibt Baum in Levelorder aus
	/** */
	BinaryTree<T> clone();		//Erzeugt eine tiefe Kopie des Baums.
	/**
	 * @throws IOException  */
	void saveToFile (String filename) throws IOException; //Speichert die int-Werte des Baums in der Datei
	/** */
	void clear();
	
}
