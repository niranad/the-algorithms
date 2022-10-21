package datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import datastructures.DoublyLinkedList;

class DoublyLinkedListTest {

	@Test
	void test() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		assertTrue(list.isEmpty());
		
		list.insertAtFront(3);
		assertEquals(list.toString(), "[3]");
		
		list.insertAtBack(7);
		assertEquals(list.toString(), "[3, 7]");
		
		list.insertAtFront(2);
		assertEquals(list.toString(), "[2, 3, 7]");
		
		list.insertAtBack(4);
		assertEquals(list.toString(), "[2, 3, 7, 4]");
		
		assertFalse(list.isEmpty());
		
		assertEquals(list.removeFromFront(), 2);
		assertEquals(list.toString(), "[3, 7, 4]");
		
		assertEquals(list.removeFromBack(), 4);
		assertEquals(list.toString(), "[3, 7]");
		
		assertEquals(list.insert(1, 5), 5);
		assertEquals(list.toString(), "[3, 5, 7]");
		
		assertEquals(list.get(1), 5);
		
		assertEquals(list.remove(1), 5);
		assertEquals(list.toString(), "[3, 7]");
		
		list.insertAtBack(11);
		list.insertAtBack(13);
		list.insertAtBack(9);
		list.insertAtFront(1);
		
		assertEquals(list.toString(), "[1, 3, 7, 11, 13, 9]");
		list.reverse();
		assertEquals(list.toString(), "[9, 13, 11, 7, 3, 1]");
	}

}
