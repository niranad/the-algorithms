package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm to print all root-to-leaf paths in a binary tree.
 */

public class RootToLeafPaths {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 9, 10, 4, 15, 6, 13, 16 };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		rootToLeafPaths(root);
	}
	
	
	private static void rootToLeafPaths(TreeNode<Integer> root) {
		if (root == null) return;
		Stack<TreeNode<Integer>> stack = new Stack<>();
		TreeNode<Integer> prev = null;
		stack.push(root);
		
		while (!stack.isEmpty()) {
			TreeNode<Integer> curr = stack.peek();
			if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
				if (curr.getLeft() != null) {
					stack.push(curr.getLeft());
				} else if (curr.getRight() != null) {
					stack.push(curr.getRight());
				}
			} else if (curr.getLeft() == prev) {
				if (curr.getRight() != null) {
					stack.push(curr.getRight());
				}
			} else {
				if (prev == curr) {
					for (TreeNode<Integer> node : stack) {
						System.out.print(node.getData() + " ");
					}
					System.out.println();
				}
				stack.pop();
			}
			prev = curr;
		}
	}

}
