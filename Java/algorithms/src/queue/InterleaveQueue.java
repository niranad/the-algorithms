package queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
 * Problem statement:
 * Given a queue of integers, rearrange the elements by interleaving the first half of
 * the list with the second half of the list. For example, suppose a queue stores the following
 * sequence of values: [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]. Consider the two halves of
 * this list: first half: [11, 12, 13, 14, 15] second half: [16, 17, 18, 19, 20]. These are
 * combined in an alternating fashion to form a sequence of interleave pairs: the first values
 * from each half (11 and 16), then the second values from each half (12 and 17), then the
 * third values from each half (13 and 18), and so on. In each pair, the value from the first
 * half appears before the value from the second half. Thus, after the call, the queue stores the
 * following values: [11, 16, 12, 17, 13, 18, 14, 19, 15, 20].
 */

public class InterleaveQueue {

	public static void main(String[] args) {
		Queue<Integer> queue = new ArrayDeque<>();
		int[] testData = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		Arrays.stream(testData).forEach(n -> queue.offer(n));
		System.out.println(interleave(queue));
	}
	
	private static Queue<Integer> interleave(Queue<Integer> q) {
		if (q.size() % 2 != 0) {
			throw new IllegalArgumentException("Queue size must be even");
		}
		
		int[] integers = new int[q.size() / 2];
		
		for (int i = 0, half = q.size() / 2; i < half; i++) {
			integers[i] = q.poll();
		}
		
		for (int i = 0, len = integers.length; i < len; i++) {
			q.offer(integers[i]);
			q.offer(q.poll());
		}
		
		return q;
	}

}
