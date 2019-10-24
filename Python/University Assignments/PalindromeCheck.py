"""
Checks if a word is a palindrome

Author: Ante Zovko
Version: 5/6/2019
"""


userString = input("Enter a string\n")
checkString = userString[::-1]


if userString == checkString:
    print(userString + " is a Palindrome")
else:
    print(userString + " is not a Palindrome")


