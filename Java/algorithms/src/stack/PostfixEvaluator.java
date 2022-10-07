package stack;

/*
 * Problem statement: Implement an algorithm to evaluate a postfix expression.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import static java.lang.Double.*;

public class PostfixEvaluator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a valid arithmetic expression: ");
		String input = sc.nextLine();

		try {
			ArrayList<String> postfix = InfixToPostfix.enhancedInfixToPostfix(input);
			System.out.println(evaluatePostfix(postfix));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		sc.close();
	}

	// assuming postfix is an arithmetic expression
	private static double evaluatePostfix(ArrayList<String> postfix) {
		Stack<Double> stack = new Stack<>();
		int idx = 0, len = postfix.size();
		stack.push(Double.MIN_VALUE);

		while (idx < len) {
			String token = postfix.get(idx++);
			if (token.matches("(\\d+(\\.\\d+)?)")) { // if operand
				stack.push(parseDouble(token));
			} else if (token.matches("[+*/%-]")) { // if (binary) operator
				double secondOp = stack.pop();
				double firstOp;
				if (stack.peek() == Double.MIN_VALUE) {
					stack.push(calculate(0, secondOp, token));
				} else {
					firstOp = stack.pop();
					stack.push(calculate(firstOp, secondOp, token));
				}
			}
		}

		double result = stack.pop();
		return result == Double.MIN_VALUE ? 0 : result;
	}

	// binary operation
	private static double calculate(double first, double second, String operator) {
		switch (operator) {
		case "+":
			return first + second;
		case "-":
			return first - second;
		case "*":
			return first * second;
		case "/":
			return first / second;
		case "%":
			return first % second;
		default:
			return 0;
		}
	}

}
