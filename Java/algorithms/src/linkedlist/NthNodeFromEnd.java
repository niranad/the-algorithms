package linkedlist;

import java.util.Arrays;
import java.util.Scanner;

import datastructures.LLNode;
import datastructures.LinkedList;

/*
 * Problem statement: Implement an algorithm to find the nth node from the end of linked list.
 */

public class NthNodeFromEnd {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a space-separated list of elements in the LinkedList: ");
		String[] input = scanner.nextLine().split("\\s");
		LinkedList<String> list = new LinkedList<>(Arrays.asList(input));
		System.out.print("Enter value of n for nth element to find: ");
		int n = scanner.nextInt();
		System.out.println(nthNode(list.getHead(), n));
		scanner.close();
	}

	private static Object nthNode(LLNode<String> head, int n) {
		LLNode<String> pointer1 = head;
		LLNode<String> pointer2 = head;
		int i = 0;

		while (pointer2 != null) {
			pointer2 = pointer2.getNext();
			i++;
			if (i > n) {
				pointer1 = pointer1.getNext();
			}
		}

		if (i - n >= 0) {
			return pointer1.getData();
		}
		
		return null;
	}

}
