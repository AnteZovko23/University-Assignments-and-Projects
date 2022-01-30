from distutils.command.build_scripts import first_line_re
from itertools import count
import Matrix_Calculations
from tkinter import *
import copy

Viewpoint = [0, 0, -500]
canvas_width = 400
canvas_height = 400
d = 500

apex = [0,-100,100]
base1 = [50,-150,50]
base2 = [50,-150,150]
base3 = [-50,-150,150]
base4 = [-50,-76,50]

# Definition of the five polygon faces using the meaningful point names
# Polys are defined in clockwise order when viewed from the outside
frontpoly = [apex,base1,base4]
rightpoly = [apex,base2,base1]
backpoly = [apex,base3,base2]
leftpoly = [apex,base4,base3]
bottompoly = [base1,base2,base3,base4]

# Definition of the object
Pyramid = [bottompoly, frontpoly, rightpoly, backpoly, leftpoly]


# for polygon in Pyramid:
#     print(Matrix_Calculations.back_face_culling_algorithm(Viewpoint, polygon))


#**********************************************************************#

"""
This function converts from 3D to 2D (+ depth) using the perspective projection technique.  Note that it
will return a NEW list of points.  We will not want to keep around the projected points in our object as
they are only used in rendering
"""
def project(point):
    ps = []
    
    # For each coordinate in the given point, project them using the formulas on page 11
    x_ps = -Viewpoint[2] * point[0] / (-Viewpoint[2] + point[2])
    y_ps = -Viewpoint[2] * point[1] / (-Viewpoint[2] + point[2])
    z_ps = -Viewpoint[2] * point[2] / (-Viewpoint[2] + point[2])
    
    # Add the projected point to the list of projected points
    ps.append(x_ps)
    ps.append(y_ps)
    ps.append(z_ps)
    
    # Return projected coordinates
    return ps

"""
This function converts a 2D point to display coordinates in the tk system.  Note that it will return a
NEW list of points.  We will not want to keep around the display coordinate points in our object as 
they are only used in rendering.
"""
def convertToDisplayCoordinates(point):
    displayXY = []
    
    # Converts drawing coordinates to display coordinates using formulas on page 13
    display_X = canvas_width/2 + point[0]
    display_Y = canvas_height/2 - point[1]

    # Adds converted coordinates to list
    displayXY.append(display_X)
    displayXY.append(display_Y)

    # Returns the list of converted coordinates
    return displayXY
        
#**********************************************************************#
        
def project_and_convert_to_display_coordinates( polygon):
    
    display_polygon = []

    for point in polygon:
        projected_points = project(point)
        projected_points[0], projected_points[1] = convertToDisplayCoordinates(projected_points)
    
        # Make every coordinate in projected_points a rounded float
        display_polygon.append(list(map(lambda x: float(round(x, 0)), projected_points)))
    
    print(display_polygon)
    
        
    return display_polygon


