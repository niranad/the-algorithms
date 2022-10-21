package datastructures.test;

import datastructures.Stack;
import exception.EmptyStackException;

public class StackTest2 {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		
		System.out.println("Pushing integers:");
		stack.push(3);
		System.out.printf("The stack is: %s%n", stack);
		stack.push(1);
		System.out.printf("The stack is: %s%n", stack);
		stack.push(5);
		System.out.printf("The stack is: %s%n", stack);
		stack.push(2);
		System.out.printf("The stack is: %s%n", stack);
		
		System.out.printf("%nGetting integers:%n");
		System.out.printf("integer at index 2: %d%n", stack.get(2));
		System.out.printf("integer at index 1: %d%n", stack.get(1));
		System.out.printf("integer at index 3: %d%n", stack.get(3));
		System.out.printf("integer at index 0: %d%n", stack.get(0));
		
		System.out.printf("%nPopping integers:");
		try {
			Integer removedInt = stack.pop();
			System.out.printf("%n%d removed%nThe stack is: %s%n", removedInt, stack);
			
			removedInt = stack.pop();
			System.out.printf("%n%d removed%nThe stack is: %s%n", removedInt, stack);
			
			removedInt = stack.pop();
			System.out.printf("%n%d removed%nThe stack is: %s%n", removedInt, stack);
			
			removedInt = stack.pop();
			System.out.printf("%n%d removed%nThe stack is: %s%n", removedInt, stack);
			
			removedInt = stack.pop();
			System.out.printf("%n%d removed%nThe stack is: %s%n", removedInt, stack);
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
	}

}
