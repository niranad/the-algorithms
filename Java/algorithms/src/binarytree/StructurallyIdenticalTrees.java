package binarytree;

import java.util.Arrays;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Given two binary trees, return true if they are structurally identical.
 */

public class StructurallyIdenticalTrees {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 4, 12, 6, };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root1 = bst.getRoot();

		int[] integers2 = { 11, 6, 8, 14, 7, 15, 9, };
		BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
		Arrays.stream(integers2).forEach(n -> bst2.insert(n));
		TreeNode<Integer> root2 = bst2.getRoot();

		System.out.println(areIdentical(root1, root2));
	}

	// Using recursion
	private static boolean areIdentical(TreeNode<Integer> root1, TreeNode<Integer> root2) {
		if (root1 == null && root2 != null || root1 != null && root2 == null)
			return false;
		if (root1 != null && root2 != null) {
			return areIdentical(root1.getLeft(), root2.getLeft())
				&& areIdentical(root1.getRight(), root2.getRight());
		}
		return true;
	}

}
