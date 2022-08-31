package datastructures;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class implements a {@code CircularLinkedList} backed by "sequential
 * access" data store with unidirectional element nodes. {@code Null} elements
 * are not allowed in this list. Each instance of this list can only contain a
 * maximum of {@code Integer.MAX_VALUE} elements. The implementation is similar
 * to that of doubly-linked list except that the end of the list is not
 * indicated with a {@code null} reference but the element with a reference to
 * the head. This variation of {@code LinkedList} is useful for managing system
 * resources and can be used to implement stacks and queues.
 *
 * @param <T>
 */
public class CircularLinkedList<T> extends AbstractSequentialList<T>
	implements List<T>, Serializable {
	private static final long serialVersionUID = 8053160007983243372L;
	private CLLNode<T> head;
	private CLLNode<T> tail;
	private int size;

	public CircularLinkedList() {
	}

	public CircularLinkedList(Collection<T> collection) {
		for (T elem : collection) {
			addLast(elem);
			size++;
		}
	}

	/**
	 * Adds an element to the beginning of this list only if there are less elements
	 * than the maximum number of elements (i.e., {@code Integer.MAX_VALUE}) for
	 * this instance of the list.
	 * 
	 * @param data
	 * @throws NullPointerException     if element is {@code null}
	 * @throws IllegalArgumentException if element is not a {@link Serializable}
	 *                                  object.
	 */
	public void addFirst(T data) throws NullPointerException, IllegalArgumentException {
		if (data == null) {
			throw new NullPointerException();
		}

		if (!(data instanceof Serializable)) {
			throw new IllegalArgumentException();
		}

		if (size() == Integer.MAX_VALUE) {
			return;
		}

		CLLNode<T> newNode = createNode(data);

		if (head == null) { // If list is empty
			head = newNode;
			head.next = head;
			tail = head;
		} else if (head == tail) { // If list has one element
			newNode.next = head;
			head.next = newNode;
			tail = head;
			tail.previous = newNode;
			head = newNode;
		} else {
			newNode.next = head;
			head.previous = newNode;
			tail.next = newNode;
			head = newNode;
		}

		size++;
	}

	/**
	 * Adds an element to the end of this list only if there are less elements than
	 * the maximum number of elements (i.e., {@code Integer.MAX_VALUE}) for this
	 * instance of the list.
	 * 
	 * @param data element to be inserted
	 * @throws NullPointerException     if element is {@code null}
	 * @throws IllegalArgumentException if element is not a {@link Serializable}
	 *                                  object
	 */
	public void addLast(T data) throws NullPointerException, IllegalArgumentException {
		if (data == null) {
			throw new NullPointerException();
		}

		if (!(data instanceof Serializable)) {
			throw new IllegalArgumentException();
		}

		if (size() == Integer.MAX_VALUE) {
			return;
		}

		CLLNode<T> newNode = createNode(data);

		if (head == null) { // If list is empty
			head = newNode;
			head.next = head;
			tail = head;
		} else if (head == tail) { // If list has one element
			newNode.next = head;
			newNode.previous = head;
			head.next = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.next = head;
			newNode.previous = tail;
			tail = newNode;
		}

		size++;
	}

	/**
	 * Adds an element to the end of this list. This operation fails if the size of
	 * the list equals {@code Integer.MAX_VALUE}.
	 * 
	 * @return {@code true} if the operation succeeded, {@code false} otherwise.
	 */
	@Override
	public boolean add(T data) throws NullPointerException {
		if (data == null) {
			throw new NullPointerException();
		}

		if (size() == Integer.MAX_VALUE) {
			return false;
		}

		addLast(data);
		return true;
	}

	/**
	 * Adds an element at the specified index, if
	 * {@code index >= 0 && index <= size}.
	 * 
	 * @param data  element to be inserted
	 * @param index position where the new element is inserted
	 *
	 */
	@Override
	public void add(int index, T data)
		throws IndexOutOfBoundsException, NullPointerException, IllegalArgumentException {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
		}

		if (data == null) {
			throw new NullPointerException();
		}

		if (!(data instanceof Serializable)) {
			throw new IllegalArgumentException();
		}

		if (index == 0) {
			addFirst(data);
		} else if (index == size()) {
			addLast(data);
		} else {
			CLLNode<T> newNode = createNode(data);
			CLLNode<T> current;
			int currIdx;

			if (size() - index > index) { // If index is closer to the head
				current = head;
				currIdx = 0;
				while (index > currIdx) {
					current = current.next;
					currIdx++;
				}
			} else {
				current = tail;
				currIdx = size() - 1;
				while (currIdx > index) {
					current = current.previous;
					currIdx--;
				}
			}

			newNode.next = current;
			newNode.previous = current.previous;
			current.previous.next = newNode;
			current.previous = newNode;
			size++;
		}
	}

	/**
	 * Removes and returns the first element of this list.
	 * 
	 * @return {@code data} at the first element or {@code null} if list is empty
	 */
	public T removeFirst() {
		if (head != null) { // If list is not empty
			T removedData = head.data;

			if (head == tail) { // If list has one element
				head = null;
				tail = head;
				size--;
				return removedData;
			}

			head = head.next;
			head.previous = null;
			tail.next = head;
			size--;
			return removedData;
		}

		return null;
	}

	/**
	 * Removes and returns the last element of the circular linked list.
	 * 
	 * @return {@code data} at the last element or {@code null} if list is empty
	 */
	public T removeLast() {
		if (head != null) { // If list is not empty
			T removedData;

			if (head == tail) { // If list has one element
				removedData = head.data;
				head = null;
				tail = head;
				size--;
				return removedData;
			}

			removedData = tail.data;
			tail.previous.next = tail.next;
			tail = tail.previous;
			size--;
			return removedData;
		}

		return null;
	}

	@Override
	public T remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
		}

		if (index == size() - 1) {
			return removeLast();
		}

		if (index == 0) {
			return removeFirst();
		}

		CLLNode<T> current;
		int currIdx;

		if (size() - index > index) {
			current = head;
			currIdx = 0;

			while (index > currIdx) {
				current = current.next;
				currIdx++;
			}
		} else {
			current = tail;
			currIdx = size() - 1;

			while (currIdx > index) {
				current = current.previous;
				currIdx--;
			}
		}

		current.previous.next = current.next;
		current.next.previous = current.previous;
		size--;
		return current.data;
	}

	@Override
	public int indexOf(Object data) throws NullPointerException {
		if (data == null) {
			throw new NullPointerException();
		}

		if (head != null) { // If list is not empty
			CLLNode<T> current = head;
			int currIdx = 0;

			do {
				if (data.equals(current.data)) {
					return currIdx;
				}
				current = current.next;
				currIdx++;
			} while (current.next != head);
		}

		return -1;
	}

	@Override
	public boolean remove(Object data) throws NullPointerException {
		if (data == null) {
			throw new NullPointerException();
		}

		if (head != null) { // If list is not empty
			CLLNode<T> current = head;
			do {
				if (data.equals(current.data)) {
					if (current == head) {
						removeFirst();
						return true;
					}

					current.previous.next = current.next;
					if (current != tail) {
						current.next.previous = current.previous;
					}
					size--;
					return true;
				}

				current = current.next;
			} while (current != head);
		}

		return false;
	}

	/**
	 * Returns the first element in the circular linked list.
	 * 
	 * @return first element, {@code null} if list is empty.
	 */
	public T getFirst() {
		if (head != null) { // If list is not empty
			return head.data;
		}
		return null;
	}

	/**
	 * Returns the last element of the circular linked list.
	 * 
	 * @return last element, {@code null} if list is empty
	 */
	public T getLast() {
		if (head != null) { // If list is not empty
			return tail.data;
		}
		return null;
	}

	private CLLNode<T> createNode(T data) {
		return new CLLNode<T>(data);
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
		}

		CLLNode<T> current;
		int currIdx;

		if (size() - index > index) { // If element to get is closer to the head
			current = head;
			currIdx = 0;

			while (index > currIdx) {
				current = current.next;
				currIdx++;
			}
		} else {
			current = tail;
			currIdx = size - 1;

			while (currIdx > index) {
				current = current.previous;
				currIdx--;
			}
		}

		return current.data;
	}

	/**
	 * Replaces the {@code oldData} if found with the {@code newData} in this list.
	 * 
	 * @param oldData
	 * @param newData
	 * @return {@code true} if the replacement succeeds, {@code false} otherwise
	 */
	public boolean replace(T oldData, T newData) {
		if (head != null) { // If list is not empty
			CLLNode<T> current = head;
			do {
				if (oldData.equals(current.data)) {
					current.data = newData;
					return true;
				}

				current = current.next;
			} while (current != head);
		}

		return false;
	}

	@Override
	public Object[] toArray() {
		if (head != null) {
			Object[] arr = new Object[size()];
			CLLNode<T> node = head;
			int i = 0;

			do {
				arr[i] = node.data;
				node = node.next;
				i++;
			} while (node != head);

			return arr;
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> E[] toArray(E[] arr) throws ArrayStoreException, NullPointerException {
		if (arr == null) {
			throw new NullPointerException();
		}

		if (head != null) { // If list is not empty
			CLLNode<T> current = head;
			int i = 0;

			if (arr.length < size) {
				Object[] newArr = new Object[size];
				do {
					newArr[i] = current.data;
					current = current.next;
					i++;
				} while (current != head);

				return (E[]) newArr;
			} else {
				try {
					do {
						arr[i] = (E) current.data;
						current = current.next;
						i++;
					} while (current != head);

					if (arr.length > size) {
						arr[size] = null;
					}

					return arr;
				} catch (ClassCastException e) {
					throw new ArrayStoreException();
				}
			}
		}

		return null;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean contains(Object data) {
		if (head != null) { // If list is not empty
			CLLNode<T> current = head;
			do {
				if (data.equals(current.data)) {
					return true;
				}
				current = current.next;
			} while (current != head);
		}

		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		head = null;
		tail = head;
		size = 0;
	}

	@Override
	public ListIterator<T> listIterator(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
		}

		CLLNode<T> current;
		int currIdx;

		if (size() - index > index) {
			current = head;
			currIdx = 0;
			while (index > currIdx) {
				current = current.next;
				currIdx++;
			}
		} else {
			current = tail;
			currIdx = size() - 1;
			while (currIdx > index) {
				current = current.previous;
				currIdx--;
			}
		}

		final CLLNode<T> start = current;

		return new ListIterator<T>() {
			private CLLNode<T> cursor = start;
			private CLLNode<T> lastReturnedElem;
			private int currentIndex = index - 1;
			private int listSize = size();
			private boolean isListEnd;
			private boolean canRemoveFromList;

			@Override
			public void add(T data) {
				checkConcurrentMod();

				if (cursor == null || cursor == head) { // If list is empty or cursor is at the head
					addFirst(data);
					cursor = head;
				} else {
					CLLNode<T> newNode = createNode(data);
					newNode.next = cursor;
					newNode.previous = cursor.previous;
					cursor.previous.next = newNode;
					cursor.previous = newNode;
					size++;

					if (canRemoveFromList) {
						canRemoveFromList = false;
					}
				}

				currentIndex++;
				listSize++;
			}

			@Override
			public void remove() throws IllegalStateException {
				checkConcurrentMod();

				if (!canRemoveFromList) {
					throw new IllegalStateException();
				}

				if (lastReturnedElem == null) {
					return;
				}

				if (lastReturnedElem == head) {
					if (head == tail) { // If list has one element
						head = null;
						tail = head;
					} else {
						head = head.next;
						head.previous = null;
						tail.next = head;
					}
				} else {
					lastReturnedElem.previous.next = lastReturnedElem.next;
					if (lastReturnedElem != tail) {
						lastReturnedElem.next.previous = lastReturnedElem.previous;
					}
				}

				if (hasPrevious()) {
					cursor = cursor.previous;
				} else {
					cursor = null;
				}

				currentIndex--;
				size--;
				listSize--;
				canRemoveFromList = false;
			}

			private void checkConcurrentMod() {
				if (size() != listSize) {
					throw new ConcurrentModificationException();
				}
			}

			@Override
			public boolean hasPrevious() {
				checkConcurrentMod();
				return (cursor != null && cursor != head);
			}

			@Override
			public boolean hasNext() {
				checkConcurrentMod();
				return (cursor != null && !isListEnd);
			}

			@Override
			public T next() throws NoSuchElementException {
				checkConcurrentMod();
				// If iterator has one or more elements to process
				if (cursor != null && !isListEnd) {
					CLLNode<T> current = cursor;

					if (current.next == head) { // If the cursor is at the end of list
						isListEnd = !isListEnd;
					}

					if (currentIndex < size() - 1) { // If the cursor position is before the last element
						cursor = cursor.next;
					}

					if (!canRemoveFromList)
						canRemoveFromList = true;

					currentIndex++;
					lastReturnedElem = current;
					return current.data;
				}

				throw new NoSuchElementException();
			}

			@Override
			public T previous() throws NoSuchElementException {
				checkConcurrentMod();

				if (cursor != null && currentIndex >= 0) { // If list is not empty or there are more
													// elements before the cursor
					if (isListEnd) {
						isListEnd = !isListEnd;
					}

					CLLNode<T> current;
					int previousIdx;

					if (listSize - currentIndex > currentIndex) { // If element at index is closer to the start of
													// list
						current = head;
						previousIdx = 0;
						while (currentIndex > previousIdx) {
							current = current.next;
							previousIdx++;
						}
					} else {
						current = tail;
						previousIdx = listSize - 1;
						while (previousIdx > currentIndex) {
							current = current.previous;
							previousIdx--;
						}
					}

					if (!canRemoveFromList) {
						canRemoveFromList = true;
					}

					cursor = current;
					currentIndex--;
					lastReturnedElem = current;
					return current.data;
				}

				throw new NoSuchElementException();
			}

			@Override
			public int nextIndex() {
				checkConcurrentMod();
				return (cursor == null ? 0 : currentIndex + 1);
			}

			@Override
			public int previousIndex() {
				checkConcurrentMod();
				return currentIndex;
			}

			@Override
			public void set(T data) {
				checkConcurrentMod();
			}
		};
	}

	public ListIterator<T> listIterator() {
		return listIterator(0);
	}
	
	@Override
	public String toString() {
		if (size() == 0) {
			return "[]";
		}
		
		String str = "[";
		int i = 0;
		
		for (T elem : this) {
			str += String.valueOf(elem);
			if (++i < size()) str += ", ";
		}
		
		str += "]";
		return str;
	}

	private class CLLNode<E> {
		E data;
		CLLNode<E> next;
		CLLNode<E> previous;

		CLLNode(E data) {
			this.data = data;
		}
	}
}
