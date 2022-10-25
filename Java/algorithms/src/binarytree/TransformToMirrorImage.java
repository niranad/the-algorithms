package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for converting a tree to its mirror. Mirror of a tree is another 
 * tree with left and right children of all non-leaf nodes interchanged. 
 */

public class TransformToMirrorImage {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 9, 10, 4, 15, 6, 13, 16 };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		transformToMirror(root);
		// If transformed successfully, in-order traversal will print the elements in decreasing order.
		System.out.println(bst.inorderTraversal());
	}
	
	// Recursive solution
	static void transform(TreeNode<Integer> root) {
		if (root == null) return;
		if (root.getLeft() != null || root.getRight() != null) {
			TreeNode<Integer> temp = root.getLeft();
			root.setLeft(root.getRight());
			root.setRight(temp);
		}
		transform(root.getLeft());
		transform(root.getRight());
	}
	
	// Iterative solution using a Queue
	private static void transformToMirror(TreeNode<Integer> root) {
		if (root == null) return;
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			TreeNode<Integer> curr = queue.poll();
			if (curr.getLeft() != null || curr.getRight() != null) {
				TreeNode<Integer> left = curr.getLeft();
				curr.setLeft(curr.getRight());
				curr.setRight(left);
			}
			if (curr.getLeft() != null) {
				queue.offer(curr.getLeft());
			}
			if (curr.getRight() != null) {
				queue.offer(curr.getRight());
			}
		}
	}
}
