package stack;

import java.util.Arrays;
import java.util.Stack;

/*
 * Problem statement: Given a stack, implement an algorithm to reverse the stack using 
 * only push and pop operations.
 */

public class ReverseStack {

	public static void main(String[] args) {
		String[] sentence = new String[] { "world", "the", "loved", "so", "God", "For"};
		Stack<String> words = new Stack<>();
		
		Arrays.stream(sentence).forEach(s -> words.push(s));
		reverseStack(words);
		System.out.println(words);
	}
	
	
	private static <T extends Object> void reverseStack(Stack<T> stack) {
		if (stack.empty()) return;
		T temp = stack.pop();
		reverseStack(stack);
		insertAtBottom(stack, temp);
	}
	
	private static <T extends Object> void insertAtBottom(Stack<T> stack, T data) {
		if (stack.empty()) {
			stack.push(data);
			return;
		}
		
		T temp = stack.pop();
		insertAtBottom(stack, data);
		stack.push(temp);
	}
}
