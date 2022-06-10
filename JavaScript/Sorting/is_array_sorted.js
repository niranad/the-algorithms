/**
 * Check if an array of numbers is sorted in either increasing or decreasing order.
 * @param {*} arr array to be checked
 * @returns true if array is sorted, false otherwise.
 */
export default function isArraySorted(arr) {
  if (arr.length === 1 || arr.length === 0) return true

  // check if array is sorted in increasing order
  function checkIncreasing() {
    let i = 0,
      j = 1

    while (arr[i] <= arr[j]) {
      ++i
      if (++j > arr.length - 1) return true
    }

    return false
  }

  function checkDecreasing() {
    let i = arr.length - 1,
      j = arr.length - 2

    while (arr[i] <= arr[j]) {
      --i
      if (--j < 0) return true
    }

    return false
  }

  return checkIncreasing() || checkDecreasing()
}

// Time Complexity: O(n) (Worst-case analysis)
