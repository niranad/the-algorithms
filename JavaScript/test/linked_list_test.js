import { assert } from 'chai';
import { LinkedList } from '../DataStructures/linked_list.js';

let list = new LinkedList();

describe('LinkedList - addFirst(value)', () => {
  it('should add a node at the beginning of the list', () => {
    list.addFirst(7);
    list.addFirst(5);
    list.addFirst(3);
    assert.strictEqual(list.getHead().getData(), 3);
    assert.strictEqual(list.getHead().getNext().getData(), 5);
    assert.strictEqual(list.getHead().getNext().getNext().getData(), 7);
  });

  it('should throw an error if value is undefined', () => {
    assert.throws(list.addFirst);
  });
});

describe('LinkedList - addLast(value)', () => {
  before(() => {
    list.clear();
  });

  it('should add a node at the end of the list', () => {
    list.addLast(3);
    list.addLast(5);
    list.addLast(7);
    assert.strictEqual(list.getHead().getNext().getNext().getData(), 7);
    assert.strictEqual(list.getHead().getNext().getData(), 5);
    assert.strictEqual(list.getHead().getData(), 3);
  });

  it('should throw an error if value is undefined', () => {
    assert.throws(list.addLast);
  });
});

describe('LinkedList - add(value, index)', () => {
  beforeEach(() => {
    list.clear();
  });

  it('should add a node at the specified index if it exists', () => {
    list.addFirst(9);
    list.addLast(7);
    list.add(5, 1);
    assert.strictEqual(
      list.getHead().getNext().getData(),
      5,
      'should add node at the index',
    );
    assert.strictEqual(
      list.getHead().getNext().getNext().getData(),
      7,
      "'add' should shift former value at the index to the right",
    );
  });

  it('should throw an error if index is out of bounds', () => {
    assert.throws(list.add);
  });
});

describe('LinkedList - removeFirst()', () => {
  beforeEach(() => {
    list.clear();
  });

  it('should remove the first node from the list and return its value', () => {
    list.addFirst(3);
    list.addFirst(1);
    list.addLast(5);
    assert.strictEqual(list.removeFirst(), 1);
    assert.strictEqual(list.size(), 2);
  });

  it('should return null if list is empty', () => {
    assert.isNull(list.removeFirst());
  });
});

describe('LinkedList - removeLast()', () => {
  beforeEach(() => {
    list.clear();
  });

  it('should remove the last node in the list', () => {
    list.addFirst(3);
    list.addLast('Hello');
    list.addFirst(5);
    assert.strictEqual(list.removeLast(), 'Hello');
  });

  it('should return null if list is empty', () => {
    assert.isNull(list.removeLast());
  });
});

describe('LinkedList - remove(index)', () => {
  it('should remove the node from the given index if it exists, and return the value', () => {
    list.addFirst(3);
    list.addLast(7);
    list.addLast(9);
    assert.strictEqual(list.remove(0), 3);
    assert.strictEqual(list.remove(1), 9);
    assert.strictEqual(list.remove(0), 7);
  });

  it('should return undefined if index is out of bounds', () => {
    assert.isUndefined(list.remove(0));
  });
});

describe('LinkedList - dataAt(index)', () => {
  beforeEach(() => {
    list.clear();
  })

  it('should return the value at given node index', () => {
    list.addLast(7);
    list.addLast(11);
    list.addFirst(2);
    assert.strictEqual(list.dataAt(0), 2);
    assert.strictEqual(list.dataAt(1), 7);
    assert.strictEqual(list.dataAt(2), 11);
  })

  it('should return undefined if index is out of bounds', () => {
    assert.isUndefined(list.dataAt(0));
  })
})

describe('LinkedList - indexOf(value)', () => {
  it('should return index position of the first node with the given value', () => {
    list.addFirst(3);
    list.addLast(7);
    list.addFirst(6);
    assert.strictEqual(list.indexOf(6), 0);
    assert.strictEqual(list.indexOf(7), 2);
    assert.strictEqual(list.indexOf(3), 1);
  });

  it('should return -1 for a non-existent value in the list', () => {
    assert.strictEqual(list.indexOf(5), -1);
  })
})

describe('LinkedList - size', () => {
  it('should return the size of the list', () => {
    assert.strictEqual(list.size(), 3);
  })
})

describe('LinkedList - clear()', () => {
  it('should set head to null and list length to zero', () => {
    list.clear();
    assert.isNull(list.getHead());
    assert.strictEqual(list.size(), 0);
  })
 })
