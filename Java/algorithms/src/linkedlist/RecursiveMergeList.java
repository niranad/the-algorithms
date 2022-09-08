package linkedlist;

import datastructures.LLNode;

public class RecursiveMergeList {

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
		if (head1 == null) {
			return head2;
		}
		
		if (head2 == null) {
			return head1;
		}
		
		LLNode<Integer> head = new LLNode<>(0);
		
		if (head1.getData() <= head2.getData()) {
			head = head1;
			head.setNext(mergeLists(head1.getNext(), head2));
		} else {
			head = head2;
			head.setNext(mergeLists(head1, head2.getNext()));
		}
		
		return head;
	}
}