def compute_edge_table( polygon):
    
    
    # # Sort points by x-coordinate and y-coordinate
    # polygon.sort(key=lambda x: (x[0], x[1]))
    print(polygon)
    
    # # If points have the same y-coordinate, remove one of them
    # for i in range(len(polygon) - 1):
    #     if polygon[i][1] == polygon[i+1][1]:
    #         polygon.pop(i+1)

    # Define all edges with the smaller y being the first edge and skip if the edge is a horizontal line
    edges = []
    for i in range(len(polygon) - 1):
            
        # The smaller y goes first
        if(polygon[i][1] < polygon[i+1][1]):
            edges.append([polygon[i],polygon[i+1]])
        elif(polygon[i][1] > polygon[i+1][1]):
            edges.append([polygon[i+1],polygon[i]])
        else:
            continue
    
    # Append last and first edge with the smaller y going first
    if(polygon[0][1] < polygon[len(polygon)-1][1]):
        edges.append([polygon[0],polygon[len(polygon)-1]])
    else:
        edges.append([polygon[len(polygon)-1],polygon[0]])
        
    print(edges)
    # print(polygon)
    # # Define all possible edges with the smaller y-coordinate as the first point in each edge
    # edges = []
    # for i in range(len(polygon) - 1):
    #     # Check which point has the smaller y-coordinate
    #     if polygon[i][1] == polygon[i+1][1]:
    #         continue
    #     elif polygon[i][1] < polygon[i+1][1]:
    #         edges.append([polygon[i], polygon[i+1]])
    #     else:
    #         edges.append([polygon[i+1], polygon[i]])
    
    # # Check last and first point
    # if polygon[-1][1] < polygon[0][1]:
    #     edges.append([polygon[-1], polygon[0]])
    # else:
    #     edges.append([polygon[0], polygon[-1]])

   
    # print(edges)
    # Compute the edge table
    edge_table = {}
    # For each edge, compute x_start, y_start, y_end, and dX
    counter = 0
    print(edges)
    for edge in edges:
        x_start = edge[0][0]
        y_start = edge[0][1]
        y_end = edge[1][1]
        
        try:
            dX = (edge[1][0] - edge[0][0]) / (edge[1][1] - edge[0][1])
        
        except:
            dX = 0
        
        # Add information to edge table
        edge_table["Edge {}".format(counter)] = [x_start, y_start, y_end, dX]
        
        counter+=1

    # Sort edge dictionary by increasing y_start values
    edge_table = dict(sorted(edge_table.items(), key=lambda x: x[1][1]))

    updated_edge_table = {}
    counter = 0
    # Rename keys of edge table dictionary based on position
    for edge_table_item in edge_table.values():
        
        
        
        updated_edge_table["Edge {}".format(counter)] = edge_table_item
        
        
        
        counter += 1

    
    
      
    print(updated_edge_table)
    return updated_edge_table

# print(polygon)
# print(edge_table)

def fill_polygon( poly, color):
    
    ## Possible issues with sorting
    polygon = copy.deepcopy(poly)
    
    # if not Matrix_Calculations.back_face_culling_algorithm(Viewpoint, polygon):
    #     return

    displayPolygon = project_and_convert_to_display_coordinates(polygon)
    # print(displayPolygon)
    
    edge_table = compute_edge_table(displayPolygon)
    
    
    
    # If edge table is empty, return
    if len(edge_table) == 0:
        return
    
    # Get all Y_start values from edge table
    y_start_values = list(map(lambda x: edge_table[x][1], edge_table))
    
    # Get all Y_end values from edge table
    y_end_values = list(map(lambda x: edge_table[x][2], edge_table))
    
    first_fill_line = min(y_start_values)
    last_fill_line = max(y_end_values)

    i, j, next_x = 0, 1, 2
    
    # Set first two edges' x-coordinates
    # Get i-th edge's x-coordinate
    current_edge_x = edge_table.get("Edge {}".format(i))[0]
    current_edge_2_x = edge_table["Edge {}".format(j)][0]

    
    for line_rows in range(int(first_fill_line)+1, int(last_fill_line)):
        
        left_edge = None
        right_edge = None
        # Determine which edge is Left and which is Right
        if current_edge_x < current_edge_2_x:
            left_edge = current_edge_x
            right_edge = current_edge_2_x
            
        else:
            left_edge = current_edge_2_x
            right_edge = current_edge_x
            
        # For each x from left_edge to right_edge, draw a line
        for x in range(int(left_edge)+1, int(right_edge)):
            w.create_line(x, line_rows, x + 1, line_rows, fill=color)
            
        # Update x-values with dX
        current_edge_x = current_edge_x + edge_table["Edge {}".format(i)][3]
        current_edge_2_x = current_edge_2_x + edge_table["Edge {}".format(j)][3]

        # When the bottom of an edge is reached, switch to the next edge
        if(line_rows >= edge_table["Edge {}".format(i)][2] and line_rows < last_fill_line):
            i = next_x
            current_edge_x = edge_table["Edge {}".format(i)][0]
            next_x += 1
            
        if(line_rows >= edge_table["Edge {}".format(j)][2] and line_rows < last_fill_line):
            j = next_x
            current_edge_2_x = edge_table["Edge {}".format(j)][0]
            next_x += 1




 


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
    display_X = canvas_width/2 + point[0]
    display_Y = canvas_height/2 - point[1]
   
    # Adds converted coordinates to list
    displayXY.append(display_X)
    displayXY.append(display_Y)
   
    # Returns the list of converted coordinates
    return displayXY


