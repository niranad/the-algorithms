import { assert } from 'chai'
import binarySearch from '../Searching/binary_search.js'

let a = [2, 3, 5, 7, 9, 11, 13, 17, 19, 23, 29, 31, 37]

describe('binarySearch', () => {
  it('Returns 0 for first element key (edge case)', () => {
    assert.strictEqual(binarySearch(a, 2), 0)
  })

  it('Returns (a.length - 1) for last element key (edge case)', () => {
    assert.strictEqual(binarySearch(a, 37), 12)
  })

  it('Returns a negative value if key not found', () => {
    assert.isBelow(binarySearch(a, -1), 0)
  })

  it('Returns a positive value for key at the middle', () => {
    assert.strictEqual(binarySearch(a, 13), 6)
  })
})
