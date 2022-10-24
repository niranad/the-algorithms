package binarytree;

import java.util.Arrays;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for deleting an element from a binary search tree.
 */

public class DeleteNodeInBST {

	public static void main(String[] args) {
		int[] integers = { 8, 3, 5, 11, 4, 12, 6, };
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Arrays.stream(integers).forEach(n -> bst.insert(n));
		TreeNode<Integer> root = bst.getRoot();
		deleteNode(root, 8);
		System.out.println(bst.inorderTraversal());
	}

	private static TreeNode<Integer> deleteNode(TreeNode<Integer> root, int data) {
		if (root == null)
			return null;
		if (data < root.getData()) {
			deleteNode(root.getLeft(), data);
		} else if (data > root.getData()) {
			deleteNode(root.getRight(), data);
		} else {
			if (root.getRight() != null && root.getLeft() != null) {
				TreeNode<Integer> replacement = root.getRight();
				while (replacement.getLeft() != null) {
					replacement = replacement.getLeft();
				}
				root.setData(replacement.getData());
				root.setRight(deleteNode(root.getRight(), replacement.getData()));
			} else if (root.getLeft() != null) {
				root.setData(root.getLeft().getData());
				root.setLeft(null);
			} else if (root.getRight() != null) {
				root.setData(root.getRight().getData());
				root.setRight(null);
			} else {
				root = null; // would not delete the leaf node because we don't have access to the
								// reference as it is private to BST implementation
			}
		}
		return root;
	}

}
