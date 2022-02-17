###########################################################################################
# Name: Ante Zovko 
# Date: October 18th, 2019
# Description: Makes an LED light blink at a predetermined speed and if a switch
#              is closed makes it blink faster. Returns to normal 
#              speed after releasing the switch  
###########################################################################################

##### Setup

import RPi.GPIO as GPIO
from time import sleep

GPIO.setmode(GPIO.BCM)

## Output pin
GPIO.setup(17, GPIO.OUT)

## Input pin
GPIO.setup(25, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)

##### Setup

## Controls the blinking speed of the LED by using the
## sleep function between being on and off
## Duration of sleep controlled by speed parameter
def blinkingLEDSpeed(speed):
    GPIO.output(17, GPIO.HIGH)  
    sleep(speed)
    GPIO.output(17, GPIO.LOW)
    sleep(speed)

## Infinite loop that checks if the switch is open or closed
## and based on that passes the sleep duration parameter to the
## blinkingLEDSpeed function
while(True):
    ## If switch is closed speeds the LED blinking up
    if(GPIO.input(25) == GPIO.HIGH):
        blinkingLEDSpeed(0.1)
    ## If switch is open slows the LED blinking down
    else:
        blinkingLEDSpeed(0.5)

