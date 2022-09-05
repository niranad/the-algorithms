package linkedlist;

import datastructures.LLNode;

/*
 * Problem statement: Implement an algorithm to find the middle of a linked list. Return the value.
 */

public class MidOfList {

	public static void main(String[] args) {
		LLNode<Object> head = new LLNode<>(2);
		head.setNext(new LLNode<>(4));
		head.getNext().setNext(new LLNode<>(6));
		System.out.println(getMid(head));
	}
	
	private static Object getMid(LLNode<Object> head) {
		if (head == null) {
			return null;
		}
		// if list has one or two elements
		if (head.getNext() == null || head.getNext().getNext() == null) {
			return head.getData();
		}
		
		LLNode<Object> slowPtr = head;
		LLNode<Object> fastPtr = head;
		
		while (fastPtr != null && fastPtr.getNext() != null) {
			slowPtr = slowPtr.getNext();
			fastPtr = fastPtr.getNext().getNext();
		}
		
		return slowPtr.getData();
	}

}
