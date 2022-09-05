package datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import datastructures.UnrolledLinkedList;

class UnrolledLinkedListTest {

	@Test
	void test() {
		UnrolledLinkedList<Integer> ulist = new UnrolledLinkedList<>();
		int[] elements = new int[] {10, 3, 7, 22, 54, 18, 16, 9, 5, 55, 25, 17, 11, 40};
		Arrays.stream(elements).forEach(n -> ulist.add(n));

		assertEquals(ulist.size(), 14);
		assertEquals(ulist.listSize(), 4);
		
		// test get methods
		assertEquals(ulist.getFirst(), elements[0]);
		assertEquals(ulist.getLast(), elements[13]);
		assertEquals(ulist.get(0), elements[0]);
		assertEquals(ulist.get(11), elements[11]);
		assertEquals(ulist.get(5), elements[5]);
		
		// test add(index, data)
		ulist.add(0, 77);
		assertEquals(ulist.get(0), 77);
		ulist.add(6, 55);
		assertEquals(ulist.get(6), 55);
		ulist.add(15, 87);
		assertEquals(ulist.get(15), 87);
		assertEquals(ulist.size(), 17);
		ulist.add(17, 33);  // when index equals size
		assertEquals(ulist.getLast(), 33);
		
		// test remove(index)
		assertEquals(ulist.remove(0), 77);
		assertEquals(ulist.remove(5), 55);
		assertEquals(ulist.remove(13), 87);
		assertEquals(ulist.remove(14), 33);
		assertEquals(ulist.size(), 14);
		
		// test listIterator
		int i = 0;
		for (Integer n : ulist) {
			assertEquals(n, elements[i++]);
		}
		
		ListIterator<Integer> iterator = ulist.listIterator();
		assertEquals(iterator.next(), 10);
		assertEquals(iterator.previous(), 10);
		assertEquals(iterator.nextIndex(), 0);
		assertEquals(iterator.previousIndex(), -1);
		assertFalse(iterator.hasPrevious());
		assertTrue(iterator.hasNext());
		
		iterator.next();
		iterator.next();
		iterator.next();
		assertEquals(iterator.next(), 22);
		assertEquals(iterator.next(), 54);
		assertEquals(iterator.previous(), 54);
		assertEquals(iterator.previous(), 22);
		
		iterator.remove();
		assertFalse(ulist.contains(22));
		assertTrue(ulist.size() == 13);
		assertEquals(iterator.next(), 54);
		iterator.remove();
		assertFalse(ulist.contains(54));
		assertEquals(iterator.next(), 18);
		assertEquals(iterator.next(), 16);
		
		iterator.add(37);
		iterator.add(31);
		
		assertEquals(iterator.nextIndex(), 7);
		assertEquals(ulist.get(6), 31);
		assertEquals(iterator.next(), 9);
		
		i = 0;
		while (i++ < 5) {  // loop to the second to last element
			iterator.next();
		}
		
		assertEquals(iterator.next(), 40);
		iterator.add(72);
		// attempt to access element added after iterator has reached the end
		assertThrows(NoSuchElementException.class, () -> iterator.next());
	}
}
