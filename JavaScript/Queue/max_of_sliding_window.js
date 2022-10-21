/*
Given array A[] with sliding window of size w which is 
moving from the very left of the array to the very right. 
Assume that we can only see the w numbers in the window 
each time the sliding window moves rightwards by one 
position. For example: The array is [1 3 -1 -3 5 3 6 7], 
and w is 3; Output is [3, 3, 5, 5, 6, 7]
 */


function slidingWindowMax(arr, w) {
  if (!arr || arr.length < 1 || !w || w <= 1) return arr;

  let i = 0, len = arr.length;
  let result = [];

  while (i + w <= len) {
    let max = Number.NEGATIVE_INFINITY;
    let j = i;

    while (j - i < w) {
      if (max < arr[j]) {
        max = arr[j];
      }
      j++;
    }

    result.push(max);
    i++;
  }

  return result;
}
