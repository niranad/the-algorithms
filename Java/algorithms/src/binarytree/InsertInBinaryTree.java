package binarytree;

import java.util.Arrays;

/*
 * Problem statement: Give an algorithm to insert an element in Binary Tree.
 */

import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;


public class InsertInBinaryTree {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(recursiveInsert(root, 15));
	}
	
	// assume TreeNode is a Binary Tree node that has an insert method
	private static void insert(TreeNode<Integer> root, int data) {
		if (root == null) return;
		if (root.getData() == null) {
			root.insert(data);
			return;
		}
		Stack<TreeNode<Integer>> stack = new Stack<>();
		stack.push(root);
		while (!stack.empty()) {
			TreeNode<Integer> curr = stack.pop();
			if (curr.getLeft() == null) {
				curr.insert(data);
				return;
			} else if (curr.getRight() == null) {
				curr.insert(data);
				return;
			} else {
				stack.push(curr.getRight());
				stack.push(curr.getLeft());
			}
		}
	}
	
	private static boolean recursiveInsert(TreeNode<Integer> root, int data) {
		if (root == null) return false;
		if (root.getData() == null) {
			root.insert(data);
			return true;
		}
		if (root.getLeft() == null || root.getRight() == null) {
			root.insert(data);
			return true;
		}
		return recursiveInsert(root.getLeft(), data) || recursiveInsert(root.getRight(), data); 
	}

}
