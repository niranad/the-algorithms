package linkedlist;

import datastructures.LLNode;

/*
 * Problem statement: 
 * Split a Circular Linked List into two equal parts. If the number of nodes in the 
 * list are odd then make first list one node more than second list.
 */

public class SplitList {

	public static void main(String[] args) {
		// 2->3->5->7->9
		LLNode<Object> head = new LLNode<>(2);
		head.setNext(new LLNode<>(3));
		head.getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().setNext(new LLNode<>(7));
		head.getNext().getNext().getNext().setNext(new LLNode<>(9));
		splitList(head);
	}

	private static void splitList(LLNode<Object> head) {
		if (head == null || head.getNext() == null) {  // If list is empty or has one element
			return;
		}

		LLNode<Object> fastPtr = head;
		LLNode<Object> slowPtr = head;

		while (fastPtr != null && fastPtr.getNext() != null
			&& fastPtr.getNext().getNext() != null) {
			slowPtr = slowPtr.getNext();
			fastPtr = fastPtr.getNext().getNext();
		}

		LLNode<Object> secondListHead = slowPtr.getNext();
		slowPtr.setNext(null);

		System.out.println(head); // print first list
		System.out.println(secondListHead);
	}

}
