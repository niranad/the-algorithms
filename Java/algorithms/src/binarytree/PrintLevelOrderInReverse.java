package binarytree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for printing the level order elements in a binary tree
 * in reverse order.
 */

public class PrintLevelOrderInReverse {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		reverseLevelOrder(root);
	}
	
	private static void reverseLevelOrder(TreeNode<Integer> root) {
		if (root == null) return;
		Stack<Integer> stack = new Stack<>();
		Queue<TreeNode<Integer>> queue = new ArrayDeque<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			TreeNode<Integer> curr = queue.poll();
			stack.push(curr.getData());
			if (curr.getRight() != null) {
				queue.offer(curr.getRight());
			}
			if (curr.getLeft() != null) {
				queue.offer(curr.getLeft());
			}
		}
		
		while (!stack.empty()) {
			System.out.print(stack.pop() + " ");
		}
	}

}