root = Tk()
outerframe = Frame(root)
outerframe.pack()

w = Canvas(outerframe, width=canvas_width, height=canvas_height)

# apex = [0,50,100]
# base1 = [50,-50,50]
# base2 = [50,-50,150]
# base3 = [-50,-50,150]
# base4 = [-50,-50,50]


# # fill_polygon(Pyramid, 'red')    
# apex = [0,50,100, 1]
# base1 = [40.55797877317731,-50,42.07720346948115, 1]
# base2 = [50,-50,150, 1]
# base3 = [-50,-50,150, 1]
# base4 = [-57.922796530518845,-50,59.4420212268227, 1]
base1 = [50,25.811923270530365,63.64693911811081, 1]
base3 = [50,-73.80754653943052,72.36251347538138, 1]
base5 = [-50,25.811923271116303,63.6469391182488, 1]
base7 = [-50,-73.80754653943046, 72.36251347538138, 1]
frontpoly = [base1, base3, base7, base5]

# Definition of the five polygon faces using the meaningful point names
# Polys are defined in clockwise order when viewed from the outside
# frontpoly = [apex,base1,base4]
# rightpoly = [apex,base2,base1]

drawPoly(frontpoly)
fill_polygon(frontpoly, "red")

w.pack()

controlpanel = Frame(outerframe)
controlpanel.pack()

# resetcontrols = Frame(controlpanel, height=100, borderwidth=2, relief=RIDGE)
# resetcontrols.pack(side=LEFT)

# resetcontrolslabel = Label(resetcontrols, text="Reset")
# resetcontrolslabel.pack()

# resetButton = Button(resetcontrols, text="Reset", fg="green", command=reset)
# resetButton.pack(side=LEFT)

# scalecontrols = Frame(controlpanel, borderwidth=2, relief=RIDGE)
# scalecontrols.pack(side=LEFT)

# scalecontrolslabel = Label(scalecontrols, text="Scale")
# scalecontrolslabel.pack()

# largerButton = Button(scalecontrols, text="Larger", command=larger)
# largerButton.pack(side=LEFT)

# smallerButton = Button(scalecontrols, text="Smaller", command=smaller)
# smallerButton.pack(side=LEFT)

# translatecontrols = Frame(controlpanel, borderwidth=2, relief=RIDGE)
# translatecontrols.pack(side=LEFT)

# translatecontrolslabel = Label(translatecontrols, text="Translation")
# translatecontrolslabel.pack()

# forwardButton = Button(translatecontrols, text="FW", command=forward)
# forwardButton.pack(side=LEFT)

# backwardButton = Button(translatecontrols, text="BK", command=backward)
# backwardButton.pack(side=LEFT)

# leftButton = Button(translatecontrols, text="LF", command=left)
# leftButton.pack(side=LEFT)

# rightButton = Button(translatecontrols, text="RT", command=right)
# rightButton.pack(side=LEFT)

# upButton = Button(translatecontrols, text="UP", command=up)
# upButton.pack(side=LEFT)

# downButton = Button(translatecontrols, text="DN", command=down)
# downButton.pack(side=LEFT)

# rotationcontrols = Frame(controlpanel, borderwidth=2, relief=RIDGE)
# rotationcontrols.pack(side=LEFT)

# rotationcontrolslabel = Label(rotationcontrols, text="Rotation")
# rotationcontrolslabel.pack()

# xPlusButton = Button(rotationcontrols, text="X+", command=xPlus)
# xPlusButton.pack(side=LEFT)

# xMinusButton = Button(rotationcontrols, text="X-", command=xMinus)
# xMinusButton.pack(side=LEFT)

# yPlusButton = Button(rotationcontrols, text="Y+", command=yPlus)
# yPlusButton.pack(side=LEFT)

# yMinusButton = Button(rotationcontrols, text="Y-", command=yMinus)
# yMinusButton.pack(side=LEFT)

# zPlusButton = Button(rotationcontrols, text="Z+", command=zPlus)
# zPlusButton.pack(side=LEFT)

# zMinusButton = Button(rotationcontrols, text="Z-", command=zMinus)
# zMinusButton.pack(side=LEFT)

root.mainloop()