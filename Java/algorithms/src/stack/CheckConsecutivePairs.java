package stack;

import java.util.Stack;

/*
 * Problem statement: Given a stack of integers, implement an algorithm to check whether each 
 * successive pair of numbers in the stack is consecutive or not. The pairs can be increasing 
 * or decreasing, and if the stack has an odd number of elements, the element at the top is 
 * left out of a pair. For example, if the stack of elements are [4, 5, -2, -3, 11, 10, 5, 6, 20], 
 * then the output should be true because each of the pairs (4, 5), (-2, -3), (11, 10), and (5, 6) 
 * consists of consecutive numbers.
 */

public class CheckConsecutivePairs {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(4);
		stack.push(5);
		stack.push(-2);
		stack.push(-3);
		stack.push(11);
		stack.push(10);
		stack.push(5);
		stack.push(6);
		stack.push(20);

		System.out.println(isConsecutivePairs(stack));
	}

	private static boolean isConsecutivePairs(Stack<Integer> stack) {
		if (stack == null || stack.empty()) return false;
		
		Integer[] integers = new Integer[stack.size()];
		stack.copyInto(integers);
		
		int i = stack.size() - 1;
		
		if (stack.size() % 2 != 0) {
			i -= 1;
		}
		
		int a = 0, b = 0, k = i;
		
		while (i >= 0) {
			if (i % 2 != 0) {
				if (i != k && Math.abs(a - b) != 1) {
					return false;
				}
				a = Math.abs(integers[i--]);
			} else {
				b = Math.abs(integers[i--]);
			}
		}
		
		return true;
	}
}
