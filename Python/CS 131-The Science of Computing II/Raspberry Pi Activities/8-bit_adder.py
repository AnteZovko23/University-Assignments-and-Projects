######################################################################
# Name: Ante Zovko
# Date: 1/27/2020
# Description: Binary addition of two random binary numbers using 
#			   a full adder(two half adders) and displaying the result
#			   using LEDs on a breadboard
######################################################################
import RPi.GPIO as GPIO		# bring in GPIO functionality
from random import randint	# to generate random integers

# function that defines the GPIO pins for the nine output LEDs
def setGPIO():
	# define the pins (change these if they are different)
	gpio = [17, 18, 27, 22, 26, 12, 16, 20, 21]
	# set them up as output pins
	GPIO.setup(gpio, GPIO.OUT)

	return gpio

# function that randomly generates an 8-bit binary number
def setNum():
	# create an empty list to represent the bits
	num = []
	# generate eight random bits
	for i in range(0, 8):
		# append a random bit (0 or 1) to the end of the list
		num.append(randint(0, 1))

	return num

# function that displays the sum (by turning on the appropriate LEDs)
def display():
	for i in range(len(sum)):
		# if the i-th bit is 1, then turn the i-th LED on
		if (sum[i] == 1):
			GPIO.output(gpio[i], GPIO.HIGH)
		# otherwise, turn it off
		else:
			GPIO.output(gpio[i], GPIO.LOW)


# function that implements a full adder using two half adders
# inputs are Cin, A, and B; outputs are S and Cout
def fullAdder(Cin, A, B):
	
	S0, C0 = halfAdder(A, B)
	S, C1 = halfAdder(S0, Cin)  


	Cout = C0 | C1

	return S, Cout	

# Half Adder
# Takes two inputs and returns sum and carry
def halfAdder(A, B):
	S = A ^ B
	C = A & B

	return S, C


# controls the addition of each 8-bit number to produce a sum
def calculate(num1, num2):
	Cout = 0		# the initial Cout is 0
	sum = []		# initialize the sum
	n = len(num1) - 1	# the position of the right-most bit of num1

	# step through each bit, from right-to-left
	while (n >= 0):
		# isolate A and B (the current bits of num1 and num2)
		A = num1[n]
		B = num2[n]
		# set the Cin (as the previous half adder's Cout)
		Cin = Cout

		# call the fullAdder function that takes Cin, A, and B...
		# ...and returns S and Cout
		S, Cout = fullAdder(Cin, A, B)

		# insert the sum bit, S, at the beginning (index 0) of sum
		sum.insert(0, S)

		# go to the next bit position (to the left)
		n -= 1

	# insert the final carry out at the beginning of the sum
	sum.insert(0, Cout)

	return sum

# use the Broadcom pin scheme
GPIO.setmode(GPIO.BCM)

# setup the GPIO pins
gpio = setGPIO()

# get a random num1 and display it to the console
num1 = setNum()
print "     ", num1

# get a random num2 and display it to the console
num2 = setNum()
print "+    ", num2

# calculate the sum of num1 + num2 and display it to the console
sum = calculate(num1, num2)
print "= ", sum

# turn on the appropriate LEDs to "display" the sum
display()

# wait for user input before cleaning up and resetting the GPIO pins
raw_input("Press ENTER to terminate")
GPIO.cleanup()

