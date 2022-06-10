/**
 * Sorts an array in-place by partitioning elements using iteration and divide and conquer
 * technique.
 * @param {*} arr unsorted array
 */

// ignore 'export default' used for exporting to the test file.
// Correct input is assumed.
export default function quickSort(arr) {
  // Place the leftmost element (partition element) originally at startIdx in its final
  // position in the sorted version of the array (bounded by startIdx and endIdx)
  // and return the index, the partition index at which the array will be halved.
  // (Time Complexity: O(n))
  function partitionSort(startIdx, endIdx) {
    while (startIdx < endIdx) {
      while (startIdx < endIdx) {
        // Compare with the rightmost element and swap if greater, increment startIdx
        // to the next uncompared leftmost element and break. Otherwise, compare with the
        // second rightmost element and so on.
        if (arr[startIdx] > arr[endIdx]) {
          let temp = arr[startIdx]
          arr[startIdx++] = arr[endIdx]
          arr[endIdx] = temp
          break
        }
        endIdx--
      }
      // Compare the partition element (swapped to the right) with the next uncompared
      // leftmost element and swap if lesser, decrement endIdx to the next uncompared rightmost
      // element and break. Otherwise, increment startIdx to the next uncompared leftmost
      // element
      while (startIdx < endIdx) {
        if (arr[endIdx] < arr[startIdx]) {
          let temp = arr[endIdx]
          arr[endIdx--] = arr[startIdx]
          arr[startIdx] = temp
          break
        }
        startIdx++
      }
    }

    return startIdx
  }

  // halve the array bounded by startIdx and endIdx at the partitioning element
  // (Time Complexity: O(logn))
  function halve(startIdx, endIdx) {
    let partitionIdx = partitionSort(startIdx, endIdx)

    if (partitionIdx - 1 > startIdx) {
      halve(startIdx, partitionIdx - 1)
    }
    if (partitionIdx + 1 < endIdx) {
      halve(partitionIdx + 1, endIdx)
    }
  }

  halve(0, arr.length - 1)
}

// Overall Time Complexity: O(nlogn) (Worst-case analysis)
