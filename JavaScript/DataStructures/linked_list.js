/**
 * Linked List implementation using ES5 constructor function. This implementation
 * uses private variables to implement the head and length of the list in order to prevent
 * direct access to the head node and length through the dot operator as is the case
 * with ES6 class.
 */
export function LinkedList() {
  // private variables
  let head = null;
  let length = 0;

  /**
   * Gets the head element node.
   * @returns the head element
   */
  this.getHead = function () {
    return head;
  };

  /**
   * Adds a node of the given data to the beginning of the list.
   * @param {*} data data of node to be added
   */
  this.addFirst = function (data) {
    let newNode = new LLNode(data);
    // if list is empty
    if (head === null) {
      head = newNode;
    } else {
      newNode.setNext(head);
      head = newNode;
    }

    ++length;
  };

  /**
   * Adds a node of the given data to the end of the list.
   * @param {*} data data of node to be added
   */
  this.addLast = (data) => {
    let newNode = new LLNode(data);
    // if list is empty
    if (head === null) {
      head = newNode;
    } else {
      let current = head;

      while (current.getNext() !== null) {
        current = current.getNext();
      }

      current.setNext(newNode);
    }

    ++length;
  };

  /**
   * Adds a node of the given data at the specified index in the list. Throws an
   * error if index is out of bounds.
   * @param {*} data data of node to be added
   * @param {*} index position at which to add the node
   */
  this.add = function (data, index) {
    let newNode = new LLNode(data);
    // if index exist (i.e., index is within bounds)
    if (index >= 0 && index <= length - 1) {
      // if adding at the first position
      if (index === 0) {
        newNode.setNext(head);
        head = newNode;
        length++;
        return;
      }

      let current = head; // current node
      let currIdx = 0; // current index pointer
      // scan the list to the node before the index
      while (current.getNext() !== null) {
        // if the current node is the node before the index
        if (currIdx === index - 1) {
          break;
        }
        current = current.getNext();
        currIdx++;
      }

      let nodeAtIndex = current.getNext();
      newNode.setNext(nodeAtIndex);
      current.setNext(newNode);
      length++;
      return;
    }

    throw Error('Index out of bounds!');
  };

  /**
   * Removes the first node in the list.
   * @returns the data at the first node removed or null if list is empty.
   */
  this.removeFirst = function () {
    // if list is empty
    if (head === null) {
      return null;
    }

    let firstdata = head.getData();
    head = head.getNext();
    length--;

    return firstdata;
  };

  /**
   * Removes the last node in the list.
   * @returns data at the last node removed or null if list is empty
   */
  this.removeLast = function () {
    // if list is empty
    if (head === null) {
      return null;
    }

    let lastdata;

    // if the list has only one node
    if (head.getNext() === null) {
      lastdata = head.getData();
      head = null;
      length--;
      return lastdata;
    }

    let current = head;
    // scan the list to a node before the last
    while (current.getNext().getNext() !== null) {
      current = current.getNext();
    }

    lastdata = current.getNext().getData();
    current.setNext(null); // set the last node to null;
    length--;

    return lastdata;
  };

  /**
   * Removes the data of node at the given index and returns it.
   * @param {*} index position of node to remove
   * @returns data at the removed node or null if index is invalid
   */
  this.remove = (index) => {
    // if index exists (i.e., within bounds)
    if (index >= 0 && index <= length - 1) {
      let removeddata;
      if (index === 0) {
        removeddata = head.getData();
        head = head.getNext();
        length--;
        return removeddata;
      }

      let current = head;
      let currIdx = 0;
      // scan the list to a node before the desired node to remove
      while (currIdx < index - 1) {
        current = current.getNext();
        currIdx++;
      }

      let nodeToRemove = current.getNext();
      removeddata = nodeToRemove.getData();
      let nodeAfter = nodeToRemove.getNext();
      current.setNext(nodeAfter);
      length--;

      return removeddata;
    }
  };

  /**
   * Returns the data of the node at the given index position.
   * @param {*} index position of node whose data is to be retrieved
   * @returns data of the node at the given index or undefined if index is invalid
   */
  this.dataAt = (index) => {
    // if index is valid
    if (index >= 0 && index <= length - 1) {
      let current = head;
      let currIdx = 0;

      while (currIdx < index) {
        current = current.getNext();
        currIdx++;
      }

      return current.getData();
    }
  };

  /**
   * Returns the index of the first node whose data equals the given data.
   * @param {*} data data of the node whose index should be retrieved
   * @returns index position of the node with the given data or -1 if index is out of
   * bounds
   */
  this.indexOf = (data) => {
    let current = head;
    let currIdx = 0;
    // scan the list to find the node with the given data
    while (current !== null && current.getData() !== data) {
      current = current.getNext();
      currIdx++;
    }

    if (current !== null) {
      return currIdx;
    }

    return -1;
  };

  /**
   * Returns the size of the list.
   * @returns the size of the list
   */
  this.size = () => {
    return length;
  };

  /**
   * Empties the list.
   */
  this.clear = () => {
    head = null;
    length = 0;
  };
}

/**
 * Linked List node implementation using ES5 constructor function.
 * @param {*} data data at the node to be created
 */
export function LLNode(data) {
  // if data is truthy
  if (data === undefined || data === null) {
    throw Error('data cannot be null or undefined');
  }

  // private variables
  let _data = data;
  let _next = null;

  this.getData = function () {
    return data;
  };

  this.setdata = function (data) {
    if (data !== undefined) {
      _data = data;
    }
  };

  this.getNext = function () {
    return _next;
  };

  this.setNext = function (next) {
    if (next === null || next instanceof LLNode) {
      _next = next;
    } else {
      throw Error('Attempt to add an invalid node object to this list');
    }
  };
}
