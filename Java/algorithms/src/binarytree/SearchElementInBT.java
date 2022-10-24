package binarytree;

/*
 * Problem statement: Give an algorithm to search an element in a binary tree.
 */

import java.util.Arrays;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

public class SearchElementInBT {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(recursiveSearch(root, 10));
	}
	
	// assuming a search through a BT of integers
	private static boolean search(TreeNode<Integer> root, Object key) {
		if (root == null || key == null) return false;
		Stack<TreeNode<Integer>> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.empty()) {
			TreeNode<Integer> curr = stack.pop();
			if (curr.getData().equals(key)) {
				return true;
			}
			if (curr.getRight() != null) {
				stack.push(curr.getRight());
			}
			if (curr.getLeft() != null) {
				stack.push(curr.getLeft());
			}
		}
		
		return false;
	}
	
	// recursive solution
	private static boolean recursiveSearch(TreeNode<Integer> root, Object key) {
		if (root == null) {
			return false;
		}
		if (root.getData().equals(key)) {
			return true;
		}
		return search(root.getLeft(), key) || search(root.getRight(), key);
	}

}
