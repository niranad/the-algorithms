package linkedlist;

import datastructures.DLLNode;

/*
 * Problem statement: Given the head node of a doubly linked list, reverse the list
 * and return the new head.
 */

public class DoublyLinkedListReversal {

	public static void main(String[] args) {
		DLLNode<Object> head = new DLLNode<>(10, null);
		head.setNext(new DLLNode<>(8, head));
		head.getNext().setNext(new DLLNode<>(6, head.getNext()));
		head.getNext().getNext().setNext(new DLLNode<>(4, head.getNext().getNext()));
		System.out.println(reverseList(head));
	}

	private static DLLNode<Object> reverseList(DLLNode<Object> head) {
		while (head != null) {
			DLLNode<Object> next = head.getNext();  // save next node
			DLLNode<Object> prev = head.getPrev();  // save previous node
			head.setPrev(next);
			head.setNext(prev);
			
			if (head.getPrev() != null) {
				head = head.getPrev();
			} else {
				break;
			}
		}

		return head;
	}
}
