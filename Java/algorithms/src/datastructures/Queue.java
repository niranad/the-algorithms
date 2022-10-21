package datastructures;

import java.util.AbstractQueue;
import java.util.Iterator;

public class Queue<T> extends AbstractQueue<T> {
	private QueueNode<T> head;
	private QueueNode<T> tail;
	
	public Queue() {
		head = tail = null;
	}
	
	public T getFirst() {
		return head.data;
	}
	
	public QueueNode<T> getHead() {
		return head;
	}
	
	public T getLast() {
		return tail.data;
	}
	
	public QueueNode<T> getTail() {
		return tail;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			QueueNode<T> current = Queue.this.head;
			
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				T next = current.data;
				current = current.nextNode;
				return next;
			}
			
		};
	}

	@Override
	public boolean offer(T e) {
		if (head == null) {
			head = tail = new QueueNode<>(e);
		} else if (head == tail) {
			tail = new QueueNode<>(e);
			head.nextNode = tail;
			tail.prevNode = head;
		} else {
			tail.nextNode = new QueueNode<>(e);
			tail.nextNode.prevNode = tail;
			tail = tail.nextNode;
		}
		return true;
	}

	@Override
	public T poll() {
		T polled = head.data;
		head = head.nextNode;
		
		if (head != null) {
			head.prevNode = null;
		} else {
			head = tail = null;
		}
		
		return polled;
	}

	@Override
	public T peek() {
		return head != null ? head.data : null;
	}

	@Override
	public int size() {
		int s = 0;
		QueueNode<T> currNode = head;
		
		while (currNode != null) {
			currNode = currNode.nextNode;
			s++;
		}
		
		return s;
	}
	
	public boolean isEmpty() {
		return head == null;
	}

}
