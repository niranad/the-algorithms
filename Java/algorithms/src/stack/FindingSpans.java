package stack;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Given an array A the span S[i] of A[i] is the maximum number of consecutive 
 * elements A[j] immediately preceding A[i] and such that A[j] ≤ A[j + 1]?
 */

public class FindingSpans {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a space-separated list of integers: ");
		String[] arr = scanner.nextLine().split("\\s+");
		int[] nums = new int[arr.length];
		// convert each string into numbers
		int i = 0;
		for (String s : arr) {
			nums[i++] = Integer.parseInt(s);
		}
		
		System.out.println(Arrays.toString(findSpan(nums)));
		scanner.close();
	}
	
	private static int[] findSpan(int[] arr) {
		int[] spans = new int[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			int span = 1;
			int j = i - 1;
			
			while (j >= 0) {
				if (arr[j] > arr[i]) {
					break;
				} else {
					span++;
					j--;
				}
			}
			
			spans[i] = span;
		}
		
		return spans;
	}

}
