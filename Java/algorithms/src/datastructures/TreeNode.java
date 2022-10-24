package datastructures;

import java.util.Comparator;

public class TreeNode<T extends Comparable<T>> {
	T data;
	TreeNode<T> left;
	TreeNode<T> right;
	
	TreeNode(T data) {
		this.data = data;
		left = right = null;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		if (data == null) {
			throw new IllegalArgumentException();
		}
		this.data = data;
	}
	
	public TreeNode<T> getLeft() {
		return left;
	}
	
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}
	
	public TreeNode<T> getRight() {
		return right;
	}
	
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}
	
	public T insert(T data) {
		if (data.compareTo(this.data) < 0) {
			if (left == null) {
				left = new TreeNode<T>(data);
			} else {
				return left.insert(data);
			}
		} else if (data.compareTo(this.data) > 0) {
			if (right == null) {
				right = new TreeNode<T>(data);
			} else {
				return right.insert(data);
			}
		} else {
			return null;
		}
		
		return data;
	}
	
	public T insert(T data, Comparator<T> comp) {
		if (comp.compare(data, this.data) < 0) {
			if (left == null) {
				left = new TreeNode<T>(data);
			} else {
				return left.insert(data);
			}
		} else if (comp.compare(data, this.data) > 0) {
			if (right == null) {
				right = new TreeNode<T>(data);
			} else {
				return right.insert(data);
			}
		} else {
			return null;
		}
		
		return data;
	}
}
