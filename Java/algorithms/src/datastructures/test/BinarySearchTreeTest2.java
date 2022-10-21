package datastructures.test;

import java.security.SecureRandom;
import datastructures.BinarySearchTree;

public class BinarySearchTreeTest2 {

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		SecureRandom random = new SecureRandom();

		System.out.println("Inserting the numbers:");
		random.ints(45, 7, 100)
			.boxed()
			.forEach(num -> {
				System.out.printf("%d ", num);
				tree.insert(num);
			});
		
		System.out.printf("%n%nTree size: %d", tree.size());

		System.out.printf("%n%nPreorder traversal:%n");
		System.out.println(tree.preorderTraversal());

		System.out.printf("%nInorder traversal:%n");
		System.out.println(tree.inorderTraversal());

		System.out.printf("%nPostorder traversal:%n");
		System.out.println(tree.postorderTraversal());
		
		System.out.printf("%nMin data: %s", tree.findMin());
		System.out.printf("%nMax data: %s", tree.findMax());
		
		System.out.printf("%n%ndeleted 20: %b", tree.delete(20));
		System.out.printf("%ntree size: %s", tree.size());
	}

}
