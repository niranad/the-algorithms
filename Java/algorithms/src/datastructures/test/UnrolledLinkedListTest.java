package datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import java.security.SecureRandom;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import datastructures.UnrolledLinkedList;

class UnrolledLinkedListTest {

	@Test
	void test() {
		UnrolledLinkedList<Integer> ulist = new UnrolledLinkedList<>();
		int[] elements = new int[14];
		SecureRandom random = new SecureRandom();
		
		for (int i = 0; i < 14; i++) {
			random.reseed();
			elements[i] = 10 + random.nextInt(100);
		}
		
		Arrays.stream(elements).forEach(n -> ulist.add((Integer) n));

		assertEquals(ulist.size(), 14);
		assertEquals(ulist.listSize(), 4);
		
		assertEquals(ulist.getFirst(), elements[0]);
		assertEquals(ulist.getLast(), elements[13]);
		assertEquals(ulist.get(0), elements[0]);
		assertEquals(ulist.get(11), elements[11]);
		assertEquals(ulist.get(5), elements[5]);
		
		assertThrows(IndexOutOfBoundsException.class, () -> ulist.add(14, 25));	
		ulist.add(0, 77);
		assertEquals(ulist.get(0), 77);
		ulist.add(6, 55);
		assertEquals(ulist.get(6), 55);
		
		ulist.remove(7);
		ulist.remove(11);
		ulist.remove(6);
		assertEquals(ulist.size(), 13);
		ulist.remove(0);
		ulist.remove(0);
		ulist.clear();
		assertEquals(ulist.size(), 0);
		assertEquals(ulist.listSize(), 1);
	}
}
