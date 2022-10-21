package datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.junit.jupiter.api.Test;

import datastructures.SortedLinkedList;

class SortedLinkedListTest {

	@Test
	void test() {
		SortedLinkedList<Integer> list = new SortedLinkedList<>();

		assertTrue(list.isEmpty());

		// Testing add()
		assertTrue(list.add(3));
		assertTrue(list.add(11));
		assertTrue(list.add(8));
		assertTrue(list.add(22));
		assertTrue(list.add(55));
		assertTrue(list.add(33));
		assertTrue(list.add(45));
		assertTrue(list.add(67));
		assertTrue(list.add(48));
		assertTrue(list.add(39));
		assertTrue(list.add(77));
		assertTrue(list.add(57));
		assertTrue(list.add(60));
		assertTrue(list.add(50));

		assertFalse(list.isEmpty());

		assertEquals(list.toString(),
			"[3, 8, 11, 22, 33, 39, 45, 48, 50, 55, 57, 60, 67, 77]");

		assertEquals(list.size(), 14);
		assertEquals(list.get(6), 45);

		// Testing toArray()
		Object[] objArr = list.toArray();
		assertEquals(Arrays.toString(objArr),
			"[3, 8, 11, 22, 33, 39, 45, 48, 50, 55, 57, 60, 67, 77]");

		// Testing toArray(E[] a)
		Integer[] intArr = new Integer[14];
		Integer[] testArr = { 3, 8, 11, 22, 33, 39, 45, 48, 50, 55, 57, 60, 67, 77 };
		assertArrayEquals(list.toArray(intArr), testArr);

		// Testing containsAll(Collection<?> c)
		Integer[] compareArr = { 8, 11, 51, 33, 45 };
		LinkedList<Integer> compareList = new LinkedList<>(Arrays.asList(compareArr));
		Set<Integer> compareSet = new HashSet<>(compareList);

		assertFalse(list.containsAll(compareList));
		assertFalse(list.containsAll(compareSet));

		// Testing contains(Object o)
		assertTrue(list.contains(39));
		assertTrue(list.contains(57));
		assertTrue(list.contains(45));

		// Testing addAll(Collection<? extends T> c)
		Integer[] arrToAdd = { 66, 84, 19, 59, 92, 7 };
		LinkedList<Integer> listToAdd = new LinkedList<>(Arrays.asList(arrToAdd));

		assertTrue(list.addAll(listToAdd));
		assertEquals(list.toString(),
			"[3, 7, 8, 11, 19, 22, 33, 39, 45, 48, 50, 55, 57, 59, 60, 66, 67, 77, 84, 92]");
		assertEquals(list.size(), 20);

		Integer[] arrToAdd2 = { 10, 20, 40, 70 };
		LinkedList<Integer> listToAdd2 = new LinkedList<>(Arrays.asList(arrToAdd2));
		Set<Integer> setToAdd = new HashSet<>(listToAdd2);
		assertTrue(list.addAll(setToAdd));
		assertEquals(list.toString(),
			"[3, 7, 8, 10, 11, 19, 20, 22, 33, 39, 40, 45, 48, 50, 55, 57, 59, 60, 66, 67, 70, 77, 84, 92]");
		assertEquals(list.size(), 24);

		// Testing remove(Object o)
		assertTrue(list.remove(20));
		assertFalse(list.remove(20));
		assertTrue(list.remove(60));
		assertTrue(list.removeAtIndex(3));
		assertTrue(list.removeAtIndex(5));
		assertEquals(list.size(), 20);

		assertEquals(list.toString(),
			"[3, 7, 8, 11, 19, 33, 39, 40, 45, 48, 50, 55, 57, 59, 66, 67, 70, 77, 84, 92]");

		// Testing removeAll(Collection<?> c)
		Integer[] removeArr = { 3, 77, 33, 66, 19 };
		LinkedList<Integer> removeList = new LinkedList<>(Arrays.asList(removeArr));
		assertTrue(list.removeAll(removeList));
		assertEquals(list.size(), 15);
		assertEquals(list.toString(),
			"[7, 8, 11, 39, 40, 45, 48, 50, 55, 57, 59, 67, 70, 84, 92]");

		// Testing retainAll(Collection<?> c)
		Integer[] retainArr = { 22, 34, 57, 11, 39, 67 };
		LinkedList<Integer> retainList = new LinkedList<>(Arrays.asList(retainArr));
		assertTrue(list.retainAll(retainList));
		assertEquals(list.toString(), "[11, 39, 57, 67]");
		assertEquals(list.size(), 4);

		// Testing removeAtIndex(int index)
		assertTrue(list.removeAtIndex(0));
		assertTrue(list.removeAtIndex(2));
		assertEquals(list.toString(), "[39, 57]");
		assertEquals(list.size(), 2);

		assertTrue(list.removeAtIndex(list.size() - 1));
		assertEquals(list.toString(), "[39]");

		assertTrue(list.removeAtIndex(0));
		assertEquals(list.toString(), "[]");
		assertTrue(list.isEmpty());

		// List instantiated from another collection
		Integer[] arr = { 45, 67, 70, 48, 50, 11, 57, 59, 39, 40, 55, };
		LinkedList<Integer> newList = new LinkedList<>(Arrays.asList(arr));
		list = new SortedLinkedList<>(newList);

		assertEquals(list.toString(), "[11, 39, 40, 45, 48, 50, 55, 57, 59, 67, 70]");
		assertFalse(list.isEmpty());

		// Testing merge(SortedLinkedList<T> list)
		Integer[] integers = { 30, 51, 46, 14, 27 };
		SortedLinkedList<Integer> list2 = new SortedLinkedList<>(Arrays.asList(integers));
		assertTrue(list.merge(list2));
		assertEquals(list.toString(),
			"[11, 14, 27, 30, 39, 40, 45, 46, 48, 50, 51, 55, 57, 59, 67, 70]");

		// Testing iterator()
		Iterator<Integer> iterator = list.iterator();
		list.forEach(n -> assertEquals(n, iterator.next()));

		// Clearing list
		list.clear();

		assertTrue(list.isEmpty());
		assertEquals(list.toString(), "[]");
		assertEquals(list.size(), 0);
	}
}
