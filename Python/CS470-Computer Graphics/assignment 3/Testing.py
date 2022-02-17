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
        
def project_and_convert_to_display_coordinates(polygon):
        
        display_polygon = []
        for point in polygon:
            projected_points = project(point)
            projected_points[0], projected_points[1] = convertToDisplayCoordinates(projected_points)
        
            # Make every coordinate in projected_points a rounded float except z coordinate
            display_polygon.append([float(round(projected_points[0])), float(round(projected_points[1])), projected_points[2]])
            # display_polygon.append(list(map(lambda x: float(round(x, 0)), projected_points)))
            
        
        
        
        return display_polygon
    

def compute_edge_table( polygon):


    

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
    elif(polygon[0][1] > polygon[len(polygon)-1][1]):
        edges.append([polygon[len(polygon)-1],polygon[0]])
        
    
    # Compute the edge table
    edge_table = {}
    # For each edge, compute x_start, y_start, y_end, and dX
    counter = 0
    # print(edges)
    for edge in edges:
        x_start = edge[0][0]
        y_start = edge[0][1]
        y_end = edge[1][1]
        z_start = edge[0][2]
        
        try:
            dX = (edge[1][0] - edge[0][0]) / (edge[1][1] - edge[0][1])
            dZ = (edge[1][2] - edge[0][2]) / (edge[1][1] - edge[0][1])
        except:
            dX = 0
            dZ = 0
        
        # Add information to edge table
        edge_table["Edge {}".format(counter)] = [x_start, y_start, y_end, dX, z_start, dZ]
        
        counter+=1

    # Sort edge dictionary by increasing y_start values
    edge_table = dict(sorted(edge_table.items(), key=lambda x: x[1][1]))

    updated_edge_table = {}
    counter = 0
    # Rename keys of edge table dictionary based on position
    for edge_table_item in edge_table.values():
        
        
        
        updated_edge_table["Edge {}".format(counter)] = edge_table_item
        
        
        
        counter += 1

    
    
    
    # print(updated_edge_table)
    return updated_edge_table

# print(polygon)
# print(edge_table)

def fill_polygon( poly, color, z_buffer):
    polygon = copy.deepcopy(poly)
    
    # if not Matrix_Calculations.back_face_culling_algorithm(viewpoint, polygon):
    #     return


    displayPolygon = project_and_convert_to_display_coordinates(polygon)
    # print(displayPolygon)
    
    edge_table = compute_edge_table(displayPolygon)
    
    # If edge table is empty, return
    if len(edge_table) == 0 or len(edge_table) == 1:
        return
    
    # print(edge_table)
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

    current_edge_z = edge_table.get("Edge {}".format(i))[4]
    current_edge_2_z = edge_table.get("Edge {}".format(j))[4]
    
    for line_rows in range(int(first_fill_line)+1, int(last_fill_line)):
        
        left_edge = None
        right_edge = None
        
        left_z_edge = None
        right_z_edge = None
        
        # Determine which edge is Left and which is Right
        if current_edge_x < current_edge_2_x:
            left_edge = current_edge_x
            right_edge = current_edge_2_x
            
            left_z_edge = current_edge_z
            right_z_edge = current_edge_2_z
            
        else:
            left_edge = current_edge_2_x
            right_edge = current_edge_x
            
            left_z_edge = current_edge_2_z
            right_z_edge = current_edge_z
            
        # The initial Z for the current fill line
        z_value = left_z_edge
        dZ_fill_line = 0
        
        # Compute dZ for the fill line. Can be 0 if line is 1 pixel long
        if (right_z_edge - left_z_edge) != 0:
            dZ_fill_line = (right_z_edge-left_z_edge)/(right_edge - left_edge)
        else:
            dZ_fill_line = 0

        # For each x from left_edge to right_edge, draw a line
        for x in range(int(left_edge)+1, int(right_edge)):
            
            if z_value < z_buffer[line_rows][x]:
            
                canvas.create_line(x, line_rows, x + 1, line_rows, fill=color)
                z_buffer[line_rows][x] = z_value
            z_value = z_value + dZ_fill_line
            
        # Update x-values and z-values with dX and Dz
        current_edge_x = current_edge_x + edge_table["Edge {}".format(i)][3]
        current_edge_2_x = current_edge_2_x + edge_table["Edge {}".format(j)][3]
        
        current_edge_z = current_edge_z + edge_table["Edge {}".format(i)][5]
        current_edge_2_z = current_edge_2_x + edge_table["Edge {}".format(j)][5]
        
        # When the bottom of an edge is reached, switch to the next edge
        if(line_rows >= edge_table["Edge {}".format(i)][2] and line_rows < last_fill_line):
            i = next_x
            current_edge_x = edge_table["Edge {}".format(i)][0]
            current_edge_z = edge_table["Edge {}".format(i)][4]
            next_x += 1
            
        if(line_rows >= edge_table["Edge {}".format(j)][2] and line_rows < last_fill_line):
            j = next_x
            current_edge_2_x = edge_table["Edge {}".format(j)][0]
            current_edge_2_z = edge_table["Edge {}".format(j)][4]
            next_x += 1

    
