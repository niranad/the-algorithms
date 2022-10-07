package datastructures;

@SuppressWarnings("unchecked")
public class DynamicArrayStack<T> {
	private int size;
	private final int initialCapacity = 16;
	private final int maximumCapacity = Integer.MAX_VALUE;
	private int capacity;
	private int topIndex;
	private Object[] elementData;

	/**
	 * Constructs an instance of a {@code DynamicArrayStack} with an initial
	 * capacity of {@code 16}.
	 */
	public DynamicArrayStack() {
		capacity = initialCapacity;
		topIndex = -1;
		elementData = new Object[capacity];
	}

	/**
	 * Constructs an instance of {@code DynamicArrayStack} with the given
	 * {@code initialCapacity} provided it is a positive integer and less than or
	 * equal to the {@code maximumCapacity} which is {@code Integer.MAX_VALUE}. If
	 * the {@code initialCapacity} is zero or negative, then the stack is
	 * initialized with a capacity of {@code 16}.
	 * 
	 * @param initialCapacity
	 */
	public DynamicArrayStack(int initialCapacity) {
		topIndex = -1;

		if (initialCapacity > maximumCapacity) {
			capacity = maximumCapacity;
		} else if (initialCapacity > 0) {
			capacity = initialCapacity;
		} else {
			capacity = this.initialCapacity;
		}

		elementData = new Object[capacity];
	}

	/**
	 * Pushes an element onto the top of the stack.
	 * 
	 * @param data element to be pushed
	 * @throws Exception if the current {@code size} of the stack equals the
	 *                   {@code maximumCapacity} of the stack i.e.,
	 *                   {@code Integer.MAX_VALUE}
	 */
	public void push(T data) throws Exception {
		topIndex++;

		if (topIndex < capacity) {
			elementData[topIndex] = data;
			size++;
		} else {
			growStack(data);
		}
	}

	private void growStack(T data) throws Exception {
		if (capacity == maximumCapacity) {
			topIndex--;
			throw new Exception("Maximum capacity exceeded. Stack is full");
		}

		capacity *= 2;

		if (capacity < 0) { // if integer overflows
			capacity = maximumCapacity;
		}

		Object[] elementStack = new Object[capacity];
		int i = 0;

		for (Object el : elementData) {
			elementStack[i++] = el;
		}

		elementData = elementStack;
		elementData[topIndex] = data;
		size++;
	}

	/**
	 * Pops an element from the top of the stack.
	 * @return data element removed from the top of stack
	 * @throws Exception if the stack is empty
	 */
	public T pop() throws Exception {
		if (size == 0) {
			throw new Exception("Stack is empty");
		}

		T topElem = (T) elementData[topIndex];
		elementData[topIndex] = null;
		topIndex--;
		size--;
		shrinkStack();
		return topElem;
	}

	private void shrinkStack() {
		if (capacity - size >= (3 * capacity / 4)) {
			capacity /= 2;

			Object[] shrinkedStack = new Object[capacity];
			int i = 0;

			for (Object el : elementData) {
				shrinkedStack[i++] = el;
			}

			elementData = shrinkedStack;
			shrinkedStack = null;
		}
	}

	/**
	 * Returns the element at the top of this stack, or {@code null} if stack is
	 * empty.
	 * 
	 * @return the element at the top of this stack.
	 */
	public T peek() {
		if (topIndex < 0) {
			return null;
		}

		return (T) elementData[topIndex];
	}

	public boolean search(Object o) {
		int currIdx = topIndex;

		while (currIdx >= 0) {
			if (elementData[currIdx--].equals(o)) {
				return true;
			}
		}

		return false;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	/**
	 * Deletes the elements of the stack if not empty and re-initialize the stack
	 * with a capacity of {@code 16}.
	 */
	public void clear() {
		if (size > 0) {
			elementData = new Object[initialCapacity];
		}
	}

}
