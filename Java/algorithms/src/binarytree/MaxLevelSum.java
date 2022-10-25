package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm to calculate the maximum sum of any level in a binary tree.
 */

public class MaxLevelSum {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 9, 10, 4, 15, 6, 13, 16 };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(findLevelMaxSum(root));
	}
	
	private static int findLevelMaxSum(TreeNode<Integer> root) {
		if (root == null) return 0;
		int max = 1, currMax = 0;
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.offer(root);
		queue.offer(null);
		
		while (!queue.isEmpty()) {
			TreeNode<Integer> curr = queue.poll();
			if (curr == null) {
				max = Math.max(max, currMax);
				currMax = 0;
				if (!queue.isEmpty()) {
					queue.offer(null);
				} 
			} else {
				if (curr.getLeft() != null) {
					queue.offer(curr.getLeft());
					currMax += curr.getLeft().getData();
				}
				if (curr.getRight() != null) {
					queue.offer(curr.getRight());
					currMax += curr.getRight().getData();
				}
			}
		}
		
		return max;
	}

}
