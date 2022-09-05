package linkedlist;

import datastructures.LLNode;

/*
 * Problem statement: Implement an algorithm to display a linked list starting from the end
 * without reversing the original list.
 */

public class PrintReverseOrder {

	public static void main(String[] args) {
		LLNode<Object> head = new LLNode<>(2);
		head.setNext(new LLNode<>(4));
		head.getNext().setNext(new LLNode<>(6));
		printReverse(head);
	}
	
	private static void printReverse(LLNode<Object> head) {
		if (head == null ) {
			return;
		}
		
		printReverse(head.getNext());
		System.out.println(head.getData());
	}

}
