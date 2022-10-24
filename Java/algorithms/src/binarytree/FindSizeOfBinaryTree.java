package binarytree;

import java.util.Arrays;
import java.util.Stack;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

public class FindSizeOfBinaryTree {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(recursiveGetSize(root));
	}
	
	private static int getSize(TreeNode<Integer> root) {
		int size = 0;
		if (root == null) return size;
		Stack<TreeNode<Integer>> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.empty()) {
			size++;
			TreeNode<Integer> curr = stack.pop();
			if (curr.getLeft() != null) {
				stack.push(curr.getLeft());
			}
			if (curr.getRight() != null) {
				stack.push(curr.getRight());
			}
		}
		
		return size;
	}
	
	private static int recursiveGetSize(TreeNode<Integer> root) {
		if (root == null) return 0;
		int leftCount = root.getLeft() == null ? 0 : recursiveGetSize(root.getLeft());
		int rightCount = root.getRight() == null ? 0 : recursiveGetSize(root.getRight());
		return 1 + leftCount + rightCount;
	}
}
