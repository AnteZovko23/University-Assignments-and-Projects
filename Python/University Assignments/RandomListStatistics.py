###########################################################################################
# Name: Ante Zovko 
# Date: November 6th, 2019
# Description: 
###########################################################################################

import random

# function that prompts the user for a list size, minimum and maximum values, creates the list, and returns it
# you must use the list functions discussed in class to add integers to the list
def fillList():

    listOfInts = []

    numOfInts = int(raw_input("How many random integers would you like to add to the list? "))
    minNum = int(raw_input("What would you like the minimum value to be? "))
    maxNum = int(raw_input("What would you like the maximum value to be? "))

    for num in range(0, numOfInts):
        listOfInts.append(random.randint(minNum, maxNum))
    
    return listOfInts

# function that receives the list as a parameter, and calculates and returns the mean
def meanOfList(nums):
    sum = 0

    for val in nums:
        sum += val

    avg = float(sum) / len(nums)
    
    return avg

# function that receives the list as a parameter, and calculates and returns the median
def medianOfList(nums):
    nums.sort()
    print(nums)
    if(len(nums) % 2 != 0):
        medianIndex = (len(nums) + 1) / 2
        median = nums[medianIndex - 1]
    else:
        medianIndex = (len(nums) + 1) // 2
        median = float(nums[medianIndex - 1] + nums[medianIndex ]) / 2

    return median


# function that receives the list as a parameter, and calculates and returns the range
def rangeOfList(nums):
    
    rangeVar = max(nums) - min(nums)

    return rangeVar


###############################################
# MAIN PART OF THE PROGRAM
# implement the main part of your program below
# comments have been added to assist you
###############################################
# create the list
nums = fillList()

# display the list
# there is no need to write/call your own function for this part
print("The list: {}".format(nums))

# calculate and display the mean
print("The mean of the list is {}".format(meanOfList(nums)))

# calculate and display the median
print("The median of the list is {}".format(medianOfList(nums)))

# calculate and display the range
print("The range of the list is {}".format(rangeOfList(nums)))

