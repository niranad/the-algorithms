package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm to calculate the height of a binary tree.
 */

public class FindHeightOfBT {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 9, 10, 4, 12, 6, };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(iterGetHeight(root));
	}

	
	private static int getHeight(TreeNode<Integer> root) {
		if (root == null)
			return 0;
		int leftHeight = root.getLeft() == null ? -1 : getHeight(root.getLeft());
		int rightHeight = root.getRight() == null ? -1 : getHeight(root.getRight());
		return 1 + Math.max(leftHeight, rightHeight);
	}
	
	// Iterative solution using Stack
	private static int iterGetHeight(TreeNode<Integer> root) {
		if (root == null) return 0;
		Stack<TreeNode<Integer>> stack = new Stack<>();
		TreeNode<Integer> prev = null;
		int maxHeight = 0;
		stack.push(root);
		
		while (!stack.empty()) {
			TreeNode<Integer> curr = stack.peek();
			if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
				if (curr.getLeft() != null) {
					stack.push(curr.getLeft());
				} else if (curr.getRight() != null) {
					stack.push(curr.getRight());
				}
			} else if (prev == curr.getLeft()) {
				if (curr.getRight() != null) {
					stack.push(curr.getRight());
				}
			} else {
				if (stack.size() > maxHeight) {
					maxHeight = stack.size();
				}
				stack.pop();
			}
			prev = curr;
		}
		
		return maxHeight - 1;
	}

}
