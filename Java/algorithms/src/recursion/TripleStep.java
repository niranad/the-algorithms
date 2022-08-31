package recursion;

import java.math.BigInteger;
import java.util.Scanner;

/*
 * Problem statement: A child is running up a stair case of n steps and can hop in either 1 step, 2 steps
 * or 3 steps at a time. Implement a method to count how many possible ways the child can run up the stairs.
 * 
 */

public class TripleStep {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number of steps in the stair case: ");
		int n = scanner.nextInt();
		System.out.println(possibleWays(n));
		scanner.close();
	}

	private static BigInteger possibleWays(int n) {
		if (n < 0) {
			return BigInteger.ZERO;
		}
		return possibleWays(n, new BigInteger[n + 1]);
	}

	private static BigInteger possibleWays(int n, BigInteger[] memo) {
		if (n < 0) {
			return BigInteger.ZERO;
		} else if (n == 0) {
			return BigInteger.ONE;
		}

		if (memo[n] == null) {
			memo[n] = possibleWays(n - 1, memo).add(possibleWays(n - 2, memo))
				.add(possibleWays(n - 3, memo));
		}
		return memo[n];
	}
}
