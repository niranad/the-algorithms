package linkedlist;

import datastructures.LLNode;

public class MergeSorted {

	public static void main(String[] args) {
		LLNode<Integer> head1 = new LLNode<>(2);
		head1.setNext(new LLNode<>(3));
		head1.getNext().setNext(new LLNode<>(5));

		LLNode<Integer> head2 = new LLNode<>(1);
		head2.setNext(new LLNode<>(4));
		head2.getNext().setNext(new LLNode<>(7));
		head2.getNext().getNext().setNext(new LLNode<>(10));

		System.out.println(mergeLists(head1, head2));
	}

	private static LLNode<Integer> mergeLists(LLNode<Integer> head1, LLNode<Integer> head2) {
		LLNode<Integer> head = new LLNode<>(0);
		LLNode<Integer> current = head;

		while (head1 != null && head2 != null) {
			if (head1.getData() <= head2.getData()) {
				current.setNext(head1);
				head1 = head1.getNext();
				current = current.getNext();
			} else {
				current.setNext(head2);
				head2 = head2.getNext();
				current = current.getNext();
			}
		}

		if (head2 != null) {
			current.setNext(head2);
		}
		if (head1 != null) {
			current.setNext(head1);
		}

		return head.getNext();
	}
}
