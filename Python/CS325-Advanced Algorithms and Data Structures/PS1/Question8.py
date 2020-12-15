"""
Author: Ante Zovko
Version: 12/15/2020
Description: Singly Linked List Data Structure Implementation
"""

class SinglyLinkedList:
    class _Node:
        __slots__ = '_element', '_next'

        def __init__(self, element, next):
            self._element = element
            self._next = next

    def __init__(self):
        self._head = None
        self._tail = None
        self._size = 0

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0
		
    def add_first(self, e):
        self._head = self._Node(e, self._head)
        if self.is_empty():
            self._tail = self._head
        self._size += 1

    def add_last(self, e):
        newnode = self._Node(e, None)
        if self.is_empty():
            self._head = newnode
        else:
            self._tail._next = newnode
        self._tail = newnode
        self._size += 1

    def remove_first(self):
        
        answer = self._head._element
        self._head = self._head._next
        self._size -= 1
        if self.is_empty():
            self._tail = None
        return answer

    def remove_last(self):
       
        if self._size == 1:
            answer = self._head._element
            self._head = None
            self._tail = None
        else:
            answer = self._tail._element
            current_node = self._head
            next_node = current_node._next
            while next_node != self._tail:
                current_node = next_node
                next_node = next_node._next
            self._tail = current_node
        self._size -= 1
        return answer

    # Reverses a given LinkedList in O(1) space complexity
    def reverse(self):


            prev, current = None, self._head

            # Capture current head for setting tail at the end
            temp = self._head
            
            while(current is not None):

                nextNode, current._next, prev = current._next, prev, current
                
                # Move to the next
                current = nextNode


            # Set head and tail correctly
            self._head = prev
            
            if(self._size > 1):
                self._tail = temp
            else:
                self._tail = None


    # Prints the list
    def printList(self):

            temp = self._head

            while(temp):
                print(temp._element)
                temp = temp._next
 
 

S = SinglyLinkedList()

for i in range(10):
    S.add_last(i)



S.printList()
S.reverse()
print()
S.printList()
print()

print(S._head._element)
print(S._tail._element)
