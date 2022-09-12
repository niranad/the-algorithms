package linkedlist;

import datastructures.LLNode;

/*
 * Problem statement: Given a linked list with even and odd numbers, create an algorithm for making 
 * changes to the list in such a way that all even numbers appear at the beginning while maintaining 
 * their original ordering i.e., 3->4->5->6->7 should become 4->6->3->5->7
 */

public class StableEvensMigration {

	public static void main(String[] args) {
		// 3->4->5->6->7
		LLNode<Integer> head = new LLNode<>(3);
		head.setNext(new LLNode<>(4));
		head.getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().setNext(new LLNode<>(6));
		head.getNext().getNext().getNext().setNext(new LLNode<>(7));
		System.out.println(migrateEvens(head));
	}

	private static LLNode<Integer> migrateEvens(LLNode<Integer> head) {
		if (head == null)
			return null;

		LLNode<Integer> head1 = null, head2 = null;  // even and odd list heads
		LLNode<Integer> curr = head,  // traverses the original list
			curr1 = head1,  // populates the even list
			curr2 = head2;  // populates the odd list

		while (curr != null) {
			if (curr.getData() % 2 == 0) {
				if (curr1 == null) {
					head1 = new LLNode<>(curr.getData());
					curr1 = head1;
				} else {
					curr1.setNext(new LLNode<>(curr.getData()));
					curr1 = curr1.getNext();
				}
			} else {
				if (curr2 == null) {
					head2 = new LLNode<>(curr.getData());
					curr2 = head2;
				} else {
					curr2.setNext(new LLNode<>(curr.getData()));
					curr2 = curr2.getNext();
				}
			}
			curr = curr.getNext();
		}

		if (curr1 != null && curr2 != null) {
			curr1.setNext(head2);
			head = head1;
		} 

		return head;
	}
}
