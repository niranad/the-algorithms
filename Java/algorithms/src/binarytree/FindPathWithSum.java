package binarytree;

import java.util.Arrays;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for checking the existence of path with given sum. That 
 * means, given a sum, check whether there exists a path from root to any of the nodes.
 */

public class FindPathWithSum {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 9, 10, 4, 15, 6, 13, 16 };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(hasPathSum(root, 38));
	}

	// Recursive solution
	private static boolean hasPathSum(TreeNode<Integer> root, int sum) {
		return hasPathSum(root, sum, 0);
	}
	private static boolean hasPathSum(TreeNode<Integer> root, int sum, int currSum) {
		if (root == null)
			return false;
		currSum += root.getData();
		if (currSum == sum)
			return true;
		return hasPathSum(root.getLeft(), sum, currSum)
			|| hasPathSum(root.getRight(), sum, currSum);
	}

	
	// Iterative solution using a Stack
	private static boolean pathExists(TreeNode<Integer> root, int sum) {
		if (root == null)
			return false;
		int temp = root.getData();
		Stack<TreeNode<Integer>> stack = new Stack<>();
		TreeNode<Integer> prev = null;
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode<Integer> curr = stack.peek();
			if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
				if (curr.getLeft() != null) {
					stack.push(curr.getLeft());
					temp += curr.getLeft().getData();
				} else if (curr.getRight() != null) {
					stack.push(curr.getRight());
					temp += curr.getRight().getData();
				}
			} else if (curr.getLeft() == prev) {
				if (curr.getRight() != null) {
					stack.push(curr.getRight());
					temp += curr.getRight().getData();
				}
			} else {
				TreeNode<Integer> top = stack.pop();
				temp -= top.getData();
			}
			prev = curr;

			if (temp == sum && !stack.isEmpty())
				return true;
		}

		return false;
	}

}
