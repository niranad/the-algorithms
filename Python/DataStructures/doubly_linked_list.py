"""Doubly Linked List"""


class DLL:
    def __init__(self):
        self.head = None
        self.tail = None
        self.length = 0

    def insert_head(self, data):
        """Inserts a node with the given data at the beginning of the list."""
        new_node = DLLNode(data)

        # if list is empty
        if self.head is None:
            self.head = self.tail = new_node  # set head and tail to point to new node
        else:  # the list has one or more element
            new_node.next = self.head  # set the next pointer of new node to point to the head

            # if list has one element
            if self.head == self.tail:
                self.tail.prev = new_node  # set the prev pointer of tail to point to new node

            self.head.prev = new_node  # set the prev pointer of head to point to new node
            self.head = new_node  # set new node as the new head

        self.length = self.length + 1  # increment length

    def insert_tail(self, data):
        """Inserts a node with the given data at the end of the list."""
        new_node = DLLNode(data)

        # if list is empty
        if self.head == self.tail:
            self.head = self.tail = new_node
        else:  # list has one or more elements
            current = self.head
            # iterate the list to the last element
            while current.next is not None:
                current = current.next

            current.next = new_node  # set the last node's next pointer to the new node
            new_node.prev = current  # set the new node's prev pointer to the last node
            self.tail = new_node  # set the tail reference to point to the new node

        self.length = self.length + 1

    def insert(self, data, index):
        """Inserts a node with the given data at the specified index in the list. Raises an
        exception if the index does not exist."""
        new_node = DLLNode(data)

        # if index exists (also implies that the list is not empty)
        if 0 <= index <= self.length - 1:
            # if the list has only one element (meaning to insert at index 0)
            if self.head == self.tail:
                new_node.next = self.head  # set new node's next pointer to the head
                # set head's and tail's prev pointer and to the new node
                self.head.prev = self.tail.prev = new_node
                self.head = new_node  # set new head
            else:  # list has more than one element
                current = self.head
                curr_idx = 0
                # iterate to the index at which to insert
                while curr_idx < index:
                    current = current.next
                    curr_idx = curr_idx + 1

                new_node.next = current  # set the new node's next pointer to node at index (current)
                new_node.prev = current.prev  # set new node's prev pointer to that of the node at index
                current.prev = new_node  # set the prev pointer of node at index to the new node

                # if inserting at the beginning of the list (index = 0)
                self.head = new_node

            self.length = self.length + 1
            return

        raise Exception('Index out of bounds')

    def remove_head(self):
        """Removes the first node of the list and returns its data."""
        # if list is not empty
        if self.head is not None:
            first_data = self.head
            # if there is only one element in the list
            if self.head == self.tail:
                self.head = self.tail = None
            else:
                self.head = self.head.next  # advance the head to the next node
                self.head.prev = None  # discard new head's prev reference to former head

            self.length = self.length - 1
            return first_data

        return None

    def remove_tail(self):
        """Removes the last node of the list and returns its data."""
        # if list is not empty
        if self.head is not None:
            current = self.head
            # advance current node to the last node
            while current.next is not None:
                current = current.next

            last_data = current.data

            # if there is only one element in the list
            if self.head == self.tail:
                self.head = self.tail = None
            else:
                self.tail = current.prev  # set last node's prev as new last node
                self.tail.next = None

            self.length = self.length - 1
            return last_data

        return None

    def remove(self, index):
        """Removes the node at the specified index and returns its data. Raises an exception if
        the index is out of bounds."""
        # if index exists (also implies that the list is not empty)
        if 0 <= index <= self.length - 1:
            # if removing the head element
            if index == 0:
                removed_data = self.head.data
                # if there is only one element in the list
                if self.head == self.tail:
                    self.head = self.tail = None
                else:  # there are more than one elements in the list
                    self.head = self.head.next
                    self.head.prev = None
            else:  # if removing middle or last element
                current = self.head
                curr_idx = 0
                # advance current node to the node at index
                while curr_idx < index:
                    current = current.next
                    curr_idx = curr_idx + 1

                node_before = current.prev  # get the node before the node to remove

                # if node at index is not the tail node
                if current != self.tail:
                    node_after = current.next  # get the node after the node to remove
                    # remove node at index
                    node_before.next = node_after
                    node_after.prev = node_before
                else:  # node at index is the tail node
                    node_before.next = None  # discard the reference to the last node in node before
                    self.tail = node_before  # set new tail

            self.length = self.length - 1
            return current.data

        raise Exception('Index out of bounds')

    def size(self):
        """Returns the length of the list."""
        return self.length

    def is_empty(self):
        """Returns a boolean indicating whether the list is empty or not."""
        return self.head is None

    def clear(self):
        """Removes every element in the list."""
        self.head = self.tail = None
        self.length = 0


class DLLNode:
    def __init__(self, data, prev=None, _next=None):
        self.data = data
        self.prev = prev
        self.next = _next
