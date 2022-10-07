package datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import datastructures.LinkedListStack;

class ConstantTimeGetMinStackTest {

	@Test
	void test() throws Exception {
		LinkedListStack<Integer> stack = new LinkedListStack<>();
		Integer[] nums = new Integer[] {7, 9, 4, 12, 8, 2, 6, 10};
		Arrays.stream(nums).forEach((n) -> {
			try {
				stack.push(n);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		assertEquals(stack.peek(), 10);
		assertEquals(stack.getMinimum(), 2);
		assertEquals(stack.getMinStackSize(), 3);
		
		stack.pop(); // pops 10
		stack.pop(); // pops 6
		assertEquals(stack.getMinStackSize(), 3);  
		
		stack.pop(); // pops 2
		assertEquals(stack.getMinimum(), 4);
	}

}
