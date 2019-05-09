package uebung4;

import static pr.MakeItSimple.readIntegerArray;

import uebung2.IntElement;
import uebung2.MyBinaryTree;

public class BTreeImpl implements BTree {

	private int minKeySize = 1;
	private int minChildrenSize = minKeySize + 1;
	private int maxKeySize = 2 * minKeySize;
	private int maxChildrenSize = maxKeySize + 1;

	private BNode root = null;
	private int size = 0;

	// deafult 1 order BTree
	public BTreeImpl() {
	}

	public BTreeImpl(int order) {
		this.minKeySize = order;
		this.minChildrenSize = minKeySize + 1;
		this.maxKeySize = 2 * minKeySize;
		this.maxChildrenSize = maxKeySize + 1;
	}

	public boolean insert(Comparable value) {
		if (isEmpty()) {
			root = new BNode(null, maxKeySize, maxChildrenSize);
			root.addKey(value);
		} else {
			BNode node = root;
			while (node != null) {
				//if no children
				if (node.numberOfChildren() == 0) {
					node.addKey(value);
					//if enough space for key
					if (node.numberOfKeys() <= maxKeySize) {
						break;
					}
					// else need to split up
					split(node);
					break;
				}
				
				//if children exist, search for right node to insert
				// Lesser or equal
				Comparable lesser = node.getKey(0);
				if (value.compareTo(lesser) <= 0) {
					node = node.getChild(0);
					continue;
				}

				// Greater
				int numberOfKeys = node.numberOfKeys();
				int last = numberOfKeys - 1;
				Comparable greater = node.getKey(last);
				if (value.compareTo(greater) > 0) {
					node = node.getChild(numberOfKeys);
					continue;
				}

				// Search internal nodes
				for (int i = 1; i < node.numberOfKeys(); i++) {
					Comparable prev = node.getKey(i - 1);
					Comparable next = node.getKey(i);
					if (value.compareTo(prev) > 0 && value.compareTo(next) <= 0) {
						node = node.getChild(i);
						break;
					}
				}
			}
		}

		size++;

		return true;
	}


	public void split(BNode nodeToSplit) {
		BNode node = nodeToSplit;
		int numberOfKeys = node.numberOfKeys();
		int medianIndex = numberOfKeys / 2;
		Comparable medianValue = node.getKey(medianIndex);

		BNode left = new BNode(null, maxKeySize, maxChildrenSize);
		for (int i = 0; i < medianIndex; i++) {
			left.addKey(node.getKey(i));
		}
		if (node.numberOfChildren() > 0) {
			for (int j = 0; j <= medianIndex; j++) {
				BNode c = node.getChild(j);
				left.addChild(c);
			}
		}

		BNode right = new BNode(null, maxKeySize, maxChildrenSize);
		for (int i = medianIndex + 1; i < numberOfKeys; i++) {
			right.addKey(node.getKey(i));
		}
		if (node.numberOfChildren() > 0) {
			for (int j = medianIndex + 1; j < node.numberOfChildren(); j++) {
				BNode c = node.getChild(j);
				right.addChild(c);
			}
		}

		if (node.parent == null) {
			// new root, height of tree is increased
			BNode newRoot = new BNode(null, maxKeySize, maxChildrenSize);
			newRoot.addKey(medianValue);
			node.parent = newRoot;
			root = newRoot;
			node = root;
			node.addChild(left);
			node.addChild(right);
		} else {
			// Move the median value up to the parent
			BNode parent = node.parent;
			parent.addKey(medianValue);
			parent.removeChild(node);
			parent.addChild(left);
			parent.addChild(right);

			if (parent.numberOfKeys() > maxKeySize)
				split(parent);
		}
	}


