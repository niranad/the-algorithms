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
	
	
	// Using recursion
//	private static int treeDiameter(TreeNode<Integer> root) {
//		return getDiameter(root, 0);
//	}
//	
//	private static int getDiameter(TreeNode<Integer> root, int diameter) {
//		if (root == null) return 0;
//		if (root.getLeft() == null && root.getRight() == null) return 1;	
//		int left = getDiameter(root.getLeft(), diameter);
//		int right = getDiameter(root.getRight(), diameter);
//		diameter = Math.max(diameter, right + left);
//		return diameter;
//	}
	
	// Using two Queues -> Alternating queues for level processing
	private static int findDiameter(TreeNode<Integer> root) {
		if (root == null) return 0;
		Queue<TreeNode<Integer>> queue1 = new LinkedList<>();
		Queue<TreeNode<Integer>> queue2 = new LinkedList<>();
		int diameter = 0;
		boolean procQueue1 = true;  // indicates whether to process queue1 or queue2
		queue1.offer(root);
		
		while (!queue1.isEmpty() || !queue2.isEmpty()) {
			TreeNode<Integer> curr = null;
			if (queue1.isEmpty() && !queue2.isEmpty()) {
				diameter = Math.max(diameter, queue2.size());
				procQueue1 = false;
			} else if (!queue1.isEmpty() && queue2.isEmpty()) {
				diameter = Math.max(diameter, queue1.size());
				procQueue1 = true;
			}
			
			if (procQueue1) {
				curr = queue1.poll();
			} else {
				curr = queue2.poll();
			}
			
			if (curr.getLeft() != null) {
				if (procQueue1) {
					queue2.offer(curr.getLeft());
				} else {
					queue1.offer(curr.getLeft());
				}
				
			}
			if (curr.getRight() != null) {
				if (procQueue1) {
					queue2.offer(curr.getRight());
				} else {
					queue1.offer(curr.getRight());
				}
			}
		}
		
		return diameter;
	}

}
