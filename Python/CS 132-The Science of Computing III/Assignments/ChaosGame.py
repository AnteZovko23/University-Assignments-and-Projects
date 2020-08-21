######################################################################################################################
# Name: Ante Zovko
# Date: 4/5/2020
# Description: Creates a sierpinski triangle using the chaos game algorithm
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
class ChaosGame(Canvas):
    pointRadius = 0

    def __init__(self, parent):
        Canvas.__init__(self, parent, bg="white")

        self.master = parent
        
        
    # Plots the points
    def plotPoints(self, numOfPoints):
        
        ######## Vertices #########
        topVertex = Point(300, 4)
        self.create_oval(topVertex.pointX - 3, topVertex.pointY - 3, topVertex.pointX + 3, topVertex.pointY + 3,outline="Red", fill="Red")
        botVertexLeft = Point(4, 514)
        self.create_oval(botVertexLeft.pointX-3, botVertexLeft.pointY-3, botVertexLeft.pointX + 3, botVertexLeft.pointY + 3,outline="Red", fill="Red")
        botVertexRight = Point(595, 514)
        self.create_oval(botVertexRight.pointX-3, botVertexRight.pointY-3, botVertexRight.pointX + 3, botVertexRight.pointY + 3,outline="Red", fill="Red")
        ########################

        ###### First midpoint from two random Vertices ##########
        vertices = [topVertex,botVertexLeft,botVertexRight]
        midPt = botVertexLeft.midpt(botVertexRight)
        self.create_oval(midPt.pointX, midPt.pointY, midPt.pointX + self.pointRadius*2, midPt.pointY + self.pointRadius*2,outline="Black")
        ########################


        ## Selects a vertex randomly 
        ## Finds the midpoint between the original midpoint and the selected vertex
        ## Plots it
        ## Finds the midpoint between the new midpoint and a random vertex
        for i in range(numOfPoints):
            
            vertex = choice(vertices)
            midPt2 = midPt.midpt(vertex)
            self.create_oval(midPt2.pointX, midPt2.pointY, midPt2.pointX + self.pointRadius*2, midPt2.pointY + self.pointRadius*2,outline="Black")
            midPt = midPt2
    

        self.pack(fill="both", expand=1)

    

##########################################################
WIDTH = 600
HEIGHT = 520
# the number of points to plot
NUM_POINTS = 50000

# create the window
window = Tk()
window.geometry("{}x{}".format(WIDTH, HEIGHT))
window.title("Chaos Game")
# create the coordinate system as a Tkinter canvas inside the window
s = ChaosGame(window)
# plot some random points
s.plotPoints(NUM_POINTS)
# wait for the window to close
window.mainloop()