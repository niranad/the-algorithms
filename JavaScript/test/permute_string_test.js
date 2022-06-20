import { assert } from 'chai';
import permuteString from '../Strings/permute_string.js';

let string = 'ABC';

describe('permuteString', () => {
  it('Returns all permutations of "ABC" as an array', () => {
    assert.sameMembers(permuteString(string), [
      'ABC',
      'BAC',
      'CBA',
      'BCA',
      'CAB',
      'ACB',
    ]);
  });
});
