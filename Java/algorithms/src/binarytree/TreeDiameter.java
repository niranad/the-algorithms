package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for finding the diameter of the binary tree. The diameter 
 * of a tree (sometimes called the width) is the number of nodes on the longest path between two 
 * leaves in the tree.
 */

public class TreeDiameter {

	public static void main(String[] args) {
		int[] integers = { 11, 8, 15, 5, 9, 12, 17, 3, 6, 10, 16, 20, };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(findDiameter(root));
	}
	
	
	// Using two Queues -> Alternating queues for level processing
	private static int findDiameter(TreeNode<Integer> root) {
		if (root == null) return 0;
		int diameter = 1, currDiam = 0;
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.offer(root);
		queue.offer(null);
		
		while (!queue.isEmpty()) {
			TreeNode<Integer> curr = queue.poll();
			if (curr == null) {
				diameter = Math.max(diameter, currDiam);
				currDiam = 0;
				if (!queue.isEmpty()) {
					queue.offer(null);
				} 
			} else {
				if (curr.getLeft() != null) {
					queue.offer(curr.getLeft());
					currDiam++;
				}
				if (curr.getRight() != null) {
					queue.offer(curr.getRight());
					currDiam++;
				}
			}
		}
		
		return diameter;
	}

}
