package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Implement an iterative preorder traversal of a Binary Search Tree
 * and return the result as an array.
 */

public class IterativePreorderTraversal {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(preorderTraversal(root));
	}
	
	private static ArrayList<Integer> preorderTraversal(TreeNode<Integer> root) {
		ArrayList<Integer> arr = new ArrayList<>();
		if (root == null) return arr;
		
		Stack<TreeNode<Integer>> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.empty()) {
			TreeNode<Integer> node = stack.pop();
			arr.add(node.getData());
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
		}
		
		return arr;
	}

}
