package uebung5_RevisedOldUebung4;

public interface AVLTree <T>{

	/**
	 * Inserts a value into your AVLTree
	 * @param value a generic value placeholder
	 * @return True if success/False if not
	 */
	public void insert(T value);
	
	/**
	 * Removes the given value from the AVL Tree
	 * @param value a generic value placeholder
	 * @return True if value was removed/False if not
	 */
	public boolean remove(T value);
	
}
