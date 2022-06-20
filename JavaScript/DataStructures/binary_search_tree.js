/**
 * A binary search tree implementation that does not allow duplicates.
 */
export class BST {
  constructor() {
    this.root = null;
  }

  /**
   * Iteratively inserts into this BST.
   * @param {*} value value to be inserted
   * @returns the inserted value if not duplicate, otherwise null
   */
  insert = (value) => {
    if (this.root === null) {
      this.root = new BSTNode(value);
    }

    let current = this.root;

    while (current) {
      if (value < current.value && !current.left) {
        current.left = new BSTNode(value);
      } else if (value < current.value && current.left) {
        current = current.left;
      } else if (value > current.value && !current.right) {
        current.right = new BSTNode(value);
      } else if (value > current.value && current.right) {
        current = current.right;
      } else {
        return null;
      }
    }

    return value;
  };

  /**
   * A depth-first traversal of this BST which processes each node's value
   * as it is visited.
   * @returns array of the processed values if BST is not empty, otherwise
   * null
   */
  preorderTraversal = () => {
    let output = [];
    const preorderHelper = (node) => {
      if (node) {
        output.push(node.value);
        preorderHelper(node.left);
        preorderHelper(node.right);
      }
    };
    preorderHelper(this.root);
    return output.length != 0 ? output : null;
  };

  /**
   * A depth-first traversal of this BST which processes the values in the
   * left subtree of each parent node before processing the value at the
   * parent node. Then, it processes the values in right subtree.
   * @returns an array of the processed values if BST is not empty,
   * otherwise null
   */
  inorderTraversal = () => {
    let output = [];
    const inorderHelper = (node) => {
      if (node !== null) {
        inorderHelper(node.left);
        output.push(node.value);
        inorderHelper(node.right);
      }
    };

    inorderHelper(this.root);
    return output.length != 0 ? output : null;
  };

  /**
   * A depth-first traversal of this BST which processes values in the left
   * subtree, then right subtree of each parent node before processing the
   * value at the parent node.
   * @returns an array of the processed values if BST is not empty,
   * otherwise null
   */
  postorderTraversal = () => {
    let output = [];
    const postorderHelper = (node) => {
      if (node) {
        postorderHelper(node.left);
        postorderHelper(node.right);
        output.push(node.value);
      }
    };

    postorderHelper(this.root);
    return output.length != 0 ? output : null;
  };

  /**
   * A breadth-first traversal of this BST which processes the values
   * of all nodes at each level of the tree i.e. processes all neighbouring
   * nodes before the child nodes of any nodes.
   * @returns an array of the processed values if BST is not empty,
   * otherwise null
   */
  levelorderTraversal = () => {
    let queue = [];
    let output = [];

    queue.push(this.root);

    while (queue.length !== 0) {
      output.push(queue[0].value);

      if (queue[0].left !== null) {
        queue.push(queue[0].left);
      }

      if (queue[0].right !== null) {
        queue.push(queue[0].right);
      }

      queue.shift();
    }

    return output;
  };

  /**
   * Iteratively checks if the tree contains a value.
   * @param {*} value value to check
   * @returns true if found, false otherwise
   */
  has = (value) => {
    let current = this.root;

    while (current) {
      if (value === current.value) {
        return true;
      } else if (value < current.value) {
        current = current.left;
      } else {
        current = current.right;
      }
    }

    return false;
  };

  /**
   * Recursively searches if this BST contains a value (key) and
   * returns a reference to the node where it is found.
   * @param {*} key search key or value
   * @returns reference to the containing node
   */
  search = (key) => {
    const treeSearch = (node) => {
      if (!node) {
        return null;
      } else if (key === node.value) {
        return node;
      } else if (key < node.value) {
        return treeSearch(node.left);
      } else {
        return treeSearch(node.right);
      }
    };

    return treeSearch(this.root);
  };

  /**
   * Finds the minimum value in this BST.
   * @returns the minimum value
   */
  findMin = () => {
    if (!this.root) return null;

    let current = this.root;

    while (current.left) {
      current = current.left;
    }

    return current.value;
  };

  /**
   * Finds the maximum value in this BST.
   * @returns the maximum value
   */
  findMax = () => {
    if (!this.root) return null;

    let current = this.root;

    while (current.right !== null) {
      current = current.right;
    }

    return current.value;
  };

  /**
   * Finds the minimum height of this BST.
   * @returns minimum height
   */
  findMinHeight = () => {
    if (!this.root) return -1;

    const findHeight = (node) => {
      if (!node) return 0;

      return 1 + Math.min(findHeight(node.left), findHeight(node.right));
    };

    return findHeight(this.root) - 1;
  };

  /**
   * Finds the maximum height of this BST.
   * @returns maximum height
   */
  findMaxHeight = () => {
    if (!this.root) return -1;

    const findHeight = (node) => {
      if (!node) return 0;

      return 1 + Math.max(findHeight(node.left), findHeight(node.right));
    };

    return findHeight(this.root) - 1;
  };

