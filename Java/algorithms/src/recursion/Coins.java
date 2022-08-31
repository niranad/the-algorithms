package recursion;

import java.util.Scanner;

/*
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and 
 * pennies (1 cent), write code to calculate the number of ways of representing cents.
 */

public class Coins {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number of cents: ");
		int cents = scanner.nextInt();
		System.out.println(countChangeWays(cents));
		scanner.close();
	}
	
	private static int countChangeWays(int cents) {
		int[] denoms = {25, 10, 5, 1};
		int[][] map = new int[cents + 1][denoms.length];
		return countChangeWays(cents, denoms, 0, map);
	}

	private static int countChangeWays(int amount, int[] denoms, int index, int[][] map) {
		if (map[amount][index] > 0) {
			return map[amount][index];
		}
		if (index >= denoms.length - 1) return 1;
		
		int denomAmount = denoms[index];
		int ways = 0;
		for (int i = 0; i * denomAmount <= amount; i++) {
			int amountRemaining = amount - i * denomAmount;
			ways += countChangeWays(amountRemaining, denoms, index + 1, map);
		}
		map[amount][index] = ways;
		return ways;
	}
}
