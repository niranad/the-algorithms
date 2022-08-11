package datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import datastructures.CircularLinkedList;

class CircularLinkedListTest {

	@Test
	void test() {
		CircularLinkedList<String> cll = new CircularLinkedList<>();
		String[] strings = new String[] { "circular", "spherical", "linked", "circle", "unlinked",
			"sphere", "list", "round", "listings", "about", };

		// Test addLast(data)
		Arrays.stream(strings).forEach(s -> cll.addLast(s));
		assertEquals(cll.size(), 10, "addLast should increment list size by 1");
		assertEquals(cll.getLast(), "about", "addLast should add new element as the last element");

		// Test addFirst(data)
		cll.addFirst("oval");
		assertEquals(cll.size(), 11, "addFirst should increment list size by 1");
		assertEquals(cll.getFirst(), "oval",
			"addFirst should add a new element as the first list element");

		// Test add(index, data)
		cll.add(0, "irregular");
		assertEquals(cll.getFirst(), "irregular",
			"'add' should add new element as the first element when index is 0");
		cll.add(12, "shape");
		assertEquals(cll.getLast(), "shape",
			"'add' should add new element as the last element when index equals the size of list");
		cll.add(7, "around");
		assertEquals(cll.get(7), "around", "'add' should add new element at the given index");
		assertEquals(cll.size(), 14, "'add' should increment the size of list");

		// Test removeFirst
		assertEquals(cll.removeFirst(), "irregular",
			"removeFirst should remove the first element from the list and return it");
		assertEquals(cll.size(), 13, "removeFirst should decrement the size of list");

		// Test removeLast
		assertEquals(cll.removeLast(), "shape",
			"removeLast should remove the last element from the list and return it");
		assertEquals(cll.size(), 12, "removeLast should decrement the size of list");

		// Test remove
		assertEquals(cll.remove(0), "oval",
			"'remove' should remove the first element when index is 0");
		assertEquals(cll.remove(10), "about",
			"'remove' should remove the last element when index equals the size of list");
		assertEquals(cll.size(), 10, "'remove' should decrement the size of the list");

		// Test indexOf
		assertEquals(cll.indexOf("round"), 8,
			"indexOf should return the correct index of the element if present in the list");

		// Test contains
		assertTrue(cll.contains("unlinked"),
			"contains should return true if element is present in list");
		assertFalse(cll.contains("abstract"),
			"contains should return false if element is not in the list");

		// Test toArray()
		assertArrayEquals(cll.toArray(),
			new Object[] { "circular", "spherical", "linked", "circle", "unlinked",
				"around", "sphere", "list", "round", "listings", },
			"toArray should return an Object array "
				+ "containing all of the elements in this list in proper sequence");

		// Test toArray(array)
		Integer[] ints = new Integer[20];
		assertThrows(ArrayStoreException.class, () -> cll.toArray(ints),
			"toArray(arr) should throw ArrayStoreException if array type is not a supertype "
				+ "or same type as the type of every element in the list");
		String[] alphas = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
			"M" };
		assertArrayEquals(cll.toArray(alphas),
			new String[] { "circular", "spherical", "linked", "circle",
				"unlinked", "around", "sphere", "list", "round", "listings", null, "L", "M" });

		ListIterator<String> listIterator = cll.listIterator();

		// Test listIterator next
		listIterator.next();
		listIterator.next();
		listIterator.next();
		assertEquals(listIterator.next(), "circle",
			"listIterator's next shoud return the next element in list");

		// Test listIterator nextIndex
		assertEquals(listIterator.nextIndex(), 4,
			"listIterator nextIndex should return the index of the element that would be returned by next call to 'next'");

		// Test listIterator's previous
		listIterator.previous();
		listIterator.previous();
		assertEquals(listIterator.previous(), "spherical",
			"listIterator's previous should return same element as 'next' if called after 'next', otherwise return the previous element");

		// Test listIterator's previousIndex
		assertEquals(listIterator.previousIndex(), 0,
			"listIterator's previous should the index of the element that would be returned by next call to 'previous'");

		// Test listIterator's hasPrevious
		assertTrue(listIterator.hasPrevious(),
			"listIterator hasPrevious should return true if next call to 'previous' would return an element");
		listIterator.previous();
		assertFalse(listIterator.hasPrevious(),
			"listIterator hasPrevious should return false if next call to 'previous' would "
				+ "throw a NoSuchElementException");

		// Test listIterator's hasNext
		assertTrue(listIterator.hasNext(),
			"listIterator hasNext should return true if next call to 'next' would return an element");
		int i = 0;
		while (i++ < 10) { // loop to the end of list
			listIterator.next();
		}
		assertFalse(listIterator.hasNext(),
			"listIterator hasNext should return false if next call to 'next' would throw a NoSuchElementException");
		assertThrows(NoSuchElementException.class, () -> listIterator.next(),
			"listIterator next should throw a NoSuchElementException if iterator has no more elements");

		// Test listIterator's add
		listIterator.previous(); // return the last element
		listIterator.add("cookie");
		assertEquals(cll.size(), 11, "listIterator 'add' should increment the list's size");
		assertEquals(cll.getLast(), "listings",
			"listIterator 'add' should add new element before the element that would be returned by next, if any");

		// Test listIterator's remove
		assertThrows(IllegalStateException.class, () -> listIterator.remove(),
			"listIterator 'remove' should only be called once per call to 'next' or 'previous' if 'add' or 'remove' "
				+ "has not been called after last call to 'next' or 'previous'");
		assertEquals(listIterator.previous(), "cookie");
		listIterator.remove();
		assertFalse(cll.contains("cookie"),
			"listIterator 'remove' should remove the last element that was returned by 'next' or 'previous'");
		assertThrows(IllegalStateException.class, () -> listIterator.remove(),
			"listIterator 'remove' can only be called once per call to 'next' or 'previous'");

		// Test listIterator concurrent modification exception
		cll.add(5, "concurrent modification");
		assertThrows(ConcurrentModificationException.class, () -> listIterator.previous(),
			"listIterator should not allow concurrent modification of list");
	}

}
