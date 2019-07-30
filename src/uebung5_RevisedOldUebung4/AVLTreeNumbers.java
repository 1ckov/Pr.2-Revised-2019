package uebung5_RevisedOldUebung4;

public class AVLTreeNumbers<T extends Number> implements AVLTree<T>{
	private AVLNode<T> root;
	
	
	@Override
	public void insert(T value) {
		if(root == null) {
		
			root = new AVLNode<T>(value, null, null);
			
		}
		else {
			
			AVLNode<T> newNode = new AVLNode<T>(value,null,null);
			
			root = insertRecursive(newNode,root);
			
		}
		
		
	}
	
	public AVLNode<T> insertRecursive(AVLNode<T> nodeToBeInserted, AVLNode<T> CurrentNode){
		
		if(nodeToBeInserted.compareTo(CurrentNode.getValue()) < 0) {
			
		}
		else if(nodeToBeInserted.compareTo(CurrentNode.getValue()) > 0) {
		
		}
		
		return null;
	}

	@Override
	public boolean remove(T value) {
		// TODO Auto-generated method stub
		return false;
	}

}
