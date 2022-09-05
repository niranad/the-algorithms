## Linked List Algorithms
This Java package consists of algorithm solutions to common **LinkedList** problems. All solutions contain 
a manual test using the ```public static void main``` method. Some solution classes make use of classes in 
the [datastructures](../datastructures) package. 

A **LinkedList** is a linear abstract data type in which each node has a reference to the next node, and 
the end of the list is terminated by the **null** object. A **LinkedList** can be singly or 
doubly linked. In the case of the latter, each node has an extra reference for the previous node. A 
doubly-linked list is more time-efficient for find, insert and delete operations when the index position is 
known since the list can be traversed from the end closest to the position. A singly-linked list, on 
the other hand, is more space efficient simply because it stores only the next reference at each node.
However, the list must be traversed from the beginning. This leads to a poor performance when searching
for the index positions closer to the end.