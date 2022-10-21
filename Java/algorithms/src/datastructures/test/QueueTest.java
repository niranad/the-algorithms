package datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import datastructures.Queue;

import java.util.Arrays;
import java.util.Iterator;

public class QueueTest {

	@Test
	public void test() {
		Queue<Integer> queue = new Queue<>();
		
		assertTrue(queue.isEmpty());
		assertEquals(queue.size(), 0);
		
		// Test offer(item)
		assertTrue(queue.offer(5));
		assertTrue(queue.offer(7));
		assertTrue(queue.offer(3));
		assertTrue(queue.offer(11));
		assertTrue(queue.offer(2));
		assertTrue(queue.offer(4));
		
		// Test size()
		assertEquals(queue.size(), 6);
		
		// Test peek()
		assertEquals(queue.peek(), (Integer) 5);
		
		// Test getFirst() and getLast()
		assertEquals(queue.getFirst(), (Integer) 5);
		assertEquals(queue.getLast(), (Integer) 4);
		
		// Test getHead() and getTail()
		assertEquals(queue.getHead().getData(), (Integer) 5);
		assertEquals(queue.getTail().getData(), (Integer) 4);
		
		// Test getPrev() and getNext()
		assertEquals(queue.getHead().getNext().getNext().getData(), (Integer) 3);
		assertEquals(queue.getTail().getPrev().getPrev().getData(), (Integer) 11);
		
		// Test iterator()
		Iterator<Integer> iterator = queue.iterator();
		Arrays.asList(5, 7, 3, 11, 2, 4)
			.stream()
			.forEach(n -> assertEquals(n, iterator.next()));
		
		// Test poll();
		assertEquals(queue.poll(), (Integer) 5);
		assertEquals(queue.poll(), (Integer) 7);
		assertEquals(queue.poll(), (Integer) 3);
		assertEquals(queue.poll(), (Integer) 11);
		assertEquals(queue.poll(), (Integer) 2);
		assertEquals(queue.poll(), (Integer) 4);
		assertEquals(queue.size(), 0);
	}
}

