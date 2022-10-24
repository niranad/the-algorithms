package binarytree;

import java.util.Arrays;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for finding the maximum element in a binary search tree.
 */

public class BSTFindMaxElement {

	public static void main(String[] args) {
		int[] integers = {8, 3, 5, 11, 9, 10, 4, 12, 6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		System.out.println(findMax(root));
	}
	
	private static int findMax(TreeNode<Integer> root) {
		TreeNode<Integer> curr = root;
		
		while (curr.getRight() != null) {
			curr = curr.getRight();
		}
		
		return curr.getData();
	}

}
