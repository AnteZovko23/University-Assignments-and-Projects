###########################################################################################
# Name: Ante Zovko
# Date: October 28, 2019
# Description: Generates a list of integers such that both its length and the integers are
#              provided by the user. Subsequently displays the list and statistical
#              information about it
###########################################################################################

# function that:
# (1) prompts the user for a list size
# (2) prompts the user for the integers to store in the list (corresponding to the list size)
# (3) creates the list
# (4) returns the list
def fillList():
    while(True):   
        try:
            listSize = int(raw_input("How many integers would you like to add to the list? "))
        except ValueError:
            print("Please enter an integer.")
            continue

        if(listSize < 1):
            print("Please enter an integer bigger than 0.")
            continue
        else:
            break


    integerList = []


    while(len(integerList) < listSize):
        try:
            userInt = int(raw_input("Enter an integer: "))
            integerList.append(userInt)
        except ValueError:
            print("Please enter only integers.")
            
    return integerList


###############################################
# MAIN PART OF THE PROGRAM
###############################################
# create the list
nums = fillList()

print("\nThe original list: {}.".format(nums))

print("The length of the list is {}.".format(len(nums)))

print("The minimum value in the list is {}.".format(min(nums)))

print("The maximum value in the list is {}.".format(max(nums)))

nums.reverse()
print("The reversed list: {}".format(nums))

nums.sort()
print("The sorted list: {}".format(nums))


