import math
import copy
from tkinter import *

"""
Name: Ante Zovko

CWID: 103-55-122

Date: December 15th, 2021

Assignment 1: 3D Transformations and Perspective Projection

The program renders a Pyramid in a 3D wire-frame viewing system and
allows for translation, scaling and rotation

Reference Material: Computer Graphics Lecture Notes by Dr. Mike O'Neal
"""




CanvasWidth = 400
CanvasHeight = 400
d = 500

# ***************************** Initialize Pyramid Object ***************************
# Definition  of the five underlying points
apex = [0,50,100]
base1 = [50,-50,50]
base2 = [50,-50,150]
base3 = [-50,-50,150]
base4 = [-50,-50,50]

# Definition of the five polygon faces using the meaningful point names
# Polys are defined in clockwise order when viewed from the outside
frontpoly = [apex,base1,base4]
rightpoly = [apex,base2,base1]
backpoly = [apex,base3,base2]
leftpoly = [apex,base4,base3]
bottompoly = [base1,base2,base3,base4]

# Definition of the object
Pyramid = [bottompoly, frontpoly, rightpoly, backpoly, leftpoly]

# Definition of the Pyramid's underlying point cloud.  No structure, just the points.
PyramidPointCloud = [apex, base1, base2, base3, base4]
DefaultPyramidPointCloud = copy.deepcopy(PyramidPointCloud)
#************************************************************************************

# This function resets the pyramid to its original size and location in 3D space
# Note that you have to be careful to update the values in the existing PyramidPointCloud
# structure rather than creating a new structure or just switching a pointer.  In other
# words, you'll need manually update the value of every x, y, and z of every point in
# point cloud (vertex list).
def resetPyramid():
    for i in range(len(PyramidPointCloud)):
        for j in range(3):
            PyramidPointCloud[i][j] = DefaultPyramidPointCloud[i][j]


# This function translates an object by some displacement.  The displacement is a 3D
# vector so the amount of displacement in each dimension can vary.
def translate(object, displacement):
    
    # Iterates through each polygon in the object
    for i in range(len(object)):
        
        # Using formulas on page 23
        
        # X coordinate
        x_coordinate_new = object[i][0] + displacement[0]
        y_coordinate_new = object[i][1] + displacement[1]
        z_coordinate_new = object[i][2] + displacement[2]
        
        # Overwrite in the list of coordinates
        object[i][0] = x_coordinate_new
        object[i][1] = y_coordinate_new
        object[i][2] = z_coordinate_new
    
    
# This function performs a simple uniform scale of an object assuming the object is
# centered at the origin.  The scalefactor is a scalar.
def scale(object,scalefactor):
    
    # Iterates through each polygon in the object
    for i in range(len(object)):
        
        # Using formulas on page 23

        # X coordinate
        x_coordinate_new = object[i][0] * scalefactor
        # Y coordinate
        y_coordinate_new = object[i][1] * scalefactor
        # Z coordinate
        z_coordinate_new = object[i][2] * scalefactor
        
        # Overwrite in the list of coordinates
        object[i][0] = x_coordinate_new
        object[i][1] = y_coordinate_new
        object[i][2] = z_coordinate_new

# This function performs a rotation of an object about the Z axis (from +X to +Y)
# by 'degrees', assuming the object is centered at the origin.  The rotation is CCW
# in a LHS when viewed from -Z [the location of the viewer in the standard postion]
def rotateZ(object,degrees):
    
    # Iterates through each polygon in the object
    for i in range (len(object)):
        
        # Using formulas on page 23
        
        # X coordinate
        x_coord = object[i][0] * math.cos(math.radians(degrees)) - object[i][1] * math.sin(math.radians(degrees))
        # Y coordinate
        y_coord = object[i][0] * math.sin(math.radians(degrees)) + object[i][1] * math.cos(math.radians(degrees))
        # Z coordinate
        object[i][2] = object[i][2]
        
        # Overwrite in the list of coordinates
        object[i][0] = x_coord
        object[i][1] = y_coord
        
# This function performs a rotation of an object about the Y axis (from +Z to +X)
# by 'degrees', assuming the object is centered at the origin.  The rotation is CW
# in a LHS when viewed from +Y looking toward the origin.
def rotateY(object,degrees):
    
    # Iterates through each polygon in the object
    for i in range(len(object)):
        
        # Using formulas on page 23
        
        # X coordinate
        x_coordinate = object[i][0] * math.cos(math.radians(degrees)) + object[i][2] * math.sin(math.radians(degrees))
        # Y coordinate
        object[i][1] = object[i][1]
        # Z coordinate
        z_coordinate = -object[i][0] * math.sin(math.radians(degrees)) + object[i][2] * math.cos(math.radians(degrees))
    

        # Overwrite in the list of coordinates
        object[i][0] = x_coordinate
        object[i][2] = z_coordinate
        
        

