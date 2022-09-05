package datastructures;

import java.util.Random;

public class SkipList<E extends Comparable<E>, T> {
	private Node head;
	private Random random;
	private long size;
	private double p;
	
	public SkipList() {
		head = new Node(null, null, 0, null, null);
		random = new Random();
		p = 0.5;
	}
	
	private long level() {
		long level = 0;
		
		while (level <= size && random.nextDouble() < p) {
			level++;
		}
		
		return level;
	}
	
	public void add(E key, T value) {
		long level = level();
		
		if (level > head.level) {
			head = new Node(null, null, level, null, head);
		}
		
		Node current = head;
		Node last = null;
		
		while (current != null) {
			if (current.next == null || current.key.compareTo(key) > 0) { 
				if (level >= current.level) {
					Node node = new Node(key, value, current.level, current.next, null);
					if (last != null) {
						last.down = node;
					}
					current.next = node;
					last = node;
				}
				current = current.down;
				continue;
			} else if (current.next.key.equals(key)) {
				current.next.value = value;
				return;
			}
			current = current.next;
		}
		
		size++;
	}
	
	public boolean containsKey(E key) {
		return get(key) != null;
	}
	
	public T remove(E key) {
		T value = null;
		Node current = head;
		
		while (current != null) {
			if (current.next == null || current.next.key.compareTo(key) >= 0) {
				if (current.next != null && current.next.key.equals(key)) {
					value = current.next.value;
					current.next = current.next.next;
				}
				current = current.down;
				continue;
			}
			current = current.next;
		}
		
		size--;
		return value;
	}
	
	public T get(E key) {
		Node current = head;
		
		while (current != null) {
			if (current.next == null || current.next.key.compareTo(key) > 0) {
				current = current.down;
				continue;
			} else if (current.next.key.equals(key)) {
				return current.next.value;
			}
			current = current.next;
		}
		
		return null;
	}

	private class Node {
		public E key;
		public T value;
		public long level;
		public Node next;
		public Node down;
		
		public Node(E key, T value, long level, Node next, Node down) {
			this.key = key;
			this.value = value;
			this.level = level;
			this.next = next;
			this.down = down;
		}
	}
}
