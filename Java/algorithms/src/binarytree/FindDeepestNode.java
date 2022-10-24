package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for finding the deepest node in a binary tree.
 */

public class FindDeepestNode {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 4, 12, 6, };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		TreeNode<Integer> deepest = deepestNode(root);
		System.out.println(deepest != null ? deepest.getData() : deepest);
	}
	
	
	// Iterative solution using Queue
	private static TreeNode<Integer> deepestNode(TreeNode<Integer> root) {
		if (root == null) return null;
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		TreeNode<Integer> deepest = null;
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			deepest = queue.poll();
			if (deepest.getLeft() != null) {
				queue.offer(deepest.getLeft());
			}
			if (deepest.getRight() != null) {
				queue.offer(deepest.getRight());
			}
		}
		
		return deepest;
	}
	
	// Iterative solution using Stack
	private static TreeNode<Integer> deepestNode2(TreeNode<Integer> root) {
		if (root == null) return root;
		
		Stack<TreeNode<Integer>> stack = new Stack<>();
		TreeNode<Integer> prev = null;
		TreeNode<Integer> deepest = null;
		int depth = 0;
		stack.push(root);
		
		while (!stack.empty()) {
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
				if (stack.size() > depth) {
					depth = stack.size();
					deepest = curr;
				}
				stack.pop();
			}
			prev = curr;
		}
		
		return deepest;
	}
	

}