def get_z_buffer():
        
    # Create z buffer array based on canvas width and height
    # Initialize with max distance which is 500 in this setup
    return [[500 for x in range(canvas_width)] for y in range(canvas_height)]

    # # Project and convert to display coordinates
    # point1 = project(point1)
    # point2 = project(point2)
    
    # point1 = convertToDisplayCoordinates(point1)
    # point2 = convertToDisplayCoordinates(point2)
    
    # dx = abs(x2 - x1)
    # dy = abs(y2 - y1)
    # dz = abs(z2 - z1)
    
    # # Determine slope of line
    # if dx >= dy and dx >= dz:
    #     slope = dx
    # elif dy >= dx and dy >= dz:
    #     slope = dy
    # else:
    #     slope = dz
    
    # # Determine increment in x, y, and z
    # x_inc = dx / slope
    # y_inc = dy / slope
    # z_inc = dz / slope
    
    # # Determine starting x, y, and z
    # x = x1
    # y = y1
    # z = z1
    
    # # Determine which direction to increment in x, y, and z
    # if x1 < x2:
    #     x_dir = 1
    # else:
    #     x_dir = -1
        
    # if y1 < y2:
    #     y_dir = 1
    # else:
    #     y_dir = -1
        
    # if z1 < z2:
    #     z_dir = 1
    # else:
    #     z_dir = -1
    
    # # Draw line
    # while x != x2 or y != y2 or z != z2:
    #     canvas.create_line(x, y, x + 1, y, fill="red")
    #     x += x_dir
    #     y += y_dir
    #     z += z_dir
    
    
   

 


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
        
        
# This function will draw a polygon by repeatedly callying drawLine on each pair of points
# making up the object.  Remember to draw a line between the last point and the first.
def drawPolyImprovised(poly):
    
    # If the polygon is a triangle, draw a line between the appropriate points
    if(len(poly) == 3):
        draw_line(poly[0],poly[1])
        draw_line(poly[1],poly[2])
        draw_line(poly[2],poly[0])
    
    # If the polygon is a quadrilateral, draw a line between the appropriate points
    else:
        draw_line(poly[0],poly[1])
        draw_line(poly[1],poly[2])
        draw_line(poly[2],poly[3])
        draw_line(poly[3],poly[0])
    

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
    canvas.create_line(convertToDisplayCoordinates_start[0],convertToDisplayCoordinates_start[1],convertToDisplayCoordinates_end[0],convertToDisplayCoordinates_end[1])
    

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

canvas = Canvas(outerframe, width=canvas_width, height=canvas_height)

# apex = [0,-100, 100]
# base1 = [50,-150, 50]
# base2 = [50,-150, 150]
# base3 = [-50,-150,150]
# base4 = [-50,-150,50]
base1 = [50,-50,50, 1]
base2 = [50,-50,150, 1]
base3 = [-50,-50,50, 1]
base4 = [-50,-50,150, 1]
base5 = [50,50,50, 1]
base6 = [50,50,150, 1]
base7 = [-50,50,50, 1]
base8 = [-50,50,150, 1]

# # fill_polygon(Pyramid, 'red')    
# apex = [0,50,100, 1]
# base1 = [40.55797877317731,-50,42.07720346948115, 1]
# base2 = [50,-50,150, 1]
# base3 = [-50,-50,150, 1]
# base4 = [-57.922796530518845,-50,59.4420212268227, 1]
# base1 = [50,25.811923270530365,63.64693911811081, 1]
# base3 = [50,-73.80754653943052,72.36251347538138, 1]
# base5 = [-50,25.811923271116303,63.6469391182488, 1]
# base7 = [-50,-73.80754653943046, 72.36251347538138, 1]
# frontpoly = [base1, base3, base7, base5]

# Definition of the five polygon faces using the meaningful point names
# Polys are defined in clockwise order when viewed from the outside
# frontpoly = [apex,base1,base4]
# rightpoly = [apex,base2,base1]
# backpoly = [apex,base3,base2]

