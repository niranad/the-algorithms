package linkedlist;

/*
 * Problem statement: Check whether a given linked list is null-terminated or not. 
 * If there is a cycle find the start node of the loop.
 */

import datastructures.LLNode;

public class FindLoopStart {

	public static void main(String[] args) {
		LLNode<Object> head = new LLNode<>("abc");
		head.setNext(new LLNode<>("def"));
		head.getNext().setNext(new LLNode<>("ghi"));
		head.getNext().getNext().setNext(head.getNext());
		System.out.println(ifCyclicFindStart(head));
	}
	
	private static Object ifCyclicFindStart(LLNode<Object> head) {
		LLNode<Object> slowPtr = head;
		LLNode<Object> fastPtr = head;
		boolean isCyclic = false;
		
		while (fastPtr != null && fastPtr.getNext() != null) {
			fastPtr = fastPtr.getNext().getNext();
			slowPtr = slowPtr.getNext();
			if (slowPtr == fastPtr) {
				isCyclic = true;
				break;
			}
		}
		
		if (isCyclic) {
			slowPtr = head;
			while (slowPtr != fastPtr) {
				slowPtr = slowPtr.getNext();
				fastPtr = fastPtr.getNext();
			}
			
			return slowPtr.getValue();
		}
		
		return null;
	}

}
