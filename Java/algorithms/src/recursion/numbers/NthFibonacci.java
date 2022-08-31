package recursion.numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/*
 * Problem statement: Compute the nth term in the Fibonacci series using a memoized recursive approach.
 * 
 * Value of n is read from the input stream.
 */

public class NthFibonacci {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		System.out.println(nthFibonacciTerm(n));
	}

	private static BigInteger nthFibonacciTerm(int n) {
		if (n < 0) {
			return null;
		}
		return fibonacciTerm(n, new BigInteger[n + 1]);
	}
	
	private static BigInteger fibonacciTerm(int i, BigInteger[] memo) {
		if (i == 0 || i == 1) {
			return BigInteger.valueOf(i);
		}
		
		if (memo[i] == null) {
			memo[i] = fibonacciTerm(i - 1, memo).add(fibonacciTerm(i - 2, memo));
		}
		return memo[i];
 	}
}
