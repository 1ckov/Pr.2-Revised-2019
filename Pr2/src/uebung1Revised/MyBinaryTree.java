package uebung1Revised;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyBinaryTree<T extends Number> implements BinaryTree<T>, Serializable {

	private Node<T> root = null;

	public List<T> asList() {
		List<T> valuesAsList = new ArrayList<>();
		if (!this.isEmpty()) {
			for (int level = 1; level <= height(); level++) {
				asList(root, valuesAsList, level);
			}
		}
		return valuesAsList;
	}

	private void asList(Node<T> currentNode, List<T> valuesAsList, int currentLevel) {
		if (currentNode != null) {
			if (currentLevel == 1) {
				valuesAsList.add(currentNode.getValue());
			} else if (currentLevel > 1) {
				asList(currentNode.getLeftChildNode(), valuesAsList, currentLevel - 1);
				asList(currentNode.getRightChildNode(), valuesAsList, currentLevel - 1);
			}
		}
		return;
	}

	@Override
	public boolean insert(final T nodeValue) {
		if (nodeValue != null) {
			// First Case where tree Empty
			if (isEmpty()) {
				// Create a Node and use root to Point to it
				root = new Node<>(nodeValue, null, null);
				return true;
			}
			// Else
			return insert(root, nodeValue);

		}
		return false;
	}

	private boolean insert(final Node<T> currentNode, final T nodeValue) {
		// Second Case our value goes to the left
		if (currentNode.compareTo(nodeValue) >= 0) {
			// If left Child is empty we put our new Node here
			if (currentNode.getLeftChildNode() == null) {
				currentNode.setLeftChildNode(new Node<T>(nodeValue, null, null));
				return true;
			}
			// Else we go deeper
			return insert(currentNode.getLeftChildNode(), nodeValue);

		}
		// Third Case our value goes to the right
		else if (currentNode.compareTo(nodeValue) < 0) {
			// if right Child is empty we put our new Node here
			if (currentNode.getRightChildNode() == null) {
				currentNode.setRight(new Node<T>(nodeValue, null, null));
				return true;
			}
			// Else we go deeper
			return insert(currentNode.getRightChildNode(), nodeValue);
		}
		return false;
	}

	@Override
	public boolean insert(String filename) {
		return false;
	}

	@Override
	public boolean contains(final T valueToBeFound) {
		if (!isEmpty()) {
			// if the Tree isn't empty we check if our object is at root
			if (root.compareTo(valueToBeFound) == 0) {
				return true;

			} else
				// if not we go into recursion
				return contains(root, valueToBeFound);

		}
		return false;
	}

	private boolean contains(final Node<T> current, final T valueToBeFound) {
		// If our value gets found
		if (current.compareTo(valueToBeFound) == 0) {
			return true;
		}

		// else we might go left
		else if (current.compareTo(valueToBeFound) >= 0) {
			if (current.getLeftChildNode() != null) {
				return contains(current.getLeftChildNode(), valueToBeFound);
			}
		}

		// or right
		else if (current.compareTo(valueToBeFound) < 0) {
			if (current.getRightChildNode() != null) {
				return contains(current.getRightChildNode(), valueToBeFound);
			}
		}

		return false;
	}

	@Override
	public int size() {
		if (!isEmpty()) {
			return size(root);
		}
		return 0;
	}

	private int size(final Node<T> currentNode) {
		if (currentNode != null) {
			return size(currentNode.getLeftChildNode()) + size(currentNode.getRightChildNode()) + 1;
		}
		return 0;
	}

	@Override
	public int height() {
		if (!isEmpty()) {
			return height(root);
		}
		return 0;
	}

	private int height(final Node<T> currentNode) {
		if (currentNode != null) {
			// We take a measurement of each height
			int heightOfLeftNodeSubTree = height(currentNode.getLeftChildNode());
			int heightOfRightNodeSubTree = height(currentNode.getRightChildNode());
			// Compare
			if (heightOfLeftNodeSubTree > heightOfRightNodeSubTree) {
				// and return the bigger one + 1 for current Node
				return heightOfLeftNodeSubTree + 1;
			} else {
				// same here
				return heightOfRightNodeSubTree + 1;
			}
		}
		return 0;
	}

	@Override
	public int getMax() {
		if (!isEmpty()) {
			return getMax(root);
		}
		return 0;
	}

	private int getMax(final Node<T> currentNode) {

		if (currentNode.getRightChildNode() == null) {
			// Return our current element if its the most right
			return currentNode.getValue().intValue();
		}

		else {
			// if there is more go deeper
			return getMax(currentNode.getRightChildNode());
		}
	}

	@Override
	public int getMin() {
		if (isEmpty()) {
			return getMin(root);
		}
		return 0;
	}

	private int getMin(final Node<T> parentNode) {
		if (parentNode.getLeftChildNode() == null) {
			// Return the left most element
			return parentNode.getValue().intValue();
		} else {
			// if there is more go deeper
			return getMin(parentNode.getLeftChildNode());
		}

	}

	@Override
	public boolean remove(final T value) {
		if (isEmpty() || !contains(value)) {
			return false;
		} else if (root.compareTo(value) == 0 && size() == 1) {
			root = null;
			return true;
		} else if (root.compareTo(value) == 0 && size() > 1) {
			Node<T> temp = new Node<>();
			temp.setLeftChildNode(root);
			boolean result = root.remove(value, temp);
			root = temp.getLeftChildNode();
			return result;
		} else if (root.compareTo(value) != 0) {
			return root.remove(value, null);
		}

		return false;
	}

	@Override
	public boolean isEmpty() {
		// If our root == null we are dealing with an empty tree;
		return root == null;
	}

	@Override
	public void addAll(BinaryTree<T> otherTree) {
		if (!otherTree.isEmpty()) {
			List<T> valueList = ((MyBinaryTree<T>) otherTree).asList();
			for (T element : valueList) {
				this.insert(element);
			}
		}
	}

	@Override
	public void printInorder() {
		printInorder(root.getLeftChildNode());
		System.out.print(root.getValue());
		printInorder(root.getRightChildNode());
		System.out.println();
	}

	private void printInorder(Node<T> currentNode) {
		if (currentNode == null) {
			return;
		}
		printInorder(currentNode.getLeftChildNode());
		System.out.print(currentNode.getValue() + " ");
		printInorder(currentNode.getRightChildNode());
	}

	@Override
	public void printPostorder() {
		printPostorder(root.getLeftChildNode());
		printPostorder(root.getRightChildNode());
		System.out.print(root.getValue());
		System.out.println();

	}

	private void printPostorder(Node<T> currentNode) {
		if (currentNode == null) {
			return;
		}
		printPostorder(currentNode.getLeftChildNode());
		printPostorder(currentNode.getRightChildNode());
		System.out.print(currentNode.getValue() + " ");
	}

	@Override
	public void printPreorder() {

		System.out.print(root.getValue());
		printPreorder(root.getLeftChildNode());
		printPreorder(root.getRightChildNode());
		System.out.println();

	}

	private void printPreorder(Node<T> currentNode) {
		if (currentNode == null) {
			return;
		}
		System.out.print(currentNode.getValue() + " ");
		printPreorder(currentNode.getLeftChildNode());
		printPreorder(currentNode.getRightChildNode());

	}

	@Override
	public void printLevelorder() {
		for (int level = 1; level <= height(); level++) {
			printLevelorder(root, level);
			System.out.println("");
		}
		System.out.println("");
	}

	private void printLevelorder(Node<T> currentNode, int currentLevel) {
		if (currentNode != null) {
			if (currentLevel == 1) {
				System.out.print(currentNode.getValue() + " ");
			} else if (currentLevel > 1) {
				printLevelorder(currentNode.getLeftChildNode(), currentLevel - 1);
				printLevelorder(currentNode.getRightChildNode(), currentLevel - 1);
			}
		}
	}

	@Override
	public BinaryTree<T> clone() {
		BinaryTree<T> cloneTree = new MyBinaryTree<>();
		cloneTree.addAll(this);
		return cloneTree;
	}

	@Override
	public void saveToFile(String filename) throws IOException {
		List<T> valuesAsList = this.asList();
		File toBeSaved = new File(filename);
		DataOutputStream os = null;

		if (!toBeSaved.exists()) {
			try {
				toBeSaved.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(toBeSaved)));
			for (T valuesAsNumber : valuesAsList) {
				os.writeInt(valuesAsNumber.intValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			os.close();
			System.out.println("Saved");
		}

	}

	public void saveAsObject(String filename) {
		File toBeSaved = new File(filename);
		if (!toBeSaved.exists()) {
			try {
				toBeSaved.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream fs = new FileOutputStream(toBeSaved);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(this);
			os.close();
			fs.close();
			System.out.println("The object has been saved");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clear() {
		this.root = null;

	}

}
