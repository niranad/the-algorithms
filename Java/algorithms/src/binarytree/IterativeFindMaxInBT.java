package binarytree;

import java.util.Arrays;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an iterative solution to find maximum element in a binary tree.
 */

public class IterativeFindMaxInBT {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(findMax(root));
	}
	
	private static int findMax(TreeNode<Integer> root) {
		int max = Integer.MIN_VALUE;
		if (root == null) return max;
		Stack<TreeNode<Integer>> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.empty()) {
			TreeNode<Integer> curr = stack.pop();
			max = Math.max(max, curr.getData());
			if (curr.getLeft() != null) {
				stack.push(curr.getLeft());
			}
			if (curr.getRight() != null) {
				stack.push(curr.getRight());
			}
		}
		
		return max;
	}

}
