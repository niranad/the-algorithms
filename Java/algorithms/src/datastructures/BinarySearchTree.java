package datastructures;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<T>> {
	private TreeNode<T> root;
	private int size;
	private int depth;
	private int leftSubtreeDepth;
	private int rightSubtreeDepth;

	public BinarySearchTree() {
		root = null;
	}

	public TreeNode<T> getRoot() {
		return root;
	}

	protected void setRoot(TreeNode<T> root) throws UnsupportedOperationException {
		if (!(this instanceof BalancedBST)) {
			throw new UnsupportedOperationException();
		}
		this.root = root;
	}

	/**
	 * Inserts a node with its data into this binary search tree.
	 * 
	 * @param {@code data}
	 * @return {@code data}
	 */
	public T insert(T data) {
		if (root == null) {
			root = new TreeNode<T>(data);
			return data;
		} else {
			return root.insert(data);
		}
	}

	/**
	 * Inserts a node with its data item into this binary search tree with an
	 * ordering specified by a {@code Comparator} object and returns the inserted
	 * data item.
	 * 
	 * @param data to be inserted
	 * @param comp - {@code Comparator} object to order the data items in the tree
	 * @return the inserted data item
	 */
	public T insert(T data, Comparator<T> comp) {
		if (root == null) {
			root = new TreeNode<T>(data);
		} else {
			root.insert(data, comp);
		}

		return data;
	}

	/**
	 * Traverses the binary search tree by processing each node as the node is
	 * visited. After processing the data in a particular node, it processes the
	 * data in the left subtree, then processes the data in the right subtree.
	 */
	public String preorderTraversal() {
		ArrayList<T> outputTree = new ArrayList<>();
		preorderHelper(root, outputTree);
		return outputTree.toString();
	}

	private void preorderHelper(TreeNode<T> node, ArrayList<T> outputTree) {
		if (node == null) {
			return;
		}

		outputTree.add(node.data);
		preorderHelper(node.left, outputTree);
		preorderHelper(node.right, outputTree);
	}

	/**
	 * Traverses the binary search tree by processing a node's data after the data
	 * in the node's left subtree are processed.
	 */
	public String inorderTraversal() {
		ArrayList<T> outputTree = new ArrayList<>();
		inorderHelper(root, outputTree);
		return outputTree.toString();
	}

	private void inorderHelper(TreeNode<T> node, ArrayList<T> outputTree) {
		if (node == null) {
			return;
		}

		inorderHelper(node.left, outputTree);
		outputTree.add(node.data);
		inorderHelper(node.right, outputTree);
	}

	public String postorderTraversal() {
		ArrayList<T> outputTree = new ArrayList<>();
		postorderHelper(root, outputTree);
		return outputTree.toString();
	}

	private void postorderHelper(TreeNode<T> node, ArrayList<T> outputTree) {
		if (node == null) {
			return;
		}

		postorderHelper(node.left, outputTree);
		postorderHelper(node.right, outputTree);
		outputTree.add(node.data);
	}

	public int getDepth() {
		leftSubtreeDepth = 0;
		rightSubtreeDepth = 0;

		traverseLeftSubtree(root);
		traverseRightSubtree(root);

		if (leftSubtreeDepth > rightSubtreeDepth) {
			depth = leftSubtreeDepth;
		} else if (rightSubtreeDepth > leftSubtreeDepth) {
			depth = rightSubtreeDepth;
		} else {
			depth = leftSubtreeDepth;
		}

		return depth;
	}

	public T findMin() {
		if (root != null) {
			TreeNode<T> currNode = root;

			while (currNode.left != null) {
				currNode = currNode.left;
			}

			return currNode.data;
		} else {
			return null;
		}
	}

	public T findMax() {
		if (root != null) {
			TreeNode<T> currNode = root;

			while (currNode.right != null) {
				currNode = currNode.right;
			}

			return currNode.data;
		} else {
			return null;
		}
	}

	private void traverseLeftSubtree(TreeNode<T> node) {
		if (node == null) {
			return;
		}

		++leftSubtreeDepth;
		traverseLeftSubtree(node.left);
	}

	private void traverseRightSubtree(TreeNode<T> node) {
		if (node == null) {
			return;
		}

		++rightSubtreeDepth;
		traverseRightSubtree(node.right);
	}

	public String levelOrderTraversal() {
		Queue<TreeNode<T>> queue = new Queue<>();
		ArrayList<T> outputTree = new ArrayList<>();

		queue.offer(root);

		while (!queue.isEmpty()) {
			outputTree.add(queue.peek().data);

			if (queue.peek().left != null) {
				queue.offer(queue.peek().left);
			}

			if (queue.peek().right != null) {
				queue.offer(queue.peek().right);
			}

			queue.poll();
		}

		return outputTree.toString();
	}

	public boolean delete(T item) {
		int size = size();
		deleteNode(item, root);
		
		return size != size();
	}
	
	private TreeNode<T> deleteNode(T item, TreeNode<T> root) {
		if (root == null) return root;
		else if (item.compareTo(root.data) < 0) {  // if item is less than current, search left subtree
			root.left = deleteNode(item, root.left);
		} else if (item.compareTo(root.data) > 0) {  // if item is greater than current, search right subtree
			root.right = deleteNode(item, root.right);
		} else {
			if (root.left == null && root.right == null) {  // if item node is a leaf node
				root = null;
			} else if (root.left == null) {  // if item node has only a  right child
				root = root.right;
			} else if (root.right == null) {  // if item node has only a left child
				root = root.left;
			} else {  // if item node has two children, replace with closest in value from either subtree
				TreeNode<T> current = root.right;
				while (current.left != null) current = current.left;
				root.data = current.data;
				root.right = deleteNode(current.data, root.right);
			}
		}
		return root;
	}


	/**
	 * Returns the {@code size} of the binary search tree. It traverses the tree by
	 * calling a recursive traversal helper method which increments the
	 * {@code size class} variable as each node is visited.
	 * 
	 * @return size of the binary search tree.
	 */
	public int size() {
		size = 0;
		sizeHelper(root);
		return size;
	}

	private void sizeHelper(TreeNode<T> node) {
		if (node == null) {
			return;
		}

		++size;
		sizeHelper(node.left);
		sizeHelper(node.right);
	}

	/**
	 * Searches the tree for the node containing the key and returns the reference
	 * to the node's data if found. Returns null otherwise.
	 * 
	 * @param key to search
	 * @return T reference to the node's data
	 */
	public T search(T key) {
		TreeNode<T> currNode = root;

		while (currNode != null && currNode.data.compareTo(key) != 0) {
			if (currNode.data.compareTo(key) > 0) {
				if (currNode.left != null) {
					currNode = currNode.left;
				} else {
					return null;
				}
			} else if (currNode.data.compareTo(key) < 0) {
				if (currNode.right != null) {
					currNode = currNode.right;
				} else {
					return null;
				}
			}
		}

		return currNode != null ? currNode.data : null;
	}
	
	public void levelOrderOutputTree() {
		levelOrderOutput(root, 40);
	}

	private void levelOrderOutput(TreeNode<T> node, int spaces) {
		Queue<TreeNode<T>> queue = new Queue<>();

		queue.offer(node);

		while (!queue.isEmpty()) {
			for (int i = 1; i <= spaces; i++) {
				System.out.print(" ");
			}

			Queue<TreeNode<T>> utilQueue = new Queue<>();

			while (!queue.isEmpty()) {
				utilQueue.offer(queue.poll());
			}

			Queue<TreeNode<T>> utilQueue2 = new Queue<>();

			while (!utilQueue.isEmpty()) {
				System.out.printf("%s       ", utilQueue.peek().data);
				utilQueue2.offer(utilQueue.poll());
			}

			System.out.println();

			while (!utilQueue2.isEmpty()) {
				if (utilQueue2.peek().left != null) {
					queue.offer(utilQueue2.peek().left);
				}

				if (utilQueue2.peek().right != null) {
					queue.offer(utilQueue2.peek().right);
				}

				utilQueue2.poll();
			}

			spaces -= 3;
		}
	}

	public void outputTree() {
		outputTreeHelper(root, 5);
	}

	private void outputTreeHelper(TreeNode<T> node, int totalSpaces) {
		while (node != null) {
			outputTreeHelper(node.right, totalSpaces + 5);
			for (int i = 1; i <= totalSpaces; i++) {
				System.out.print(" ");
			}
			System.out.printf("%s%n", node.data);
			node = node.left;
			totalSpaces += 5;
		}
	}

	@Override
	public String toString() {
		return preorderTraversal();
	}
}

