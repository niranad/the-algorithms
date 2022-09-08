package linkedlist;

/*
 * Problem statement: Reverse the linked list in pairs. If you have a linked list that holds 1→2→3→4→X, 
 * then after the function has been called the linked list would hold 2→1→4→3→X.
 */

import datastructures.LLNode;

public class IterativeReverseInPairs {

	public static void main(String[] args) {
		// 2->3->5->7->9
		LLNode<Object> head = new LLNode<>(2);
		head.setNext(new LLNode<>(3));
		head.getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().setNext(new LLNode<>(7));
		head.getNext().getNext().getNext().setNext(new LLNode<>(9));
		System.out.println(reverseInPairs(head));
	}

	private static LLNode<Object> reverseInPairs(LLNode<Object> head) {
		LLNode<Object> next = null,
			doubleNext = null,
			prev = null,
			current = head;

		while (current != null && current.getNext() != null) {
			next = current.getNext();
			doubleNext = next.getNext();
			next.setNext(current);
			current.setNext(doubleNext);

			if (prev != null) {
				prev.setNext(next);
			} else {
				head = next;
			}

			prev = current;
			current = current.getNext();
		}

		return head;
	}
}