# Using bresenham's line algorithm, draw a line from point1 to point2 with perspective correction
def draw_line(x1, y1, x2, y2):
    canvas.create_line(x1,y1,x2,y2, fill="red")
    print(x1,y1,x2,y2)
    # Arrange points such that x1 < x2 and y1 < y2
    # if y1 > y2:
    #     y1, y2 = y2, y1
    
    # Ensure that x1 < x2 and y1 < y2
    if y1 > y2:
        x1, x2 = x2, x1
        y1, y2 = y2, y1

    
    # Set the initial x and y coordinates and make them integers
    x1, y1, x2, y2 = int(round(x1)), int(round(y1)), int(round(x2)), int(round(y2))

    x = x1
    y = y1
    
    print(y, y2)    
    dx = x2 - x1
    dy = y2 - y1
    
    try:
        m = dy/dx
    except ZeroDivisionError:
        m = "inf"
        inverse_m = "inf"

    try:
        inverse_m = dx/dy
    except ZeroDivisionError:
        inverse_m = "inf"
    
    print(x, y, dx, dy, m, inverse_m, x2, y2)
    

    if m == "inf" or inverse_m == "inf":
        # Case 1: vertical line -> m = inf
        if m == "inf":
            canvas.create_line(x,y,x+1,y+1)
            while (y != y2):
                y = y + 1 if y1 < y2 else y - 1
                canvas.create_line(x,y,x+1,y+1)
        
        # Case 2: horizontal line -> inverse_m = inf
        else:
            canvas.create_line(x,y,x+1,y+1)
            while (x != x2):
                x = x + 1 if x2 > x1 else x - 1
                canvas.create_line(x,y,x+1,y+1)
                

    elif abs(m) < 1:
        
        
        p = 2 * dy - dx
        # canvas.create_line(x,y,x2,y2, fill="red")
        canvas.create_line(x,y,x+1,y+1, width=1)
        while (x != x2):
            
            if x1 > x2:
                x = x - 1
            else:
                x = x + 1            
            # print(p)
            if (p < 0):
                p = p + 2 * dy

                
            else:
                if m < 0:
                    p = p + 2 * dy + 2 * dx
                else:
                    p = p + 2 * dy - 2 * dx
                    
                y = y + 1
                    
            canvas.create_line(x,y,x+1,y+1)
            
    elif abs(m) > 1:
            
            p = 2 * dx - dy

            canvas.create_line(x,y,x+1,y+1)
            while (y != y2):
            
                # print(p)
                if y1 > y2:
                    y = y - 1
                else:
                    y = y + 1    
                
                if (p < 0):
                    if inverse_m < 0:
                        p = p - 2 * dx
                    else:
                        p = p + 2 * dx
                        
                else:
                    if inverse_m < 0:
                        p = p - 2 * dx - 2 * dy
                        x -= 1
                    else:
                        p = p + 2 * dx - 2 * dy
                        x += 1
                    
                canvas.create_line(x,y,x+1,y+1)
                
    elif abs(m) == 1:
        # canvas.create_line(x,y,x2,y2, fill="red")
        canvas.create_line(x,y,x+1,y+1)
        while (x != x2):
            if m < 0:
                x = x - 1
            else:
                x = x + 1
            
            print("test")
            y = y + 1
            canvas.create_line(x,y,x+1,y+1)
    
        
# leftpoly = [apex,base4,base3]
# bottompoly = [base1,base2,base3,base4]

# drawPolyImprovised(frontpoly)
# drawPolyImprovised(rightpoly)
# drawPolyImprovised(backpoly)
# drawPolyImprovised(leftpoly)    
# drawPolyImprovised(bottompoly)

        
# draw_line(1, 1, 8, 5)
# point1 = project(apex)
base1 = project(base1)
base2 = project(base2)
base3 = project(base3)
base4 = project(base4)
base5 = project(base5)
base6 = project(base6)
base7 = project(base7)
base8 = project(base8)

# point1 = convertToDisplayCoordinates(point1)
base1 = convertToDisplayCoordinates(base1)
base2 = convertToDisplayCoordinates(base2)
base3 = convertToDisplayCoordinates(base3)
base4 = convertToDisplayCoordinates(base4)
base5 = convertToDisplayCoordinates(base5)
base6 = convertToDisplayCoordinates(base6)
base7 = convertToDisplayCoordinates(base7)
base8 = convertToDisplayCoordinates(base8)

bottompoly = [base1, base2, base4, base3]
toppoly = [base5,base7,base8,base6]
frontpoly = [base1, base3, base7, base5]
rightpoly = [base1, base5, base6, base2]
leftpoly = [base3, base4, base8, base7]
backpoly = [base2, base6, base8, base4]

polys = [bottompoly, toppoly, frontpoly, rightpoly, leftpoly, backpoly]

for poly in polys:
    for i in range(len(poly)):    
        draw_line(poly[i][0],poly[i][1],poly[(i+1)%4][0],poly[(i+1)%4][1])


# draw_line(point1[0],point1[1],point2[0],point2[1])

# fill_polygon(frontpoly, "red", get_z_buffer())
# draw_line(apex, base1)
canvas.pack()

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