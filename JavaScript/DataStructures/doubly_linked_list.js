/**
 * Doubly Linked List
 */
export default class DLL {
  constructor() {
    this.head = null;
    this.tail = null;
  }

  insertAtFront = (data) => {
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

  insertAtBack = (data) => {
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

    return currNode.data;
  };

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

  removeFromFront = () => {
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

  removeFromBack = () => {
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

  isEmpty = () => this.head === null;

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
    this.prev = prev;
    this.data = data;
    this.next = next;
  }
}
