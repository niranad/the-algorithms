package binarytree;

import java.util.Arrays;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Using recursion, give an algorithm to determine the max element in a binary tree.
 */

public class BinaryTreeFindMax {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(findMax(root));

	}
	
	// assuming TreeNode is Binary Tree Node (NB: TreeNode is actually a BST node)
	private static int findMax(TreeNode<Integer> root) {
		int max = Integer.MIN_VALUE;
		if (root != null) {
			int leftMax = findMax(root.getLeft());
			int rightMax = findMax(root.getRight());
			max = Math.max(leftMax, rightMax);
			max = Math.max(max, root.getData());
		}
		return max;
	}

}
