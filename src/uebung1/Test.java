package uebung1;
public class Test {
	public static void main(String[] args) {
		BinaryTree nTree = new MyBinaryTree();
		nTree.insert(2);
		nTree.insert(3);
		nTree.remove(2);
		System.out.println(nTree.contains(2));
//		
//		
//		wint[] i = {1, 4, 5, 8, 21};
//		saveIntegerArray( i, "C:/Users/student/git/18-team-repo/Pr2/src/uebung1/s");
//		nTree.insert("C:/Users/student/git/18-team-repo/Pr2/src/uebung1/s");
//		nTree.insert(16);
//		BinaryTree mTree = nTree.clone();
//		System.out.println(nTree.getMax()==mTree.getMax());
//		System.out.println(nTree.getMin()==mTree.getMin());
//		System.out.println(nTree.height()==mTree.height());
//		System.out.println(nTree.size()==mTree.size());
//		
//		nTree.insert(25);
//		System.out.println(nTree.getMax() > mTree.getMax());
//		System.out.println(nTree.getMin() == mTree.getMin());
//		System.out.println(nTree.height() > mTree.height());
//		System.out.println(nTree.size() > mTree.size());
//		nTree.printInorder();
//		mTree.printInorder();
//		nTree.remove(16);
//		System.out.println(mTree.contains(16));
//		nTree.printInorder();
//		BinaryTree test = new MyBinaryTree();
//		int choice = -1;
//		do {
//			System.out.println("Geben Sie eine function ein: \n" + "1 - Insert; \n"
//					+ "2 - Contains; \n" + "3 - Size; \n"
//					+ "4 - Height; \n" + "5 - Get Maximum \n" + "6 - Get Minimum \n"
//					+ "7 - Remove; \n" + "8 - Clear; \n" + "9 - Print Order: \n" + "10 - Clone; \n"
//					+ "0 - Exit; \n");
//			choice = readInt();
//			if (choice == 1) {
//				println("Geben sie die Zahl ein: ");
//				int t = readInt();
//				test.insert(t);
//			}
//			else if (choice == 2) {
//				println("Geben sie die zahl an nach der sie suchen: ");
//				int t = readInt();
//				test.contains(t);
//			}
//			else if (choice == 3) {
//				println(test.size());
//			}
//			else if (choice == 4) {
//				println(test.height());
//			}
//			else if (choice == 5) {
//				println(test.getMax());
//			}
//			else if (choice == 6) {
//				println(test.getMin());
//			}
//			else if (choice == 7) {
//				println("Geben sie die Zahl ein: ");
//				int t = readInt();
//				test.remove(t);
//			}
//			else if (choice == 8) {
//				test.clear();
//			}
//			else if (choice == 9) {
//				println("W�hlen sie einen order aus: \n" + "1 - Inorder \n"+ "2 - Postorder \n" + "3 - Preorder \n"+ "4 - Levelorder \n");
//				int t = readInt();
//					if(t == 1){
//						test.printInorder();
//					}
//					else if(t == 2){
//						test.printPostorder();
//					}
//					else if(t == 3){
//						test.printPreorder();
//					}
//					else if(t == 4){
//						test.printLevelorder();
//					}
//				
//			}
//			else if (choice == 10) {
//				BinaryTree newTree = test.clone();
//			}
//			
//			
//		} while (choice != 0);

		BinaryTree n = new MyBinaryTree();
		n.insert(23);
		n.insert(27);
		n.remove(23);
	}
}
