package uebung2;



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
	Element elem;
	Node left;
	Node right;


	public Node(Element elem){
		this.elem =  elem;
	}
	public Node(Element value, Node left, Node right){
		this.elem = value;
		this.left = left;
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public Element getValue() {
		return elem;
	}

	public void setLeft(Node n) {
		left = n;
	}

	public void setRight(Node n) {
		right = n;
	}

	public void setValue(Element e) {
		elem = e;
	}

	public boolean remove(Element elem, Node parent) {
		if (this.elem.compareTo(elem) == 1) {
			if (left != null) {
				return left.remove(elem, this);
			}
			else {
				return false;
			}
		}
		else if (this.elem.compareTo(elem) == -1) {
			if (right != null) {
				return right.remove(elem, this);
			}
			else {
				return false;
			}
		}
		else {
			if (left != null && right != null) {
				this.elem = right.minValue();
				right.remove(this.elem, this);

			}
			else if (parent.left == this) {
				parent.left = (left != null) ? left : right;
			}
			else if (parent.right == this) {
				parent.right = (right != null) ? left : right;

			}
			return true;
		}
	}

	public Element minValue() {
		if (left == null) {
			return elem;
		}
		else {
			left.minValue();
		}
		return null;
	}
	
	public boolean validator(Element elem){
		if(this.getValue().getClass().equals(elem.getClass())){
			return true;
		}
		return false;
		
	}
//	public boolean search(int val) {
//		if(val == this.value){
//			return true;
//		}
//		else if(val < this.value){
//			if(left == null){
//				return false;
//			}
//			else{
//				return left.search(val);
//			}
//		}
//		else if(val > this.value){
//			if(right == null){
//				return false;
//			}
//			else{
//				return right.search(val);
//			}
//		}
//		return false;
//	}
//	public Node find(int value){
//		
//	}
}