package recursion.numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Problem statement: Write a recursive function to multiply two positive integers without using the
 * operator.You can use addition, subtraction, and bit shifting, but you should minimize the number
 * of those operations.
 * 
 * First and second line of input are first and second integer respectively.
 */

public class RecursiveMultiply {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter first integer: ");
		int int1 = Integer.parseInt(reader.readLine());
		System.out.print("Enter second integer: ");
		int int2 = Integer.parseInt(reader.readLine());
		System.out.println(recursiveMultiply(int1, int2));
	}

	private static int recursiveMultiply(int a, int b) {
		return recursiveMultiply(a, b, a);
	}
	
	private static int recursiveMultiply(int a, int b, int result) {
		if (b == 0) {
			result -= a;
			return result;
		} else if (b == 1) {
			return result;
		} else if (b < 0) {
			result -= a;
			b++;
			return recursiveMultiply(a, b, result);
		} else {
			result += a;
			b--;
			return recursiveMultiply(a, b, result);
		}
	}
}
