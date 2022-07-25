import { assert } from 'chai';
import DLL from '../DataStructures/doubly_linked_list.js';

let list;

describe('DLL, constructor()', () => {
  before(() => {
    list = new DLL();
  });

  it('should pass if list is empty at instantiation', () => {
    assert(list.isEmpty() === true, 'list is empty');
  });

  it('should pass if list size is 0 at instantiation', () => {
    assert(list.size() === 0, 'list size is 0');
  });

  it("should pass if list's head and tail are null at instantiation", () => {
    assert.isNull(list.head, "list's head is null at instantiation");
    assert.isNull(list.tail, "list's tail is null at instantiation");
  });
});

describe('DLL, insertFirst(data)', () => {
  it('should pass if data were inserted successfully', () => {
    assert.equal(list.insertFirst(5), 5);
    assert.equal(list.insertFirst(3), 3);
    assert.equal(list.insertFirst(-1), -1);
  });

  it('should pass if list is not empty after insertion', () => {
    assert.isFalse(list.isEmpty());
  });

  it('should pass if list size equals to number of inserted data', () => {
    assert.equal(list.size(), 3);
  });
});

describe('DLL, insertLast(data)', () => {
  before(() => {
    list = new DLL();
  });

  it('should pass if data were inserted successfully', () => {
    assert.equal(list.insertLast(2), 2);
    assert.equal(list.insertLast(4), 4);
    assert.equal(list.insertLast(5), 5);
  });

  it('should pass if list is not empty after insertions', () => {
    assert.isFalse(list.isEmpty());
  });

  it('should pass if list size equals to number of inserted data', () => {
    assert.equal(list.size(), 3);
  });
});

describe('DLL, insert(index, data)', () => {
  it('should pass if data were successfully inserted', () => {
    assert.equal(list.insert(0, -1), -1);
    assert.equal(list.insert(3, 7), 7);
    assert.equal(list.insert(2, 3), 3);
  });

  it('should pass if list size equals to the number of inserted data, including previous insertions', () => {
    assert.equal(list.size(), 6);
  });

  it('should pass if element at index at 2, first element and last element are 3, -1 and 5 respectively', () => {
    assert.equal(list.get(2), 3);
    assert.equal(list.get(0), -1);
    assert.equal(list.get(5), 5);
  });

  it('should pass if list is empty when an attempt to insert at an index is made', () => {
    list = new DLL();
    assert.isNull(list.insert(2, 5));
  });
});

describe('DLL, insertFirst(data) and insertLast(data)', () => {
  before(() => {
    list = new DLL();
  });

  it('should pass if data were inserted successfully', () => {
    assert.equal(list.insertFirst(3), 3);
    assert.equal(list.insertLast(5), 5);
    assert.equal(list.insertFirst(2), 2);
    assert.equal(list.insertFirst(-1), -1);
    assert.equal(list.insertLast(7), 7);
    assert.equal(list.insertLast(11), 11);
    assert.equal(list.insertFirst(-2), -2);
  });

  it('should pass if list size equals to number of inserted data', () => {
    assert.equal(list.size(), 7);
  });
});

describe('DLL, get(index)', () => {
  it('should pass if element at index 3, first element, and last element are 3, -2 and 11 respectively', () => {
    assert.equal(list.get(3), 3);
    assert.equal(list.get(0), -2);
    assert.equal(list.get(6), 11);
  });
});

describe('DLL, removeFirst(), removeLast(), and remove(index)', () => {
  it('should pass if elements were successfully removed', () => {
    assert.equal(list.removeFirst(), -2);
    assert.equal(list.removeLast(), 11);
    assert.equal(list.remove(3), 5);
  });

  it('should pass if list size is equal to the number of remaining elements', () => {
    assert.equal(list.size(), 4);
  });
});

describe('DLL, reverse()', () => {
  it('reverse method should reverse the list when called', () => {
    list.reverse();
    assert.strictEqual(list.size(), 4);
    assert.strictEqual(list.tail.data, -1);
    assert.strictEqual(list.tail.prev.data, 2);
    assert.strictEqual(list.head.data, 7);
    assert.strictEqual(list.head.next.data, 3);
  });
});
