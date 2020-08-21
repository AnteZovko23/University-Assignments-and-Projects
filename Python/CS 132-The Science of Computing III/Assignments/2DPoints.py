######################################################################################################################
# Name: Ante Zovko
# Date: 3/17/2020
# Description: Implements 2D points with midpoint and distance functions
######################################################################################################################
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


##########################################################
# Main

# create some points
p1 = Point()
p2 = Point(3, 0)
p3 = Point(3, 4)
# display them
print "p1:", p1
print "p2:", p2
print "p3:", p3
# calculate and display some distances
print "distance from p1 to p2:", p1.dist(p2)
print "distance from p2 to p3:", p2.dist(p3)
print "distance from p1 to p3:", p1.dist(p3)
# calculate and display some midpoints
print "midpt of p1 and p2:", p1.midpt(p2)
print "midpt of p2 and p3:", p2.midpt(p3)
print "midpt of p1 and p3:", p1.midpt(p3)
# just a few more things...
p4 = p1.midpt(p3)
print "p4:", p4
print "distance from p4 to p1:", p4.dist(p1)