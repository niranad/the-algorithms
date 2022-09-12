package linkedlist;

import datastructures.LLNode;

/*
 * Problem statement: Given a linked list with even and odd numbers, create an algorithm for making 
 * changes to the list in such a way that all even numbers appear at the beginning. Ordering is not
 * important.
 */

public class UnstableEvensMigration {

	public static void main(String[] args) {
		// 3->4->5->8->9
		LLNode<Integer> head = new LLNode<>(3);
		head.setNext(new LLNode<>(4));
		head.getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().setNext(new LLNode<>(8));
		head.getNext().getNext().getNext().setNext(new LLNode<>(9));
		System.out.println(migrateEvens(head));
	}

	private static LLNode<Integer> migrateEvens(LLNode<Integer> head) {
		LLNode<Integer> prev = head;
		LLNode<Integer> curr = head.getNext();

		while (curr != null) {
			if (curr.getData() % 2 == 0) {  // also treats zero as an even number
				LLNode<Integer> next = curr.getNext();
				curr.setNext(head);
				prev.setNext(next);
				head = curr;
				curr = next;
			} else {
				prev = curr;
				curr = prev.getNext();
			}
		}

		return head;
	}
}
