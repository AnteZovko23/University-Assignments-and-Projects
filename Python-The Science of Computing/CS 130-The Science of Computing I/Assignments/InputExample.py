###########################################################################################
# Name: Ante Zovko 
# Date: October 2nd, 2019
# Description: Prompt the user for a name and age and display their name and twice their age
###########################################################################################


# prompt the user for a name and an age

userName = raw_input("Please enter your name: ")
userAge = raw_input("How old are you {}? ".format(userName))


# display the final output
# If the user doesn't enter, displays a warning
try:
    print("Hello {}. You are {}. Twice your age is {}").format(userName, int(userAge),int(userAge)*2)
except:
    print("Please enter a number for your age!")
