package linkedlist;

import datastructures.LLNode;

/*
 * Problem statement: Given two sorted linked lists, give an algorithm for the printing common elements of them.
 */

public class SortedListsCommons {

	public static void main(String[] args) {
		// 2->3->5->7->9
		LLNode<Integer> head = new LLNode<>(2);
		head.setNext(new LLNode<>(3));
		head.getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().setNext(new LLNode<>(7));
		head.getNext().getNext().getNext().setNext(new LLNode<>(9));

		// 5->6->7->8->9
		LLNode<Integer> head2 = new LLNode<>(5);
		head2.setNext(new LLNode<>(6));
		head2.getNext().setNext(new LLNode<>(7));
		head2.getNext().getNext().setNext(new LLNode<>(8));
		head2.getNext().getNext().getNext().setNext(new LLNode<>(9));

		printCommons(head, head2);   // 5, 7, 9
	}

	// Assumes the elements are integers. For other objects, they must implement the
	// comparable interface
	private static void printCommons(LLNode<Integer> head1, LLNode<Integer> head2) {
		LLNode<Integer> ptr1 = head1;
		LLNode<Integer> ptr2 = head2;

		while (ptr1 != null && ptr2 != null) {
			if (ptr1.getData() == ptr2.getData()) {
				System.out.println(ptr1.getData());
				ptr1 = ptr1.getNext();
				ptr2 = ptr2.getNext();
			} else if (ptr1.getData() < ptr2.getData()) {
				ptr1 = ptr1.getNext();
			} else {
				ptr2 = ptr2.getNext();
			}
		}
	}
}
