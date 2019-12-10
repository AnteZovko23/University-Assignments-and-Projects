###########################################################################################
# Name: Ante Zovko 
# Date: December 6th, 2019
# Description: Counts the number of 0s in each integer between 1 and user-determined number
#              Provides the amount of time it took for the algorithm to execute
# Version: Python 2.7
###########################################################################################

from time import time

elapsedTime = 0

# Iterates through each number between 1 and user input
# While the given number is not 0 it checks if it is divisible by 0
# and then does integer division by 10 to remove the last number
def zerosCounter(userInput):
    global elapsedTime
    zeros = 0
    counter = 0
    startTime = time()
    # Number iterator
    for nums in range(1, userInput + 1):
        # Increments the counter if the number is divisible by 0
        while(nums != 0):
                if(nums % 10 == 0):
                    counter += 1
                nums //= 10

    endTime = time()
    elapsedTime = endTime - startTime
    return counter

try:
    userInput = int(raw_input("What number do you want to count zeros to? "))
    if(userInput < 1):
        print("Plase enter a number bigger than 0")
        raise SystemExit(0)
except ValueError:
    print("Please enter a number")
    raise SystemExit(0)

print("The number of zeros written from 1 to {} is {}.").format(userInput, zerosCounter(userInput))
print("This took {} seconds.").format(elapsedTime)

