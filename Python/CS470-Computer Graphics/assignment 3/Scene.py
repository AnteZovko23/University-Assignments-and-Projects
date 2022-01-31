# Tkinter scene class
from tkinter import *
import copy
import Matrix_Calculations

"""
Author: Ante Zovko
Date: January 12, 2022

Creates a tkinter scene with a canvas
Allows for the addition of 3D objects to the scene
Demonstrates the three basic operations (translation, rotation, scaling) using matrices

Reference Material: Computer Graphics Lecture Notes by Dr. Mike O'Neal
"""

class Scene(object):
    
    
    """
    Constructor
    
    Takes canvas width, height, and viewer's distance from the screen 
    """
    def __init__(self, canvas_width, canvas_height, viewpoint):
        
        # Initialize the canvas
        self.root = Tk()
        outerframe = Frame(self.root)
        outerframe.pack()
        
        self.canvas_width = canvas_width
        self.canvas_height = canvas_height
        self.viewpoint = viewpoint
        self.canvas = Canvas(outerframe, width=self.canvas_width, height=self.canvas_height)
        self.canvas.pack()
        
        # Z buffer
        self.z_buffer = self.get_z_buffer()
        
        # Render modes
        self.render_modes = ["Wireframe", "Polygon_Fill_Edges", "Polygon_Fill"]
        
        self.render_mode_index = 1
        
        self.current_render_mode = self.render_modes[self.render_mode_index]
        
        # Initialize the current object
        self.object_list = []
        self.current_object = None
        
        # Initialize all of the buttons and their functions
        controlpanel = Frame(outerframe)
        controlpanel.pack()

        resetcontrols = Frame(controlpanel, height=100, borderwidth=2, relief=RIDGE)
        resetcontrols.pack(side=LEFT)

        resetcontrolslabel = Label(resetcontrols, text="Reset")
        resetcontrolslabel.pack()

        resetButton = Button(resetcontrols, text="Reset", fg="green", command=self.reset)
        resetButton.pack(side=LEFT)

        scalecontrols = Frame(controlpanel, borderwidth=2, relief=RIDGE)
        scalecontrols.pack(side=LEFT)

        scalecontrolslabel = Label(scalecontrols, text="Scale")
        scalecontrolslabel.pack()

        largerButton = Button(scalecontrols, text="Larger", command=self.larger)
        largerButton.pack(side=LEFT)

        smallerButton = Button(scalecontrols, text="Smaller", command=self.smaller)
        smallerButton.pack(side=LEFT)

        translatecontrols = Frame(controlpanel, borderwidth=2, relief=RIDGE)
        translatecontrols.pack(side=LEFT)

        translatecontrolslabel = Label(translatecontrols, text="Translation")
        translatecontrolslabel.pack()

        forwardButton = Button(translatecontrols, text="FW", command=self.forward)
        forwardButton.pack(side=LEFT)

        backwardButton = Button(translatecontrols, text="BK", command=self.backward)
        backwardButton.pack(side=LEFT)

        leftButton = Button(translatecontrols, text="LF", command=self.left)
        leftButton.pack(side=LEFT)

        rightButton = Button(translatecontrols, text="RT", command=self.right)
        rightButton.pack(side=LEFT)

        upButton = Button(translatecontrols, text="UP", command=self.up)
        upButton.pack(side=LEFT)

        downButton = Button(translatecontrols, text="DN", command=self.down)
        downButton.pack(side=LEFT)

        rotationcontrols = Frame(controlpanel, borderwidth=2, relief=RIDGE)
        rotationcontrols.pack(side=LEFT)

        rotationcontrolslabel = Label(rotationcontrols, text="Rotation")
        rotationcontrolslabel.pack()

        xPlusButton = Button(rotationcontrols, text="X+", command=self.xPlus)
        xPlusButton.pack(side=LEFT)

        xMinusButton = Button(rotationcontrols, text="X-", command=self.xMinus)
        xMinusButton.pack(side=LEFT)

        yPlusButton = Button(rotationcontrols, text="Y+", command=self.yPlus)
        yPlusButton.pack(side=LEFT)

        yMinusButton = Button(rotationcontrols, text="Y-", command=self.yMinus)
        yMinusButton.pack(side=LEFT)

        zPlusButton = Button(rotationcontrols, text="Z+", command=self.zPlus)
        zPlusButton.pack(side=LEFT)

        zMinusButton = Button(rotationcontrols, text="Z-", command=self.zMinus)
        zMinusButton.pack(side=LEFT)

        # Bind right and left arrow key to canvas
        self.root.bind('<Right>', self.select_next_object)
        self.root.bind('<Left>', self.select_previous_object)    
         # Bind right and left arrow key to canvas
        self.root.bind('1', self.set_render_mode)
        self.root.bind('2', self.set_render_mode)   
        self.root.bind('3', self.set_render_mode)  

    
    """
    Given a point cloud and the original point cloud of the created object, this function resets the object to its 
    original position
    """
    def reset_polyhedron(self, point_cloud, original_point_cloud):
        for i in range(len(point_cloud)):
            for j in range(3):
                point_cloud[i][j] = original_point_cloud[i][j]

    """
    Gets the root of the scene
    """
    def get_root(self):
        return self.root
    
    """
    This function translates an object by some displacement. The displacement is a 3D
    vector so the amount of displacement in each dimension can vary.
    """
    def translate(self, object, displacement):
        
        # Get translation matrix
        translation_matrix = Matrix_Calculations.get_translation_matrix(displacement)
        
        
        # Iterates through each polygon in the object
        for i in range(len(object)):
            
            # Using Matrix notation and composite matices in section 8
            new_coordinates = (Matrix_Calculations.matrix_multiplication(object[i], translation_matrix))

            object[i][0], object[i][1], object[i][2] = new_coordinates[0][0], new_coordinates[0][1], new_coordinates[0][2]
            
    
    """    
    This function performs a simple uniform scale of an object. The scalefactor is a scalar.
    """
    def scale(self, object,scalefactor):
        
        # Calculates the bounding box of the object
        x_center, y_center, z_center = Matrix_Calculations.calculate_visual_center(object)
        
        # Get translation matrix with inverted center coordinates to move object to origin
        translation_matrix_origin = Matrix_Calculations.get_translation_matrix([-x_center, -y_center, -z_center])
        
        # Get scale matrix
        scaling_matrix = Matrix_Calculations.get_scaling_matrix([scalefactor, scalefactor, scalefactor])
        
        # Get translation matrix with original center coordinates to move object back to original position
        translation_matrix_back = Matrix_Calculations.get_translation_matrix([x_center, y_center, z_center])
        
        # Calculate first composite matrix
        composite_matrix_1 = Matrix_Calculations.matrix_multiplication(translation_matrix_origin, scaling_matrix)
        
        # Calculate final composite matrix
        composite_matrix_2 = Matrix_Calculations.matrix_multiplication(composite_matrix_1, translation_matrix_back)
        
        # Iterates through each polygon in the object
        for i in range(len(object)):
                
            # Using Matrix notation and composite matices in section 8
            new_coordinates = (Matrix_Calculations.matrix_multiplication(object[i], composite_matrix_2))
    
            object[i][0], object[i][1], object[i][2] = new_coordinates[0][0], new_coordinates[0][1], new_coordinates[0][2]

            
    """
    # This function performs a rotation of an object about the Z axis (from +X to +Y)
    # by 'degrees'. The rotation is CCW
    # in a LHS when viewed from -Z [the location of the viewer in the standard postion]
    """
    def rotateZ(self, object,degrees):
        
        # Calculates the bounding box of the object
        x_center, y_center, z_center = Matrix_Calculations.calculate_visual_center(object)
        
        # Get translation matrix with inverted center coordinates to move object to origin
        translation_matrix_origin = Matrix_Calculations.get_translation_matrix([-x_center, -y_center, -z_center])
        
        # Get rotation matrix about Z axis
        rotation_matrix = Matrix_Calculations.get_rotation_matrix('z', degrees)
        
        # Get translation matrix with original center coordinates to move object back to original position
        translation_matrix_back = Matrix_Calculations.get_translation_matrix([x_center, y_center, z_center])

        # Calculate first composite matrix
        composite_matrix_1 = Matrix_Calculations.matrix_multiplication(translation_matrix_origin, rotation_matrix)
        
        # Calculate final composite matrix
        composite_matrix_2 = Matrix_Calculations.matrix_multiplication(composite_matrix_1, translation_matrix_back)
        
        # Iterates through each polygon in the object
        for i in range (len(object)):
            
            new_coordinates = (Matrix_Calculations.matrix_multiplication(object[i], composite_matrix_2))
            
            object[i][0], object[i][1], object[i][2] = new_coordinates[0][0], new_coordinates[0][1], new_coordinates[0][2]
            
            
    """
    # This function performs a rotation of an object about the Y axis (from +Z to +X)
    # by 'degrees'. The rotation is CW
    # in a LHS when viewed from +Y looking toward the origin.
    """
    def rotateY(self, object,degrees):
        
        # Calculates the bounding box of the object
        x_center, y_center, z_center = Matrix_Calculations.calculate_visual_center(object)
        
        # Get translation matrix with inverted center coordinates to move object to origin
        translation_matrix_origin = Matrix_Calculations.get_translation_matrix([-x_center, -y_center, -z_center])
        
        # Get rotation matrix about Z axis
        rotation_matrix = Matrix_Calculations.get_rotation_matrix('y', degrees)
        
        # Get translation matrix with original center coordinates to move object back to original position
        translation_matrix_back = Matrix_Calculations.get_translation_matrix([x_center, y_center, z_center])

        # Calculate first composite matrix
        composite_matrix_1 = Matrix_Calculations.matrix_multiplication(translation_matrix_origin, rotation_matrix)
        
        # Calculate final composite matrix
        composite_matrix_2 = Matrix_Calculations.matrix_multiplication(composite_matrix_1, translation_matrix_back)
        
        # Iterates through each polygon in the object
        for i in range(len(object)):
            
               
            new_coordinates = (Matrix_Calculations.matrix_multiplication(object[i], composite_matrix_2))
            
            object[i][0], object[i][1], object[i][2] = new_coordinates[0][0], new_coordinates[0][1], new_coordinates[0][2]
            
            

    """
    # This function performs a rotation of an object about the X axis (from +Y to +Z)
    # by 'degrees'. The rotation is CW
    # in a LHS when viewed from +X looking toward the origin.
    """
    def rotateX(self, object, degrees):
        
        
        # Calculates the bounding box of the object
        x_center, y_center, z_center = Matrix_Calculations.calculate_visual_center(object)
        
        # Get translation matrix with inverted center coordinates to move object to origin
        translation_matrix_origin = Matrix_Calculations.get_translation_matrix([-x_center, -y_center, -z_center])
        
        # Get rotation matrix about Z axis
        rotation_matrix = Matrix_Calculations.get_rotation_matrix('x', degrees)
        
        # Get translation matrix with original center coordinates to move object back to original position
        translation_matrix_back = Matrix_Calculations.get_translation_matrix([x_center, y_center, z_center])

        # Calculate first composite matrix
        composite_matrix_1 = Matrix_Calculations.matrix_multiplication(translation_matrix_origin, rotation_matrix)
        
        # Calculate final composite matrix
        composite_matrix_2 = Matrix_Calculations.matrix_multiplication(composite_matrix_1, translation_matrix_back)
        
        # Iterates through each polygon in the object
        for i in range(len(object)):
            
               
            new_coordinates = (Matrix_Calculations.matrix_multiplication(object[i], composite_matrix_2))
            
            object[i][0], object[i][1], object[i][2] = new_coordinates[0][0], new_coordinates[0][1], new_coordinates[0][2]
            
    """    
    This function will draw an object by repeatedly callying drawPoly on each polygon in the object
    If selected is true then the object will be drawn with red lines, otherwise it will be drawn with black lines
    """
    def drawObject(self, object, selected=True):
        
       
        lines = []
        # Iterates through each polygon in the object
        for poly in object:
            
            # If polygon is not visible, skip it
            if not Matrix_Calculations.back_face_culling_algorithm(self.viewpoint, poly):
                continue
                
            else:
                # Draws each polygon and append the id of the drawn line
                lines.append(self.drawPoly(poly, selected))
                # if(len(poly) == 4):
                    # print(poly)
                
                obj = None
                for i in range(len(self.object_list)):
                    # Compare array with object array
                    if(self.object_list[i]["Polyhedron"] == object):
                        obj = self.object_list[i]

            
                # Get index of poly
                index = object.index(poly)
                
                # If fill polygon modes are selected
                if(self.current_render_mode != "Wireframe"):
                    self.fill_polygon(poly, obj["Colors"][index])
                    
        return lines
    
    
        
    """
    This function will draw a polygon by repeatedly callying drawLine on each pair of points
    making up the object.  Remember to draw a line between the last point and the first.
    """
    def drawPoly(self, poly, selected=True):
        lines = []
        
        for i in range(len(poly)):
            # Draw lines between each pair of points, if i + 1 is out of bounds then draw a line between the last point and the first
            lines.append(self.drawLine(poly[i], poly[(i + 1) % len(poly)], selected))
        
        return lines
        

    """
    Project the 3D endpoints to 2D point using a perspective projection implemented in 'project'
    Convert the projected endpoints to display coordinates via a call to 'convertToDisplayCoordinates'
    draw the actual line using the built-in create_line method
    
    If selected is true then the line will be drawn with red lines, otherwise it will be drawn with black lines
    """
    def drawLine(self, start, end, selected=True):

        # Project start and end points    
        project_start = self.project(start)
        project_end = self.project(end)
        
        # Convert them to display coordinates
        convertToDisplayCoordinates_start = self.convertToDisplayCoordinates(project_start)
        convertToDisplayCoordinates_end = self.convertToDisplayCoordinates(project_end)
        
        # Create a line between the projected points
        # If object is selected the line is red, else black
        
        # If polygon_fill mode is not selected
        
        if(self.current_render_mode != "Polygon_Fill"):
            if selected:
                color = "yellow"
            else:
                color = "black"
                
            return self.canvas.create_line(convertToDisplayCoordinates_start[0],convertToDisplayCoordinates_start[1],convertToDisplayCoordinates_end[0],convertToDisplayCoordinates_end[1], fill=color, width=3)
        
    """
    This function converts from 3D to 2D (+ depth) using the perspective projection technique.  Note that it
    will return a NEW list of points.  We will not want to keep around the projected points in our object as
    they are only used in rendering
    """
    def project(self, point):
        ps = []
        
        # For each coordinate in the given point, project them using the formulas on page 11
        x_ps = -self.viewpoint[2] * point[0] / (-self.viewpoint[2] + point[2])
        y_ps = -self.viewpoint[2] * point[1] / (-self.viewpoint[2] + point[2])
        z_ps = -self.viewpoint[2] * point[2] / (-self.viewpoint[2] + point[2])
        
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
    def convertToDisplayCoordinates(self, point):
        displayXY = []
        
        # Converts drawing coordinates to display coordinates using formulas on page 13
        display_X = self.canvas_width/2 + point[0]
        display_Y = self.canvas_height/2 - point[1]
    
        # Adds converted coordinates to list
        displayXY.append(display_X)
        displayXY.append(display_Y)
    
        # Returns the list of converted coordinates
        return displayXY
            
    
    """
    Adds a new object to the canvas given a list of polygons and point cloud
    The added object will be selected by default
    """
    def add_object(self, polyhedron, point_cloud, colors):
        
        # If there is no object in the scene then skip else delete the current object
        if(self.current_object != None):
            for line in self.current_object["Lines"]:
                for item in line:
                    self.canvas.delete(item)
          
            # Redraw with black lines      
            self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], False)
        
        # Append to the list of objects
        self.object_list.append({"Polyhedron": polyhedron, "PointCloud": point_cloud, "DefaultPointCloud": copy.deepcopy(point_cloud), "Lines": [], "Colors": colors})
        
        # Current object is the last one added
        self.current_object = self.object_list[-1]
        
        # Draw the current object with red lines
        current_lines = self.drawObject(self.current_object["Polyhedron"], True)
        self.current_object["Lines"] = current_lines
        
        # Update canvas
        self.canvas.update()
    
    # **************************************************************************
    # Everything below this point implements the interface
      
    """
    Selects the previous object in the list of objects
    """
    def select_previous_object(self, event):
        
        # # Delete current object with black lines
        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
          
        # # Redraw with black lines  
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], False)
        
        # Select previous in list
        self.current_object = self.object_list[(self.object_list.index(self.current_object) - 1) % len(self.object_list)]
        self.z_buffer = self.get_z_buffer()
        self.redraw_canvas()
        # # Delete selected and redraw with different color lines
        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
                
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)
    
    """
    Selects the next object in the list of objects
    """
    def select_next_object(self, event):
        
    
        # # Delete current object with black lines
        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
          
        # # Redraw with black lines      
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], False)
        
        # # Select next in list
        self.current_object = self.object_list[(self.object_list.index(self.current_object) + 1) % len(self.object_list)]
        self.z_buffer = self.get_z_buffer()
        self.redraw_canvas()
        # # Delete selected and redraw with different color lines
        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
                
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)
        

    def reset(self):
        
        self.canvas.delete("all")
        
        self.z_buffer = self.get_z_buffer()
        
        
        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.reset_polyhedron(self.current_object["PointCloud"], self.current_object["DefaultPointCloud"])
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def larger(self):

        # self.canvas.delete("all")    

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.scale(self.current_object["PointCloud"], 1.1)
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()
        
    def smaller(self):

        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.scale(self.current_object["PointCloud"], 0.9)
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def forward(self):
        
        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.translate(self.current_object["PointCloud"],[0,0,5])
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def backward(self):
        
        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
            # for item in line:
                # self.canvas.delete(item)
            
        self.translate(self.current_object["PointCloud"],[0,0,-5])
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def left(self):
        
        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
              
            
        self.translate(self.current_object["PointCloud"],[-5,0,0])
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def right(self):

        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.translate(self.current_object["PointCloud"],[5,0,0])
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def up(self):

        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.translate(self.current_object["PointCloud"],[0,5,0])
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def down(self):

        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.translate(self.current_object["PointCloud"],[0,-5,0])
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def xPlus(self):
        
        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.rotateX(self.current_object["PointCloud"], 5)
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def xMinus(self):

        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.rotateX(self.current_object["PointCloud"], -5)
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def yPlus(self):
        
        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.rotateY(self.current_object["PointCloud"], 5)
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)
        
        self.redraw_canvas()
        
    def yMinus(self):
        
        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.rotateY(self.current_object["PointCloud"], -5)
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def zPlus(self):
        
        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.rotateZ(self.current_object["PointCloud"], 5)
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

    def zMinus(self):
        
        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()

        # for line in self.current_object["Lines"]:
        #     for item in line:
        #         self.canvas.delete(item)
            
        self.rotateZ(self.current_object["PointCloud"], -5)
        # self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

        self.redraw_canvas()

   # **************************************************************************
   
   
   # ASSIGNMENT 3
   
   # **************************************************************************
   
    def project_and_convert_to_display_coordinates(self, polygon):
        
        display_polygon = []
        for point in polygon:
            projected_points = self.project(point)
            projected_points[0], projected_points[1] = self.convertToDisplayCoordinates(projected_points)
        
            # Make every coordinate in projected_points a rounded float
            display_polygon.append([float(round(projected_points[0])), float(round(projected_points[1])), projected_points[2]])
            
            # display_polygon.append(list(map(lambda x: float(round(x, 0)), projected_points)))
        
        
        
        return display_polygon
    
   
    def compute_edge_table(self, polygon):
    
    
       

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
    
    def fill_polygon(self, poly, color):
        polygon = copy.deepcopy(poly)
        
        if not Matrix_Calculations.back_face_culling_algorithm(self.viewpoint, polygon):
            return

        displayPolygon = self.project_and_convert_to_display_coordinates(polygon)
        # print(displayPolygon)
        
        edge_table = self.compute_edge_table(displayPolygon)
        
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
        
        for line_rows in range(int(first_fill_line), int(last_fill_line)):
            
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
                try:
                    dZ_fill_line = (right_z_edge-left_z_edge)/(right_edge - left_edge)
                except:
                    dZ_fill_line = 0
            else:
                dZ_fill_line = 0

            # For each x from left_edge to right_edge, draw a line
            for x in range(int(left_edge), int(right_edge)):
                
                
                # if(z_value >= self.z_buffer[line_rows][x]):
                    # print(z_value, self.z_buffer[line_rows][x])
                if z_value < self.z_buffer[x][line_rows]:

                    self.canvas.create_line(x, line_rows, x + 1, line_rows, fill=color)
                    self.z_buffer[x][line_rows] = z_value
                z_value = z_value + dZ_fill_line
                
            # Update x-values and z-values with dX and Dz
            current_edge_x = current_edge_x + edge_table["Edge {}".format(i)][3]
            current_edge_2_x = current_edge_2_x + edge_table["Edge {}".format(j)][3]
            
            current_edge_z = current_edge_z + edge_table["Edge {}".format(i)][5]
            current_edge_2_z = current_edge_2_z + edge_table["Edge {}".format(j)][5]
            
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

    
    def get_z_buffer(self):
        
        # Create z buffer array based on canvas width and height
        # Initialize with max distance which is 500 in this setup
        return [[500 for x in range(self.canvas_width)] for y in range(self.canvas_height)]
        
     
    # Redraw canvas with all objects except the currently selected object
    def redraw_canvas(self):
        
        self.canvas.delete("all")
        
        # Draw the rest of the objects except the current object
        for object in self.object_list:
            if(object != self.current_object):
                for line in object["Lines"]:
                    for item in line:
                        self.canvas.delete(item)
                    
                    object["Lines"] = self.drawObject(object["Polyhedron"], False)
        
        
        # Draw the current object
        self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True)

    
    def set_render_mode(self, event):
        
        # If 1 is pressed then the render mode is wireframe
        if event.char == "1":
            
            self.render_mode_index = 0
            self.current_render_mode = self.render_modes[self.render_mode_index]
            self.z_buffer = self.get_z_buffer()
            self.redraw_canvas()

        # If 2 is pressed then the render mode is Polygon_Fill_Edges
        elif event.char == "2":
            self.render_mode_index = 1
            self.current_render_mode = self.render_modes[self.render_mode_index]
            self.z_buffer = self.get_z_buffer()
            self.redraw_canvas()
            
        elif event.char == "3":
            
            self.render_mode_index = 2
            self.current_render_mode = self.render_modes[self.render_mode_index]
            self.z_buffer = self.get_z_buffer()
            self.redraw_canvas()