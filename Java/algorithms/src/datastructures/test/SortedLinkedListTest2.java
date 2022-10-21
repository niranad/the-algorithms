package datastructures.test;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import datastructures.SortedLinkedList;

public class SortedLinkedListTest2 {
	/**
	 * Tests {@code class SortedLinkedList}. The only permitted runtime exceptions
	 * that may be thrown in this method are {@code IndexOutOfBoundsException} and
	 * {@code EmptyListException}. The former is due to the random generation of
	 * integers and the {@code retainAll} operation which may lead to lesser number
	 * of elements than 3 before the {@code removeAtIndex} operation is performed
	 * for index 2. The latter exception is due to the {@code retainAll} operation
	 * which may lead to an empty {@code SortedLinkedList} if no elements in its
	 * {@code Collection} argument is contained in the list.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SecureRandom random = new SecureRandom();
		SortedLinkedList<Integer> list = new SortedLinkedList<>();

		random.ints(25, 0, 100).boxed().forEach(n -> list.add(n));

		System.out.printf("The sorted list is:%n%s%n", list);
		System.out.printf("%nList size is: %d%n", list.size());
		System.out.printf("%nNode at index 6: %d%n", list.get(6));

		System.out.printf("%nArray container populated from list: %n%s%n",
			Arrays.toString(list.toArray(new Integer[25])));

		System.out.printf(
			"%nRemoved integer object (23) from list: %b%nList is now: %n%s%n",
			list.remove(23), list);

		Integer[] array = { 8, 20 };
		List<Integer> arrayList = new LinkedList<Integer>(Arrays.asList(array));

		System.out.printf("%nList containsAll arrayList: %b%n",
			list.containsAll(arrayList));

		System.out.printf("%nAdded arrayList to list: %b%nNew list is: %n%s%n",
			list.addAll(arrayList), list);

		Integer[] array2 = { 8, 11, 20, 22, 14, 32, 33, 35, 45, 56, 70, 63 };
		List<Integer> arrayList2 = new LinkedList<Integer>(Arrays.asList(array2));

		System.out.printf("%nRetained all arrayList2 in list: %b%nNew list is: %n%s%n",
			list.retainAll(arrayList2), list);

		System.out.printf("%nRemoved arrayList from list: %b%nNew list is: %s%n",
			list.removeAll(arrayList), list);

		System.out.printf("%nRemoved node at index 2: %b%nThe list is now: %s",
			list.removeAtIndex(2), list);
		System.out.printf("%nRemoved node at first index: %b%nThe list is now: %s",
			list.removeAtIndex(0), list);
		System.out.printf("%nRemoved node at last index: %b%nThe list is now: %s",
			list.removeAtIndex(list.size() - 1), list);
		System.out.printf("%n%nList contains 50: %b", list.contains(50));

		System.out.printf("%n%nArray from list: %n%s", Arrays.toString(list.toArray()));

		System.out.printf("%n%nInstantiating list from collection:%n");
		LinkedList<Integer> list2 = new LinkedList<>();
		random.ints(20, 10, 77).boxed().forEach(n -> list2.offer(n));

		System.out.printf("Collection list: %s", list2);

		SortedLinkedList<Integer> listFromCollection = new SortedLinkedList<>(list2);

		System.out.printf("%nList instantiated from collection:%n%s%n", listFromCollection);
		
		Iterator<Integer> iterator = listFromCollection.iterator();
		System.out.printf("%nIterating over listFromCollection:%n");
		listFromCollection.forEach(n -> System.out.printf("%d, ", iterator.next()));

		System.out.printf("%n%nClearing list...");
		listFromCollection.clear();

		System.out.printf("%nList is: %s", listFromCollection);
	}
}