# This function performs a rotation of an object about the X axis (from +Y to +Z)
# by 'degrees', assuming the object is centered at the origin.  The rotation is CW
# in a LHS when viewed from +X looking toward the origin.
def rotateX(object,degrees):
    
    # Iterates through each polygon in the object
    for i in range(len(object)):
        
        # Using formulas on page 23
        
        # X coordinate
        object[i][0] = object[i][0]
        # Y coordinate
        y_coordinate = object[i][1] * math.cos(math.radians(degrees)) - object[i][2] * math.sin(math.radians(degrees))
        # Z coordinate
        z_coordinate = object[i][1] * math.sin(math.radians(degrees)) + object[i][2] * math.cos(math.radians(degrees))
    
        # Overwrite in the list of coordinates
        object[i][1] = y_coordinate
        object[i][2] = z_coordinate
    
# The function will draw an object by repeatedly callying drawPoly on each polygon in the object
def drawObject(object):
    
    # Iterates through each polygon in the object
    for poly in object:
        # Draws each polygon
        drawPoly(poly)
    

# This function will draw a polygon by repeatedly callying drawLine on each pair of points
# making up the object.  Remember to draw a line between the last point and the first.
def drawPoly(poly):
    
    # If the polygon is a triangle, draw a line between the appropriate points
    if(len(poly) == 3):
        drawLine(poly[0],poly[1])
        drawLine(poly[1],poly[2])
        drawLine(poly[2],poly[0])
    
    # If the polygon is a quadrilateral, draw a line between the appropriate points
    else:
        drawLine(poly[0],poly[1])
        drawLine(poly[1],poly[2])
        drawLine(poly[2],poly[3])
        drawLine(poly[3],poly[0])
    

# Project the 3D endpoints to 2D point using a perspective projection implemented in 'project'
# Convert the projected endpoints to display coordinates via a call to 'convertToDisplayCoordinates'
# draw the actual line using the built-in create_line method
def drawLine(start,end):

    # Project start and end points    
    project_start = project(start)
    project_end = project(end)
    
    # Convert them to display coordinates
    convertToDisplayCoordinates_start = convertToDisplayCoordinates(project_start)
    convertToDisplayCoordinates_end = convertToDisplayCoordinates(project_end)
    
    # Create a line between the projected points
    w.create_line(convertToDisplayCoordinates_start[0],convertToDisplayCoordinates_start[1],convertToDisplayCoordinates_end[0],convertToDisplayCoordinates_end[1])
    

# This function converts from 3D to 2D (+ depth) using the perspective projection technique.  Note that it
# will return a NEW list of points.  We will not want to keep around the projected points in our object as
# they are only used in rendering
def project(point):
    ps = []
    
    # For each coordinate in the given point, project them using the formulas on page 11
    x_ps = d * point[0] / (d + point[2])
    y_ps = d * point[1] / (d + point[2])
    z_ps = d * point[2] / (d + point[2])
    
    # Add the projected point to the list of projected points
    ps.append(x_ps)
    ps.append(y_ps)
    ps.append(z_ps)
    
    # Return projected coordinates
    return ps

# This function converts a 2D point to display coordinates in the tk system.  Note that it will return a
# NEW list of points.  We will not want to keep around the display coordinate points in our object as 
# they are only used in rendering.
def convertToDisplayCoordinates(point):
    displayXY = []
    
    # Converts drawing coordinates to display coordinates using formulas on page 13
    display_X = CanvasWidth/2 + point[0]
    display_Y = CanvasHeight/2 - point[1]
   
    # Adds converted coordinates to list
    displayXY.append(display_X)
    displayXY.append(display_Y)
   
    # Returns the list of converted coordinates
    return displayXY
        
    

# **************************************************************************
# Everything below this point implements the interface
def reset():
    w.delete(ALL)
    resetPyramid()
    drawObject(Pyramid)

def larger():
    w.delete(ALL)
    scale(PyramidPointCloud,1.1)
    drawObject(Pyramid)

def smaller():
    w.delete(ALL)
    scale(PyramidPointCloud,.9)
    drawObject(Pyramid)

