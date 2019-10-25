from tabulate import tabulate
import math

##########################################################################################
# Name: Ante Zovko
# Date: October 25, 2019
# Description: #TODO
##########################################################################################

# a function that displays the table
#TODO

# a function that calculates the average number of comparisons of a sequential search on a list of size n
# -input: the list size
# -output: the average number of comparisons
def linearComparisions(size):
    return int((size // 2) + 1)


# a function that calculates the maximum number of comparisons of a binary search on a list of size n
# -input: the list size
# -output: the average number of comparisons
def binaryComparisons(size):
    return round(math.log(size, 2))


###############################################
# MAIN PART OF THE PROGRAM
###############################################

# get user input for the minimum (make sure that it is >= 0)
while True:
    try:
        minNumber = int(raw_input("Minimum number of list items (>=0)? "))
    except ValueError:
        print("Please enter a number!")
        continue

    if(minNumber < 0):
        print("*ERROR: Minimum must be >= 0!")
        continue
    else:
        break
       

# get user input for the maximum (make sure that is is >= minimum)
while True:
    try:
        maxNumber = int(raw_input("Maximum number of list items (>= min({}))? ".format(minNumber)))
    except ValueError:
        print("Please enter a number!")
        continue

    if(maxNumber < minNumber):
        print("*ERROR: Maximum must be >= minimum({})!".format(minNumber))
        continue
    else:
        break


# get user input for the interval (make sure that it is >= 1)
while True:
    try:
        interval = int(raw_input("The interval between each row of the table (>= 1)? "))
    except ValueError:
        print("Please enter a number!")
        continue

    if(interval < 1):
        print("*ERROR: Interval must be >= 1!")
        continue
    else:
        break

# generate the table
#TODO
print(tabulate([["n","Seq", "Bin", "Perf"],["Alice",24,2,2],["Bob",19]], headers="firstrow"))