	public Comparable removeGreatestValue(BNode node) {
		Comparable value = null;
		if (node.numberOfKeys() > 0) {
			value = node.removeKey(node.numberOfKeys() - 1);
		}
		return value;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	public boolean contains(Comparable value) {
		BNode node = getNode(value);
		return (node != null);
	}


	public BNode getNode(Comparable value) {
		BNode node = root;
		while (node != null) {
			Comparable lesser = node.getKey(0);
			if (value.compareTo(lesser) < 0) {
				if (node.numberOfChildren() > 0)
					node = node.getChild(0);
				else
					node = null;
				continue;
			}

			int numberOfKeys = node.numberOfKeys();
			int last = numberOfKeys - 1;
			Comparable greater = node.getKey(last);
			if (value.compareTo(greater) > 0) {
				if (node.numberOfChildren() > numberOfKeys)
					node = node.getChild(numberOfKeys);
				else
					node = null;
				continue;
			}

			for (int i = 0; i < numberOfKeys; i++) {
				Comparable currentValue = node.getKey(i);
				if (currentValue.compareTo(value) == 0) {
					return node;
				}

				int next = i + 1;
				if (next <= last) {
					Comparable nextValue = node.getKey(next);
					if (currentValue.compareTo(value) < 0 && nextValue.compareTo(value) > 0) {
						if (next < node.numberOfChildren()) {
							node = node.getChild(next);
							break;
						}
						return null;
					}
				}
			}
		}
		return null;
	}



	public boolean combine(BNode node) {
		BNode parent = node.parent;
		int index = parent.indexOf(node);
		int indexOfLeftNeighbor = index - 1;
		int indexOfRightNeighbor = index + 1;

		BNode rightNeighbor = null;
		int rightNeighborSize = -minChildrenSize;
		if (indexOfRightNeighbor < parent.numberOfChildren()) {
			rightNeighbor = parent.getChild(indexOfRightNeighbor);
			rightNeighborSize = rightNeighbor.numberOfKeys();
		}

		// Try to borrow neighbor
		if (rightNeighbor != null && rightNeighborSize > minKeySize) {
			// Try to borrow from right neighbor
			Comparable removeValue = rightNeighbor.getKey(0);
			int prev = getIndexOfPreviousValue(parent, removeValue);
			Comparable parentValue = parent.removeKey(prev);
			Comparable neighborValue = rightNeighbor.removeKey(0);
			node.addKey(parentValue);
			parent.addKey(neighborValue);
			if (rightNeighbor.numberOfChildren() > 0) {
				node.addChild(rightNeighbor.removeChild(0));
			}
		} else {
			BNode leftNeighbor = null;
			int leftNeighborSize = -minChildrenSize;
			if (indexOfLeftNeighbor >= 0) {
				leftNeighbor = parent.getChild(indexOfLeftNeighbor);
				leftNeighborSize = leftNeighbor.numberOfKeys();
			}

			if (leftNeighbor != null && leftNeighborSize > minKeySize) {
				// Try to borrow from left neighbor
				Comparable removeValue = leftNeighbor.getKey(leftNeighbor.numberOfKeys() - 1);
				int prev = getIndexOfNextValue(parent, removeValue);
				Comparable parentValue = parent.removeKey(prev);
				Comparable neighborValue = leftNeighbor.removeKey(leftNeighbor.numberOfKeys() - 1);
				node.addKey(parentValue);
				parent.addKey(neighborValue);
				if (leftNeighbor.numberOfChildren() > 0) {
					node.addChild(leftNeighbor.removeChild(leftNeighbor.numberOfChildren() - 1));
				}
			} else if (rightNeighbor != null && parent.numberOfKeys() > 0) {
				// Cant borrow from neighbors, try to combined with right neighbor
				Comparable removeValue = rightNeighbor.getKey(0);
				int prev = getIndexOfPreviousValue(parent, removeValue);
				Comparable parentValue = parent.removeKey(prev);
				parent.removeChild(rightNeighbor);
				node.addKey(parentValue);
				for (int i = 0; i < rightNeighbor.getKeysSize(); i++) {
					Comparable v = rightNeighbor.getKey(i);
					node.addKey(v);
				}
				for (int i = 0; i < rightNeighbor.getChildrenSize(); i++) {
					BNode c = rightNeighbor.getChild(i);
					node.addChild(c);
				}

				if (parent.parent != null && parent.numberOfKeys() < minKeySize) {
					// removing key made parent too small, combined up tree
					this.combine(parent);
				} else if (parent.numberOfKeys() == 0) {
					// parent no longer has keys, make this node the new root which decreases the height of the tree
					node.parent = null;
					root = node;
				}
			} else if (leftNeighbor != null && parent.numberOfKeys() > 0) {
				// Can't borrow from neighbors, try to combined with left
				// neighbor
				Comparable removeValue = leftNeighbor.getKey(leftNeighbor.numberOfKeys() - 1);
				int prev = getIndexOfNextValue(parent, removeValue);
				Comparable parentValue = parent.removeKey(prev);
				parent.removeChild(leftNeighbor);
				node.addKey(parentValue);
				for (int i = 0; i < leftNeighbor.getKeysSize(); i++) {
					Comparable v = leftNeighbor.getKey(i);
					node.addKey(v);
				}
				for (int i = 0; i < leftNeighbor.getChildrenSize(); i++) {
					BNode c = leftNeighbor.getChild(i);
					node.addChild(c);
				}

				if (parent.parent != null && parent.numberOfKeys() < minKeySize) {
					// removing key made parent too small, combined up tree
					this.combine(parent);
				} else if (parent.numberOfKeys() == 0) {
					// parent no longer has keys, make this node the new root
					// which decreases the height of the tree
					node.parent = null;
					root = node;
				}
			}
		}

		return true;
	}


	public int getIndexOfPreviousValue(BNode node, Comparable value) {
		for (int i = 1; i < node.numberOfKeys(); i++) {
			Comparable t = node.getKey(i);
			if (t.compareTo(value) >= 0)
				return i - 1;
		}
		return node.numberOfKeys() - 1;
	}


	public int getIndexOfNextValue(BNode node, Comparable value) {
		for (int i = 0; i < node.numberOfKeys(); i++) {
			Comparable t = node.getKey(i);
			if (t.compareTo(value) >= 0)
				return i;
		}
		return node.numberOfKeys() - 1;
	}

	public int size() {
		return size;
	}



	@Override
	public boolean insert(String filename) {

		int[] intsFromFile = readIntegerArray(filename);

		for (int i : intsFromFile) {
			Comparable value = new Integer(i);
			insert(value);

		}
		return true;

	}

	@Override
	public void delete(Comparable obj) {
		BNode node = this.getNode(obj);

		if (node != null) {

			int index = node.indexOf(obj);
			node.removeKey(obj);
			if (node.numberOfChildren() == 0) {
				// leaf node
				if (node.parent != null && node.numberOfKeys() < minKeySize) {
					this.combine(node);
				} else if (node.parent == null && node.numberOfKeys() == 0) {
					// Removing root node with no keys or children
					root = null;
				}
			} else {
				// internal node
				BNode lesser = node.getChild(index);
				BNode greatest = this.getGreatestNode(lesser);
				Comparable replaceValue = this.removeGreatestValue(greatest);
				node.addKey(replaceValue);
				if (greatest.parent != null && greatest.numberOfKeys() < minKeySize) {
					this.combine(greatest);
				}
				if (greatest.numberOfChildren() > maxChildrenSize) {
					this.split(greatest);
				}
			}

			size--;
		}

	}

	@Override
	public int height() {
		if (isEmpty()) {
			return 0;
		}
		else{
			int height = height(root.getChild(0))+1;
			return height;
		}

	}
	public int height(BNode node){
		if(node==null){
			return 0;
		}
		else{
			int height = height(node.getChild(0));
			return height + 1;
		}
		
	}

	
	public BNode getGreatestNode(BNode nodeToGet) {
		BNode node = nodeToGet;
		while (node.numberOfChildren() > 0) {
			node = node.getChild(node.numberOfChildren() - 1);
		}
		return node;
	}
	@Override
	public Comparable getMax() {
		if(isEmpty()){
			return null;
		}
		Comparable max = root.getKey(root.getKeysSize() -1);
		BNode node = root;
		while(node.numberOfChildren() > 0){
			if(node.getChild(node.getChildrenSize() -1) != null){
			node = node.getChild(node.getChildrenSize() -1);

			if(node.getKey(node.getKeysSize() -1) != null){
				
			max = node.getKey(node.getKeysSize() - 1);
			}
			}
			else{
				break;
			}
		}
		return max;
		
	}

	@Override
	public Comparable getMin() {
		if(isEmpty()){
			return null;
		}
		Comparable min = root.getKey(root.getKeysSize() -1);
		BNode node = root;
		while(node.numberOfChildren() > 0){
			if(node.getChild(0) != null){
			node = node.getChild(0);

			if(node.getKey(0) != null){
				
			min = node.getKey(0);
			}
			}
			else{
				break;
			}
		}
		return min;
	}

	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	@Override
	public void printPostOrder() {
		if (!isEmpty()) {
			for (int i = 0; i < root.getChildrenSize(); i++) {
				if (root.getChild(i) != null) {

					printPostOrder(root.getChild(i));
				}

			}
			for (int j = 0; j < root.getKeysSize(); j++) {
				System.out.print(root.getKey(j));
			}

		}

	}

	public void printPostOrder(BNode child) {
		if (child != null) {
			for (int i = 0; i < child.getChildrenSize(); i++) {
				if (child.getChild(i) != null) {

					printPostOrder(child.getChild(i));
				}
			}
			for (int j = 0; j < child.getKeysSize(); j++) {
				System.out.print(child.getKey(j));
			}
		}

	}

	@Override
	public void printPreOrder() {
		if (!isEmpty()) {
			for (int j = 0; j < root.getKeysSize(); j++) {
				System.out.print(root.getKey(j));
			}
			for (int i = 0; i < root.getChildrenSize(); i++) {

				if (root.getChild(i) != null) {

					printPreOrder(root.getChild(i));
				}
			}
		}

	}

	private void printPreOrder(BNode child) {
		if (child != null) {
			for (int j = 0; j < child.getKeysSize(); j++) {
				System.out.print(child.getKey(j));
			}
			for (int i = 0; i < child.getChildrenSize(); i++) {
				if (child.getChild(i) != null) {

					printPreOrder(child.getChild(i));
				}
			}

		}

	}

	@Override
	public void printInOrder() {
		if (!isEmpty()) {
			for (int i = 0; i < root.getChildrenSize() - 1; i++) {
				if (root.getChild(i) != null) {
					printInOrder(root.getChild(i));
				}
			}
			for (int j = 0; j < root.getKeysSize(); j++) {
				System.out.print(root.getKey(j));
			}
			if (root.getChild(root.getChildrenSize() - 1) != null) {
				printInOrder(root.getChild(root.getChildrenSize() - 1));
			}

		}

	}

	public void printInOrder(BNode child) {
		if (child != null) {
			for (int i = 0; i < child.getChildrenSize() - 1; i++) {
				if (child.getChild(i) != null) {
					printInOrder(child.getChild(i));
				}
			}
			for (int j = 0; j < child.getKeysSize(); j++) {
				System.out.print(child.getKey(j));
			}

			if (child.getChild(child.getChildrenSize() - 1) != null) {
				printInOrder(child.getChild(child.getChildrenSize() - 1));
			}
		}
	}

	@Override
	public void printLevelOrder() {
		for (int level = 1; level <= this.height(); level++) {
			printLevel(root, level);
		}

	}

	private void printLevel(BNode node, int level) {
		if (node == null)
			return;
		if (level == 1){
			for (int j = 0; j < node.getKeysSize(); j++) {
				System.out.print(node.getKey(j));
			}
		}
		else if (level > 1) {
			for (int i = 0; i < node.getChildrenSize(); i++) {
			printLevel(node.getChild(i), level - 1);
			}
			
		}
		
	}

	@Override
	public void addAll(BTreeImpl other) {
		if (!other.isEmpty()) {
			for (int level = 1; level <= other.height(); level++) {
				addAll(other.root, level, this);
			}
		}

	}

	public void addAll(BNode node, int level, BTreeImpl thisTree) {
		if (node == null)
			return;
		if (level == 1){
			for (int j = 0; j < node.getKeysSize(); j++) {
				this.insert(node.getKey(j));
			}
		}
		else if (level > 1) {
			for (int i = 0; i < node.getChildrenSize(); i++) {
				addAll(node.getChild(i), level - 1, thisTree);
			}
		}
		
	}
	
	public BTree clone() {
		BTreeImpl newTree = new BTreeImpl();
		if(isEmpty()){
			return newTree;
		}
		for (int level = 1; level <= this.height(); level++) {
			clone(this.root, level, newTree);
		}
		return newTree;
		
	}

	private void clone(BNode node, int level, BTree newTree) {
		if (node == null)
			return;
		if (level == 1){
			for (int j = 0; j < node.getKeysSize(); j++) {
				newTree.insert(node.getKey(j));
			}
		}
		else if (level > 1) {
			for (int i = 0; i < node.getChildrenSize(); i++) {
				clone(node.getChild(i), level - 1, newTree);
			}
		}
		
	}
}
