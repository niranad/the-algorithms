package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for finding the total of full nodes in a binary tree.
 * A full node is one having two children.
 */

public class FullNodesCount {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 4, 12, 6, };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(fullNodesCount(root));
	}

	// Using recursion
	private static int fullNodes(TreeNode<Integer> root) {
		if (root != null) {
			if (root.getLeft() != null && root.getRight() != null) {
				return 1 + fullNodes(root.getLeft()) + fullNodes(root.getRight());
			}
			if (root.getLeft() != null) {
				return fullNodes(root.getLeft());
			}
			if (root.getRight() != null) {
				return fullNodes(root.getRight());
			}
		}
		return 0;
	}

	// Using Queue
	private static int fullNodesCount(TreeNode<Integer> root) {
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		int count = 0;
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode<Integer> curr = queue.poll();
			if (curr.getLeft() != null && curr.getRight() != null) {
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
