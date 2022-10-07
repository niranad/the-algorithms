package stack;

import java.util.Scanner;
import java.util.Stack;

/*
 * Problem statement: Check if a given string contains a series of balanced brackets such that 
 * the brackets are considered unbalanced if any pair of bracket (parentheses, curly braces or square
 * brackets) contains an unbalanced bracket e.g., [(){[}]] is unbalanced whereas [(){[]}] is balanced.
 */

public class BalancedBrackets {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a containing brackets: ");
		String input = sc.nextLine();
		System.out.println(isBalanced(input));
		sc.close();
	}

	private static boolean isBalanced(String s) {
		Stack<Character> stack = new Stack<>();
		int i = 0, len = s.length();

		while (i < len) {
			char curr = s.charAt(i); 

			if (String.valueOf(curr).matches("[\\(\\[\\{]")) {  // if left bracket
				stack.push(curr); 
				i++;
			} else if (String.valueOf(curr).matches("[\\)\\]\\}]")) {  // if right bracket
				if (stack.size() == 0) {
					return false;
				}

				char popped = stack.pop();

				if (curr == ')' && popped == '(' || curr == ']' && popped == '['
					|| curr == '}' && popped == '{') {
					i++;
				} else {
					return false;
				}
			} else {  // if not bracket
				i++;
			}
		}
		
		return stack.size() == 0;
	}
}
