package datastructures.test;

import java.util.Arrays;

import datastructures.BalancedBST;

public class BalancedBSTTest {

	public static void main(String[] args) {
		BalancedBST<Integer> tree = new BalancedBST<>();
		
		Integer[] nums = {11, 9, 4, 10, 7, 15, 4, 9, 3, 6, 2};
		
		Arrays.stream(nums).forEach(el -> tree.insert(el));
		
		System.out.println(tree.preorderTraversal());
		
		tree.delete(7);
		tree.delete(2);
		tree.delete(3);
		tree.delete(4);
		
		System.out.println(tree.preorderTraversal());
	}

}
