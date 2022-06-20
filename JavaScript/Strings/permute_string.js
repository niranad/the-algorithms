/**
 * Finds all permutations of a string. Each character is treated as unique
 * whether or not the input string contains duplicate characters. This permutation
 * is without repetition.
 * @param {*} s input string
 * @returns an array of all permutations of the string
 */
export default function permuteString(s) {
  // array of string characters
  let charArr = s.split('');
  // array of all permutations
  let permutations = [];

  // find permutations by recursion
  function permute(arr, i, n) {
    if (i > n - 2) {
      permutations.push(arr.join(''));
    } else {
      for (let j = i; j < n; j++) {
        let swappedCharArr = swap(arr, i, j);
        permute(swappedCharArr, i + 1, n);
      }
    }
  }

  // swap two characters
  function swap(arr, i, j) {
    // make a copy of arr to prevent mutation
    let a = [...arr];
    let temp = a[i];
    a[i] = a[j];
    a[j] = temp;
    return a;
  }

  permute(charArr, 0, s.length);
  return permutations;
}
