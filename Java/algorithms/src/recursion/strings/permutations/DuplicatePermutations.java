package recursion.strings.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/*
 * Problem statement: Write a method to compute all permutations of a string whose characters are not 
 * necessarily unique. The list of permutations should not have duplicates.
 */

public class DuplicatePermutations {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String duplicateString = scanner.nextLine();
		System.out.println(uniquePermutations(duplicateString));
		scanner.close();
	}

	private static ArrayList<String> uniquePermutations(String str) {
		ArrayList<String> permutations = new ArrayList<String>();
		str = getUniqueString(str);
		getUniquePermutations(str, 0, permutations);
		return permutations;
	}
	
	private static String getUniqueString(String s) {
		String[] arr = s.split("");
		HashSet<String> uniqueCharSet = new HashSet<>(Arrays.asList(arr));
		Object[] uniqueChars = uniqueCharSet.toArray();
		return Arrays.toString(uniqueChars).replace("[", "").replace("]", "").replace(", ", "");
	}

	private static void getUniquePermutations(String uniqueStr, int startIdx,
		ArrayList<String> permutations) {
		if (startIdx == uniqueStr.length() - 1) {
			return;
		}

		for (int i = startIdx; i < uniqueStr.length(); i++) {
			String permutation = swap(uniqueStr, startIdx, i);
			if (startIdx == uniqueStr.length() - 2) {
				permutations.add(permutation);
			}
			getUniquePermutations(permutation, startIdx + 1, permutations);
		}
	}

	private static String swap(String s, int idx1, int idx2) {
		String[] arr = s.split("");
		if (idx1 != idx2) {
			String temp = arr[idx1];
			arr[idx1] = arr[idx2];
			arr[idx2] = temp;
		}

		return Arrays.toString(arr).replace("[", "").replace("]", "").replace(", ", "");
	}
}
