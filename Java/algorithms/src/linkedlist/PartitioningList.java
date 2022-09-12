package linkedlist;

import datastructures.LLNode;

/*
 * Problem Statement:
 * Given a linked list and a value K, partition it such that all nodes less than K 
 * come before nodes greater than or equal to K. You should preserve the original relative 
 * order of the nodes in each of the two partitions. For example, given 1->4->3->2->5->2 
 * and K = 3, return 1->2->2->4->3->5
 */

public class PartitioningList {

	public static void main(String[] args) {
		// 6->3->7->5->9
		LLNode<Integer> head = new LLNode<>(6);
		head.setNext(new LLNode<>(3));
		head.getNext().setNext(new LLNode<>(7));
		head.getNext().getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().getNext().setNext(new LLNode<>(9));
		System.out.println(partitionList(head, 7));
	}

	private static LLNode<Integer> partitionList(LLNode<Integer> head, int k) {
		LLNode<Integer> list1 = null, list2 = null;  // two new lists to store partitions
		LLNode<Integer> curr1 = list1, curr2 = list2;  // pointers to populate the two lists
		LLNode<Integer> curr = head;  // pointer to loop through list

		while (curr != null) {
			if (curr.getData() < k) {
				if (curr1 != null) {
					curr1.setNext(curr);
					curr1 = curr1.getNext();
				} else {
					list1 = curr1 = curr;
				}
			} else {
				if (curr2 != null) {
					curr2.setNext(curr);
					curr2 = curr2.getNext();
				} else {
					list2 = curr2 = curr;
				}
			}

			curr = curr.getNext();
		}

		if (curr1 != null && curr2 != null) {
			curr1.setNext(list2); // join the end of list1 to start of list2
			head = list1;
		}

		return head;
	}
}
