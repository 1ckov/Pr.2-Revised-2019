package uebung5_RevisedOldUebung4;

public class AVLNode<T extends Number> implements Comparable<T>{
	
	private T value;
	private AVLNode<T> leftChild;
	private AVLNode<T> rightChild;
	private int balance;
	
	public AVLNode(T value ,AVLNode<T> leftChild, AVLNode<T> rightChild) {
		
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.balance = 0;
		
	}
	
	public AVLNode() {
		this(null,null,null);
	}
	
	public void setLeftChild(AVLNode<T> newChild) {
		this.leftChild = newChild;
	}
	
	public void setRightChild(AVLNode<T> newChild) {
		this.rightChild = newChild;
	}
	
	public AVLNode<T> getLeftChild(){
		return this.leftChild;
	}
	
	public AVLNode<T> getRightChild(){
		return this.rightChild;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return this.value;
	}
	
	

	@Override
	public int compareTo(T value) {
		
		return this.value.intValue() - value.intValue();
		
	}
	
	
}
