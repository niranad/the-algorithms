package numbers.equations;

/*
 * Checks if there exists two positive integers a and b for which 2a + 2b + 2ab = X, 
 * where X is any given positive integer. Returns "YES" if a solution exist, "NO" otherwise.
 * 
 * First input is the number of test cases. Each test case is a single integer value of X.
 */

import static java.lang.Integer.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloEquation {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int tests = parseInt(reader.readLine());
		int i = 0;

		while (i < tests) {
			int x = parseInt(reader.readLine());
			boolean answer = eqnIsSolvableForX(x);
			System.out.println(answer ? "YES" : "NO");
			i++;
		}

		reader.close();
	}

	private static boolean eqnIsSolvableForX(int x) {
		if (x > 4) {
			int num = x + 4;

			// If num is a perfect square
			double sqrt = Math.sqrt(num);
			int intPart = (int) sqrt;
			if (sqrt == intPart) {
				return true;
			}
			// Otherwise
			for (int i = 3; i <= Math.sqrt(num); i++) {
				if (num % i == 0) {
					return true;
				}
			}
		}

		return false;
	}
}
