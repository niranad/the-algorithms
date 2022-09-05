package iteration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * Problem statement: Given a short string s and a long string l, find all the permutations of the
 * short string in the long string and return their locations.
 * 
 * Two input strings are required when executed. First line of input is the short string while 
 * the second line is the long string.
 */

public class PermutationSubsequence {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the short string: ");
		String shortStr = reader.readLine();
		System.out.print("Enter the long string: ");
		String longStr = reader.readLine();
		System.out.println(permutSubseqLocs(shortStr, longStr));
	}

	private static ArrayList<Integer> permutSubseqLocs(String shortStr, String longStr) {
		int len = longStr.length();
		int shortLen = shortStr.length();
		String temp = "";
		ArrayList<Integer> locations = new ArrayList<>();

		for (int i = 0; i < len; i++) {
			temp += longStr.charAt(i);
			if (temp.length() == shortLen) {
				if (arePermutations(shortStr, temp)) {
					locations.add(i - (shortLen - 1));
				}
				temp = temp.substring(1);  // drop the first char
			}
		}

		return locations;
	}

	private static boolean arePermutations(String shortStr, String longStrSubseq) {
		for (int i = 0; i < shortStr.length(); i++) {
			int index = longStrSubseq.indexOf(shortStr.charAt(i));
			if (index < 0) {
				return false;
			}
			longStrSubseq = longStrSubseq.substring(0, index) + longStrSubseq.substring(index + 1);
		}

		return true;
	}
}

/* Runtime Complexity: O((B-S+1)*S) ~ O(BS) where B and S are lengths of the 
 * long and short string respectively.
 */