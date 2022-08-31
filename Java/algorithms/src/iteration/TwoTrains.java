package iteration;

import java.util.Scanner;

import static java.lang.Integer.*;

/*
 * Problem statement: There are N train stations and the time taken to travel between any two stations
 * by any train is known. Two trains A and B are traveling under the restriction that train A waits for
 * train B to reach the next station before leaving the preceding station any time during the journey.
 * Find the total time taken by train B to travel from station i to N.
 * 
 * First line of input is length of the array of travel time between any two stations; 
 * second line contains space-separated integers in the array of travel time.
 */

public class TwoTrains {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the length of the time array: ");
		int n = parseInt(input.next());
		System.out.print("Enter a list of space-separated time integers in the array: ");
		int minimumTime = minBTravelTime(n, input);
		System.out.println(minimumTime);
	}

	private static int minBTravelTime(int timeArrLen, Scanner reader) {
		int travelTime = 0;
		int totalWaitTime = 0;
		int delay = 0;
		int prevTime = 0;
		int tempSum1 = 0;
		int tempSum2 = 0;
		int i = 0;

		while (i < timeArrLen) {
			int time = parseInt(reader.next());

			if (i == 0) {
				totalWaitTime = time;
				delay = time;
				tempSum1 = time;
			} else if (time > prevTime) {
				if (delay != 0) {
					delay = time - prevTime;
					totalWaitTime += delay;
					tempSum1 = time;
					tempSum2 = 0;
				} else {
					int waitTime = (tempSum2 + time) - tempSum1;
					if (waitTime > 0) {
						totalWaitTime += waitTime;
						delay = waitTime;
						tempSum1 = time;
						tempSum2 = 0;
					} else {
						delay = 0;
						tempSum1 += time;
						tempSum2 += time;
					}
				}
			} else if (time <= prevTime) {
				if (tempSum1 > 0) {
					tempSum2 += time;
				}
				tempSum1 += time;
				delay = 0;
			}

			prevTime = time;
			travelTime += time;
			i++;
		}

		return (travelTime + totalWaitTime);
	}
}
