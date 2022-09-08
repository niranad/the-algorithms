package linkedlist;

import datastructures.LLNode;

public class RecursiveReverseInPairs {

	public static void main(String[] args) {
		// 2->3->5->7->9
		LLNode<Object> head = new LLNode<>(2);
		head.setNext(new LLNode<>(3));
		head.getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().setNext(new LLNode<>(7));
		head.getNext().getNext().getNext().setNext(new LLNode<>(9));
		System.out.println(reverseInPairs(head));
	}

	private static LLNode<Object> reverseInPairs(LLNode<Object> head) {
		if (head == null || head.getNext() == null) {
			return head;
		} else {
			LLNode<Object> temp = head.getNext();
			head.setNext(temp.getNext());
			temp.setNext(head);
			head = temp;
			head.getNext().setNext(reverseInPairs(head.getNext().getNext()));
		}

		return head;
	}
}
