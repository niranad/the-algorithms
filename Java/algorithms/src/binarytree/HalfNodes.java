package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for finding the number of half nodes (nodes with 
 * only one child) in the binary tree.
 */

public class HalfNodes {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 4, 12, 6, };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(halfNodesCount(root));

	}

	// Using recursion
	private static int halfNodes(TreeNode<Integer> root) {
		if (root != null) {
			if (root.getLeft() != null && root.getRight() == null) {
				return 1 + halfNodes(root.getLeft());
			}
			if (root.getLeft() == null && root.getRight() != null) {
				return 1 + halfNodes(root.getRight());
			}
			if (root.getLeft() != null && root.getRight() != null) {
				return halfNodes(root.getLeft()) + halfNodes(root.getRight());
			}
		}
		return 0;
	}

	// Using Queue
	private static int halfNodesCount(TreeNode<Integer> root) {
		if (root == null)
			return 0;
		int count = 0;
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode<Integer> curr = queue.poll();
			if (curr.getLeft() != null && curr.getRight() == null
				|| curr.getLeft() == null && curr.getRight() != null) {
				count++;
			}
			if (curr.getLeft() != null) {
				queue.offer(curr.getLeft());
			}
			if (curr.getRight() != null) {
				queue.offer(curr.getRight());
			}
		}
		
		return count;
	}

}
