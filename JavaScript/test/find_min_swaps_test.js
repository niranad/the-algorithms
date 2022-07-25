import { assert } from 'chai';
import findMinSwaps from '../Arrays/Sorting/find_min_swaps.js';

let arr;

describe('findMinSwaps', () => {
  it('Returns 5 for the array: [4, 2, 5, 7, 9, 3]', () => {
    arr = [4, 2, 5, 7, 9, 3];
    assert.strictEqual(findMinSwaps(arr), 5, 'should equal 5');
  });

  it('Returns 1 for the array: [2, 3, 5, 7, 6, 8]', () => {
    arr = [2, 3, 5, 7, 6, 8];
    assert.strictEqual(findMinSwaps(arr), 1, 'should equal 1');
  });

  it('Returns 5 for the array: [1, 9, 7, 6, 2, 15, 6, 2]', () => {
    arr = [1, 9, 7, 6, 2, 15, 6, 2];
    assert.strictEqual(findMinSwaps(arr), 5, 'should equal 5');
  });
});
