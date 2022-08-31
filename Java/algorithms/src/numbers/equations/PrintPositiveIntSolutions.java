package numbers.equations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Problem statement: Print all positive integer solutions to the equation a^3 + b^3 = c^3 + d^3 
 * where a, b, c and d are integers between 1 and 1000. 
 */

public class PrintPositiveIntSolutions {

	public static void main(String[] args) {
		HashMap<Integer, ArrayList<Entry<Integer, Integer>>> map = new HashMap<>();

		for (int c = 1; c <= 1_000; c++) {
			for (int d = 1; d <= 1_000; d++) {
				int result = (int) (Math.pow(c, 3) + Math.pow(d, 3));

				if (map.get(result) != null) {
					map.get(result).add(Map.entry(c, d));
				} else {
					ArrayList<Entry<Integer, Integer>> list = new ArrayList<>();
					list.add(Map.entry(c, d));
					map.put(result, list);
				}
			}
		}

		for (int a = 1; a <= 1_000; a++) {
			for (int b = 1; b <= 1_000; b++) {
				int result = (int) (Math.pow(a, 3) + Math.pow(b, 3));

				if (map.get(result) != null) {
					ArrayList<Entry<Integer, Integer>> list = map.get(result);
					for (Entry<Integer, Integer> pair : list) {
						System.out.println("a: " + a + ", b: " + b + ", c: " + pair.getKey()
							+ ", d: " + pair.getValue());
					}
				}
			}
		}
	}
}
