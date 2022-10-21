package datastructures;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

import exception.EmptyListException;

public class DoublyLinkedList<T> extends AbstractSequentialList<T> {
	private DoublyLinkedNode<T> firstNode;
	private DoublyLinkedNode<T> lastNode;
	private String name;

	public DoublyLinkedList() {
		this("DoublyLinkedList");
	}

	public DoublyLinkedList(String name) {
		this.name = name;
		firstNode = lastNode = null;
	}

	public void insertAtFront(T item) throws NullPointerException {
		if (item == null) {
			throw new NullPointerException("item cannot be null");
		}

		if (isEmpty()) {
			firstNode = lastNode = new DoublyLinkedNode<T>(item);
		} else {
			firstNode = firstNode.prevNode = new DoublyLinkedNode<T>(null, item,
				firstNode);
		}
	}

	public void insertAtBack(T item) throws NullPointerException {
		if (item == null) {
			throw new NullPointerException("item cannot be null");
		}

		if (isEmpty()) {
			firstNode = lastNode = new DoublyLinkedNode<T>(item);
		} else {
			lastNode = lastNode.nextNode = new DoublyLinkedNode<T>(lastNode, item, null);
		}
	}

	public T insert(int index, T item)
		throws NullPointerException, IndexOutOfBoundsException {
		if (item == null) {
			throw new NullPointerException("item cannot be null");
		}

		int i = 0;
		DoublyLinkedNode<T> currNode = firstNode;

		if (index <= this.size() - 1 && i >= 0) {
			while (i < index) {
				currNode = currNode.nextNode;
				++i;
			}

			if (index == 0) {
				firstNode = new DoublyLinkedNode<T>(null, item, firstNode);
				firstNode.nextNode.prevNode = firstNode;
			} else {
				DoublyLinkedNode<T> nodeBeforeIndex = currNode.prevNode;
				DoublyLinkedNode<T> newNode = new DoublyLinkedNode<>(nodeBeforeIndex,
					item, currNode);
				currNode.prevNode = newNode;
				nodeBeforeIndex.nextNode = newNode;
			}

			return item;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public T get(int index) throws EmptyListException, IndexOutOfBoundsException {
		if (isEmpty()) {
			throw new EmptyListException(name + ": cannot retrieve from an empty list");
		} else if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(
				name + ": index (" + index + ") is invalid");
		}

		int i = 0;
		DoublyLinkedNode<T> currNode = firstNode;

		while (i < index) {
			currNode = currNode.nextNode;
			++i;
		}

		return currNode.data;
	}
	
	public void reverse() {
		DoublyLinkedNode<T> currNode = firstNode;
		
		while (currNode != null) {
			DoublyLinkedNode<T> prevNode = currNode.prevNode;
			DoublyLinkedNode<T> nextNode = currNode.nextNode;
			currNode.prevNode = nextNode;
			currNode.nextNode = prevNode;
			currNode = currNode.prevNode;
		}
		
		DoublyLinkedNode<T> last = firstNode;
		DoublyLinkedNode<T> first = lastNode;
		firstNode = first;
		lastNode = last;
	}

	public T removeFromFront() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException(name + ": cannot remove from an empty list");
		}

		T removedItem;

		if (firstNode == lastNode) {
			removedItem = firstNode.data;
			firstNode = lastNode = null;
		} else {
			removedItem = firstNode.data;
			firstNode = firstNode.nextNode;
			firstNode.prevNode = null;
		}

		return removedItem;
	}

	public T removeFromBack() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException(name + ": cannot remove from an empty list");
		}

		T removedItem;

		if (firstNode == lastNode) {
			removedItem = lastNode.data;
			firstNode = lastNode = null;
		} else {
			DoublyLinkedNode<T> currNode = firstNode;

			while (currNode.nextNode != lastNode) {
				currNode = currNode.nextNode;
			}

			removedItem = currNode.nextNode.data;
			lastNode = currNode;
			currNode.nextNode = null;
		}

		return removedItem;
	}

	public T remove(int index) throws EmptyListException, IndexOutOfBoundsException {
		if (isEmpty()) {
			throw new EmptyListException(name + ": cannot remove from an empty list");
		} else if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(
				name + ": index (" + index + ") is invalid");
		}

		int i = 0;
		DoublyLinkedNode<T> currNode = firstNode;

		while (i < index) {
			currNode = currNode.nextNode;
			i++;
		}

		T removedItem = currNode.data;
		
		if (index == 0) {
			if (size() == 1) {
				firstNode = lastNode = null;
			} else {
				firstNode = firstNode.nextNode;
				firstNode.prevNode = null;
			}
		} else if (index == size() - 1) {
			lastNode = lastNode.prevNode;
			lastNode.nextNode = null;
		} else {
			currNode.prevNode.nextNode = currNode.nextNode;
			currNode.nextNode.prevNode = currNode.prevNode;
			currNode = null;
		}

		return removedItem;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return null;
	}

	@Override
	public int size() {
		int size = 0;
		DoublyLinkedNode<T> currNode = firstNode;

		while (currNode != null) {
			++size;
			currNode = currNode.nextNode;
		}

		return size;
	}

	public boolean isEmpty() {
		return firstNode == null;
	}

	@Override
	public String toString() {
		DoublyLinkedNode<T> node = firstNode;
		String s = "";

		while (node != null) {
			s += node.data + (node.nextNode == null ? "" : ", ");
			node = node.nextNode;
		}

		return "[" + s + "]";
	}

	// for manual (main class) test for integer linked list
	public void print() {
		DoublyLinkedNode<T> node = firstNode;

		System.out.printf("The list by node(s) (prevData, data, nextData) is: %n");

		while (node != null) {
			System.out.printf("(prev=%d, %d, next=%d)%s",
				node.prevNode == null ? -9999 : node.prevNode.data, node.data,
				node.nextNode == null ? -9999 : node.nextNode.data,
				(node.nextNode != null) ? ", " : "");
			node = node.nextNode;
		}

		System.out.printf("%n%n");
	}
}

class DoublyLinkedNode<T> {
	T data;
	DoublyLinkedNode<T> prevNode;
	DoublyLinkedNode<T> nextNode;

	DoublyLinkedNode(T data) {
		this(null, data, null);
	}

	DoublyLinkedNode(DoublyLinkedNode<T> prevNode, T data, DoublyLinkedNode<T> nextNode) {
		this.prevNode = prevNode;
		this.data = data;
		this.nextNode = nextNode;
	}
}
