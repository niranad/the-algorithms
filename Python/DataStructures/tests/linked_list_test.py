import unittest
from ..linked_list import LinkedList


class LinkedListTest(unittest.TestCase):
    def test_insert_first(self):
        ll = LinkedList()
        ll.insert_first(7)
        self.assertEqual(ll.head.data, 7, 'insert_first should insert value 7 as the first value')

        ll.insert_first(5)
        self.assertEqual(ll.head.data, 5, 'insert_first should insert value 5 as the first value')

    def test_insert_last(self):
        ll = LinkedList()
        ll.insert_last(3)
        self.assertEqual(ll.head.data, 3, 'insert_last should insert value 3 as the last value')

        ll.insert_last(5)
        self.assertEqual(ll.head.next.data, 5, 'insert_last should insert value 5 as the last value')

        ll.insert_last(7)
        self.assertEqual(ll.head.next.next.data, 7, 'insert_last should insert value 7 as the last value')

    def test_insert(self):
        ll = LinkedList()
        ll.insert_first(2)
        # if index is out of bounds
        self.assertFalse(ll.insert(3, 1), 'insert should not insert at an index >= length of list')
        self.assertFalse(ll.insert(9, -1), 'insert should not insert at a negative index')

        # if index is within range
        ll.insert(3, 0)
        self.assertEqual(ll.head.data, 3, 'insert should successfully insert at index 0')
        ll.insert(5, 1)
        self.assertEqual(ll.head.next.data, 5, 'insert should successfully insert at index 1')
        ll.insert(7, 2)
        self.assertEqual(ll.head.next.next.data, 7, 'insert should successfully insert at index 2')

        self.assertEqual(ll.head.next.next.next.data, 2, 'insert should preserve existing values')

    def test_remove_first(self):
        ll = LinkedList()
        ll.insert_first(7)
        ll.insert_first(5)
        ll.insert_first(3)
        # 3 -> 5 -> 7

        self.assertEqual(ll.remove_first(), 3, 'remove_first should remove the first value 3')
        self.assertEqual(ll.remove_first(), 5, 'remove_first should remove the first value 5')
        self.assertEqual(ll.remove_first(), 7, 'remove_first should remove the first value 7')
        self.assertIsNone(ll.remove_first(), 'remove_first should return None for an empty list')

    def test_remove_last(self):
        ll = LinkedList()
        ll.insert_first(3)
        ll.insert_first(5)
        ll.insert_first(7)
        # 7 -> 5 -> 3

        self.assertEqual(ll.remove_last(), 3, 'remove_last should remove the last value 3')
        self.assertEqual(ll.remove_last(), 5, 'remove_last should remove the last value 5')
        self.assertEqual(ll.remove_last(), 7, 'remove_last should remove the last value 7')
        self.assertIsNone(ll.remove_last(), 'remove_last should return None for an empty list')

    def test_remove(self):
        ll = LinkedList()
        ll.insert_last(3)
        ll.insert_last(5)
        ll.insert_last(7)
        # 3 -> 5 -> 7

        self.assertEqual(ll.remove(1), 5, 'should remove the value 5 at index 1')
        self.assertEqual(ll.remove(1), 7, 'should remove the value 7 at index 1')
        self.assertEqual(ll.remove(0), 3, 'should remove the value 3 at index 0')
        self.assertIsNone(ll.remove(0), 'remove should return None for an empty list')

    def test_remove_by_data(self):
        ll = LinkedList()
        ll.insert_last(3)
        ll.insert_last(5)
        ll.insert_last(7)
        # 3 -> 5 -> 7

        self.assertEqual(ll.remove_by_data(5), 5, 'remove_by_data should remove the value 5')
        self.assertEqual(ll.remove_by_data(7), 7, 'remove_by_data should remove the value 7')
        self.assertEqual(ll.remove_by_data(3), 3, 'remove_by_data should remove the value 3')
        self.assertIsNone(ll.remove_by_data(3), 'remove_by_data should return None for an empty list')

    def test_get(self):
        ll = LinkedList()
        ll.insert_first(3)
        ll.insert_first(5)
        ll.insert_last(7)
        # 5 -> 3 -> 7

        self.assertEqual(ll.get(1), 3, 'get should return the value 3')
        self.assertEqual(ll.get(2), 7, 'get should return the value 7')
        self.assertEqual(ll.get(0), 5, 'get should return the value 5')
        self.assertIsNone(ll.get(-1), 'get should return None for a negative index')
        self.assertIsNone(ll.get(3), 'get should return None for an index >= length of the list')

    def test_get_position(self):
        ll = LinkedList()
        ll.insert_first(3)
        ll.insert_first(5)
        ll.insert_last(7)
        # 5 -> 3 -> 7

        self.assertEqual(ll.get_position(7), 2, 'get_position should return index value of 2')
        self.assertEqual(ll.get_position(3), 1, 'get_position should return index value of 1')
        self.assertEqual(ll.get_position(5), 0, 'get_position should return index value of 0')
        self.assertEqual(ll.get_position(-5), -1, 'get_position should return -1 for a non-existent value')

    def test_size(self):
        ll = LinkedList()
        self.assertEqual(ll.size(), 0, 'size should return 0 for an empty list')

        ll.insert_first(3)
        self.assertEqual(ll.size(), 1, 'size should return 1')

        ll.insert_first(5)
        self.assertEqual(ll.size(), 2, 'size should return 2')

        ll.insert_last(7)
        self.assertEqual(ll.size(), 3, 'size should return 3')

    def test_clear(self):
        ll = LinkedList()
        ll.insert_first(3)
        ll.insert_first(5)
        ll.insert_last(7)

        ll.clear()
        self.assertIsNone(ll.head, 'clear should set the head element to None')
        self.assertEqual(ll.length, 0, 'clear should set the length of the list to 0')


if __name__ == '__main__':
    unittest.main()
