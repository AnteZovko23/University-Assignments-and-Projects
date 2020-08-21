######################################################################################################################
# Name: Ante Zovko
# Date: 3/24/2020
# Description: Creates a predetermined number of Point instances and plots them on a canvas
######################################################################################################################
from Tkinter import *
from random import randint, choice
import math
# the 2D point class
class Point(object):
    def __init__(self, pointX = 0.0, pointY = 0.0):
        self.pointX = float(pointX)
        self.pointY = float(pointY)


############### Functions ###############

    # Calculates the distance between two points
    # Params: A Point
    # Returns: The distance between them
    # Formula: https://www.purplemath.com/modules/distform.htm
    def dist(self, point):

        # Change in X and Y
        deltaX = point.pointX - self.pointX
        deltaY = point.pointY - self.pointY

        # The implemented distance formula
        distance = math.sqrt(math.pow(deltaX, 2) + math.pow(deltaY, 2))
        return distance
    
    # Calculates the midpoint between two points
    # Params: A Point
    # Returns: A point in the middle between them
    # Formula: https://www.purplemath.com/modules/midpoint.htm
    def midpt(self, point):

        # Arithmetic mean of the X and Y coordinate
        midX = (self.pointX + point.pointX)/2.0
        midY = (self.pointY + point.pointY)/2.0

        return Point(midX, midY)

    # Returns the X and Y coordinate of the point
    def __str__(self):
        return("({}, {})".format(self.pointX, self.pointY))

    
############ Getters/Setters ############
    @property
    def pointX(self):
        return self._pointX
    
    @pointX.setter
    def pointX(self, value):
        self._pointX = value

    @property
    def pointY(self):
        return self._pointY

    @pointY.setter
    def pointY(self, value):
        self._pointY = value

# the coordinate system class: (0,0) is in the top-left corner
# inherits from the Canvas class of Tkinter
class CoordinateSystem(Canvas):
    pointRadius = 0
    pointColors = ["black", "red", "green", "blue", "cyan", "yellow", "magenta"]

    def __init__(self, parent):
        Canvas.__init__(self, parent, bg="white")

        self.master = parent
        
        

    def plotPoints(self, numOfPoints):

        
        self.master.update_idletasks()
        for i in range(numOfPoints):
            
            p = Point(randint(0, self.master.winfo_width()), randint(0, self.master.winfo_height()))
            self.create_oval(p.pointX, p.pointY, p.pointX + self.pointRadius*2, p.pointY + self.pointRadius*2,outline=choice(self.pointColors))
    

        self.pack(fill="both", expand=1)



##########################################################
# the default size of the canvas is 800x800
WIDTH = 800
HEIGHT = 800
# the number of points to plot
NUM_POINTS = 5000

# create the window
window = Tk()
window.geometry("{}x{}".format(WIDTH, HEIGHT))
window.title("2D Points...Plotted")
# create the coordinate system as a Tkinter canvas inside the window
s = CoordinateSystem(window)
# plot some random points
s.plotPoints(NUM_POINTS)
# wait for the window to close
window.mainloop()