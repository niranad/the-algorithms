package datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import datastructures.Stack;

class StackTest {

	@Test
	void test() {
		Stack<Integer> stack = new Stack<>();
		assertTrue(stack.isEmpty());
		
		assertEquals(stack.push(4), 4);
		assertEquals(stack.push(5), 5);
		assertEquals(stack.push(7), 7);
		assertEquals(stack.push(10), 10);
		
		assertEquals(stack.size(), 4);
		assertEquals(stack.toString(), "[4, 5, 7, 10]");
		
		assertEquals(stack.get(2), 7);
		assertEquals(stack.get(1), 5);
		assertEquals(stack.get(3), 10);
		assertEquals(stack.get(0), 4);
		
		assertEquals(stack.pop(), 10);
		assertEquals(stack.toString(), "[4, 5, 7]");
		assertEquals(stack.pop(), 7);
		assertEquals(stack.toString(), "[4, 5]");
		assertEquals(stack.pop(), 5);
		assertEquals(stack.toString(), "[4]");
		
		assertFalse(stack.isEmpty());
	}

}
