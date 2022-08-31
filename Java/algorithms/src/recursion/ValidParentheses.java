package recursion;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * Problem statement: : Implement an algorithm to print all valid (e.g., properly opened and 
 * closed) combinations of n pairs of parentheses.
 */

public class ValidParentheses {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number of valid pairs to generate: ");
		int pairs = scanner.nextInt();
		System.out.println(generateParens(pairs));
		scanner.close();
	}

	private static Set<String> generateParens(int remainingPairs) {
		Set<String> uniqueSet = new HashSet<String>();
		if (remainingPairs == 0) {
			uniqueSet.add("");
		} else {
			Set<String> prevSet = generateParens(remainingPairs - 1);
			for (String str : prevSet) {
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) == '(') {
						String newCombo = insertIn(str, i);
						uniqueSet.add(newCombo);
					}
				}
				uniqueSet.add("()" + str);  //  Add a pair to the beginning of the string 
			}
		}
		return uniqueSet;
	}
	
	// Insert after a left parentheses
	private static String insertIn(String str, int leftParenIdx) {
		String leftSubstr = str.substring(0, leftParenIdx + 1);
		String rightSubstr = str.substring(leftParenIdx + 1);
		return leftSubstr + "()" + rightSubstr;
	}
}
/*
 * (Not very efficient solution)
 */