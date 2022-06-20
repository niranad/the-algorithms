package strings.permutations;

import java.util.ArrayList;

/**
 * <p>Problem statement:</p>
 * Generate all the strings of length {@code n} drawn from a given string of length {@code n}.
 */

public class StringPermutationWithRepetition {
	
	public static void main(String[] args) {
		System.out.println(generateStrings("abc"));
		
	}
	
	/**
	 * Generates all the strings with repetition of characters.
	 * @param s input string
	 * @return an {@code ArrayList} of the generated strings
	 */
	public static ArrayList<String> generateStrings(String s) {
		int sLen = s.length();
		String[] letters = new String[sLen];
		ArrayList<String> strings = new ArrayList<>();

		generator(sLen, sLen, s, letters, strings);
		
		return strings;
	}
	
	// generates the strings using recursion
	private static void generator(int n, int m, String s, String[] letters, ArrayList<String> strings) {
		if (n < 1) {
			// join letters in array into a string
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < letters.length; i++) {
				sb.append(letters[i]);
			}
			
			strings.add(sb.toString());
		} else {
			for (int i = 0; i < m; i++) {
				letters[n - 1] = String.valueOf(s.charAt(i));
				generator(n - 1, m, s, letters, strings);
			}
		}
	}
}

// Time Complexity: O(n*m^n) or O(n^2n) since n = m
