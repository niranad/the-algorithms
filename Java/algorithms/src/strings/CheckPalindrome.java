package strings;

import java.util.Scanner;

/*
 * Problem statement: Given an array of characters formed with a’s and b’s. The string is marked with special 
 * character X which represents the middle of the list (for example: ababaababXbababbaaa). 
 * Check whether the string is palindrome.
 */

public class CheckPalindrome {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a string to check for palindrome: ");
		String input = sc.nextLine();
		System.out.println(isPalindrome(input));
		sc.close();

	}

	private static boolean isPalindrome(String s) {
		int i = 0, j = s.length() - 1;

		while (i < j && s.charAt(i) == s.charAt(j)) {
			i++;
			j--;
		}

		return i >= j;
	}

}
