package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*
 * A histogram is a polygon composed of sequence of rectangles aligned at a common base line. 
 * For simplicity, assume that the rectangles have equal widths but may have different heights. 
 * For example, the figure on the left shows a histogram that consists of rectangles with the 
 * heights 3, 2, 5, 6, 1, 4, 4, measured in units where 1 is the width of the rectangles. 
 * Here our problem is: given an array with heights of rectangles (assuming width is 1), we 
 * need to find the largest rectangle possible. For the given example, the largest rectangle 
 * is the shared part.
 */

public class LargestRectUnderHistogram {

	public static void main(String[] args) {
		int[] heights = { 3, 2, 7, 4, 10, 6, 14, 8 };
		System.out.println(maxRectAreaUnderHistogram(heights));
	}

	// naive solution -> O(n^2) runtime
	private static int findMaxArea(int[] heights) {
		if (heights == null || heights.length == 0)
			return 0;

		Stack<Integer> precedingAreas = new Stack<>();
		int maxArea = 0;

		for (int i = 0, len = heights.length; i < len; i++) {
			if (maxArea < heights[i]) {
				maxArea = heights[i];
			}

			int count = 1; // count how many consecutive preceding and succeeding heights are equal
							// or greater

			// count consecutive succeeding heights
			for (int j = i + 1; j < len; j++) {
				if (heights[j] >= heights[i]) {
					count++;
				} else {
					break;
				}
			}

			// count consecutive preceding heights
			if (!precedingAreas.empty() && precedingAreas.peek() >= heights[i]) {
				Integer[] precedingHeights = new Integer[i];
				precedingAreas.copyInto(precedingHeights);
				ArrayList<Integer> list = new ArrayList<>(Arrays.asList(precedingHeights));
				int k = i - 1;

				while (k >= 0 && list.get(k) >= heights[i]) {
					count++;
					k--;
				}
			}

			maxArea = Math.max(maxArea, heights[i] * count);
			precedingAreas.push(heights[i]);
		}

		return maxArea;
	}

	// efficient solution -> O(n) runtime
	private static int maxRectAreaUnderHistogram(int[] barHeights) {
		if (barHeights == null || barHeights.length == 0) {
			return 0;
		}

		Stack<Integer> stack = new Stack<>();
		int maxArea = 0, i = 0, len = barHeights.length;

		while (i < len) {
			// If stack is empty or current bar is higher or equal to that at stack top, push its index to the stack
			if (stack.empty() || barHeights[stack.peek()] <= barHeights[i]) {
				stack.push(i++);
			} else {
				int top = stack.pop();
				maxArea = Math.max(maxArea,
					barHeights[top] * (stack.empty() ? i : i - stack.peek() - 1));
			}
		}

		// Pop the remaining bars from stack and calculate area with every popped
		// bar as the smallest bar
		while (!stack.empty()) {
			int top = stack.pop();
			maxArea = Math.max(maxArea,
				barHeights[top] * (stack.empty() ? i : i - stack.peek() - 1));
		}

		return maxArea;
	}
}
