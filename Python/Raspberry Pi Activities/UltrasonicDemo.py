###################################################
# Name: Ante Zovko
# November 10th, 2019
# Description: Measures distances using an ultrasonic
# range module and sorts and displays the distances 
# using bubblesort


###################################################

#### Setup

import RPi.GPIO as GPIO
from time import sleep, time

# Constants
DEBUG = False  # Debug mode
TRIGGER_TIME = 0.00001 #seconds needed t otrigger the sensor
SPEED_OF_SOUND = 343 #speed of sound m/s
SETTLE_TIME = 2 # seconds to let the sensor settle

CALIBRATIONS = 5 # number of calibration measurements
CALIBRATIONS_DELAY = 1 # seconds of delay between cal. measurements
# Set pins to broadcom
GPIO.setmode(GPIO.BCM)

#GPIO pins
TRIG = 18 # the sensor's TRIG pin
ECHO = 27 # the sensor's ECHO pin


GPIO.setup(TRIG, GPIO.OUT) # TRIG is an output
GPIO.setup(ECHO, GPIO.IN) # ECHO is an input

#Calibrates the sensor using the correction factor
def calibrate():
    print("Calibrating...")

    # prompts the user for a known distance
    print("Place sensor a measured distance away from an object")
    known_distance = input("What is the measeured distance(cm)? ")

    # measure the distance several times to get an average
    print("Getting calibration measurements...")
    distance_avg = 0
    for i in range(CALIBRATIONS):
        distance = getDistance()
        if(DEBUG):
            print("Got {}cm".format(distance))
        # get the sum
        distance_avg += distance
        # delay before using again
        sleep(CALIBRATIONS_DELAY)
    # calculate the avg distance
    distance_avg /= CALIBRATIONS
    if(DEBUG):
        print("Average is {}cm".format(distance_avg))
    
    # Calculate the correction factor
    correction_factor = known_distance / distance_avg

    if(DEBUG):
        print("Correction factor is {}".format(correction_factor))

    print("Done")

    return correction_factor
# Calculate distance using the sensor
def getDistance():
    # Trigger the sensor by setting it to high and low
    GPIO.output(TRIG, GPIO.HIGH)
    sleep(TRIGGER_TIME)
    GPIO.output(TRIG, GPIO.LOW)

    # Wait for the ECHO pin to read high
    # once it is high start time is set
    # once it is low, end time is set
    while(GPIO.input(ECHO) == GPIO.LOW):
        start = time()
    while(GPIO.input(ECHO) == GPIO.HIGH):
        end = time()

    # Calculate the duration it was high
    # I.E. how long the pulse took to get from the sensor to the object
    # and back
    duration = end - start
    # calculate the distance that the pulse traveled
    distance = duration * SPEED_OF_SOUND
    # the distance of the obj is half of the distance covered
    distance /= 2
    # from meters to cm
    distance *= 100

    return distance
# Sorting algorithm
def bubbleSort(measurementList):
    n = len(measurementList)

    for i in range(n):
        for j in range(0, n-i-1):
            if(measurementList[j]>measurementList[j+1]):
                measurementList[j], measurementList[j+1] = measurementList[j+1],measurementList[j]

    return measurementList

######## Main ######
# Allow the sensor to settle
print("Waiting for sensor to settle ({}s)...".format(SETTLE_TIME))
GPIO.output(TRIG, GPIO.LOW)
sleep(SETTLE_TIME)

# calibrate the sensor
correction_factor = calibrate()

# measure
raw_input("\nPress enter to begin...")
print("Getting measurements")
measurementList = []


# Get the distance and correct it with the correction factor
while(True):
    print("Measuring...")
    distance = getDistance() * correction_factor
    sleep(1)
    # round
    distance = round(distance, 4)
    measurementList.append(distance)
    # display the distance
    print("Distance measured: {}".format(distance))
    # prompt for another measurement
    i = raw_input("Get another measurement (Y/n)? ")
    # stop measuring if desired
    if(not i in ["y", "Y", "Yes", "yes", "YES", ""]):
        break
# Cleanup the GPIO pins
print("Done") 
GPIO.cleanup()
print("\nUnsorted measurements:")
print(measurementList)
print("Sorted measurements")
print(bubbleSort(measurementList))