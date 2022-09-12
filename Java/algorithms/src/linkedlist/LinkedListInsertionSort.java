package linkedlist;

/*
 * Problem statement: Implement an algorithm to sort a singly linked list using insertion sort.
 */

import datastructures.LLNode;

public class LinkedListInsertionSort {

	public static void main(String[] args) {
		// 4>7->3->5->2->6
		LLNode<Integer> head = new LLNode<>(4);
		head.setNext(new LLNode<>(7));
		head.getNext().setNext(new LLNode<>(3));
		head.getNext().getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().getNext().setNext(new LLNode<>(2));
		head.getNext().getNext().getNext().getNext().setNext(new LLNode<>(6));
		System.out.println(insertionSort(head));
	}

	private static LLNode<Integer> insertionSort(LLNode<Integer> head) {
		if (head == null)
			return null;

		LLNode<Integer> prev = head, curr = prev.getNext();

		while (curr != null) {
			LLNode<Integer> temp = head, tempPrev = null, next = null;
			boolean isCurrElemUnsorted = false;

			while (temp != curr) {
				if (temp.getData() > curr.getData()) { // If current is unsorted
					isCurrElemUnsorted = true;
					next = curr.getNext(); // save the next node to be compared
					prev.setNext(next);

					if (tempPrev != null) { // If insertion is not at the head
						curr.setNext(tempPrev.getNext());
						tempPrev.setNext(curr);
					} else {
						curr.setNext(head);
						head = curr;
					}
					break;
				}

				tempPrev = temp;
				temp = temp.getNext();
			}

			if (next != null || (next == null && isCurrElemUnsorted)) {
				curr = next;
			} else {
				prev = curr;
				curr = curr.getNext();
			}
		}

		return head;
	}
}
