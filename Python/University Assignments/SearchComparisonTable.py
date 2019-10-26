import math

##########################################################################################
# Name: Ante Zovko
# Date: October 25, 2019
# Description: Given varous list sizes, compares the average number of comparisons
#              between linear and binary sort and displays it in a table
##########################################################################################

# Calculates data for the table
def arrangeData(min, max, interval):
    n = min
    values = []
   

    while(n <= max):

       
        seqValue = linearComparisions(n)
        binValue = binaryComparisons(n)
        
        if(n == 0):
            performance = 0
        else:    
            performance = int(round(((float(seqValue) / binValue))))

        values.append([n, seqValue, binValue, performance])

        n += interval
    
    return values



# a function that displays the table
def displayTable(values):

    dash = "-" * 26
    headers = ["n", "Seq", "Bin", "Perf"]

    print("{:<7}{:<7}{:<7}{:<7}".format(headers[0], headers[1], headers[2], headers[3]))
    print(dash)

    for i in range(len(values)):
        print("{:<7}{:<7}{:<7}{:<7}".format(values[i][0], values[i][1], values[i][2], values[i][3]))

# a function that calculates the average number of comparisons of a sequential search on a list of size n
# -input: the list size
# -output: the average number of comparisons
def linearComparisions(size):
    return int((size // 2))


# a function that calculates the maximum number of comparisons of a binary search on a list of size n
# -input: the list size
# -output: the average number of comparisons
def binaryComparisons(size):
    if(size == 0):
        return 0
    else:
        return int(math.ceil(math.log(size, 2)))


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
displayTable(arrangeData(minNumber, maxNumber, interval))




