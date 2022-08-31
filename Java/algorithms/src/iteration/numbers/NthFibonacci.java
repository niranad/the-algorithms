package iteration.numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/*
 * Problem statement: Compute the nth term of a Fibonacci series using an iterative approach.
 */

public class NthFibonacci {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the value of n for nth term: ");
		int n = Integer.parseInt(reader.readLine());
		System.out.println(nthFibonacciTerm(n));
	}

	private static BigInteger nthFibonacciTerm(int n) {
		if (n < 0) {
			return null;
		}
		
		if (n == 0 || n == 1) {
			return BigInteger.valueOf(n);
		}
		
		BigInteger a = BigInteger.ZERO;
		BigInteger b = BigInteger.ONE;
		for (int i = 2; i < n; i++) {
			BigInteger c = a.add(b);
			a = b;
			b = c;
		}
		return a.add(b);
	}
}
