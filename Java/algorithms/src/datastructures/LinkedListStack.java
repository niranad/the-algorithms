package datastructures;

public class LinkedListStack<T extends Comparable<T>> {
	private ListNode head;
	private int size;
	private ListNode minStack;

	public void push(T data) throws Exception {
		if (size + 1 <= Integer.MAX_VALUE) {
			if (head == null) {
				head = minStack = new ListNode(data);
			} else {
				ListNode node = new ListNode(data);
				node.next = head;
				head = node;

				if (data.compareTo(minStack.elem) <= 0) {
					node.next = minStack;
					minStack = node;
				}
			}

			size++;
		} else {
			throw new Exception("Maximum stack size exceeded");
		}
	}

	public T pop() throws Exception {
		if (size == 0) {
			throw new Exception("Stack is empty");
		}

		T elem = head.elem;
		head = head.next;
		size--;

		if (elem.compareTo(minStack.elem) == 0) {
			minStack = minStack.next;
		}

		return elem;
	}

	public int size() {
		return size;
	}

	public T peek() {
		return head == null ? null : head.elem;
	}

	public void clear() {
		size = 0;
		head = null;
	}

	/**
	 * Returns the minimum element in the stack.
	 */
	public T getMinimum() {
		if (size == 0) {
			return null;
		}

		return minStack.elem;
	}

	/**
	 * Return the length of the smallest elements in the stack based on the order of
	 * push onto the stack.
	 */
	public int getMinStackSize() {
		int len = 0;
		ListNode curr = minStack;

		while (curr != null) {
			++len;
			curr = curr.next;
		}

		return len;
	}

	private class ListNode {
		T elem;
		ListNode next;

		ListNode(T elem) {
			this.elem = elem;
		}
	}
}
