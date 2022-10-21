package queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

/*
 * Problem statement:
 * Given an integer k and a queue of integers, how do you reverse the order of the
 * first k elements of the queue, leaving the other elements in the same relative order? For
 * example, if k=4 and queue has the elements [10, 20, 30, 40, 50, 60, 70, 80, 90]; the output
 * should be [40, 30, 20, 10, 50, 60, 70, 80, 90].
 */

public class ReverseFirstK {

	public static void main(String[] args) {
		Queue<Integer> queue = new ArrayDeque<>();
		int[] integers = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
		Arrays.stream(integers).forEach(n -> queue.offer(n));
		reverseFirstKElems(queue, 4);
		System.out.println(queue);
	}

	private static void reverseFirstKElems(Queue<Integer> q, int k) {
		if (q == null || k > q.size()) {
			throw new IllegalArgumentException("Queue size must be greater or equal to k");
		} else if (k > 0) {
			Stack<Integer> stack = new Stack<>();
			for (int i = 0; i < k; i++) {
				stack.push(q.poll());
			}
			
			for (int i = 0; i < k; i++) {
				q.offer(stack.pop());
			}
			
			for (int i = 0; i < q.size() - k; i++) {
				q.offer(q.poll());
			}
		}
	}

}
