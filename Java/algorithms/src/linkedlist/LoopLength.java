package linkedlist;

import datastructures.LLNode;

public class LoopLength {

	public static void main(String[] args) {
		String[] elems = new String[] { "abc", "def", "ghi", "jkl", "mno", "pqr", };
		LLNode<Object> head = new LLNode<>(elems[0]);
		head.setNext(new LLNode<>(elems[1]));
		head.getNext().setNext(new LLNode<>(elems[2]));
		head.getNext().getNext().setNext(new LLNode<>(elems[3]));
		head.getNext().getNext().getNext().setNext(new LLNode<>(elems[4]));
		LLNode<Object> tail = new LLNode<>(elems[5]);
		head.getNext().getNext().getNext().getNext().setNext(tail);
		tail.setNext(head.getNext().getNext());
		System.out.println(loopLength(head));
	}

	private static int loopLength(LLNode<Object> head) {
		LLNode<Object> slowPtr = head;
		LLNode<Object> fastPtr = head;
		boolean loopExists = false;

		while (fastPtr != null && fastPtr.getNext() != null) {
			fastPtr = fastPtr.getNext().getNext();
			slowPtr = slowPtr.getNext();

			if (slowPtr == fastPtr) {
				loopExists = true;
				break;
			}
		}

		int length = 0;

		if (loopExists) {
			do {
				slowPtr = slowPtr.getNext();
				length++;
			} while (fastPtr != slowPtr);
		}

		return length;
	}
}
