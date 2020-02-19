######################################################################################################################
# Name: Ante Zovko
# Date: 2/19/2020
# Description: Implements four sorts (bubble sort, optimized bubble sort, insertion sort and selection sort)
#              Counts the number of comparisons and swaps in each sort and displays them
#              Displays comparisons and swaps using a bar graph
#
# NOTE: Implementation based on: https://www.geeksforgeeks.org/sorting-algorithms/
######################################################################################################################

from plot import plot

# creates the list
def getList():
	 return [100, 5, 63, 29, 69, 74, 96, 80, 82, 12]
	# return [82, 65, 93, 0, 60, 31, 99, 90, 31, 70]
    # return [63, 16, 78, 69, 36, 36, 3, 66, 75, 100]
	# return [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
#	return [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
#	return [2, 1, 4, 3, 6, 5, 8, 7, 10, 9]

# the bubble sort function
# input: a list of integers
# output: a number of comparisons and swaps
def bubbleSort(arr):
    n = len(arr)
    comparisons = 0
    swaps = 0
 
    # Traverse through all array elements
    for i in range(n):
 
        # Last i elements are already in place
        for j in range(0, n-i-1):
            comparisons += 1
            # traverse the array from 0 to n-i-1
            # Swap if the element found is greater
            # than the next element
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]
                swaps += 1

    return arr, comparisons, swaps

# the optimized bubble sort function
# input: a list of integers
# output: a number of comparisons and swaps
def optimizedBubbleSort(arr):
    repeat = True
    n = len(arr)
    comparisons = 0
    swaps = 0
    j = 0

    while(repeat == True and n > 1):
        repeat = False
        for i in range(len(arr)-j-1):
            comparisons += 1
            if(arr[i] > arr[i+1]):
                arr[i], arr[i+1] = arr[i+1], arr[i]
                swaps += 1
                # Keeps the while loop alive if a swap is made
                repeat = True
        n -= 1
        j += 1 

    return arr, comparisons, swaps  


# the selection sort function
# input: a list of integers
# output: a number of comparisons and swaps
def selectionSort(arr):
    comparisons = 0
    swaps = 0
    # Traverse through all array elements 
    for i in range(len(arr)- 1): 
        
        # Find the minimum element in remaining  
        # unsorted array 
        minIndex = i 
        for j in range(i+1, len(arr)):
            comparisons += 1 
            if(arr[minIndex] > arr[j]): 
                minIndex = j 
                
        # Swap the found minimum element with  
        # the first element        
        arr[i], arr[minIndex] = arr[minIndex], arr[i]
        swaps += 1

    return arr, comparisons, swaps

# the insertion sort function
# input: a list of integers
# output: a number of comparisons and swaps
# Function to do insertion sort 
def insertionSort(arr): 
    comparisons = 0
    swaps = 0
    # Traverse through 1 to len(arr) 
    for i in range(1, len(arr)): 
  
        key = arr[i] 

        # Move elements of arr[0..i-1], that are 
        # greater than key, to one position ahead 
        # of their current position 
        j = i-1
        
        while(j >= 0 and key < arr[j]): 
                arr[j+1] = arr[j]
                swaps += 1
                j -= 1
                comparisons += 1
        arr[j+1] = key
    return arr, comparisons, swaps


# the main part of the program
sortedArray, comparisons, swaps = bubbleSort(getList())
print("The list: {}").format(getList())
print("After bubble sort: {}").format(sortedArray)
print("{} comparisons; {} swaps").format(comparisons, swaps)
print("")
bubbleList = [comparisons, swaps]

sortedArray, comparisons, swaps = optimizedBubbleSort(getList())
print("The list: {}").format(getList())
print("After optimized bubble sort: {}").format(sortedArray)
print("{} comparisons; {} swaps").format(comparisons, swaps)
print("")
optimizedBubbleList = [comparisons, swaps]

sortedArray, comparisons, swaps = selectionSort(getList())
print("The list: {}").format(getList())
print("After selection sort: {}").format(sortedArray)
print("{} comparisons; {} swaps").format(comparisons, swaps)
print("")
selectionList = [comparisons, swaps]

sortedArray, comparisons, swaps = insertionSort(getList())
print("The list: {}").format(getList())
print("After insertion sort: {}").format(sortedArray)
print("{} comparisons; {} swaps").format(comparisons, swaps)
print("")
insertionList = [comparisons, swaps]


# Plots the comparisons and swaps for each sort
plot(bubbleList, optimizedBubbleList, selectionList, insertionList)