package recursion;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
 * Problem statement: A robot is sitting on the upper left corner of grid with a rows and b columns. 
 * The robot can only move in two directions, right and down. Certain cells are "off limits" 
 * such that the robot cannot step on them. Design an algorithm to find a path for the robot from the 
 * top left to the bottom right.
 * 
 */

public class RobotGrid {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number of rows: ");
		int rows = scanner.nextInt();
		System.out.print("Enter number of columns: ");
		int columns = scanner.nextInt();
		pathFinder(rows, columns);
		scanner.close();
	}

	private static void pathFinder(int row, int col) {
		if (row < 0 || col < 0) {
			return;
		}

		boolean[][] maze = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				maze[i][j] = true;
			}
		}
		System.out.println(getPath(maze));
	}

	private static ArrayList<Point> getPath(boolean[][] maze) {
		if (maze == null || maze.length == 0) {
			return null;
		}

		ArrayList<Point> path = new ArrayList<>();
		HashSet<Point> failedPoints = new HashSet<>();

		if (getPath(maze, maze.length - 1, maze[0].length - 1, path, failedPoints)) {
			return path;
		}

		return null;
	}

	private static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path,
		HashSet<Point> failedPoints) {
		Point p = new Point(row, col);
		
		// If cell is out of bounds or inaccessible (i.e., "off limits")
		if (row < 0 || col < 0 || !maze[row][col] || failedPoints.contains(p)) {
			return false;
		}

		boolean isAtOrigin = (row == 0) && (col == 0);

		// If there's a path from start to this point, add it to list.
		if (isAtOrigin || getPath(maze, row - 1, col, path, failedPoints)
			|| getPath(maze, row, col - 1, path, failedPoints)) {
			path.add(p);
			return true;
		}

		failedPoints.add(p);
		return false;
	}
}
