package datastructures.test;

import datastructures.DoublyLinkedList;
import exception.EmptyListException;

public class DoublyLinkedListTest2 {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> intList = new DoublyLinkedList<>();
		
		intList.insertAtFront(2);
		intList.print();
		intList.insertAtFront(3);
		intList.print();
		intList.insertAtBack(7);
		intList.print();
		intList.insertAtBack(11);
		intList.print();
		intList.insert(2, 5);
		intList.print();
		intList.insert(4, 9);
		intList.print();
		intList.insert(0, 1);
		intList.print();
		
		System.out.println();
		
		try {
			int removedItem = intList.removeFromFront();
			System.out.printf("%n%d removed%n", removedItem);
			intList.print();
			
			removedItem = intList.removeFromBack();
			System.out.printf("%n%d removed%n", removedItem);
			intList.print();
			
			removedItem = intList.remove(3);
			System.out.printf("%n%d removed%n", removedItem);
			intList.print();
			
			removedItem = intList.removeFromFront();
			System.out.printf("%n%d removed%n", removedItem);
			intList.print();			
			
			removedItem = intList.removeFromBack();
			System.out.printf("%n%d removed%n", removedItem);
			intList.print();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		
		
	}
}
