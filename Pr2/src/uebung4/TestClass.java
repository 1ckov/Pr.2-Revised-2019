package uebung4;

public class TestClass {
	public static void main(String[] args) {
		BTreeImpl tree = new BTreeImpl(1);
		tree.insert(new Integer(4));
		tree.insert(new Integer(6));
		 tree.insert(new Integer(1));
		 tree.insert(new Integer(7));
		 tree.insert(new Integer(8));
		 tree.insert(new Integer(2));
		 tree.insert(new Integer(5));
		 tree.insert(new Integer(9));
		 tree.insert(new Integer(10));
		 
		 BTreeImpl tree2 = new BTreeImpl(1);
		 tree2.addAll(tree);
		 BTree tree3 = (BTreeImpl)tree.clone();
tree3.printLevelOrder();		 
		// tree.insert(new Integer(9));
		// tree.insert(new Integer(3));
		//tree.printInOrder();
//		tree.delete(new Integer(88));
//		 tree.printLevelOrder();
//		 System.out.println("\n");
//		tree2.printLevelOrder();
//		BNode node = tree2.getNode(new Integer(7));
//		BNode child = node.getChild(2);
//		System.out.println(child.getKey(0));

		 
		// System.out.println(tree.height());
//		 BNode node = tree.getNode(new Integer(6));
//		 System.out.println(node.getKey(1));
	}
}
