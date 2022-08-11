package datastructures;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * A simple variation of {@code LinkedList} containing circular linked list at
 * each node or block. Each block maintains the property such that its list has
 * no more than {@code Math.sqrt(n)} elements where {@code n} is the total
 * number of elements in the {@code UnrolledLinkedList}.
 * 
 * <p>
 * If each block contains the same or approximately equal number of list
 * elements, a better cache performance results due to improved memory locality.
 * <p>
 *
 */
public class UnrolledLinkedList<E> extends AbstractSequentialList<E> implements List<E>, Serializable {
	private static final long serialVersionUID = 6120042534897357796L;
	private ULLNode<E> head;
	private ULLNode<E> tail;
	private int size;
	
	
	public boolean add(E data) throws NullPointerException {
		return false;
	}
	
	@Override
	public boolean remove(Object data) {
		return false;
	}
	
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		return null;
	}
	
	@Override
	public boolean contains(Object data) {
		return false;
	}
	
	@Override
	public Iterator<E> iterator() {
		return null;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public void clear() {
		head = null;
		tail = head;
	}
	
	
	private void ensureProperty() {
		
	}
	
	private void shiftElement() {
		
	}
	
	private class ULLNode<T> {
		CircularLinkedList<T> list;
		
		
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}

