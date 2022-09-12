package linkedlist;

import java.util.Hashtable;
import java.util.Objects;

import datastructures.LLNode;

/*
 * Problem statement: Given a unordered linked list, implement an algorithm to 
 * remove duplicates in it.
 */

public class RemoveDuplicates {

	public static void main(String[] args) {
		// 2->7->5->7->5
		LLNode<Object> head = new LLNode<>(2);
		head.setNext(new LLNode<>(7));
		head.getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().setNext(new LLNode<>(7));
		head.getNext().getNext().getNext().setNext(new LLNode<>(5));
		removeDups1(head);
		System.out.println(head);
	}
	
	// Using a cache; O(n) in time, O(n) in space
	private static void removeDups1(LLNode<Object> head) {
		Hashtable<Object, Boolean> map = new Hashtable<>();
		LLNode<Object> curr = head, prev = null;
		
		while (curr != null) {
			if (map.get(curr.getData()) != null) {
				prev.setNext(curr.getNext());  // delete the duplicate
			} else {
				map.put(curr.getData(), true);
				prev = curr;
			}
			
			curr = curr.getNext();
		}
	}
	
	// Using a nested loop; O(n^2) in time, O(1) in space
	private static void removeDups2(LLNode<Object> head) {
		if (head == null) {
			return;
		}
		
		LLNode<Object> uniqList = head, uniqListPtr = uniqList;
		LLNode<Object> curr = head.getNext();
		
		while (curr != null) {
			LLNode<Object> temp = uniqList;
			boolean isDuplicate = false;
			
			// compare current with elements only in unique list
			do {
				if (Objects.equals(temp.getData(), curr.getData())) {
					isDuplicate = true;
					break;
				}
				temp = temp.getNext();
			} while (temp != uniqListPtr.getNext());
			
			if (!isDuplicate) {
				uniqListPtr.setNext(curr);
				uniqListPtr = uniqListPtr.getNext();
			}
			
			curr = curr.getNext();
		}
		
		uniqListPtr.setNext(null);  // terminate the end of uniqList
	}
}
