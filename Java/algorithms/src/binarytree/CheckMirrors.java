package binarytree;

import java.util.Arrays;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Given two trees, give an algorithm for checking whether they are mirrors of 
 * each other.
 */

public class CheckMirrors {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 9, 10, 4, 15, 6, 13, 16 };
		BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
		BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> {
			bst1.insert(n);
			bst2.insert(n);
		});
		TreeNode<Integer> root1 = bst1.getRoot();
		TreeNode<Integer> root2 = bst2.getRoot();
		TransformToMirrorImage.transform(root1);

		System.out.println(areMirrors(root1, root2));
	}

	// Recursive solution
	private static boolean areMirrors(TreeNode<Integer> root1, TreeNode<Integer> root2) {
		if (root1 == null && root2 != null || root1 != null && root2 == null
			|| root1.getData() != root2.getData()) {
			return false;
		} else if (root1 != null && root2 != null) {
			return areMirrors(root1.getLeft(), root2.getRight())
				&& areMirrors(root1.getRight(), root2.getLeft());
		}
		return true;
	}

	private static void mirrors(TreeNode<Integer> root1, TreeNode<Integer> root2) {

	}

}
