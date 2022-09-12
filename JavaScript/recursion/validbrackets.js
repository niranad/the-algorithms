/*
 * Problem statement: : Implement an algorithm to print all valid (e.g., properly opened and
 * closed) combinations of n pairs of brackets containing all three bracket types (i.e., squares,
 * braces and parentheses) whereby each type has same number of pairs. Ensure that n is a multiple
 * of 3.
 */

function generateBrackets(pairsInMultOf3) {
  if (
    typeof pairsInMultOf3 !== 'number' ||
    pairsInMultOf3 <= 0 ||
    pairsInMultOf3 % 3 !== 0
  )
    throw new Error('pairsInMultOf3 must be a positive integer multiple of 3');

  let count = pairsInMultOf3 / 3;
  let chars = new Array(pairsInMultOf3 * 2);
  let arr = [];

  function generator(
    list,
    leftParenRem,
    rightParenRem,
    leftSqRem,
    rightSqRem,
    leftBraceRem,
    rightBraceRem,
    chars,
    index,
  ) {
    if (
      leftParenRem < 0 ||
      leftSqRem < 0 ||
      leftBraceRem < 0 ||
      rightParenRem < leftParenRem ||
      rightSqRem < leftSqRem ||
      rightBraceRem < leftBraceRem
    ) {
      // invalid state
      return;
    }

    if (
      leftParenRem == 0 &&
      rightParenRem == 0 &&
      leftSqRem == 0 &&
      rightSqRem == 0 &&
      leftBraceRem == 0 &&
      rightBraceRem == 0
    ) {
      arr.push(chars.join(''));
    } else {
      chars[index] = '(';
      generator(
        list,
        leftParenRem - 1,
        rightParenRem,
        leftSqRem,
        rightSqRem,
        leftBraceRem,
        rightBraceRem,
        chars,
        index + 1,
      );

      chars[index] = ')';
      generator(
        list,
        leftParenRem,
        rightParenRem - 1,
        leftSqRem,
        rightSqRem,
        leftBraceRem,
        rightBraceRem,
        chars,
        index + 1,
      );

      chars[index] = '[';
      generator(
        list,
        leftParenRem,
        rightParenRem,
        leftSqRem - 1,
        rightSqRem,
        leftBraceRem,
        rightBraceRem,
        chars,
        index + 1,
      );

      chars[index] = ']';
      generator(
        list,
        leftParenRem,
        rightParenRem,
        leftSqRem,
        rightSqRem - 1,
        leftBraceRem,
        rightBraceRem,
        chars,
        index + 1,
      );

      chars[index] = '{';
      generator(
        list,
        leftParenRem,
        rightParenRem,
        leftSqRem,
        rightSqRem,
        leftBraceRem - 1,
        rightBraceRem,
        chars,
        index + 1,
      );

      chars[index] = '}';
      generator(
        list,
        leftParenRem,
        rightParenRem,
        leftSqRem,
        rightSqRem,
        leftBraceRem,
        rightBraceRem - 1,
        chars,
        index + 1,
      );
    }
  }

  generator(arr, count, count, count, count, count, count, chars, 0);
  return arr;
}
