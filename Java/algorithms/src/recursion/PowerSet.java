package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Problem statement: Write a method to return all subsets of a set.
 */

public class PowerSet {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter a list of space-separated set elements: ");
		String inputSet = reader.readLine();
		HashSet<String> set = new HashSet<>(Arrays.asList(inputSet.split(" ")));
		String[] distinctSet = set.toArray(new String[set.size()]);
		System.out.println(getSubsets(distinctSet));
	}

	private static Set<ArrayList<String>> getSubsets(String[] arr) {
		ArrayList<String> subset = new ArrayList<>(Arrays.asList(arr));
		Set<ArrayList<String>> subsets = new HashSet<>();
		subsets.add(subset);
		getSubsets(subset, subsets);
		return subsets;
	}

	private static void getSubsets(ArrayList<String> subset, Set<ArrayList<String>> subsets) {	
		int len = subset.size();
		
		if (len == 1) {
			return;
		}
		
		for (int i = 0; i < len; i++) {
			ArrayList<String> subsetCopy = new ArrayList<>(subset);
			subsetCopy.remove(i);
			if (subsets.add(subsetCopy)) {
				getSubsets(subsetCopy, subsets);
			}
		}
	}
}
