package recursion;

import java.util.ArrayList;

public class StringPermutations {

	public static void main(String[] args) {
		System.out.println(permuteString("ABCDEF"));
	}

	public static ArrayList<String> permuteString(String s) {
		// array of characters
		String[] charArr = s.split("");
		// resulting permutations
		ArrayList<String> permutations = new ArrayList<>();
		
		permute(charArr, 0, s.length(), permutations);
		return permutations;
	}

	// find all permutations by recursion
	private static void permute(String[] arr, int idx, int len, ArrayList<String> permutations) {
		if (idx > len - 2) {
			// join letters in arr
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]);
			}
		
			permutations.add(sb.toString());
		} else {
			for (int j = idx; j < arr.length; j++) {
				String[] newCharArr = swap(arr, idx, j);
				permute(newCharArr, idx + 1, len, permutations);
			}
		}
	}
	
	private static String[] swap(String[] arr, int i, int j) {
		// make copy of arr
		String[] a = new String[arr.length];
		System.arraycopy(arr, 0, a, 0, arr.length);
		
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}
}
