package uebung1Revised;

import java.io.Serializable;

public class Node<T extends Number> implements Comparable<T>,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5316989444848821308L;
	private T value;
	private Node<T> leftChildNode;
	private Node<T> rightChildNode;

	/**
	 * Empty Constructor Initializing an empty Node.
	 */
	public Node() {
		this.value = null;
		this.leftChildNode = null;
		this.rightChildNode = null;
	}

	/**
	 * Initializes Node with Values.
	 * 
	 * @param value - Object of type T
	 * @param leftChildNode  - Left Child of our node
	 * @param rightChildNode - Right Child of our node
	 */
	public Node(T value, Node<T> leftChildNode, Node<T> rightChildNode) {
		this.value = value;
		this.leftChildNode = leftChildNode;
		this.rightChildNode = rightChildNode;
	}

	/**
	 * Value getter.
	 * 
	 * @return - Value.
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Value setter.
	 * 
	 * @param value - value to be set
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * Left Getter
	 * 
	 * @return - Left Child Node
	 */
	public Node<T> getLeftChildNode() {
		return this.leftChildNode;
	}

	/**
	 * Left Setter
	 * 
	 * @param leftCHildNode - Node to be set as Left
	 */
	public void setLeftChildNode(Node<T> leftCHildNode) {
		this.leftChildNode = leftCHildNode;
	}

	/**
	 * Right Getter
	 * 
	 * @return - Right Child Node
	 */
	public Node<T> getRightChildNode() {
		return this.rightChildNode;
	}

	/**
	 * Right Setter
	 * 
	 * @param right - Node to be set as Right
	 */
	public void setRight(Node<T> right) {
		this.rightChildNode = right;
	}

	/**
	 * Compares two Nodes directly.
	 */
	@Override
	public int compareTo(T that) {
		return this.getValue().intValue() - that.intValue();
	}

	public T getMinValue() {
		return getMinValue(this);
	}

	public T getMinValue(Node<T> leftLowest) {
		if(leftLowest.getLeftChildNode() != null) {
			return getMinValue(leftLowest.getLeftChildNode());
		}
		return leftLowest.getValue();
	}

	
	public boolean remove(T valueToBeRemoved, Node<T> parentNodeOfThis) {
		//if our value is smaller than the current value
		if(this.compareTo(valueToBeRemoved) > 0) {
			//and there is a left path 
			if(this.getLeftChildNode() != null) {
				//go deeper
				return getLeftChildNode().remove(valueToBeRemoved,this);
			}
			else {
				//else return false because it should not be here
				return false;
			}
		}
		//if our value is bigger than the current value
		else if (this.compareTo(valueToBeRemoved) < 0) {
			// and a right path exists
			if(this.getRightChildNode() != null) {
				// go deeper
				return getRightChildNode().remove(valueToBeRemoved, this);
			}
			else {
				//else it is not in the tree
				return false;
			}
		}
		else {
			//if is inner Node
			if(this.getLeftChildNode() != null && this.getRightChildNode() != null) {
				//we take the smallest node to be found and set its value here
				this.setValue(getRightChildNode().getMinValue());
				this.remove(this.value, this);
			}
			else if(parentNodeOfThis.getLeftChildNode() == this) {
				//we know it has either 1 or 0 children, if the left is null,
				parentNodeOfThis.setLeftChildNode((this.getLeftChildNode() != null)
						//we set it to the right Node which will either be null or a node
						? this.getLeftChildNode() : this.getRightChildNode());
			}
			else if(parentNodeOfThis.getRightChildNode() == this) {
				//same goes here
				parentNodeOfThis.setRight((this.getLeftChildNode() != null) ? this.getLeftChildNode() : this.getRightChildNode());
			}
			return true;
		}
	}

}
