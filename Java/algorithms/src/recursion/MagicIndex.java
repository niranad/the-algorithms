package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Problem statement: A magic index in an array A[0...n-1] is defined to be an index 
 * such that A[i] = i. Given a sorted array of distinct integers, write a method to find a 
 * magic index, if one exists, in array A.
 * 
 */

public class MagicIndex {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter a list of space-separated integers of test array: ");
		String[] array = reader.readLine().split("\\s");
		int[] sortedInts = new int[array.length];
		int i = 0;
		for (String s : array) {
			try {
				sortedInts[i++] = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.println("Encountered a non-integer");
				return;
			}
		}
		System.out.println(magicIndex(sortedInts, 0, sortedInts.length - 1));
	}

	private static int magicIndex(int[] arr, int startIdx, int endIdx) {
		if (endIdx < startIdx) {
			return -1;
		}

		int midIdx = (startIdx + endIdx) / 2;

		if (arr[midIdx] == midIdx) {
			return midIdx;
		} else if (arr[midIdx] > midIdx) {
			return magicIndex(arr, startIdx, midIdx - 1);
		} else {
			return magicIndex(arr, midIdx + 1, endIdx);
		}
	}

}
