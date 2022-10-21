package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

public class IterativeInorderTraversal {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(inorderTraversal(root));
	}
	
	private static ArrayList<Integer> inorderTraversal(TreeNode<Integer> root) {
		ArrayList<Integer> arr = new ArrayList<>();
		if (root == null) return arr;
		
		Stack<TreeNode<Integer>> stack = new Stack<>();
		TreeNode<Integer> curr = root;
		boolean done = false;
		
		while (!done) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.getLeft();
			} else {
				if (stack.empty()) {
					done = true;
				} else {
					curr = stack.pop();
					arr.add(curr.getData());
					curr = curr.getRight();
				}
			} 
		}
		
		return arr;
	}
	
}
