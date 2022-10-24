package binarytree;

/*
 * Problem statement: Give an algorithm to find the number of leaf nodes in a binary tree.
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

public class CountLeaves {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 9, 10, 4, 12, 6, 2, -1, -3, 16, 20, 17 };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(leafNodesCount(root));
	}
	
	// Using recursion
	private static int leafNodesCount(TreeNode<Integer> root) {
		if (root == null) return 0;
		int leftLeaves = leafNodesCount(root.getLeft());
		int rightLeaves = leafNodesCount(root.getRight());
		if (root.getRight() == null && root.getLeft() == null) {
			return 1;
		}
		return leftLeaves + rightLeaves;
	}

	// Using Queue
	private static int countLeaves(TreeNode<Integer> root) {
		if (root == null)
			return 0;
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		int count = 0;
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode<Integer> curr = queue.poll();
			if (curr.getLeft() == null && curr.getRight() == null) {  // at leaf node
				count++;
			} else {
				if (curr.getLeft() != null) {
					queue.offer(curr.getLeft());
				}
				if (curr.getRight() != null) {
					queue.offer(curr.getRight());
				}
			}
		}

		return count;
	}

	// Using Stack
	private static int countLeafNodes(TreeNode<Integer> root) {
		if (root == null)
			return 0;
		Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>>();
		TreeNode<Integer> prev = null;
		int count = 0;
		stack.push(root);

		while (!stack.isEmpty()) {
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
				if (curr.getLeft() == null && curr.getRight() == null) { // at leaf node
					count++;
				}
				stack.pop();
			}
			prev = curr;
		}

		return count;
	}

}
