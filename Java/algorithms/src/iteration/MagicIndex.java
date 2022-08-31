package iteration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Problem statement: A magic index in an array A[0...n-1] is defined to be an index 
 * such that A[i] = i. Given a sorted array of distinct integers, write a method to find a 
 * magic index, if one exists, in array A.
 * 
 * 
 */

public class MagicIndex {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
		System.out.println(magicIndex(sortedInts));
	}
	
	private static int magicIndex(int[] arr) {
		int startIdx = 0;
		int endIdx = arr.length - 1;
		int midIdx = 0;
		
		while (startIdx <= endIdx) {
			midIdx = (startIdx + endIdx) / 2;
			if (arr[midIdx] == midIdx) {
				return midIdx;
			} else if (arr[midIdx] < midIdx) {
				startIdx = midIdx + 1;
			} else if (arr[midIdx] > midIdx) {
				endIdx = midIdx - 1;
			}
		}
		
		return -midIdx - 1;
	}

}
