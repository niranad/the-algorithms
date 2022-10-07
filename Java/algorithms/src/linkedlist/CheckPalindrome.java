package linkedlist;

import java.util.ArrayList;

import datastructures.LLNode;

/*
 * Problem statement: Implement an algorithm to check if a linked list is a palindrome.
 */

public class CheckPalindrome {

	public static void main(String[] args) {
		// 2->3->5->3->2
		LLNode<Object> head = new LLNode<>(2);
		head.setNext(new LLNode<>(3));
		head.getNext().setNext(new LLNode<>(5));
		head.getNext().getNext().setNext(new LLNode<>(3));
		head.getNext().getNext().getNext().setNext(new LLNode<>(2));
		System.out.println(isPalindrome(head));
	}

	private static boolean checkPalindrome(LLNode<Object> head) {
		LLNode<Object> temp = head;
		ArrayList<Object> asc = new ArrayList<>();
		
		while (temp != null) {
			asc.add(temp.getData());
			temp = temp.getNext();
		}
		
		ArrayList<Object> desc = new ArrayList<>();
		getListDataFromEnd(head, desc);
		return asc.equals(desc);
	}
	
	private static void getListDataFromEnd(LLNode<Object> temp, ArrayList<Object> list) {
		if (temp == null) {
			return;
		}
		
		getListDataFromEnd(temp.getNext(), list);
		list.add(temp.getData());
	}
	
	private static boolean isPalindrome(LLNode<Object> head) {
		ArrayList<Object> items = new ArrayList<>();
		
		LLNode<Object> curr = head;
		int len = 0;
		
		while (curr != null) {
			len++;
			items.add(curr.getData());
			curr = curr.getNext();
		}
		
		int i = 0, j = len - 1;
		
		while (i < j && items.get(i).equals(items.get(j))) {
			i++;
			j--;
		}
		
		return i >= j;
	}
}