  /**
   * Finds the height of a node whose value is given. The height of a
   * node is the maximum number of levels from the next node to the
   * leaf node inclusive.
   * @param {*} nodeValue value of node whose height is needed
   * @returns height of the node if it exists, -1 otherwise
   */
  findHeight = (nodeValue) => {
    let current = this.root;

    // find the node
    while (current) {
      if (nodeValue === current.value) {
        break;
      } else if (nodeValue < current.value) {
        current = current.left;
      } else {
        current = current.right;
      }
    }

    const findNodeHeight = (node) => {
      if (!node) return 0;

      return (
        1 + Math.max(findNodeHeight(node.left), findNodeHeight(node.right))
      );
    };

    if (current) {
      return findNodeHeight(current) - 1;
    } else {
      return -1;
    }
  };

  /**
   * Finds the depth of the node whose value is given. The depth of a
   * node is the number of levels from the node up to the root node,
   * excluding the node's level.
   * @param {*} value value of node whose depth is needed
   * @returns depth of the node if it exists, -1 otherwise
   */
  findDepth = (value) => {
    if (value === undefined) return this.findMaxHeight();
    if (!this.root) return -1;

    let depth = 0;

    const findNodeDepth = (node) => {
      if (node.value === value) {
        return depth;
      } else if (value < node.value && node.left) {
        ++depth;
        return findNodeDepth(node.left);
      } else if (value < node.value && !node.left) {
        return -1;
      } else if (value > node.value && node.right) {
        ++depth;
        return findNodeDepth(node.right);
      } else {
        return -1;
      }
    };

    return findNodeDepth(this.root);
  };

  /**
   * Balances this BST. The order in which values are inserted into a BST
   * greatly impacts the efficiency of its operations. In a balanced BST,
   * operations such as insert, delete and find have O(logn) time.
   * A BST can be balanced by inserting the median values
   */
  balanceTree = () => {
    let sortedValues = this.inorderTraversal();
    let mid = sortedValues[Math.floor(this.size() / 2)];
    let midValues = [mid];

    // queues to hold left and right array partitions of sortedValues array
    // and subsequent array partitions with elements less and greater than
    // mid respectively
    let leftQueue = [sortedValues.filter((d) => d < mid)];
    let rightQueue = [sortedValues.filter((d) => d > mid)];

    const processMidOfPartitions = () => {
      while (leftQueue.length !== 0 || rightQueue.length !== 0) {
        if (leftQueue.length !== 0) {
          let left = leftQueue.shift();
          let mid = left[Math.floor(left.length / 2)];
          let left1 = [];
          let left2 = [];
          left.forEach((d) => {
            if (d < mid) {
              left1.push(d);
            } else if (d > mid) {
              left2.push(d);
            }
          });
          if (left1.length !== 0) {
            leftQueue.push(left1);
          }
          if (left2.length !== 0) {
            leftQueue.push(left2);
          }
          midValues.push(mid);
        }
        if (rightQueue.length !== 0) {
          let right = rightQueue.shift();
          let mid = right[Math.floor(right.length / 2)];
          let right1 = [];
          let right2 = [];
          right.forEach((d) => {
            if (d < mid) {
              right1.push(d);
            } else if (d > mid) {
              right2.push(d);
            }
          });
          if (right1.length !== 0) {
            rightQueue.push(right1);
          }
          if (right2.length !== 0) {
            rightQueue.push(right2);
          }
          midValues.push(mid);
        }
      }
    };
    processMidOfPartitions();
    this.root = null;
    midValues.forEach((d) => this.insert(d));
  };

  /**
   * Deletes a node with the given value using recursion to locate the
   * node.
   * @param {*} value value of node to be deleted
   * @returns reference to the root node
   */
  delete = (value) => {
    const deleteNode = (value, root = this.root) => {
      if (!root) return null;
      else if (value < root.value) {
        root.left = deleteNode(value, root.left);
      } else if (value > root.value) {
        root.right = deleteNode(value, root.right);
      } else {
        if (!root.left && !root.right) {
          root = null;
        } else if (!root.left) {
          root = root.right;
        } else if (!root.right) {
          root = root.left;
        } else {
          let current = root.right;
          while (current.left) current = current.left;
          root.value = current.value;
          root.right = deleteNode(current.value, root.right);
        }
      }
      return root;
    };

    return deleteNode(value);
  };

  /**
   * Inverts the binary search tree by swapping the left child with the right child and
   * vice-versa. After inversion, the tree is no longer a binary search tree.
   * A binary search tree inverted an even number of times is still a binary search tree.
   */
  invert = () => {
    const invertTree = (node) => {
      if (node != null) {
        let left = node.left;
        node.left = node.right;
        node.right = left;
        invertTree(node.left);
        invertTree(node.right);
      }
    };

    invertTree(this.root);
  };

  size = () => {
    let _size = 0;
    const sizeHelper = (node) => {
      if (node === null) {
        return;
      }

      _size++;
      sizeHelper(node.left);
      sizeHelper(node.right);
    };
    sizeHelper(this.root);
    return _size;
  };
}

/**
 * Binary Search Tree Node
 */
class BSTNode {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}
