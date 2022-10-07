package stack;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Problem statement: Implement an algorithm to convert infix expression to postfix expression.
 */

public class InfixToPostfix {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter an infix expression: ");
		String input = sc.nextLine();

		try {
			System.out.println(enhancedInfixToPostfix(input));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sc.close();
	}

	// assumes the infix contains one-character operands
	private static String infixToPostfix(String infix) {
		Stack<String> stack = new Stack<>();
		String postfix = "";
		int len = infix.length();
		int idx = 0;
		stack.push("(");

		while (!stack.empty()) {
			if (idx < len) {
				String curr = String.valueOf(infix.charAt(idx++));
				if (curr.matches("\\d")) { // if operand
					postfix += curr;
				} else if (curr.matches("[+*/%(-]")) { // if operator or left parenthesis
					while (!stack.empty() && !stack.peek().matches("\\(")) {
						String operator = stack.peek();
						if (precedence(operator) >= precedence(curr)) {
							postfix += stack.pop();
						} else {
							break;
						}
					}
					stack.push(curr);
				} else if (curr.matches("\\)")) {
					while (!stack.empty() && !stack.peek().matches("\\(")) {
						postfix += stack.pop();
					}

					if (!stack.empty()) {
						stack.pop();
					}
				}
			} else if (!stack.empty() && !stack.peek().matches("\\(")) {
				postfix += stack.pop();
			} else {
				stack.pop();
			}
		}

		return postfix;
	}

	// fool-proof version for multiple-character operands
	static ArrayList<String> enhancedInfixToPostfix(String infix) throws Exception {
		if (!isValidInfix(infix)) {
			throw new Exception("Invalid Expression!");
		}

		// Split the infix into operands, operators and parentheses
		Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)|[+*/%)(-]");
		Matcher matcher = pattern.matcher(infix);
		ArrayList<String> tokens = new ArrayList<>();

		while (matcher.find()) {
			tokens.add(matcher.group());
		}

		Stack<String> stack = new Stack<>();
		ArrayList<String> postfix = new ArrayList<>();
		int currIdx = 0, len = tokens.size();
		stack.push("(");

		while (!stack.empty()) {
			if (currIdx < len) {
				String curr = tokens.get(currIdx++);
				if (curr.matches("(\\d+(\\.\\d+)?)")) { // if operand
					postfix.add(curr);
				} else if (curr.matches("[+*/%(-]")) { // if operator or left parenthesis
					while (!stack.empty() && !stack.peek().matches("\\(")) {
						String operator = stack.peek();
						if (precedence(operator) >= precedence(curr)) {
							postfix.add(stack.pop());
						} else {
							break;
						}
					}
					stack.push(curr);
				} else if (curr.matches("\\)")) { // if right parenthesis
					while (!stack.empty() && !stack.peek().matches("\\(")) {
						postfix.add(stack.pop());
					}

					if (!stack.empty()) {
						stack.pop();
					}
				}
			} else if (!stack.peek().matches("\\(")) {
				postfix.add(stack.pop());
			} else {
				stack.pop();
			}
		}

		return postfix;
	}

	private static boolean isValidInfix(String infix) {
		// invalid infix token pattern
		Pattern invalidToken = Pattern.compile("[^0-9+*/%)(.\\s-]+");
		Matcher matcher1 = invalidToken.matcher(infix);
		// trailing operator or left parenthesis
		Pattern trailingChar = Pattern.compile("[+*/%(-]+$");
		Matcher matcher2 = trailingChar.matcher(infix);
		// consecutive operators
		Pattern multipleOp = Pattern.compile("([+*/%-](\\s+)?[+*/%-]{1,})");
		Matcher matcher3 = multipleOp.matcher(infix);

		if (matcher1.find() || matcher2.find() || matcher3.find()) {
			return false;
		}

		// check for unmatched parenthesis
		Stack<Character> stack = new Stack<>();
		int idx = 0, len = infix.length();

		while (idx < len) {
			char curr = infix.charAt(idx++);
			if (curr == '(') {
				stack.push(curr);
			} else if (curr == ')') {
				if (stack.empty()) {
					return false;
				}
				stack.pop();
			}
		}

		return stack.empty();
	}

	private static int precedence(String operator) {
		switch (operator) {
		case "(":
			return 3;
		case "*":
		case "/":
		case "%":
			return 2;
		case "+":
		case "-":
			return 1;
		default:
			return 0;
		}
	}
}
