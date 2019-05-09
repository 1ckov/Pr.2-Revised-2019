package uebung1;
import static pr.MakeItSimple.*;

public class MyBinaryTree implements BinaryTree {
	Node root;

	public MyBinaryTree() {
		root = null;
	}

	static BinaryTree empty() {
		return new MyBinaryTree();
	}

	@Override
	public boolean insert(int value) {	
		//Checks if root is null
		if (isEmpty()){
			//if so sets new node as root
			root = new Node(value);
			return true;
		}
		else {
			//else Recursion
			insert(root, value);
		}
		return false;
	}

	public boolean insert(Node parent, int value) {
		//if new node smaller than current
		if (parent.value >= value) {
			if (parent.left == null) {
				//place as left child
				parent.left = new Node(value);
				return true;
			}
			else {
				insert(parent.left, value);
			}
		}
		//if new node bigger than current
		else if (parent.value < value) {
			if (parent.right == null) {
				//place as right child
				parent.right = new Node(value);
				return true;
			}
			else {
				insert(parent.right, value);
			}
		}
		return false;

	}
	//readIntegerArray(String filename)
	
	@Override
	public boolean insert(String filename) {
		int[] toBeInserted;
		//Uses a function to read the String into an array
		toBeInserted = readIntegerArray(filename);
		//Inserts all elements into our tree
		for (int i : toBeInserted) {
			this.insert(i);
		}
		return false;
	}

	@Override
	public boolean contains(int val) {
		if (isEmpty()){
			return false;
		}
		//if value is at root
		if (root.value == val) {
			return true;
		}
		boolean contains = false;
		//checks left child
		if (root.left != null) {
			contains = contains(root.left, val);
		}
		//checks right child
		if (!contains && root.right != null) {
			contains = contains(root.right, val);
		}
		return contains;
	}

	public boolean contains(Node node, int val) {
		//if found
		if (node.value == val) {
			return true;
		}
		boolean contains = false;
		
		if (node.left != null) {
			contains = contains(node.left, val);
		}
		
		if (!contains && node.right != null) {
			contains = contains(node.right, val);
		}

		return contains;

	}

	@Override
	public int size() {
		if (isEmpty()){
			return 0;
		}
		//recursion
		return 1 + size(root.left) + size(root.right);
	}

	public int size(Node parent) {
		//if current node null return 0
		if (parent == null) {
			return 0;
		}
		//if not go deeper
		return 1 + size(parent.left) + size(parent.right);

	}

	@Override
	public int height() {
		if (isEmpty()){
			return 0; 
		}
		else{
			int leftHeight = height(root.left);
			int rightHeight = height(root.right);
			if(leftHeight > rightHeight){
				//if left branch is taller
				return (leftHeight+1);
			}
			else{
				//if right branch is taller
				return (rightHeight+1);
			}
			
		}
	}

	private int height(Node node) {
		if(node == null){
			return 0; 
		}
		else{
			int leftHeight = height(node.left);
			int rightHeight = height(node.right);
			if(leftHeight > rightHeight){
				return (leftHeight+1);
			}
			else{
				return (rightHeight+1);
			}
			
		}
	}

	@Override
	public int getMax() {
		if (root == null) {
			return -1;
		}
		
		Node tmpNode = root;
		//get lowest most right node
		while (tmpNode.getRight() != null) {
			tmpNode = tmpNode.getRight();
		}
		return tmpNode.getValue();

	}

	@Override
	public int getMin() {
		if (isEmpty()){
			return -1;
		}
		Node tmpNode = root;
		//get lowest most left node;
		while (tmpNode.getLeft() != null) {
			tmpNode = tmpNode.getLeft();

		}
		return tmpNode.getValue();
	}

	@Override
	public boolean remove(int val) {
		if (isEmpty()){
			return false;
		}
		else{
			//if the target node is found
			if(root.getValue() == val){
				Node n = new Node(0);
				n.setLeft(root);
				boolean result = n.remove(val, n);
				root = n.getLeft();
				return result;
			}
			else{
				return root.remove(val, null);
			}
		}
	}
	public boolean find(int val){
		if(isEmpty()){
			return false;
		}
		else{
			return root.search(val);
		}
	}
	

	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	@Override
	public void addAll(BinaryTree otherTree) {
		
	}

	@Override
	public void printInorder() {
		if (!isEmpty()){
			printInorder(root.left);
			println(root.value);
			printInorder(root.right);
		}

	}

	public void printInorder(Node node) {
		if(node != null){
			printInorder(node.left);
			println(node.value);
			printInorder(node.right);
		}
	}

	@Override
	public void printPostorder() {
		if (!isEmpty()){
			printPostorder(root.left);
			printPostorder(root.right);
			println(root.value);
			
		}

	}
	
	public void printPostorder(Node node) {
		if(node != null){
			printPostorder(node.left);
			printPostorder(node.right);
			println(node.value);
			
		}
	}

	@Override
	public void printPreorder() {
		if (!isEmpty()){
			println(root.value);
			printPreorder(root.left);
			printPreorder(root.right);
		}

	}

	private void printPreorder(Node node) {
		if(node != null){
			println(node.value);
			printPreorder(node.left);
			printPreorder(node.right);
			
			
		}
		
	}

	@Override
	public void printLevelorder() {
		for(int level = 1; level <= height(root); level++){
			printLevel(root, level);
		}

	}
	
	 public void printLevel (Node node ,int level)
	    {
	        if (node == null)
	            return;
	        if (level == 1)
	            println(node.value);
	        else if (level > 1)
	        {
	            printLevel(node.left, level-1);
	            printLevel(node.right, level-1);
	        }
	    }

	@Override
	public BinaryTree clone() {
		MyBinaryTree newTree = new MyBinaryTree();
		if (isEmpty()){
			newTree.root = null;
			return newTree;
		}
		for(int level = 1; level <=height(root);level++){
			clone(root,level,newTree);
		}
		return newTree;

		
	}

	
	private void clone(Node node, int level, BinaryTree newTree) {
		if (node == null)
            return;
        if (level == 1)
            newTree.insert(node.value);
        else if (level > 1)
        {
            clone(node.left, level-1, newTree);
            clone(node.right, level-1, newTree);
        }
	}

	//saveIntegerArray(filename)
	@Override
	public void saveToFile(String filename) {
		
	}
	
	@Override
	public void clear(){
		if(!isEmpty()){
			root = null;
		}
	}
	

	
}
