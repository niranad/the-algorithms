/*
Problem statement: Recursively remove all adjacent duplicates: Given an array of numbers, 
recursively remove adjacent duplicate numbers. The output array should not have any
adjacent duplicates. E.g. Input array: 1,5,6,8,8,8,0,1,1,0,6,5 | Output array: 1
*/

function removeAdjDups(arr) {
  if (!arr || arr.length < 2) return arr;

  let temp = [...arr];
  let i = 0, j = 1, len = temp.length;

  while (j < len) {
    let adjCount = 1;
    while (temp[i] === temp[j]) {
      adjCount++;
      j++;
    }

    // If adjacent duplicates found at current iteration
    if (adjCount > 1) {
      temp.splice(i, adjCount);
      len -= adjCount;
      i--;
      j = i + 1;
    } else {
      i++;
      j++;
    }
  }

  return temp;
}

