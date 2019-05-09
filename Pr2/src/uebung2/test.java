package uebung2;



public class test {
	
	private static void testNormal() {
		BinaryTree tree = new MyBinaryTree();
		tree.insert(new IntElement(10));
		tree.insert(new IntElement(0));
		
		BinaryTree tree2 = new MyBinaryTree();
		tree2.insert(new IntElement(12));
		tree2.insert(new IntElement(20));
		tree2.insert(new IntElement(11));
		
//		tree.remove(new IntElement(12));
		
		tree.addAll(tree2);
		
		//System.out.println(tree.height());
		//System.out.println(tree.size());
		//tree.getMin().getVal().print();
		//tree.getMax().getVal().print();
		
		System.out.println(tree.getMax().compareTo(new IntElement(1)));
	}
	
	private static void testWithFile() {
		BinaryTree tree = new MyBinaryTree();
		tree.insert("test.txt");
		
		
//		System.out.println(tree.getMin().compareTo(new StringElement("Hallo")));
	}
	
	public static void main(String[] args) {
		testNormal();
//		
	}
	
	private static void testWithEmpty() {
		BinaryTree tree1 = new MyBinaryTree();
		BinaryTree tree2 = (MyBinaryTree)tree1.clone();
		System.out.println(tree1.height());
		System.out.println(tree1.size());
		System.out.println(tree1.getMax());
		System.out.println(tree1.getMin());
	}
	
	private static void testWithOneElem() {
		BinaryTree tree1 = new MyBinaryTree();
		
		BinaryTree tree2 = (MyBinaryTree)tree1.clone();
		tree1.insert(new IntElement(10));
		System.out.println(tree1.height());
		System.out.println(tree1.size());
		tree1.getMin().getVal().print();
		System.out.println();
		tree1.getMax().getVal().print();
		System.out.println();
		System.out.println(tree2.height());
		System.out.println(tree2.size());
		tree2.getMin().getVal().print();
		System.out.println();
		tree2.getMax().getVal().print();
		System.out.println();
	}

}
