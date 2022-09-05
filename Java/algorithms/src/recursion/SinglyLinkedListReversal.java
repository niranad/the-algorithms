package recursion;

import datastructures.LLNode;

public class SinglyLinkedListReversal {

	public static void main(String[] args) {
		int[] elems = new int[] { 11, 13, 15, 18, 21, 25, };
		LLNode<Integer> head = new LLNode<>(elems[0]);
		head.setNext(new LLNode<>(elems[1]));
		head.getNext().setNext(new LLNode<>(elems[2]));
		head.getNext().getNext().setNext(new LLNode<>(elems[3]));
		head.getNext().getNext().getNext().setNext(new LLNode<>(elems[4]));
		LLNode<Integer> tail = new LLNode<>(elems[5]);
		head.getNext().getNext().getNext().getNext().setNext(tail);
		System.out.println(reverseList(head));

	}

	private static LLNode<Integer> reverseList(LLNode<Integer> head) {
		return reverseList(head, null);
	}

	private static LLNode<Integer> reverseList(LLNode<Integer> current, LLNode<Integer> prev) {
		if (current == null) {
			return prev;
		}
		LLNode<Integer> next = current.getNext();
		current.setNext(prev);
		prev = current;
		current = next;
		return reverseList(current, prev);
	}
}
