from distutils.command.build_scripts import first_line_re
from itertools import count
import Matrix_Calculations

Viewpoint = [0, 0, -500]
canvas_width = 400
canvas_height = 400

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
        
        # Make every coordinate in projected_points a rounded float
        display_polygon.append(list(map(lambda x: float(round(x, 0)), projected_points)))
        
        
        
            
    return display_polygon
        
def compute_edge_table(polygon):
    
    # Sort points by y-coordinate
    polygon.sort(key=lambda x: x[1])
    
    # If points have the same y-coordinate, remove one of them
    for i in range(len(polygon) - 1):
        if polygon[i][1] == polygon[i+1][1]:
            polygon.pop(i+1)
    
    
    # Define all possible edges with the smaller y-coordinate as the first point in each edge
    edges = []
    for i in range(len(polygon) - 1):
        # Check which point has the smaller y-coordinate
        if polygon[i][1] < polygon[i+1][1]:
            edges.append([polygon[i], polygon[i+1]])
        else:
            edges.append([polygon[i+1], polygon[i]])
    
    # Check last and first point
    if polygon[-1][1] < polygon[0][1]:
        edges.append([polygon[-1], polygon[0]])
    else:
        edges.append([polygon[0], polygon[-1]])

    
    # Compute the edge table
    edge_table = {}

    # For each edge, compute x_start, y_start, y_end, and dX
    counter = 0
    for edge in edges:
        x_start = edge[0][0]
        y_start = edge[0][1]
        y_end = edge[1][1]
        dX = (edge[1][0] - edge[0][0]) / (edge[1][1] - edge[0][1])
        
        # Add information to edge table
        edge_table["Edge {}".format(counter)] = [x_start, y_start, y_end, dX]
        
        counter+=1

    
    return edge_table
    
    # print(polygon)
    # print(edge_table)
    
def fill_polygon(polygon, color):
    
    # if not Matrix_Calculations.back_face_culling_algorithm(Viewpoint, polygon):
    #     return
    
    displayPolygon = project_and_convert_to_display_coordinates(polygon)
    
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
    
    
    for line_rows in range(first_fill_line, last_fill_line):
        
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
        for x in range(int(left_edge), int(right_edge)):
            pass
            # w.create_line(x, line_rows, x + 1, line_rows, fill=color)
            
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

    
fill_polygon(Pyramid[1], 'red')    
    
    
    
 
 
