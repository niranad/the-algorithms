package recursion;

import java.util.Hashtable;
import java.util.Scanner;

/*
 * Problem statement: Given a string of brackets consisting of square brackets, parentheses 
 * and curly braces, determine if the string consist matching pairs of brackets.
 * 
 */

public class MatchingBrackets {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a string to test for matching pairs of brackets: ");
		String s = sc.nextLine();
		System.out.println(isMatchedBrackets(s));
		sc.close();
	}

	private static boolean isMatchedBrackets(String s) {
		Hashtable<Character, Character> map = new Hashtable<>(5);
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
		return isMatchedBrackets(s, s, map);
	}

	private static boolean isMatchedBrackets(String initialStr, String remStr,
		Hashtable<Character, Character> map) {
		if (initialStr.isBlank()) {
			return false;
		}

		if (remStr.length() == 0) {
			return true;
		}

		char leftmostBrac = remStr.charAt(0);
		remStr = remStr.substring(1);

		if (map.get(leftmostBrac) != null) {  // If leftmost bracket is an open bracket
			char currChar = '0';
			int i = -1;
			
			// loop to the corresponding closing bracket
			do {
				i++;
				currChar = remStr.charAt(i);
			} while (map.get(leftmostBrac) != currChar && (i + 1) < remStr.length());

			// remove the corresponding closing bracket and repeat the process
			if (map.get(leftmostBrac) == currChar) {
				remStr = remStr.substring(0, i) + remStr.substring(i + 1);
				return isMatchedBrackets(initialStr, remStr, map);
			}

			return false;
		}

		return false;
	}
}
