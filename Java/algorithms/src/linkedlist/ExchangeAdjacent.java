package linkedlist;

import datastructures.LLNode;

/*
 * Problem statement: Implement an algorithm to exchange adjacent nodes in a linked list.
 */

public class ExchangeAdjacent {

	public static void main(String[] args) {
		// 2->3->5->7->9
		LLNode<Object> head = new LLNode<>(2);
		head.setNext(new LLNode<>(3));
		head.getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().setNext(new LLNode<>(7));
		head.getNext().getNext().getNext().setNext(new LLNode<>(9));
		System.out.println(exchangeAdjNodes(head));
	}

	private static LLNode<Object> exchangeAdjNodes(LLNode<Object> head) {
		LLNode<Object> temp = new LLNode<>(0);
		temp.setNext(head);

		LLNode<Object> prev = temp, current = head;

		while (current != null && current.getNext() != null) {
			LLNode<Object> temp2 = current.getNext().getNext();
			current.getNext().setNext(prev.getNext());
			prev.setNext(current.getNext());
			current.setNext(temp2);
			prev = current;
			current = prev.getNext();
		}

		return temp.getNext();
	}
}

// Same as reversing the linked list in pairs
