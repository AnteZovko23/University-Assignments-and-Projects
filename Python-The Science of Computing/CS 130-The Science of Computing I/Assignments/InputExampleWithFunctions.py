###########################################################################################
# Name: Ante Zovko
# Date: October 8th, 2019
# Description: Takes a users name and age, greets him and displays his age multiplied by 2
###########################################################################################

# function that prompts the user for a name and returns it
def getUserName():
    userName = raw_input("Please enter your name: ")
    return userName


# function that receives the user's name as a parameter, and prompts the user for an age and returns it
def getUserAge(userName):
    userAge = raw_input("How old are you? ")
    return userAge

# function that receives the user's name and age as parameters and displays the final output
def displayOutput(userName, userAge):
    print("Hi {}. You are {} years old. Twice your age is {}".format(userName, userAge, int(userAge)*2))


###############################################
# MAIN PART OF THE PROGRAM
###############################################
# get the user's name

userName = getUserName()

# get the user's age

userAge = getUserAge(userName)

# display the final output

try:
    displayOutput(userName, userAge)
except:
    print("Please enter a number for your age!")
