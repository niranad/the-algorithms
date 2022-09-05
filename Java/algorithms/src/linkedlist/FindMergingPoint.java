package linkedlist;

import java.util.Hashtable;

import datastructures.LLNode;

/*
 * Problem statement:
 * Suppose there are two singly linked lists both of which intersect at some point and become 
 * a single linked list. The head or start pointers of both the lists are known, but the intersecting 
 * node is not known. Also, the number of nodes in each of the lists before they intersect is 
 * unknown and may be different in each list. List1 may have n nodes before it reaches the 
 * intersection point, and List2 might have m nodes before it reaches the intersection point 
 * where m and n may be m = n,m < n or m > n. Give an algorithm for finding the merging point.
 */

public class FindMergingPoint {

	public static void main(String[] args) {
		LLNode<Object> point = new LLNode<>(7);
		point.setNext(new LLNode<>(9));
		point.getNext().setNext(new LLNode<>(11));

		LLNode<Object> head1 = new LLNode<>(3);
		head1.setNext(new LLNode<>(5));
		head1.getNext().setNext(point);

		LLNode<Object> head2 = new LLNode<>(2);
		head2.setNext(new LLNode<>(4));
		head2.getNext().setNext(new LLNode<>(6));
		head2.getNext().getNext().setNext(point);

		System.out.println(findMergingPoint(head1, head2));
	}

	private static LLNode<Object> findMergingPoint(LLNode<Object> head1, LLNode<Object> head2) {
		if (head1 == null || head2 == null) {
			return null;
		}

		if (head1 == head2) {
			return head1;
		}

		Hashtable<LLNode<Object>, Integer> map = new Hashtable<>();
		LLNode<Object> current1 = head1;
		LLNode<Object> current2 = head2;

		// populate the map
		while (current1 != null) {
			if (map.containsKey(current1)) {
				map.put(current1, map.get(current1) + 1);
			} else {
				map.put(current1, 1);
			}

			current1 = current1.getNext();
		}

		// search the merging point
		while (current2 != null) {
			if (map.containsKey(current2)) {
				return current2;
			}
			current2 = current2.getNext();
		}

		return null;
	}
}
