package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

public class IterativePostOrderTraversal {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(postorderTraversal(root));
	}
	
	private static ArrayList<Integer> postorderTraversal(TreeNode<Integer> root) {
		ArrayList<Integer> arr = new ArrayList<>();
		if (root == null) return arr;
		
		Stack<TreeNode<Integer>> stack = new Stack<>();
		TreeNode<Integer> prev = null;
		stack.push(root);
		
		while (!stack.empty()) {
			TreeNode<Integer> curr = stack.peek();
			if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
				if (curr.getLeft() != null) {
					stack.push(curr.getLeft());
				} else if (curr.getRight() != null) {
					stack.push(curr.getRight());
				}
			} else if (curr.getLeft() == prev) {
				if (curr.getRight() != null) {
					stack.push(curr.getRight());
				}
			} else {
				arr.add(curr.getData());
				stack.pop();
			}
			prev = curr;
		}
		
		return arr;
	}

}
