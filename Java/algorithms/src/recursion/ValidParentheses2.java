package recursion;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Problem statement: : Implement an algorithm to print all valid (e.g., properly opened and 
 * closed) combinations of n pairs of parentheses.
 */

public class ValidParentheses2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number of valid pairs to generate: ");
		int pairs = scanner.nextInt();
		System.out.println(generateParens(pairs));
		scanner.close();
	}

	private static ArrayList<String> generateParens(int count) {
		char[] chars = new char[count * 2];
		ArrayList<String> list = new ArrayList<>();
		addParen(list, count, count, chars, 0);
		return list;
	}

	private static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] chars,
		int index) {
		if (leftRem < 0 || rightRem < leftRem) return;
		
		if (leftRem == 0 && rightRem == 0) {
			list.add(String.copyValueOf(chars));
		} else {
			chars[index] = '(';
			addParen(list, leftRem - 1, rightRem, chars, index + 1);
			
			chars[index] = ')';
			addParen(list, leftRem, rightRem - 1, chars, index + 1);
		}
	}
}
/*
 * (Efficient solution) 
 */
