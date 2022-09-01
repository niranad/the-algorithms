package recursion;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Problem statement: : Implement an algorithm to print all valid (e.g., properly opened and 
 * closed) combinations of n pairs of brackets containing all three bracket types (i.e., squares, 
 * braces and parentheses) whereby each type has same number of pairs. Ensure that n is a multiple
 * of 3.
 */

public class ValidBrackets {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number of valid pairs in multiple of 3 to generate: ");
		int pairs = scanner.nextInt();
		System.out.println(generateBrackets(pairs));
		scanner.close();
	}

	private static ArrayList<String> generateBrackets(int pairs) {
		if (pairs != 0 && pairs % 3 == 0) {
			char[] chars = new char[pairs * 2];
			int count = (pairs / 3);
			ArrayList<String> list = new ArrayList<>();
			addParen(list, count, count, count, count, count, count, chars, 0);
			return list;
		}
		return null;
	}

	private static void addParen(ArrayList<String> list, int leftParenRem, int rightParenRem,
		int leftSqRem, int rightSqRem, int leftBraceRem, int rightBraceRem, char[] chars,
		int index) {
		if ((leftParenRem < 0 || leftSqRem < 0 || leftBraceRem < 0) || (rightParenRem < leftParenRem
			|| rightSqRem < leftSqRem || rightBraceRem < leftBraceRem)) {  // invalid state
			return;
		}

		if (leftParenRem == 0 && rightParenRem == 0 && leftSqRem == 0 && rightSqRem == 0
			&& leftBraceRem == 0 && rightBraceRem == 0) {
			list.add(String.copyValueOf(chars));
		} else {
			chars[index] = '(';
			addParen(list, leftParenRem - 1, rightParenRem, leftSqRem, rightSqRem, leftBraceRem,
				rightBraceRem, chars, index + 1);

			chars[index] = ')';
			addParen(list, leftParenRem, rightParenRem - 1, leftSqRem, rightSqRem, leftBraceRem,
				rightBraceRem, chars, index + 1);

			chars[index] = '[';
			addParen(list, leftParenRem, rightParenRem, leftSqRem - 1, rightSqRem, leftBraceRem,
				rightBraceRem, chars, index + 1);

			chars[index] = ']';
			addParen(list, leftParenRem, rightParenRem, leftSqRem, rightSqRem - 1, leftBraceRem,
				rightBraceRem, chars, index + 1);

			chars[index] = '{';
			addParen(list, leftParenRem, rightParenRem, leftSqRem, rightSqRem, leftBraceRem - 1,
				rightBraceRem, chars, index + 1);

			chars[index] = '}';
			addParen(list, leftParenRem, rightParenRem, leftSqRem, rightSqRem, leftBraceRem,
				rightBraceRem - 1, chars, index + 1);
		}
	}
}