def forward():
    w.delete(ALL)
    translate(PyramidPointCloud,[0,0,5])
    drawObject(Pyramid)

def backward():
    w.delete(ALL)
    translate(PyramidPointCloud,[0,0,-5])
    drawObject(Pyramid)

def left():
    w.delete(ALL)
    translate(PyramidPointCloud,[-5,0,0])
    drawObject(Pyramid)

def right():
    w.delete(ALL)
    translate(PyramidPointCloud,[5,0,0])
    drawObject(Pyramid)

def up():
    w.delete(ALL)
    translate(PyramidPointCloud,[0,5,0])
    drawObject(Pyramid)

def down():
    w.delete(ALL)
    translate(PyramidPointCloud,[0,-5,0])
    drawObject(Pyramid)

def xPlus():
    w.delete(ALL)
    rotateX(PyramidPointCloud,5)
    drawObject(Pyramid)

def xMinus():
    w.delete(ALL)
    rotateX(PyramidPointCloud,-5)
    drawObject(Pyramid)

def yPlus():
    w.delete(ALL)
    rotateY(PyramidPointCloud,5)
    drawObject(Pyramid)

def yMinus():
    w.delete(ALL)
    rotateY(PyramidPointCloud,-5)
    drawObject(Pyramid)

def zPlus():
    w.delete(ALL)
    rotateZ(PyramidPointCloud,5)
    drawObject(Pyramid)

def zMinus():
    w.delete(ALL)
    rotateZ(PyramidPointCloud,-5)
    drawObject(Pyramid)

root = Tk()
outerframe = Frame(root)
outerframe.pack()

w = Canvas(outerframe, width=CanvasWidth, height=CanvasHeight)
drawObject(Pyramid)
w.pack()

controlpanel = Frame(outerframe)
controlpanel.pack()

resetcontrols = Frame(controlpanel, height=100, borderwidth=2, relief=RIDGE)
resetcontrols.pack(side=LEFT)

resetcontrolslabel = Label(resetcontrols, text="Reset")
resetcontrolslabel.pack()

resetButton = Button(resetcontrols, text="Reset", fg="green", command=reset)
resetButton.pack(side=LEFT)

scalecontrols = Frame(controlpanel, borderwidth=2, relief=RIDGE)
scalecontrols.pack(side=LEFT)

scalecontrolslabel = Label(scalecontrols, text="Scale")
scalecontrolslabel.pack()

largerButton = Button(scalecontrols, text="Larger", command=larger)
largerButton.pack(side=LEFT)

smallerButton = Button(scalecontrols, text="Smaller", command=smaller)
smallerButton.pack(side=LEFT)

translatecontrols = Frame(controlpanel, borderwidth=2, relief=RIDGE)
translatecontrols.pack(side=LEFT)

translatecontrolslabel = Label(translatecontrols, text="Translation")
translatecontrolslabel.pack()

forwardButton = Button(translatecontrols, text="FW", command=forward)
forwardButton.pack(side=LEFT)

backwardButton = Button(translatecontrols, text="BK", command=backward)
backwardButton.pack(side=LEFT)

leftButton = Button(translatecontrols, text="LF", command=left)
leftButton.pack(side=LEFT)

rightButton = Button(translatecontrols, text="RT", command=right)
rightButton.pack(side=LEFT)

upButton = Button(translatecontrols, text="UP", command=up)
upButton.pack(side=LEFT)

downButton = Button(translatecontrols, text="DN", command=down)
downButton.pack(side=LEFT)

rotationcontrols = Frame(controlpanel, borderwidth=2, relief=RIDGE)
rotationcontrols.pack(side=LEFT)

rotationcontrolslabel = Label(rotationcontrols, text="Rotation")
rotationcontrolslabel.pack()

xPlusButton = Button(rotationcontrols, text="X+", command=xPlus)
xPlusButton.pack(side=LEFT)

xMinusButton = Button(rotationcontrols, text="X-", command=xMinus)
xMinusButton.pack(side=LEFT)

yPlusButton = Button(rotationcontrols, text="Y+", command=yPlus)
yPlusButton.pack(side=LEFT)

yMinusButton = Button(rotationcontrols, text="Y-", command=yMinus)
yMinusButton.pack(side=LEFT)

zPlusButton = Button(rotationcontrols, text="Z+", command=zPlus)
zPlusButton.pack(side=LEFT)

zMinusButton = Button(rotationcontrols, text="Z-", command=zMinus)
zMinusButton.pack(side=LEFT)

root.mainloop()