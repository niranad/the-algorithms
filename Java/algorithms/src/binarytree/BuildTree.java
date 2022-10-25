package binarytree;

import datastructures.BinarySearchTree;
import datastructures.TreeNode;

/*
 * Problem statement: Give an algorithm for constructing binary tree from given inorder and preorder traversals.

 */

public class BuildTree {

	public static void main(String[] args) {
		int[] inorder = { 3, 4, 5, 6, 8, 9, 10, 11, 13, 15, 16, };
		int[] preorder = { 8, 3, 5, 4, 6, 11, 9, 10, 15, 13, 16, };
		TreeNode<Integer> root = buildTree(inorder, preorder);
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.setRoot(root);
		System.out.println(bst.inorderTraversal());
	}

	// Using recursion -> assuming elements are integers
	private static TreeNode<Integer> buildTree(int[] inorder, int[] preorder) {
		if (inorder.length == 0 || preorder.length == 0) {
			return null;
		}
		return buildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
	}

	private static TreeNode<Integer> buildTree(int[] inorder, int inStart, int inEnd,
		int[] preorder, int preStart, int preEnd) {
		if (inStart > inEnd || preStart > preEnd) {
			return null;
		}
		int data = preorder[preStart];
		TreeNode<Integer> root = new TreeNode<>(data);
		int offset = inStart;
		for (; offset <= inEnd; offset++) {
			if (data == inorder[offset]) {
				break;
			}
		}

		root.setLeft(buildTree(inorder, inStart, offset - 1, preorder, preStart + 1,
			preStart + offset - inStart));
		root.setRight(buildTree(inorder, offset + 1, inEnd, preorder,
			preStart + offset - inStart + 1, preEnd));

		return root;
	}

}
