package linkedlist;

import java.util.Hashtable;

import datastructures.LLNode;

/*
 * Problem statement: Given a linked list, check if it is null-terminated or has a cycle.
 */

public class FindLoop {

	public static void main(String[] args) {
		LLNode<Object> head = new LLNode<>("abc");
		head.setNext(new LLNode<>("def"));
		head.getNext().setNext(new LLNode<>("ghi"));
		head.getNext().getNext().setNext(head.getNext());
		System.out.println(isCyclic(head));
	}

	private static boolean isCyclic(LLNode<Object> head) {
		LLNode<Object> slowPtr = head; // moves one node
		LLNode<Object> fastPtr = head; // moves two nodes

		while (fastPtr != null && fastPtr.getNext() != null) {
			fastPtr = fastPtr.getNext().getNext();
			slowPtr = slowPtr.getNext();
			if (fastPtr == slowPtr) {
				return true;
			}
		}

		return false;
	}

}
