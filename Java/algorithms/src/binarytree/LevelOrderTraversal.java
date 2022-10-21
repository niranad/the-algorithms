package binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

public class LevelOrderTraversal {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(levelOrderTraversal(root));
	}
	
	private static ArrayList<Integer> levelOrderTraversal(TreeNode<Integer> root) {
		ArrayList<Integer> arr = new ArrayList<>();
		if (root == null) return arr;
		Queue<TreeNode<Integer>> queue = new ArrayDeque<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode<Integer> curr = queue.poll();
			if (curr.getLeft() != null) {
				queue.offer(curr.getLeft());
			}
			if (curr.getRight() != null) {
				queue.offer(curr.getRight());
			}
			arr.add(curr.getData());
		}
		return arr;
	}

}
