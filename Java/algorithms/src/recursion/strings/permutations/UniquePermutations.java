package recursion.strings.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Problem statement: Write a method to compute all permutations of a string with unique characters.
 * 
 * Reads the unique string from the input stream.
 */

public class UniquePermutations {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a unique string: ");
		String uniqueString = scanner.nextLine();
		System.out.println(uniquePermutations(uniqueString));
		scanner.close();
	}

	private static ArrayList<String> uniquePermutations(String uniqueStr) {
		ArrayList<String> permutations = new ArrayList<String>();
		uniquePermutations(uniqueStr, 0, permutations);
		return permutations;
	}

	private static void uniquePermutations(String uniqueStr, int startIdx,
		ArrayList<String> permutations) {
		if (startIdx == uniqueStr.length() - 1) {
			return;
		}

		for (int i = startIdx; i < uniqueStr.length(); i++) {
			String permutation = swap(uniqueStr, startIdx, i);
			if (startIdx == uniqueStr.length() - 2) {
				permutations.add(permutation);
			}
			uniquePermutations(permutation, startIdx + 1, permutations);
		}
	}

	private static String swap(String s, int idx1, int idx2) {
		if (idx1 == idx2) {
			return s;
		}

		char[] chars = s.toCharArray();
		char temp = chars[idx1];
		chars[idx1] = chars[idx2];
		chars[idx2] = temp;
		return String.copyValueOf(chars);
	}

//	private static ArrayList<String> permutation(String s) {
//		char[] str = new char[s.length()];
//	}
//	
//	private static ArrayList<String> buildPermuations(ArrayList<String> perms, char[] chars, int index, )
}
