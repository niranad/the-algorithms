/* Implement an algorithm to print all valid (e.g., properly opened and closed) combinations
   of n pairCount of parentheses. : Implement an algorithm to print all valid (e.g., properly opened and closed) combinations
   of n pairCount of parentheses. 
 */

function generateParens(pairCount) {
  function addParen(arr, leftRem, rightRem, chars, index) {
    if (leftRem < 0 || rightRem < leftRem) return;

    if (leftRem === 0 && rightRem === 0) {
      arr.push(chars.join(""));
    } else {
      chars[index] = '(';
      addParen(arr, leftRem - 1, rightRem, chars, index + 1);

      chars[index] = ')';
      addParen(arr, leftRem, rightRem - 1, chars, index + 1);
    }
  }

  let chars = new Array(pairCount * 2);
  let parens = [];
  addParen(parens, pairCount, pairCount, chars, 0)
  return parens;
}
