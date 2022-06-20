package datastructures;

public class LinkedList<T> {
	private LLNode<T> head;
	private int length;

	public LinkedList() {
		head = null;
	}

	/**
	 * Inserts a node with the given value at the beginning of the list.
	 * 
	 * @param value value of node to be inserted
	 */
	public void insertFirst(T value) {
		LLNode<T> newNode = new LLNode<T>(value);

		if (head == null) {
			head = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}

		length = length + 1;
	}

	/**
	 * Inserts a node with the given value at the end of the list.
	 * 
	 * @param value of node to be inserted
	 */
	public void insertLast(T value) {
		LLNode<T> newNode = new LLNode<T>(value);

		if (head == null) {
			head = newNode;
			return;
		}

		LLNode<T> current = head;

		// traverse the list to the last node whose next pointer is null
		while (current.next != null) {
			current = current.next;
		}

		current.next = newNode;

		length = length + 1;
	}

	/**
	 * Inserts a node with the given value at the specified index. Returns true if
	 * the operation was successful, otherwise false.
	 * 
	 * @param value of node to be inserted.
	 * @param index position at which to insert the node.
	 * @return {@code true} if the {@code index} exists and the insertion succeeds,
	 *         {@code false} otherwise.
	 */
	public boolean insert(T value, int index) {
		if (index >= 0 && index <= length - 1) {
			LLNode<T> newNode = new LLNode<T>(value);

			if (index == 0) {
				newNode.next = head;
				head = newNode;
				length = length + 1;
				return true;
			}

			LLNode<T> current = head;
			int currIdx = 0; // index of the current node

			while (current.next != null) {
				if (currIdx == index - 1) {
					break;
				}
				current = current.next;
				++currIdx;
			}

			LLNode<T> nodeAtIndex = current.next;
			newNode.next = nodeAtIndex;
			current.next = newNode;
			length = length + 1;

			return true;
		}

		return false;
	}

	/**
	 * Removes the first node from the list.
	 * 
	 * @return value at the node removed, {@code null} if list is empty.
	 *
	 */
	public T removeFirst() {
		// if the list is empty
		if (head == null) {
			return null;
		}

		T firstValue = head.value;
		head = head.next;

		return firstValue;
	}

	/**
	 * Removes the last node in the list and returns its value.
	 * 
	 * @return value of the removed last node, {@code null} if list is empty.
	 */
	public T removeLast() {
		if (head == null) {
			return null;
		}

		T lastValue;

		// if the list has only one node
		if (head.next == null) {
			lastValue = head.value;
			head = null;
			return lastValue;
		}

		LLNode<T> current = head;

		while (current.next.next != null) {
			current = current.next;
		}

		lastValue = current.next.value;
		current.next = null;

		return lastValue;
	}

	/**
	 * Removes the node at the given index such that
	 * {@code 0 <= index <= listLength - 1}.
	 * 
	 * @param index of the node to be removed.
	 * @return value at the removed node if index exists, {@code null} otherwise.
	 */
	public T remove(int index) {
		// if index exists
		if (index >= 0 && index <= length - 1) {
			T theNodeValue;

			if (index == 0) {
				theNodeValue = head.value;
				head = head.next;
				length = length - 1;
				return theNodeValue;
			}

			LLNode<T> current = this.head;
			int currIdx = 0;

			while (current.next != null) {
				// if the current node is the node before the node to be removed,
				if (currIdx == index - 1) {
					break;
				}
				current = current.next;
				currIdx++;
			}

			LLNode<T> theNode = current.next; // node to remove
			LLNode<T> nodeAfter = theNode.next;
			theNodeValue = theNode.value;
			current.next = nodeAfter;
			theNode.next = null; // detach the node from succeeding list members

			return theNodeValue;
		}

		return null;
	}

	/**
	 * Removes the first node whose value equals the given value if it exists.
	 * 
	 * @param value of the node to be removed.
	 * @return the given value if the node exists, {@code null} otherwise.
	 */
	public T removeByValue(T value) {
		if (head == null) {
			return null;
		}

		// compare value of objects
		if (head.value.equals(value)) {
			head = head.next;
			return value;
		}

		LLNode<T> current = head;

		// scan the list and stop a node before the node with the given value
		while (current.next != null && !current.next.value.equals(value)) {
			current = current.next;
		}

		// if the node with the value is found
		if (current.next != null) {
			LLNode<T> theNode = current.next; // node to remove
			LLNode<T> nodeAfter = theNode.next; // node after the node to be removed
			current.next = nodeAfter;
			theNode.next = null;

			return value;
		}

		return null;
	}

	/**
	 * Retrieves the value of the node at the specified index if the index exists
	 * i.e., {@code 0<=index<=listLength-1}.
	 * 
	 * @param index of the node whose value should be retrieved
	 * @return value of the node if index exists, {@code null} otherwise.
	 */
	public T get(int index) {
		// if index exists (also implies that list is not empty)
		if (index >= 0 && index <= length - 1) {
			LLNode<T> current = head;
			int currIdx = 0;

			while (current != null) {
				if (currIdx == index) {
					return current.value;
				}
				current = current.next;
				currIdx++;
			}
		}

		return null;
	}

	/**
	 * Retrieves the index of the first node with the given value.
	 * 
	 * @param value of the node whose index should be retrieved
	 * @return index of the node, {@code -1} if not found.
	 */
	public int indexOf(T value) {
		LLNode<T> current = head;
		int currIdx = 0;

		while (current != null) {
			if (current.value == value) {
				return currIdx;
			}
			current = current.next;
			currIdx++;
		}

		return -1;
	}

	/**
	 * Returns the value of the {@code length} attribute of this list.
	 * 
	 * @return length of the list
	 */
	public int size() {
		return length;
	}

	/**
	 * Empties the list.
	 */
	public void clear() {
		head = null;
		length = 0;
	}
}

class LLNode<T> {
	T value;
	LLNode<T> next;

	LLNode(T value) {
		this.value = value;
		this.next = null;
	}
}
