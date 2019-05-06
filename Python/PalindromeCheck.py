userString = input("Enter a string\n")
checkString = userString[::-1]


if userString == checkString:
    print(userString + " is a Palindrome")
else:
    print(userString + " is not a Palindrome")


