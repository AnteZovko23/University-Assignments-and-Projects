#####################################################################################################
# Name: Ante Zovko
# Date: October 18th, 2019
# Description: Prompts the user to enter three integers
#              Calculates various statistics(minimum, maximum, mean, median, mode, range) about them
#####################################################################################################

# function that prompts the user to enter an integer and returns it
def userInput():
    userNum = raw_input("Enter an integer: ")
    return userNum

# function that receives three integers in a list as parameters and returns the minimum of the three
def minimumValue(inputList):


    smallestNumber = inputList[0]
    for i in range(0, len(inputList)):
        if(inputList[i] < smallestNumber):
            smallestNumber = inputList[i]

    return smallestNumber

# function that receives three integers in a list as parameters and returns the maximum of the three
def maximumValue(inputList):

    biggestNumber = inputList[0]
    for i in range(0, len(inputList)):
        if(inputList[i] > biggestNumber):
            biggestNumber = inputList[i]

    return biggestNumber

# function that receives three integers in a list as parameters, and calculates and returns the mean
def meanValue(inputList):

    sum = 0
    for i in inputList:
        sum += i

    average = float(sum) / float(len(inputList))
    
    return average

# function that receives three integers in a list as parameters, and calculates and returns the median
def medianValue(inputList):

    inputList.sort()

    return inputList[1]

# function that receives three integers in a list as parameters, and calculates and returns the mode
def modeValue(inputList):

    if(inputList[0] == inputList[1]):
        return inputList[0]
    elif(inputList[1] == inputList[2]):
        return inputList[1]
    elif(inputList[2] == inputList[0]):
        return inputList[2]
    else:
        return "undefined"
    




# function that receives three integers in a list as parameters, and calculates and returns the range
def rangeValue(inputList):

    biggestValue = maximumValue(inputList)
    smallestValue = minimumValue(inputList)

    return abs(biggestValue - smallestValue)



###############################################
# MAIN PART OF THE PROGRAM
###############################################
inputList = []

# get three integers from the user
try:
    for number in range(0, 3):
        inputList.append(int(userInput()))

except:
    print("Please enter an integer!")
    raise SystemExit(0)

# determine and display minimum value
print("The minimum value is {}.".format(minimumValue(inputList)))

# determine and display the maximum value
print("The maximum value is {}.".format(maximumValue(inputList)))

# calculate and display the mean
print("The mean is {}.".format(meanValue(inputList)))

# calculate and display the median
print("The median is {}.".format(medianValue(inputList)))

# calculate and display the mode
print("The mode is {}.".format(modeValue(inputList)))

# calculate and display the range
print("The range is {}.".format(rangeValue(inputList)))

