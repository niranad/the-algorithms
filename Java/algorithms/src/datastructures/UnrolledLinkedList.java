package datastructures;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * A two-dimensional {@code LinkedList} containing a
 * {@linkplain CircularLinkedList} at each node or block. Each node maintains
 * the property such that its embedded list has no more than
 * {@code Math.sqrt(n)} elements where {@code n} is the total number of elements
 * in this list ({@code null} elements are not allowed).
 * 
 * <p>
 * Each instance of this list can store a maximum number of elements equal to
 * {@code Integer.MAX_VALUE}. This list contains the elements in reversed order
 * of insertion across the embedded lists, with the last inserted element being
 * the first element in the first inner list and the first element being the
 * last element in the last inner list. However, this implementation treats the
 * elements as though in their normal order of insertion, with {@code index 0}
 * starting from the rightmost element (as shown when printed) backwards.
 * </p>
 * 
 * <p>
 * This list has a better performance than doubly-linked or circular
 * {@code LinkedList} since insertion, find, and deletion operations take
 * O(n^(1/2)) time.
 * </p>
 *
 */
public class UnrolledLinkedList<E> extends AbstractSequentialList<E>
	implements List<E>, Serializable {
	private static final long serialVersionUID = 6120042534897357796L;
	/*
	 * The 'head' reference is used here to refer to the point where elements are
	 * initially added before being distributed or moved to other block lists, and
	 * not necessarily the beginning of this list. As a result, the last element
	 * added is the first node in head whereas the first element is the last node in
	 * tail, thereby reversing the order of insertion. This implementation works
	 * backward from the tail to put the elements in the order in which they were
	 * added.
	 */
	private ULLNode<E> head;
	private ULLNode<E> tail;

	private int size;
	private int listSize;

	public UnrolledLinkedList() {
		head = tail = new ULLNode<E>();
		listSize = 1;
	}

	public UnrolledLinkedList(Collection<E> collection) {
		for (E elem : collection) {
			add(elem);
			size++;
		}
	}

	/**
	 * Adds an element to the end of this list. This operation fails if the current
	 * size of this list is equal to {@code Integer.MAX_VALUE}.
	 * 
	 * @return {@code true} if the element was added, {@false} if the operation
	 *         failed.
	 */
	@Override
	public boolean add(E data) throws NullPointerException {
		if (data == null) {
			throw new NullPointerException();
		}

		if (size < Integer.MAX_VALUE) {
			head.list.addFirst(data);
			size++;
			maintainProperty();
			return true;
		}

		return false;
	}

	private void maintainProperty() {
		int property = (int) Math.round(Math.sqrt(size()));
		ULLNode<E> current = head;

		while (current != null) {
			if (current.list.size() > property) {
				moveLastElemToNextHead(current);
			}
			current = current.next;
		}
	}

	private void moveLastElemToNextHead(ULLNode<E> node) {
		if (node.next == null) {
			node.next = new ULLNode<>();
			tail = node.next;
			tail.previous = node;
			listSize++;
		}

		E lastElem = node.list.removeLast();
		node.next.list.addFirst(lastElem);
	}

	/**
	 * Inserts the specified element at the specified position in this list if
	 * {@code index >= 0 && index < size()}. Shifts the element currently at that
	 * position(if any) and any subsequent elements to the right (adds one to their
	 * indices).
	 * 
	 */
	@Override
	public void add(int index, E data) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		ULLNode<E> current = tail;
		int accumElemCount = current.list.size();

		while (accumElemCount < (index + 1)) {
			current = current.previous;
			accumElemCount += current.list.size();
		}

		insertIntoNode(current, data, accumElemCount - index);
	}

	private void insertIntoNode(ULLNode<E> node, E data, int index) {
		node.list.add(index, data);
		size++;
		maintainProperty(node);
	}

	private void maintainProperty(ULLNode<E> node) {
		int property = (int) Math.round(Math.sqrt(size()));
		ULLNode<E> current = node;

		while (current != null) {
			if (current.list.size() > property) {
				moveLastElemToNextHead(current);
			}
			current = current.next;
		}
	}

	@Override
	public boolean remove(Object data) {
		if (size() == 0) {
			return false;
		}

		ULLNode<E> current = head;
		for (int i = 0; i < listSize(); i++) {
			if (current.list.remove(data)) {
				return true;
			}
			current = current.next;
		}

		size--;
		return false;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		ULLNode<E> current = tail;
		int accumElemCount = current.list.size(); // accumulated sum of elements size in current
													// block

		while (accumElemCount < (index + 1)) {
			current = current.previous;
			accumElemCount += current.list.size();
		}

		E removed = current.list.remove(accumElemCount - (index + 1));
		size--;
		redistributeElems(current);
		return removed;
	}

	private void redistributeElems(ULLNode<E> removalList) {
		if (!Objects.equals(tail, removalList)) {
			ULLNode<E> current = removalList;
			E nextFirst = null;
			/*
			 * Move first element in next nodes's list to be last element in current node's
			 * list until the tail node is reached.
			 */
			do {
				nextFirst = current.next.list.removeFirst();
				current.list.addLast(nextFirst);
				current = current.next;
			} while (!Objects.equals(tail, current));
		}

		// If tail block is empty and not the same as head, remove it
		if (tail.list.size() == 0 && listSize() > 1) {
			tail = tail.previous;
			tail.next = null;
			listSize--;
		}
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		if (size() == 0) {
			return null;
		}

		if (index == 0) {
			return getFirst();
		}

		if (index == size() - 1) {
			return getLast();
		}

		ULLNode<E> current = tail;
		int accumElemCount = current.list.size();

		while (accumElemCount < index + 1) {
			current = current.previous;
			accumElemCount += current.list.size();
		}

		return current.list.get(accumElemCount - (index + 1));
	}

	public E getLast() {
		if (size == 0) {
			return null;
		}

		return head.list.getFirst();
	}

	public E getFirst() {
		if (size == 0) {
			return null;
		}

		return tail.list.getLast();
	}

	@Override
	public boolean contains(Object data) {
		if (size == 0) {
			return false;
		}

		ULLNode<E> current = head;
		for (int i = 0; i < listSize; i++) {
			if (current.list.contains(data)) {
				return true;
			}
			current = current.next;
		}

		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return listIterator();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		head = tail = new ULLNode<E>();
		size = 0;
		listSize = 1;
	}

	private class ULLNode<T> {
		CircularLinkedList<T> list;
		ULLNode<T> next;
		ULLNode<T> previous;

		ULLNode() {
			list = new CircularLinkedList<>();
		}
	}

	@Override
	public ListIterator<E> listIterator(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ULLNode<E> startNode = tail;
		int accumElemCount = startNode.list.size();
		
		while (accumElemCount < (index + 1)) {
			startNode = startNode.previous;
			accumElemCount += startNode.list.size();
		}
		
		
		final int startIdx = index - 1;
		
		return new ListIterator<E>() {
			private int totalElements = size();
			private E currentElem;
			private ULLNode<E> currentList;
			private int currElemIdx = startIdx;
			private int currListIdx =
			private boolean isIteratorEnd;
			
			
			private void checkConcurrentModification() {
				if (totalElements < size()) {
					throw new ConcurrentModificationException();
				}
			}
			
			@Override
			public boolean hasNext() {
				checkConcurrentModification();
				return (currElemIdx + 1) < size();
			}

			@Override
			public E next() {
				checkConcurrentModification();
				
				ULLNode<E> currNode = tail;
				
				
				if ()
				return null;
			}

			@Override
			public boolean hasPrevious() {
				checkConcurrentModification();
				return false;
			}

			@Override
			public E previous() {
				checkConcurrentModification();
				return null;
			}

			@Override
			public int nextIndex() {
				checkConcurrentModification();
				return 0;
			}

			@Override
			public int previousIndex() {
				checkConcurrentModification();
				return 0;
			}

			@Override
			public void remove() {
				checkConcurrentModification();

			}

			@Override
			public void set(E e) {
				checkConcurrentModification();

			}

			@Override
			public void add(E e) {
				checkConcurrentModification();

			}

		};

	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the number of nodes in this list.
	 * 
	 * @return number of nodes
	 */
	public int listSize() {
		return listSize;
	}

	/**
	 * Returns a string representation of this collection. The string representation
	 * consists of a list of the collection's embedded
	 * {@linkplain CircularLinkedList} of elements in reversed order of insertion.
	 * Each embedded list is enclosed with "[]" and comma-space-separated from the
	 * next list. The outer list is enclosed with "[]".
	 */
	public String toString() {
		if (size == 0) {
			return "[]";
		}

		String str = "[";
		ULLNode<E> current = head;
		int i = 0;

		while (current != null) {
			str += String.valueOf(current.list);
			if (++i < listSize()) {
				str += ", ";
			}
			current = current.next;
		}

		str += "]";
		return str;
	}
}
