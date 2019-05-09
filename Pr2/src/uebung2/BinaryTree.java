package uebung2;


public interface BinaryTree {
    
    /**
     * inserts value in tree.
     */
    boolean insert(Element value);
    
    /**
     * inserts  objects from file in tree
     **/
    boolean insert(String filename);
    
    /**
     * stores objects from tree in file
     **/
    boolean saveToFile(String filename);
    
    /**
     * checks if value is in tree
     **/
    boolean contains(Element value);
    
    /**
     * computes number of values in tree
     **/
    int size();
    
    /**
     * computes height of tree
     **/
    int height();
    
    /**
     * returns biggest value of tree
     **/
    Element getMax();
    
    /**
     * returns smallest value of tree
     **/
    Element getMin();
    
    /**
     * removes val from tree
     **/
    boolean remove(Element val);
    
    /**
     * checks if tree is empty
     **/
    boolean isEmpty();
    
    /**
     * removes all elements from tree
     **/
    void clear();
    
    /**
     * inserts all values from otherTree in tree
     **/
    BinaryTree addAll(BinaryTree otherTree);
    
    /**
     * prints tree in inorder
     **/
    void printInorder();
    
    /**
     * prints tree in postorder
     **/
    void printPostorder();
    
    /**
     * prints tree in preorder
     **/
    void printPreorder();
    
    /**
     * prints tree in levelorder
     **/
    void printLevelorder();
    
    /**
     * creates a deep copy of tree
     **/
    Object clone();
}