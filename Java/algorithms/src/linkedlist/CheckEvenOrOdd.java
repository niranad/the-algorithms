package linkedlist;

import datastructures.LLNode;

/*
 * Problem statement: Implement an algorithm to check whether the length of a linked list is 
 * even or odd.
 */

public class CheckEvenOrOdd {

	public static void main(String[] args) {
		LLNode<Object> head = new LLNode<>(2);
		head.setNext(new LLNode<>(4));
		head.getNext().setNext(new LLNode<>(6));
		System.out.println(isLengthEven(head) ? "Length is Even" : "Length is Odd");
	}
	
	private static boolean isLengthEven(LLNode<Object> head) {
		LLNode<Object> fastPtr = head;
		
		while (fastPtr != null && fastPtr.getNext() != null) {
			fastPtr = fastPtr.getNext().getNext();
		}
		
		return fastPtr == null;
	}
}
