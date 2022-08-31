package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Problem Statement: In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of
 * different sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order
 * of size from top to bottom (i.e., each disk sits on top of an even larger one). You have the following
 * constraints:
 * (1) Only one disk can be moved at a time.
 * (2) A disk is slid off the top of one tower onto another tower.
 * (3) A disk cannot be placed on top of a smaller disk.
 * Write a program to move the disks from the first tower to the last using stacks.
 * Print all the valid moves, representing the first, second and third towers as 1, 2, 3 such 
 * that a move from second to third tower is represented as 2 -> 3.
 * 
 */

public class TowerOfHanoi {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter number of disks to be moved: ");
		int numberOfDisks = Integer.parseInt(reader.readLine());
		towerOfHanoi(numberOfDisks, '1', '2', '3');
	}
	
	private static void towerOfHanoi(int n, char tower1, char tower2, char tower3) {
		if (n == 1) {
			System.out.println(String.valueOf(tower1) + " -> " + String.valueOf(tower3));
			return;
		}
		
		// Move n-1 disks from source peg to temporary peg using destination as auxiliary peg
		towerOfHanoi(n - 1, tower1, tower3, tower2);
		// Move 1 disk from source to destination
		System.out.println(String.valueOf(tower1) + " -> " + String.valueOf(tower3));
		// Move n-1 disks from temporary peg to destination peg using source as auxiliary peg
		towerOfHanoi(n - 1, tower2, tower1, tower3);
	}
}
