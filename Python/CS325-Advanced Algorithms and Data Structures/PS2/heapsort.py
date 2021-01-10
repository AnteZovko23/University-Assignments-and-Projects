# Heapsort implementation
# Author: Ante Zovko
# Version: 1/10/2021



def maxHeapify(givenArray, length, rootPosition):

  largest = rootPosition

  leftNode = 2*rootPosition + 1
  rightNode = 2*rootPosition + 2

  if leftNode < length and givenArray[leftNode] > givenArray[largest]:
    largest = leftNode


  if rightNode < length and givenArray[rightNode] > givenArray[largest]:
    largest = rightNode


  if largest != rootPosition:
    givenArray[rootPosition], givenArray[largest] = givenArray[largest], givenArray[rootPosition]

    maxHeapify(givenArray, length, largest)

def heapSort(givenArray):

  # Create Max Heap
  # Start at last node that is not a leaf
    start = len(givenArray) // 2 - 1


    # Heapify each node
    for i in range(start, -1, -1):
      maxHeapify(givenArray, len(givenArray), i)

    #One by one remove max
    for i in range(len(givenArray) - 1, 0, -1):
      givenArray[i], givenArray[0] = givenArray[0], givenArray[i]
      maxHeapify(givenArray, i, 0)


    
data = [2, 5, 16, 4, 10,23, 39, 18,26 ,15]


heapSort(data)

for i in data:
    print(i, end=" ")