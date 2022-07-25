/**
 * Doubly Linked List
 */
export default class DLL {
  constructor() {
    this.head = null;
    this.tail = null;
  }

  /**
   * Inserts a node with the given data as the first node of the list.
   * @param {*} data data of node to be inserted
   * @returns inserted data
   */
  insertFirst = (data) => {
    if (!data) {
      return null;
    }

    if (this.head === null) {
      this.head = this.tail = new DLLNode(data);
    } else {
      this.head = this.head.prev = new DLLNode(data, null, this.head);
    }

    return data;
  };

  /**
   * Inserts a node with the given data to the back of the list.
   * @param {*} data data of node to be added
   * @returns inserted data
   */
  insertLast = (data) => {
    if (!data) {
      return null;
    }

    if (this.head === null) {
      this.head = this.tail = new DLLNode(data);
    } else {
      this.tail = this.tail.next = new DLLNode(data, this.tail);
    }

    return data;
  };

  /**
   * Adds a node with the given data at the specified index position.
   * @param {*} index position at which the new node will be inserted
   * @param {*} data data of node to be inserted
   * @returns inserted data
   */
  insert = (index, data) => {
    if (
      this.head === null ||
      data === null ||
      data === undefined ||
      index >= this.size() ||
      index < 0
    ) {
      return null;
    }

    let i = 0;
    let currNode = this.head;

    while (i < index) {
      currNode = currNode.next;
      i++;
    }

    if (index === 0) {
      this.head = new DLLNode(data, null, this.head);
      this.head.next.prev = this.head;
    } else {
      let nodeBefore = currNode.prev;
      let newNode = new DLLNode(data, nodeBefore, currNode);
      currNode.prev = newNode;
      nodeBefore.next = newNode;
    }

    return data;
  };

  /**
   * Retrieves the list element at a given position.
   * @param {*} index position of the node to get
   * @returns reference to the node
   */
  get = (index) => {
    if (this.isEmpty() || index < 0 || index >= this.size()) {
      return null;
    }

    let currNode = this.head;
    let i = 0;

    while (i < index) {
      currNode = currNode.next;
      ++i;
    }

    return currNode;
  };

  /**
   * Reverses the order of the list, making the head element the tail and vice-versa.
   */
  reverse = () => {
    let currNode = this.head;

    while (currNode !== null) {
      let prev = currNode.prev;
      let next = currNode.next;
      currNode.prev = next;
      currNode.next = prev;
      currNode = currNode.prev;
    }

    let last = this.head;
    let first = this.tail;
    this.head = first;
    this.tail = last;
  };

  /**
   * Removes the first list node.
   * @returns the removed node's data
   */
  removeFirst = () => {
    if (this.isEmpty()) {
      return null;
    }

    let removedData = null;

    if (this.head === this.tail) {
      removedData = this.head.data;
      this.head = this.tail = null;
    } else {
      removedData = this.head.data;
      this.head = this.head.next;
      this.head.prev = null;
    }

    return removedData;
  };

  /**
   * Removes the last list node.
   * @returns the removed node's data
   */
  removeLast = () => {
    if (this.isEmpty()) {
      return null;
    }

    let removedData = null;

    if (this.head === this.tail) {
      removedData = tail.data;
      this.head = this.tail = null;
    } else {
      let currNode = this.head;

      while (currNode.next !== this.tail) {
        currNode = currNode.next;
      }

      removedData = currNode.next.data;
      currNode.next = null;
      this.tail = currNode;
    }

    return removedData;
  };

  /**
   * Removes the list node at a given position.
   * @param {*} index position of the node to be removed
   * @returns the removed node's data
   */
  remove = (index) => {
    if (this.isEmpty() || index < 0 || index >= this.size()) {
      return null;
    }

    let currNode = this.head;
    let i = 0;

    while (i < index) {
      currNode = currNode.next;
      i++;
    }

    let removedData = currNode.data;

    if (index === 0) {
      if (this.size() == 1) {
        this.head = this.tail = null;
      } else {
        this.head = this.head.next;
        this.head.prev = null;
      }
    } else if (index === this.size() - 1) {
      this.tail = this.tail.prev;
      this.tail.next = null;
    } else {
      currNode.prev.next = currNode.next;
      currNode.next.prev = currNode.prev;
      currNode = null;
    }

    return removedData;
  };

  /**
   * Removes nodes with duplicate datas, leaving one of such nodes.
   * @returns the new size of the list
   */
  removeDuplicates = () => {
    let len = this.size();
    let pointer1 = this.head;
    let pointer2 = pointer1.next;
    let i = 0;
    let j = i + 1;

    while (i < len) {
      let data1 = pointer1.data;
      while (j < len) {
        let data2 = pointer2.data;
        if (data1 === data2) {
          if (j === len - 1) {
            this.tail = this.tail.prev;
            this.tail.next = null;
          } else {
            pointer2.prev.next = pointer2.next;
            pointer2.next.prev = pointer2.prev;
          }
          len = this.size();
        }
        if (j < len - 1) {
          pointer2 = pointer2.next;
        }
        ++j;
      }
      if (i < len - 2) {
        pointer1 = pointer1.next;
        pointer2 = pointer1.next;
      }
      ++i;
      j = i + 1;
    }

    return len;
  };

  /**
   * Checks if the list is empty
   * @returns boolean
   */
  isEmpty = () => this.head === null;

  /**
   * Determines the length of the list.
   * @returns the length of the list
   */
  size = () => {
    if (this.isEmpty()) {
      return 0;
    }

    let currNode = this.head;
    let size = 0;

    while (currNode !== null) {
      ++size;
      currNode = currNode.next;
    }

    return size;
  };
}

/**
 * Doubly Linked List Node
 */
class DLLNode {
  constructor(data, prev = null, next = null) {
    this.data = data;
    if (
      (prev === null || prev instanceof DLLNode) &&
      (next === null || prev instanceof DLLNode)
    ) {
      this.prev = prev;
      this.next = next;
    }

    throw Error('Node object is incompatible with DLLNode');
  }
}
