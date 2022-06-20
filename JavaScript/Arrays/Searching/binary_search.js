/**
 * Searches for a key value in a sorted array using the divide and conquer technique by
 * halving the sorted input array based on comparison.
 * @param {*} arr sorted array
 * @param {*} key search key
 * @returns index of the search key if found, otherwise a negative value.
 */

// Correct inputs are assumed; you might need to test if inputs are valid in your use case.
export default function binarySearch(arr, key) {
  let startIndex = 0;
  let endIndex = arr.length - 1;
  let midIndex;

  while (startIndex <= endIndex) {
    midIndex = Math.floor((startIndex + endIndex) / 2);
    if (arr[midIndex] === key) {
      return midIndex;
    } else if (arr[midIndex] < key) {
      startIndex = midIndex + 1;
    } else if (arr[midIndex] > key) {
      endIndex = midIndex - 1;
    }
  }

  return -1 - midIndex;
}

// Time Complexity: O(logn) (Worst-case analysis)
