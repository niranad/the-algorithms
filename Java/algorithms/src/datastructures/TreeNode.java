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
	
	public TreeNode<T> getLeft() {
		return left;
	}
	
	public TreeNode<T> getRight() {
		return right;
	}
	
	T insert(T data) {
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
	
	T insert(T data, Comparator<T> comp) {
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
