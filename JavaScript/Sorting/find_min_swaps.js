/**
 * Finds the minimum number of swaps required to sort a one-dimensional
 * array. This implementation utilizes the quick sort algorithm.
 * @param {*} arr one-dimensional array
 * @returns minimum swaps of elements for the array to be in sorted
 * order
 */
export default function findMinSwaps(array) {
  let arr = [...array]; // a copy of array to prevent mutating the input
  let minSwaps = 0;

  // sort arr by partitioning: make the first element the partitioning
  // element and place it into its final position in the sorted version
  // of the array bounded by start and end index and return the final index
  function partitionSort(start, end) {
    while (start < end) {
      // compare partitioning element with rightmost uncompared element
      while (start < end) {
        if (arr[start] > arr[end]) {
          let temp = arr[start];
          arr[start++] = arr[end];
          arr[end] = temp;
          ++minSwaps; // increment minimum swaps
          break;
        }
        --end;
      }
      // compare partitioning element with leftmost uncompared element
      while (start < end) {
        if (arr[end] < arr[start]) {
          let temp = arr[end];
          arr[end--] = arr[start];
          arr[start] = temp;
          ++minSwaps; // increment minimum swaps
          break;
        }
        ++start;
      }
    }

    return start; // the partition index
  }

  // divide arr into halves at partition element and repeat for the halves
  function divide(start, end) {
    let partitionIdx = partitionSort(start, end);

    if (partitionIdx - 1 > start) {
      divide(start, partitionIdx - 1);
    }

    if (partitionIdx + 1 < end) {
      divide(partitionIdx + 1, end);
    }
  }

  divide(0, arr.length - 1);

  return minSwaps;
}

// Time Complexity: O(nlogn) (Worst-case analysis)
