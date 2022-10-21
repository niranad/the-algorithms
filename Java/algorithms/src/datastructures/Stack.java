package datastructures;

import exception.EmptyStackException;

public class Stack<T> {
	StackNode<T> firstNode;
	StackNode<T> lastNode;

	public Stack() {
		firstNode = lastNode = null;
	}

	public T push(T data) {
		if (isEmpty()) {
			firstNode = lastNode = new StackNode<T>(data);
		} else {
			lastNode = lastNode.nextNode = new StackNode<T>(data, lastNode, null);
		}

		return data;
	}
	
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		T removedItem;

		if (firstNode == lastNode) {
			removedItem = lastNode.data;
			firstNode = lastNode = null;
		} else {
			removedItem = lastNode.data;
			lastNode = lastNode.prevNode;
			lastNode.nextNode = null;
		}

		return removedItem;
	}

	public T get(int index) throws EmptyStackException, IndexOutOfBoundsException {
		if (isEmpty()) {
			throw new EmptyStackException("cannot retrieve data from an empty stack");
		}

		int i = 0;
		StackNode<T> currNode = firstNode;

		if (index <= size() - 1 && index >= 0) {
			while (i < index) {
				currNode = currNode.nextNode;
				i++;
			}
		} else {
			throw new IndexOutOfBoundsException();
		}

		return currNode.data;
	}

	public int size() {
		if (isEmpty()) {
			return 0;
		}

		StackNode<T> currNode = firstNode;
		int i = 0;

		while (currNode != null) {
			currNode = currNode.nextNode;
			i++;
		}

		return i;
	}

	public boolean isEmpty() {
		return firstNode == null;
	}

	@Override
	public String toString() {
		StackNode<T> currNode = firstNode;
		String s = "[";
		int i = 0;

		while (i < size()) {
			s += i != size() - 1 ? currNode.data + ", " : currNode.data + "]";
			currNode = currNode.nextNode;
			i++;
		}
		
		return size() == 0 ? "" : s;
	}
}

class StackNode<T> {
	T data;
	StackNode<T> prevNode;
	StackNode<T> nextNode;

	StackNode(T data) {
		this(data, null, null);
	}

	StackNode(T data, StackNode<T> prevNode, StackNode<T> nextNode) {
		this.data = data;
		this.prevNode = prevNode;
		this.nextNode = nextNode;
	}
}
