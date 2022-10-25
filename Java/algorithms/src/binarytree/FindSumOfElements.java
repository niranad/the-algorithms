package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for finding the sum of all elements in a binary tree.
 */

public class FindSumOfElements {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 9, 10, 4, 15, 6, 13, 16 };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(sumElements(root));
	}
	
	// Recursive solution
	private static int sumAll(TreeNode<Integer> root) {
		if (root == null) return 0;
		return root.getData() + sumAll(root.getLeft()) + sumAll(root.getRight());
	}
	
	
	// Iterative solution using a Queue
	private static int sumElements(TreeNode<Integer> root) {
		if (root == null) return 0;
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		int sum = 0;
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			TreeNode<Integer> curr = queue.poll();
			sum += curr.getData();
			if (curr.getLeft() != null) {
				queue.offer(curr.getLeft());
			}
			if (curr.getRight() != null) {
				queue.offer(curr.getRight());
			}
		}
		
		return sum;
	}

}
