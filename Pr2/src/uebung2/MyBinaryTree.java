package uebung2;

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
	public int size() {
		if (isEmpty()) {
			return 0;
		}
		// recursion
		return 1 + size(root.left) + size(root.right);
	}

	public int size(Node parent) {
		// if current node null return 0
		if (parent == null) {
			return 0;
		}
		// if not go deeper
		return 1 + size(parent.left) + size(parent.right);

	}

	@Override
	public int height() {
		if (isEmpty()) {
			return 0;
		}
		else {
			int leftHeight = height(root.left);
			int rightHeight = height(root.right);
			if (leftHeight > rightHeight) {
				// if left branch is taller
				return (leftHeight + 1);
			}
			else {
				// if right branch is taller
				return (rightHeight + 1);
			}

		}
	}

	private int height(Node node) {
		if (node == null) {
			return 0;
		}
		else {
			int leftHeight = height(node.left);
			int rightHeight = height(node.right);
			if (leftHeight > rightHeight) {
				return (leftHeight + 1);
			}
			else {
				return (rightHeight + 1);
			}

		}
	}

	@Override
	public boolean remove(Element elem) throws PRException {
		if (isEmpty()) {
			return false;
		}
		else {
			if (root.validator(elem)) {
				// if the target node is found
				Element e;
				// Checks if elem is IntElement
				if (elem instanceof IntElement) {
					e = new IntElement(0);
				}
				// Checks if elem is StringElement
				else if (elem instanceof StringElement) {
					e = new StringElement("");
				}
				// Else throws exception
				else {
					throw new PRException("This is not a valid Element");
				}
				// When the searched for element is found
				if (root.getValue().getVal().compareTo(elem) == 0) {
					Node n = new Node(e);
					n.setLeft(root);
					boolean result = root.remove(elem, n);
					root = n.getLeft();
					return result;
				}
				// else recursion till its found
				else {
					return root.remove(elem, null);
				}
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	@Override
	public void printInorder() {
		if (!isEmpty()) {
			printInorder(root.left);
			root.getValue().print();
			printInorder(root.right);
		}

	}

	public void printInorder(Node node) {
		if (node != null) {
			printInorder(node.left);
			node.getValue().print();
			printInorder(node.right);
		}
	}

	@Override
	public void printPostorder() {
		if (!isEmpty()) {
			printPostorder(root.left);
			printPostorder(root.right);
			root.getValue().print();

		}

	}

	public void printPostorder(Node node) {
		if (node != null) {
			printPostorder(node.left);
			printPostorder(node.right);
			node.getValue().print();

		}
	}

	@Override
	public void printPreorder() {
		if (!isEmpty()) {
			root.getValue().print();
			printPreorder(root.left);
			printPreorder(root.right);
		}

	}

	private void printPreorder(Node node) {
		if (node != null) {
			node.getValue().print();
			printPreorder(node.left);
			printPreorder(node.right);

		}

	}

	@Override
	public void printLevelorder() {
		for (int level = 1; level <= height(root); level++) {
			printLevel(root, level);
		}

	}

	public void printLevel(Node node, int level) {
		if (node == null)
			return;
		if (level == 1)
			node.getValue().print();
		else if (level > 1) {
			printLevel(node.left, level - 1);
			printLevel(node.right, level - 1);
		}
	}

	@Override
	public BinaryTree clone() {
		MyBinaryTree newTree = new MyBinaryTree();
		// if the current tree is empty
		if (isEmpty()) {
			newTree.root = null;
			return newTree;
		}
		// Else use the Level order method to make a deep copy
		for (int level = 1; level <= height(root); level++) {
			clone(root, level, newTree);
		}
		return newTree;

	}

	private void clone(Node node, int level, BinaryTree newTree) {
		if (node == null)
			return;
		if (level == 1)
			newTree.insert(node.getValue());
		else if (level > 1) {
			clone(node.left, level - 1, newTree);
			clone(node.right, level - 1, newTree);
		}
	}

	@Override
	public void clear() {
		if (!isEmpty()) {
			root = null;
		}
	}

	@Override
	public boolean insert(Element value) {
		// Checks if root is null
		if (isEmpty()) {
			// if so sets new node as root
			root = new Node(value);
			return true;
		}
		else {
			if (root.validator(value)) {
				// else Recursion
				insert(root, value);
			}
		}
		return false;
	}

	public boolean insert(Node parent, Element elem) {
		if (parent.getValue().compareTo(elem) > -1) {
			if (parent.left == null) {
				// place as left child
				parent.left = new Node(elem);
				return true;
			}
			else {
				insert(parent.left, elem);
			}

		}
		else {
			if (parent.right == null) {
				// place as right child
				parent.right = new Node(elem);
				return true;
			}
			else {
				insert(parent.right, elem);
			}
		}

		return false;
	}

	@Override
	public boolean contains(Element elem) {
		if (isEmpty()) {
			return false;
		}
		// if value is at root
		if (root.validator(elem)) {
			if (root.getValue().compareTo(elem) == 0) {
				return true;
			}
			boolean contains = false;
			// checks left child
			if (root.left != null) {
				contains = contains(root.left, elem);
			}
			// checks right child
			if (!contains && root.right != null) {
				contains = contains(root.right, elem);
			}
			return contains;
		}
		return false;
	}

	public boolean contains(Node node, Element elem) {
		if (node.getValue().compareTo(elem) == 0) {
			return true;
		}
		boolean contains = false;

		if (node.left != null) {
			contains = contains(node.left, elem);
		}

		if (!contains && node.right != null) {
			contains = contains(node.right, elem);
		}

		return contains;
	}

	@Override
	public Element getMax() {
		if (root == null) {
			return null;
		}

		Node tmpNode = root;
		// get lowest most right node
		while (tmpNode.getRight() != null) {
			tmpNode = tmpNode.getRight();
		}
		return tmpNode.getValue();
	}

	@Override
	public boolean saveToFile(String filename) {
		return false;
	}

	@Override
	public Element getMin() {
		if (isEmpty()) {
			return null;
		}
		Node tmpNode = root;
		// get lowest most left node;
		while (tmpNode.getLeft() != null) {
			tmpNode = tmpNode.getLeft();

		}
		return tmpNode.getValue();
	}

	@Override
	public boolean insert(String filename) {
		// checks the type of the data
		int[] intsFromFile = null;
		String[] dataFromFile = readStringArray(filename);
		boolean isInt = true;
		for (int i = 0; i < dataFromFile.length; i++) {
			for (int j = 0; j < dataFromFile[i].length(); j++) {
				if ((dataFromFile[i].charAt(j) < '0') || (dataFromFile[i].charAt(j) > '9')) {
					isInt = false;
				}
			}
		}
		if (isInt) {
			intsFromFile = readIntegerArray(filename);
		}
		// if data type is string
		if (!this.isEmpty()) {
			if (root.getValue() instanceof StringElement) {
				if (!isInt) {
					for (String s : dataFromFile) {
						StringElement elem = new StringElement(s);
						this.insert(elem);
					}
					return true;
				}
			}
			else if (root.getValue() instanceof IntElement) {
				if (isInt) {
					for (int i : intsFromFile) {
						IntElement elem = new IntElement(i);
						this.insert(elem);
					}
					return true;
				}
			}
		}
		else {
			if (!isInt) {
				for (String s : dataFromFile) {
					StringElement elem = new StringElement(s);
					this.insert(elem);
				}
				return true;
			}
			else {
				for (int i : intsFromFile) {
					IntElement elem = new IntElement(i);
					this.insert(elem);
				}
				return true;
			}
		}
		return false;

	}

	@Override
	public BinaryTree addAll(BinaryTree otherTree) {
		if (root.validator(((MyBinaryTree) otherTree).root.getValue())) {
			if (!otherTree.isEmpty()) {
				for (int level = 1; level <= height(root); level++) {
					addAll(((MyBinaryTree) otherTree).root, level, this);
				}
			}
		}
		return this;
	}

	private void addAll(Node node, int level, BinaryTree thisTree) {
		if (node == null) {
			return;
		}
		if (level == 1) {
			this.insert(node.getValue());
		}
		else if (level > 1) {
			addAll(node.left, level - 1, thisTree);
			addAll(node.right, level - 1, thisTree);
		}
	}

}
