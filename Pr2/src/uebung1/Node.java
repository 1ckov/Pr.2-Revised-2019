package uebung1;

//public class Node {
//	public Node left;
//	public Node right;
//	public int value;
//	
//	public Node(int i){
//		this.value = i;
//	}
//	
//	public Node getRight(){
//		return right;
//	}
//	
//	public Node getLeft(){
//		return left;
//	}
//	
//	public int getValue(){
//		return value;
//	}
//}
////
class Node {
	int value;
	Node left;
	Node right;

	public Node(int i) {
		value = i;
		left = right = null;
	}
	public Node(int value, Node left, Node right){
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public int getValue() {
		return value;
	}

	public void setLeft(Node n) {
		left = n;
	}

	public void setRight(Node n) {
		right = n;
	}

	public void setValue(int e) {
		value = e;
	}

	public boolean remove(int val, Node parent) {
		if (val < this.value) {
			if (left != null) {
				return left.remove(val, this);
			}
			else {
				return false;
			}
		}
		else if (val > this.value) {
			if (right != null) {
				return right.remove(val, this);
			}
			else {
				return false;
			}
		}
		else {
			
			if (left != null && right != null) {
				this.value = right.minValue();
				right.remove(this.value, this);

			}
			else if (parent.left == this) {
				parent.left = (left != null) ? left : right;
			}
			else if (parent.right == this) {
				parent.right = (left != null) ? left : right;

			}
			return true;
		}
	}

	public int minValue() {
		if (left == null) {
			return value;
		}
		else {
			left.minValue();
		}
		return 0;
	}

	public boolean search(int val) {
		if(val == this.value){
			return true;
		}
		else if(val < this.value){
			if(left == null){
				return false;
			}
			else{
				return left.search(val);
			}
		}
		else if(val > this.value){
			if(right == null){
				return false;
			}
			else{
				return right.search(val);
			}
		}
		return false;
	}
//	public Node find(int value){
//		
//	}
}