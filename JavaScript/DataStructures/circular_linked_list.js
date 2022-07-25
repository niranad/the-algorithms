/**
 * Circular Linked List Node
 */
class CLLNode {
  constructor(data, next) {
    this.data = data;
    this.next = next instanceof CLLNode ? next : null;
  }
}

/**
 * Circular Linked List
 */
class CLL {
  constructor() {
    this.head = null;
    this.length = 0;
  }

  /**
   * Inserts a node with the given data as the first node.
   * @param {*} data data to be inserted
   */
  insertFirst = (data) => {
    let newNode = new CLLNode(data);
    newNode.next = newNode;

    // If list is empty
    if (this.head === null) {
      this.head = newNode;
    } else {
      newNode.next = this.head;
      let lastNode = this.getLastNode();
      // Make new node the head
      lastNode.next = newNode;
      this.head = newNode;
    }

    this.length++;
  };

  /**
   * Inserts a node with the given data as the last node.
   * @param {*} data data to be inserted
   */
  insertLast = (data) => {
    let newNode = new CLLNode(data);
    newNode.next = newNode;

    // If list is empty
    if (this.head === null) {
      this.head = newNode;
    } else {
      newNode.next = this.head;
      let lastNode = this.getLastNode();
      // Attach the newNode
      lastNode.next = newNode;
    }

    this.length++;
  };

  /**
   * Deletes the first node and returns its data.
   * @returns data at the deleted node
   */
  removeFirst = () => {
    // If list is empty
    if (this.head === null) {
      return null;
    }

    let removedData = null;
    // If list has one element
    if (this.head === this.head.next) {
      removedData = this.head.data;
      this.head = null;
      this.length--;
      return removedData;
    }

    let lastNode = this.getLastNode();
    // Update last node's next with next node to head
    lastNode.next = this.head.next;

    removedData = this.head.data;
    this.head = this.head.next;
    this.length--;

    return removedData;
  };

  /**
   * Deletes the last node and returns its data.
   * @returns data at the deleted node
   */
  removeLast = () => {
    // If list is empty
    if (this.head === null) {
      return null;
    }

    let removedData = null;
    // If list has one element
    if (this.head === this.head.next) {
      removedData = this.head.data;
      this.head = null;
      this.length--;
      return removedData;
    }

    // Get reference to the node before last
    let current = this.head;
    while (current.next.next !== this.head) {
      current = current.next;
    }

    removedData = current.next.data;
    current.next = this.head;
    this.length--;

    return removedData;
  };

  /**
   * Deletes the first node with the given data.
   * @param {*} data data
   * @returns true if the data is found and removed, false otherwise
   */
  remove = (data) => {
    // If list is empty
    if (this.head === null) {
      return false;
    }

    // If data equals data at the head
    if (data === this.head.data) {
      this.removeFirst();
      return true;
    }

    // If list has one element (then data is not found)
    if (this.head === this.head.next) {
      return false;
    }

    // Traverse the list to find data
    let current = this.head;
    while (current.next !== this.head) {
      if (current.next.data === data) {
        current.next = current.next.next;
        this.length--;
        return true;
      }
      current = current.next;
    }

    return false;
  };

  /**
   * Returns the length of this list
   * @returns length
   */
  size = () => {
    return this.length;
  };

  /**
   * Traverses the list to the last node.
   * @returns reference to the last node
   */
  getLastNode = () => {
    let current = this.head;
    while (current !== null && current.next !== this.head) {
      current = current.next;
    }
    return current;
  };

  printList = () => {
    let current = this.head;
    let currIdx = 0;

    while (currIdx === 0 || current !== this.head) {
      console.log(current.data);
      current = current.next;
      currIdx++;
    }
  };
}
