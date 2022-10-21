package stack;

import java.util.Stack;

/*
 * Problem statement: Design an algorithm to sort a stack in ascending order.
 */

public class SortingStack {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(7);
		stack.push(2);
		stack.push(6);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		
		sortStack(stack);
		System.out.println(stack);
	}
	
	private static void sortStack(Stack<Integer> stack) {
		if (stack.empty()) return;
		int temp = stack.pop();
		sortStack(stack);
		insertInto(stack, temp);
	}
	
	private static void insertInto(Stack<Integer> stack, int data) {
		if (stack.empty() || data >= stack.peek()) {
			stack.push(data);
			return;
		}
		
		int temp = stack.pop();
		insertInto(stack, data);
		stack.push(temp);
	}

}
