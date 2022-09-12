package linkedlist;

/*
 * Problem statement: Given the head node of a sorted linked list and a new node, insert the 
 * new node in the list and return the new head.
 */

import java.util.Scanner;

import datastructures.LLNode;

public class InsertInSortedList {

	public static void main(String[] args) {
		int[] elems = new int[] { 11, 13, 15, 18, 21, 25, };
		LLNode<Integer> head = new LLNode<>(elems[0]);
		head.setNext(new LLNode<>(elems[1]));
		head.getNext().setNext(new LLNode<>(elems[2]));
		head.getNext().getNext().setNext(new LLNode<>(elems[3]));
		head.getNext().getNext().getNext().setNext(new LLNode<>(elems[4]));
		LLNode<Integer> tail = new LLNode<>(elems[5]);
		head.getNext().getNext().getNext().getNext().setNext(tail);

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter an integer to insert into the sorted list: ");
		int num = sc.nextInt();
		LLNode<Integer> newNode = new LLNode<>(num);
		System.out.println(insertInto(head, newNode));
		sc.close();
	}

	private static LLNode<Integer> insertInto(LLNode<Integer> head, LLNode<Integer> newNode) {
		LLNode<Integer> current = head;
		
		if (head == null) {
			return newNode;
		}
		
		LLNode<Integer> prev = null;
		while (current != null && current.getData() <= newNode.getData()) {
			prev = current;
			current = current.getNext();
		}
		
		newNode.setNext(current);
		if (prev != null) {
			prev.setNext(newNode);
		} else {
			head = newNode;
		}

		return head;
	}
}
