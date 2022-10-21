package datastructures;

import java.util.Collection;
import java.util.Iterator;

import exception.EmptyListException;

@SuppressWarnings("unchecked")
public class SortedLinkedList<T extends Comparable<T>> implements Collection<T> {
	private ListNode<T> head;
	private ListNode<T> tail;

	/**
	 * Instantiates an empty {@code LinkedList}.
	 */
	public SortedLinkedList() {
		head = tail = null;
	}

	/**
	 * Instantiates a sorted {@code LinkedList} from a {@code Collection} object.
	 * 
	 * @param c - a {@code Collection} type
	 */
	public SortedLinkedList(Collection<T> c) {
		c.forEach(el -> add(el));
	}

	@Override
	public boolean contains(Object o) {
		if (isEmpty()) {
			return false;
		}

		ListNode<T> currNode = head;

		while (currNode != null) {
			if (currNode.data.equals((T) o)) {
				return true;
			}
			currNode = currNode.nextNode;
		}

		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> iterator = c.iterator();

		while (iterator.hasNext()) {
			if (!contains((T) iterator.next())) {
				return false;
			}
		}

		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			ListNode<T> current = SortedLinkedList.this.head;
			
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				T data = current.data;
				current = current.nextNode;
				return data;
			}
		};
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size()];
		ListNode<T> currNode = head;

		for (int i = 0; i < size(); i++) {
			array[i] = currNode.data;
			currNode = currNode.nextNode;
		}

		return array;
	}

	@Override
	public <E> E[] toArray(E[] a) {
		ListNode<T> currNode = head;

		for (int i = 0; i < a.length; i++) {
			a[i] = (E) currNode.data;
			currNode = currNode.nextNode;
		}

		return a;
	}

	@Override
	public boolean add(T data) throws NullPointerException {
		if (data == null) {
			throw new NullPointerException("cannot insert null into list");
		}

		ListNode<T> newNode = new ListNode<T>(data);

		if (isEmpty()) {
			head = tail = newNode;
		} else if (head == tail) {
			if (newNode.data.compareTo(head.data) < 0) {
				head = newNode;
				head.nextNode = tail;
				tail.prevNode = head;
			} else {
				head.nextNode = newNode;
				tail = newNode;
				tail.prevNode = head;
			}
		} else {
			ListNode<T> currNode = head;

			while (currNode.data.compareTo(newNode.data) < 0) {
				if (currNode.nextNode == null) {
					break;
				}
				currNode = currNode.nextNode;
			}

			if (currNode.data.compareTo(newNode.data) < 0) {
				if (currNode.nextNode == null) {
					currNode.nextNode = newNode;
					newNode.prevNode = currNode;
					tail = newNode;
				}
			} else if (currNode.data.compareTo(newNode.data) >= 0) {
				if (currNode.prevNode == null) {
					currNode.prevNode = newNode;
					newNode.nextNode = currNode;
					head = newNode;
				} else {
					newNode.nextNode = currNode;
					newNode.prevNode = currNode.prevNode;
					currNode.prevNode.nextNode = newNode;
					currNode.prevNode = newNode;
					currNode = null;
				}
			}
		}

		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		Iterator<?> iterator = c.iterator();

		while (iterator.hasNext()) {
			add((T) iterator.next());
		}

		return !iterator.hasNext();
	}

	public T get(int index) throws EmptyListException, IndexOutOfBoundsException {
		if (isEmpty()) {
			throw new EmptyListException();
		} else if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		int i = 0;
		ListNode<T> currNode = head;

		while (i < index) {
			currNode = currNode.nextNode;
			i++;
		}

		return currNode.data;
	}

	/**
	 * Returns the size of the sorted {@code LinkedList}
	 * 
	 * @return size
	 */
	public int size() {
		ListNode<T> currNode = head;
		int i = 0;

		while (currNode != null) {
			++i;
			currNode = currNode.nextNode;
		}

		return i;
	}

	public boolean merge(SortedLinkedList<T> list) {
		return addAll(list);
	}

	public boolean removeAtIndex(int index) {
		if (isEmpty() || index < 0 || index >= size()) {
			return false;
		} 

		ListNode<T> currNode = head;
		int i = 0;

		while (i < index) {
			currNode = currNode.nextNode;
			i++;
		}

		if (index == 0) {
			if (size() == 1) {
				head = tail = null;
			} else {
				currNode = currNode.nextNode;
				currNode.prevNode = null;
				head = currNode;
			}	
		} else if (index == size() - 1) {
			currNode = currNode.prevNode;
			currNode.nextNode = null;
			tail = currNode;
		} else {
			currNode.prevNode.nextNode = currNode.nextNode;
			currNode.nextNode.prevNode = currNode.prevNode;
			currNode = null;
		}

		return true;
	}

	@Override
	public boolean remove(Object o) {
		ListNode<T> currNode = head;

		while (currNode != null) {
			if (currNode.data.equals((T) o)) {
				if (currNode.prevNode == null) {
					if (size() == 1) {
						head = tail = null;
					} else {
						currNode = currNode.nextNode;
						currNode.prevNode = null;
						head = currNode;
					}
				} else if (currNode.nextNode == null) {
					currNode = currNode.prevNode;
					currNode.nextNode = null;
					tail = currNode;
				} else {
					currNode.prevNode.nextNode = currNode.nextNode;
					currNode.nextNode.prevNode = currNode.prevNode;
					currNode = null;
				}

				return true;
			}
			currNode = currNode.nextNode;
		}

		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Iterator<?> iterator = c.iterator();

		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (contains((T) next)) {
				remove(next);
			}
		}

		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		ListNode<T> currNode = head;

		while (currNode != null) {
			if (!c.contains(currNode.data)) {
				ListNode<T> nextNode = currNode.nextNode;

				if (currNode.prevNode == null) {
					currNode = currNode.nextNode;
					currNode.prevNode = null;
					head = currNode;
				} else if (currNode.nextNode == null) {
					currNode = currNode.prevNode;
					currNode.nextNode = null;
					tail = currNode;
				} else {
					currNode.prevNode.nextNode = currNode.nextNode;
					currNode.nextNode.prevNode = currNode.prevNode;
				}

				currNode = nextNode;
				continue;
			}

			currNode = currNode.nextNode;
		}

		return true;
	}

	@Override
	public void clear() {
		head = tail = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public String toString() {
		String s = "[";
		ListNode<T> currNode = head;

		while (currNode != null) {
			s += currNode.nextNode != null ? currNode.data + ", " : currNode.data + "]";
			currNode = currNode.nextNode;
		}

		return isEmpty() ? "[]" : s;
	}
}

class ListNode<T extends Comparable<T>> {
	T data;
	ListNode<T> prevNode;
	ListNode<T> nextNode;

	ListNode(T data) {
		this(data, null, null);
	}

	ListNode(T data, ListNode<T> prevNode, ListNode<T> nextNode) {
		this.data = data;
		this.prevNode = prevNode;
		this.nextNode = nextNode;
	}
}
