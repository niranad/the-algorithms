"""LinkedList Data Structure"""


class LinkedList:
    def __init__(self):
        self.head = None
        self.length = 0

    def insert_first(self, data):
        """Inserts a node with the given data at the beginning of the list."""
        new_node = LLNode(data)

        if self.head is None:
            self.head = new_node
        else:
            new_node.next = self.head
            self.head = new_node

        self.length = self.length + 1

    def insert_last(self, data):
        """Inserts a node with the given data at the end of the list."""
        new_node = LLNode(data)

        if self.head is None:
            self.head = new_node
            self.length = self.length + 1
            return

        current = self.head
        # get reference to the last node
        while current.next is not None:
            current = current.next

        current.next = new_node

        self.length = self.length + 1

    def insert(self, data, index):
        """Inserts a node with the given data at the specified index position in the list.
        If the given index is out of bounds, it returns false indicating the operation was
        not successful. Otherwise returns true."""

        # if index is within bounds (i.e., index exists)
        if 0 <= index <= self.length - 1:
            new_node = LLNode(data)

            if index == 0:
                new_node.next = self.head
                self.head = new_node
                self.length = self.length + 1
                return True

            current = self.head
            curr_idx = 0  # current node's index

            # locate the node before the node at the index
            while current.next is not None:
                # if the current node is the node before the node at the index
                if curr_idx == index - 1:
                    break
                current = current.next
                curr_idx = curr_idx + 1

            node_at_index = current.next
            new_node.next = node_at_index
            current.next = new_node
            self.length = self.length + 1
            return True

        return False

    def remove_first(self):
        """Removes the first node from the list and returns its value. Returns None if list is empty."""
        if self.head is None:
            return None

        first = self.head
        first_data = first.data
        self.head = first.next
        self.length = self.length - 1

        return first_data

    def remove_last(self):
        """Removes the last node from the list and returns its value. If the list is empty, returns None."""
        if self.head is None:
            return None

        if self.head.next is None:
            last_data = self.head.data
            self.head = self.head.next
            return last_data

        current = self.head

        # get reference to the node before last
        while current.next is not None and current.next.next is not None:
            current = current.next

        last = current.next
        last_data = last.data
        current.next = None
        self.length = self.length - 1

        return last_data

    def remove(self, index):
        """Removes a node at the specified index. Returns None if the index is out of bounds,
        otherwise returns the value at the removed node."""

        # if index is within bounds (i.e, it exists)
        if 0 <= index <= self.length - 1:
            if index == 0:
                the_node = self.head  # node to be removed
                self.head = the_node.next
                the_node.next = None  # detach the node from the rest of the list
                self.length = self.length - 1
                return the_node.data

            current = self.head
            curr_idx = 0  # index of the current node

            # scan the list to the node before the node to be removed
            while curr_idx < index - 1:
                current = current.next
                curr_idx = curr_idx + 1

            the_node = current.next  # node to be removed
            node_after = the_node.next  # node after the node to be removed
            current.next = node_after
            the_node.next = None
            self.length = self.length - 1
            return the_node.data

        return None

    def remove_by_data(self, data):
        """Removes the first node with the given value. Returns the value
        if found, none otherwise."""

        if self.head is None:
            return None

        if self.head.data == data:
            the_node = self.head  # the node to be removed
            self.head = the_node.next
            the_node.next = None  # detach the node from the rest of the list
            self.length = self.length - 1
            return data

        current = self.head

        while current.next is not None and current.next.data != data:
            current = current.next

        # if the node is found
        if current.next is not None:
            the_node = current.next
            node_after = the_node.next  # node after the node to be removed
            current.next = node_after
            the_node.next = None
            self.length = self.length - 1
            return data

        return None

    def get(self, index):
        """Retrieves the value of the node at the specified index. Returns None if index does not exist."""

        # if index exists (also implies that the list is not empty)
        if 0 <= index <= self.length - 1:
            current = self.head
            curr_idx = 0

            while current is not None:
                if curr_idx == index:
                    return current.data
                current = current.next
                curr_idx = curr_idx + 1

        return None

    def size(self):
        """Returns the length of the list."""
        return self.length

    def is_empty(self):
        return self.length == 0

    def get_position(self, data):
        """Returns the position of the first node with the given value, -1 if not found."""
        current = self.head
        curr_idx = 0

        while current is not None:
            if current.data == data:
                return curr_idx
            current = current.next
            curr_idx = curr_idx + 1

        return -1

    def clear(self):
        """Empties the list."""
        self.head = None
        self.length = 0


class LLNode:
    def __init__(self, data):
        self.data = data
        self.next = None
