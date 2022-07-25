import { assert } from 'chai';
import quickSort from '../Arrays/Sorting/quick_sort.js';
import isArraySorted from '../Arrays/Sorting/is_array_sorted.js';

let arr = [];

for (let i = 0; i < 10_000_000; i++) {
  arr.push(100 + Math.floor(Math.random() * 29487785));
}

let promise = new Promise((res, rej) => {
  quickSort(arr);
  const isSorted = isArraySorted(arr);
  res(isSorted);
  rej(null);
});

describe('quickSort', () => {
  before(() => {});
  it('Returns true if array is sorted in increasing order', function (done) {
    promise
      .then((result) => {
        assert.isTrue(result);
        done();
      })
      .catch((err) => done(err));
  });
});
