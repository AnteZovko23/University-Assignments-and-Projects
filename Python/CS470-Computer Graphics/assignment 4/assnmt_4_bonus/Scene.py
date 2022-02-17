# Tkinter scene class
from tkinter import *
import copy
import Matrix_Calculations
import time

"""
Author: Ante Zovko
Date: January 31, 2022

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
    def __init__(self, canvas_width, canvas_height, viewpoint, illumination_model):
        
        # Initialize the canvas
        self.root = Tk()
        outerframe = Frame(self.root)
        outerframe.pack()
        
        self.canvas_width = canvas_width
        self.canvas_height = canvas_height
        self.viewpoint = viewpoint
        self.canvas = Canvas(outerframe, width=self.canvas_width, height=self.canvas_height)
        self.canvas.pack()
        self.drawing_mode = True
        
        # Z buffer
        self.z_buffer = self.get_z_buffer()
        
        # Render modes
        self.render_modes = ["Wireframe", "Polygon_Fill_Edges", "Polygon_Fill", "Flat Shading", "Gouraud Shading", "Phong Shading"]
        self.render_mode_index = 1
        self.current_render_mode = self.render_modes[self.render_mode_index]
        
        # Illumination Model
        self.illumination_model = illumination_model
        
        # Initialize the current object
        self.object_list = []
        self.current_object = None
        
        # Initialize all of the buttons and their functions
        controlpanel = Frame(outerframe)
        controlpanel.pack()

        resetcontrols = Frame(controlpanel, height=100, borderwidth=2, relief=RIDGE)
        resetcontrols.pack(side=LEFT)

        resetcontrolslabel = Label(resetcontrols, text="Controls")
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
        
        # Add slider
        self.render_speed = 0.005
        
        speed_controls_frame = Label(controlpanel, borderwidth=2, relief=RIDGE)
        speed_controls_frame.pack(side=LEFT)
        
        speed_controls_label = Label(speed_controls_frame, text="Line Trace Speed")
        speed_controls_label.pack()

        
        self.slider = Scale(speed_controls_frame, digits = 5, resolution = 0.00001, from_=0.00001, to=0.01, orient=HORIZONTAL, command=self.update_slider_value)
        self.slider.set(self.render_speed)
        
        self.light_variables = []
        self.diffuse_constant_text = None
        self.specular_constant_text = None
        self.specular_index_text = None
        self.ambient_intensity_text = None
        self.point_light_intensity_text = None
        self.point_light_x_text = None
        self.point_light_y_text = None
        self.point_light_z_text = None
        self.distance_text = None
        
        self.slider.pack(side=LEFT)

        # Bind right and left arrow key to canvas
        self.root.bind('<Right>', self.select_next_object)
        self.root.bind('<Left>', self.select_previous_object)    
         
        # Bind number keys to select render mode
        self.root.bind('1', self.set_render_mode)
        self.root.bind('2', self.set_render_mode)   
        self.root.bind('3', self.set_render_mode)  
        self.root.bind('4', self.set_render_mode)  
        self.root.bind('5', self.set_render_mode)
        self.root.bind('6', self.set_render_mode)
        
        drawing_mode_btn = Button(resetcontrols, text="Change Line\nAlgorithm", command=self.toggle_drawing_mode)
        drawing_mode_btn.pack(side=LEFT)
        illumination_settings = Button(resetcontrols, text="Set Illumination\nSettings", command=self.new_window)
        illumination_settings.pack(side=LEFT)
        # Create checkbox
        self.is_checked = BooleanVar()
        self.toggle_line_rendering_checkbox = Checkbutton(resetcontrols, text="Show Line\nRendering", variable=self.is_checked, command=self.toggle_line_rendering)
        self.toggle_line_rendering_checkbox.pack(side=LEFT)
    
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

        # print(translation_matrix)        
        # Iterates through each polygon in the object
        for i in range(len(object)):
            
            # Using Matrix notation and composite matices in section 8
            new_coordinates = (Matrix_Calculations.matrix_multiplication(object[i], translation_matrix))

            object[i][0], object[i][1], object[i][2] = new_coordinates[0][0], new_coordinates[0][1], new_coordinates[0][2]
            # print("Old Coors")
            # print(object[i])
            # print("New Coors")
            # print(new_coordinates)
    
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
    def drawObject(self, object, selected=True, vertex_normal=None):
        
       
        lines = []
        poly_counter = 0
        # Iterates through each polygon in the object
        for poly in object:
            
            # If polygon is not visible, skip it
            if not Matrix_Calculations.back_face_culling_algorithm(self.viewpoint, poly):
                poly_counter += 1
                continue
            
                
            else:
                # Draws each polygon and append the id of the drawn line
                lines.append(self.drawPoly(poly, selected))
                obj = None
                
                for i in range(len(self.object_list)):
                    # Compare array with object array
                    if(self.object_list[i]["Polyhedron"] == object):
                        obj = self.object_list[i]

            
                # Get index of poly
                index = object.index(poly)
                
                # If fill polygon modes are selected
                if(self.current_render_mode != "Wireframe"):
                    vertex_normal_list = None
                    if vertex_normal is not None:
                        # Check if dictionary key is equal to polygon index
                        for key in vertex_normal.keys():
                            if(key == str(poly_counter)):
                                vertex_normal_list = vertex_normal[key]
                                break
   
                    self.fill_polygon(poly, obj["Colors"][index], vertex_normal_list)
                    
            poly_counter += 1
                    
                    
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
    draw the actual line using the built-in create_line method or bresenham's line drawing algorithm
    
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
        if(self.current_render_mode != "Polygon_Fill" and self.current_render_mode != "Fill_Tracing" and self.current_render_mode != "Flat Shading" and self.current_render_mode != "Gouraud Shading" and self.current_render_mode != "Phong Shading"):
            if selected:
                color = "red"
            else:
                color = "black"
                
            # Redefine the variable names for clarity    
            x1, y1 = convertToDisplayCoordinates_start[0], convertToDisplayCoordinates_start[1]
            x2, y2 = convertToDisplayCoordinates_end[0], convertToDisplayCoordinates_end[1]
            z1 = project_start[2]
            z2 = project_end[2]

            # Check drawing mode -> self implemented bresenham's algorithm or built-in create_line
            if(self.drawing_mode):
                return self.canvas.create_line(x1, y1, x2, y2, fill=color, width=2)
            
            else:
                
                """
                Implementation of bresenham's line drawing algorithm by Ante Zovko
                Source Material: https://www.youtube.com/watch?v=RGB-wlatStc
                """
                
                # Ensure that x1 < x2 and y1 < y2
                if y1 > y2:
                    x1, x2 = x2, x1
                    y1, y2 = y2, y1
                    z1, z2 = z2, z1

                
                # Make all integers
                x1, y1, x2, y2, z1, z2 = int(round(x1)), int(round(y1)), int(round(x2)), int(round(y2)), int(round(z1)), int(round(z2))

                x = x1
                y = y1
                z = z1
                
                # Dx, Dy, Dz are the differences between x1 and x2, y1 and y2, and z1 and z2
                dx = x2 - x1
                dy = y2 - y1
                dz = z2 - z1
                
                # If divided by 0 then set to infinity
                try:
                    m = dy/dx
                except ZeroDivisionError:
                    m = "inf"
                    inverse_m = "inf"

                try:
                    inverse_m = dx/dy
                except ZeroDivisionError:
                    inverse_m = "inf"
                    
                try:
                    change_z = dz/dy
                except ZeroDivisionError:
                    change_z = 1
                
                # Print debugging info
                # print("x1: ", x1, "y1: ", y1, "x2: ", x2, "y2: ", y2, "z1: ", z1, "z2: ", z2, "dx: ", dx, "dy: ", dy, "dz: ", dz, "m: ", m, "inverse_m: ", inverse_m, "change_z: ", change_z)
                
                z_value = z1
                
                """
                Check different cases for the slope (m or inverse_m is inf, m < 1, m > 1, and m = 1)
                """
                if m == "inf" or inverse_m == "inf":
                    # Case 1: vertical line -> m = inf
                    if m == "inf":
                        # Iterate through y values
                        while (y != y2):
                            # increment or decrement y based on direction of line
                            y = y + 1 if y1 < y2 else y - 1
                            
                            # Keep between canvas bounds
                            try:
                                # Check z buffer
                                if z_value < self.z_buffer[x][y]:
                                    self.canvas.create_line(x,y,x+1,y+1, fill=color, width=3)
                                    
                                    # Check if rendering mode is tracing
                                    if self.is_checked.get():
                                        time.sleep(self.render_speed)
                                        self.canvas.update()
                                    
                                    # Update z buffer
                                    self.z_buffer[x][y] = z_value
                                    
                                # Change z value
                                z_value = z_value + change_z
                            # If index out of bounds, just move on
                            except IndexError:
                                pass
                    # Case 2: horizontal line -> inverse_m = inf
                    else:
                        # self.canvas.create_line(x,y,x+1,y+1, fill=color, width=3)
                        # Iterate through x values
                        while (x != x2):
                            # Change x value depending on direction of line
                            x = x + 1 if x2 > x1 else x - 1
                            
                            # Keep between canvas bounds
                            try:
                                # Check z buffer
                                if z_value < self.z_buffer[x][y]:
                                    self.canvas.create_line(x,y,x+1,y+1, fill=color, width=3)
                                    # Check if rendering mode is tracing
                                    if self.is_checked.get():
                                        time.sleep(self.render_speed)
                                        self.canvas.update()
                                        
                                    # Update z buffer
                                    self.z_buffer[x][y] = z_value
                                
                                # Change z value
                                z_value = z_value + change_z
                            # If index out of bounds, just move on
                            except IndexError:
                                pass
                

                elif abs(m) < 1:
                    
                    # Calculate decision parameter
                    p = 2 * dy - dx

                    # Iterate through x values
                    while (x != x2):
                        
                        # Change x value depending on direction of line
                        if x1 > x2:
                            x = x - 1
                        else:
                            x = x + 1            
                        
                        # Update decision parameter
                        if (p < 0):
                            p = p + 2 * dy   
                        else:
                            if m < 0:
                                p = p + 2 * dy + 2 * dx
                            else:
                                p = p + 2 * dy - 2 * dx
                             
                            # Update y value   
                            y = y + 1
                        
                        # Keep between canvas bounds
                        try:   
                            # print(x,y,z_value)
                            # print(self.z_buffer[x][y])
                            # Check z buffer
                            if z_value < self.z_buffer[x][y]:        
                                self.canvas.create_line(x,y,x+1,y+1, fill=color, width=3)
                                
                                # Check if rendering mode is tracing
                                if self.is_checked.get():
                                    time.sleep(self.render_speed)
                                    self.canvas.update()
                                    
                                # Update z buffer
                                self.z_buffer[x][y] = z_value
                            
                            # Change z value
                            z_value = z_value + change_z
                        # If index out of bounds, just move on
                        except IndexError:
                            pass 
                            
                elif abs(m) > 1:
                        
                        # Calculate decision parameter
                        p = 2 * dx - dy

                        # Iterate through y values
                        while (y != y2):
                        
                            # print(p)
                            # Change y value depending on direction of line
                            if y1 > y2:
                                y = y - 1
                            else:
                                y = y + 1    
                            
                            # Update decision parameter
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
                            
                            # Keep between canvas bounds
                            try:
                                # Check z buffer
                                if z_value < self.z_buffer[x][y]:  
                                    self.canvas.create_line(x,y,x+1,y+1, fill=color, width=3)
                                    # Check if rendering mode is tracing
                                    if self.is_checked.get():
                                        time.sleep(self.render_speed)
                                        self.canvas.update()
                                    # Update z buffer
                                    self.z_buffer[x][y] = z_value
                                # Change z value
                                z_value = z_value + change_z
                            # If index out of bounds, just move on
                            except IndexError:
                                pass
                            
                elif abs(m) == 1:
                    # self.canvas.create_line(x,y,x2,y2, fill="red")
                    # self.canvas.create_line(x,y,x+1,y+1, fill=color, width=3)
                    
                    # Iterate through x values
                    while (x != x2):
                        
                        # Change x value depending on direction of line
                        if m < 0:
                            x = x - 1
                        else:
                            x = x + 1
                        
                        # Update y value
                        y = y + 1
                        
                        # Keep between canvas bounds
                        try:
                            # Check z buffer
                            if z_value < self.z_buffer[x][y]:
                                self.canvas.create_line(x,y,x+1,y+1, fill=color, width=3)
                                # Check if rendering mode is tracing
                                if self.is_checked.get():
                                    time.sleep(self.render_speed)
                                    self.canvas.update()
                                # Update z buffer
                                self.z_buffer[x][y] = z_value
                            # Change z value
                            z_value = z_value + change_z
                        # If index out of bounds, just move on
                        except IndexError:
                            pass
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
    def add_object(self, polyhedron, point_cloud, colors, vertex_normals=None):
        
        # If there is no object in the scene then skip else delete the current object
        if(self.current_object != None):
            for line in self.current_object["Lines"]:
                for item in line:
                    self.canvas.delete(item)
          
            # Redraw with black lines      
            self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], False, self.current_object["Vertex_Normals"])
        
        
        # Append to the list of objects
        self.object_list.append({"Polyhedron": polyhedron, "PointCloud": point_cloud, "DefaultPointCloud": copy.deepcopy(point_cloud), "Lines": [], "Colors": colors, "Vertex_Normals": vertex_normals})
        # Current object is the last one added
        self.current_object = self.object_list[-1]
        try:
            current_vertex_normals = Matrix_Calculations.calculate_vertex_normals(self.current_object["Polyhedron"])
            self.current_object["Vertex_Normals"] = current_vertex_normals
        except:
            pass
        
        # Draw the current object with red lines
        current_lines = self.drawObject(self.current_object["Polyhedron"], True, self.current_object["Vertex_Normals"])
        self.current_object["Lines"] = current_lines
        # Update canvas
        self.canvas.update()
    
    # **************************************************************************
    # Everything below this point implements the interface
      
    """
    Selects the previous object in the list of objects
    """
    def select_previous_object(self, event):
        
        
        # Select previous in list
        self.current_object = self.object_list[(self.object_list.index(self.current_object) - 1) % len(self.object_list)]
        
        # Reset z-buffer
        self.z_buffer = self.get_z_buffer()
        
        # Redraw canvas
        self.redraw_canvas()
      
        
    
    """
    Selects the next object in the list of objects
    """
    def select_next_object(self, event):
        
        # Select next in list
        self.current_object = self.object_list[(self.object_list.index(self.current_object) + 1) % len(self.object_list)]
        
        # Reset z-buffer
        self.z_buffer = self.get_z_buffer()
    
        # Redraw canvas
        self.redraw_canvas()
        
        

    def reset(self):
        
        # Wipe the canvas
        self.canvas.delete("all")
        
        # Reset z buffer
        self.z_buffer = self.get_z_buffer()
        
        # Reset polyhedron
        self.reset_polyhedron(self.current_object["PointCloud"], self.current_object["DefaultPointCloud"])

        # Redraw canvas
        self.redraw_canvas()

    def larger(self):


        self.z_buffer = self.get_z_buffer()

            
        self.scale(self.current_object["PointCloud"], 1.1)

        self.redraw_canvas()
        
    def smaller(self):


        self.z_buffer = self.get_z_buffer()

            
        self.scale(self.current_object["PointCloud"], 0.9)

        self.redraw_canvas()

    def forward(self):
        

        self.z_buffer = self.get_z_buffer()

            
        self.translate(self.current_object["PointCloud"],[0,0,5])

        self.redraw_canvas()

    def backward(self):
        
        # self.canvas.delete("all")

        self.z_buffer = self.get_z_buffer()
            
        self.translate(self.current_object["PointCloud"],[0,0,-5])

        self.redraw_canvas()

    def left(self):
        

        self.z_buffer = self.get_z_buffer()

              
            
        self.translate(self.current_object["PointCloud"],[-5,0,0])

        self.redraw_canvas()

    def right(self):


        self.z_buffer = self.get_z_buffer()

            
        self.translate(self.current_object["PointCloud"],[5,0,0])

        self.redraw_canvas()

    def up(self):


        self.z_buffer = self.get_z_buffer()

            
        self.translate(self.current_object["PointCloud"],[0,5,0])

        self.redraw_canvas()

    def down(self):


        self.z_buffer = self.get_z_buffer()

            
        self.translate(self.current_object["PointCloud"],[0,-5,0])

        self.redraw_canvas()

    def xPlus(self):
        

        self.z_buffer = self.get_z_buffer()

            
        self.rotateX(self.current_object["PointCloud"], 5)

        self.redraw_canvas()

    def xMinus(self):


        self.z_buffer = self.get_z_buffer()

            
        self.rotateX(self.current_object["PointCloud"], -5)

        self.redraw_canvas()

    def yPlus(self):
        

        self.z_buffer = self.get_z_buffer()

            
        self.rotateY(self.current_object["PointCloud"], 5)
        
        self.redraw_canvas()
        
    def yMinus(self):
        

        self.z_buffer = self.get_z_buffer()

            
        self.rotateY(self.current_object["PointCloud"], -5)

        self.redraw_canvas()

    def zPlus(self):
        

        self.z_buffer = self.get_z_buffer()

            
        self.rotateZ(self.current_object["PointCloud"], 5)

        self.redraw_canvas()

    def zMinus(self):
        

        self.z_buffer = self.get_z_buffer()

            
        self.rotateZ(self.current_object["PointCloud"], -5)

        self.redraw_canvas()

   # **************************************************************************
   
   
   # ASSIGNMENT 3
   
   # **************************************************************************

    """
    Given a polygon, it projects each point and converts X and Y to display coordinates
    """
    def project_and_convert_to_display_coordinates(self, polygon):
        
        display_polygon = []
        
        # For each point in the polygon
        for point in polygon:
            
            # Project
            projected_points = self.project(point)
            
            # Convert to display coordinates
            projected_points[0], projected_points[1] = self.convertToDisplayCoordinates(projected_points)
        
            # Make X and Y coordinate in projected_points a rounded float
            display_polygon.append([float(round(projected_points[0])), float(round(projected_points[1])), projected_points[2]]) 
        
        return display_polygon
    
   
   
    """
    Given a polygon, it computes an edge table that contains: [x_start, y_start, y_end, dX, z_start, dZ]
    """
    def compute_edge_table(self, polygon, given_vertex_normal):
        
        phong_vertex_normals = copy.deepcopy(given_vertex_normal)
        vertex_normal = copy.deepcopy(given_vertex_normal)
        
        if vertex_normal != None and len(polygon) != 8:
            # Convert vertex normals to intensities
            for i in range(len(vertex_normal)):
                self.illumination_model.set_surface_normal(vertex_normal[i])
                vertex_normal[i] = [self.illumination_model.get_diffuse_component(), self.illumination_model.get_specular_component()]
            # Define all edges with the smaller y being the first edge and skip if the edge is a horizontal line (same y value)
            edges = []
            for i in range(len(polygon) - 1):
                    
                # The smaller y goes first
                if(polygon[i][1] < polygon[i+1][1]):
                    edges.append([polygon[i],polygon[i+1], vertex_normal[i], vertex_normal[i+1], phong_vertex_normals[i], phong_vertex_normals[i+1]])
                elif(polygon[i][1] > polygon[i+1][1]):
                    edges.append([polygon[i+1],polygon[i], vertex_normal[i+1], vertex_normal[i], phong_vertex_normals[i+1], phong_vertex_normals[i]])
                else:
                    continue
            
            # Append last and first edge with the smaller y going first
            if(polygon[0][1] < polygon[len(polygon)-1][1]):
                edges.append([polygon[0],polygon[len(polygon)-1], vertex_normal[0], vertex_normal[len(polygon)-1], phong_vertex_normals[0], phong_vertex_normals[len(polygon)-1]])
            elif(polygon[0][1] > polygon[len(polygon)-1][1]):
                edges.append([polygon[len(polygon)-1],polygon[0], vertex_normal[len(polygon)-1], vertex_normal[0], phong_vertex_normals[len(polygon)-1], phong_vertex_normals[0]])
                
        else:
            # Define all edges with the smaller y being the first edge and skip if the edge is a horizontal line (same y value)
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
        # For each edge, compute _start, y_start, y_end, dX, z_start, dZ, intensity__diffuse_start_edge, dIntensity_diffuse, intensity__specular_start_edge, dIntensity_specular
        # , normal_start_edge_x, dNormal_x, normal_start_edge_y, dNormal_y, normal_start_edge_z, dNormal_z
        counter = 0
        # print()
        # print(edges)       

        # Iterate over each edge to find values for the table
        for edge in edges:
            x_start = edge[0][0]
            y_start = edge[0][1]
            y_end = edge[1][1]
            z_start = edge[0][2]
            try:
                intensity__diffuse_start_edge = edge[2][0]
                intensity__specular_start_edge = edge[2][1]
                normal_start_edge_x = edge[4][0]
                normal_start_edge_y = edge[4][1]
                normal_start_edge_z = edge[4][2]
                
            except:
                intensity__diffuse_start_edge = None
                intensity__specular_start_edge = None
                normal_start_edge_x = None
                normal_start_edge_y = None
                normal_start_edge_z = None
            
            # In case of 0 in denominator
            try:
                dX = (edge[1][0] - edge[0][0]) / (edge[1][1] - edge[0][1])
                dZ = (edge[1][2] - edge[0][2]) / (edge[1][1] - edge[0][1])
            except:
                dX = 0
                dZ = 0
                
            dIntensity_diffuse = None
            dIntensity_specular = None
            dNormal_x = None
            dNormal_y = None
            dNormal_z = None
            try:
                dIntensity_diffuse = (edge[3][0] - edge[2][0]) / (edge[1][1] - edge[0][1])
                dIntensity_specular = (edge[3][1] - edge[2][1]) / (edge[1][1] - edge[0][1])
                dNormal_x = (edge[5][0] - edge[4][0]) / (edge[1][1] - edge[0][1])
                dNormal_y = (edge[5][1] - edge[4][1]) / (edge[1][1] - edge[0][1])
                dNormal_z = (edge[5][2] - edge[4][2]) / (edge[1][1] - edge[0][1])

            except:
                pass
            # Add information to edge table
            edge_table["Edge {}".format(counter)] = [x_start, y_start, y_end, dX, z_start, dZ, intensity__diffuse_start_edge, dIntensity_diffuse, intensity__specular_start_edge, dIntensity_specular
                                                     , normal_start_edge_x, dNormal_x, normal_start_edge_y, dNormal_y, normal_start_edge_z, dNormal_z]
            
            counter+=1

        # Sort edge dictionary by increasing y_start values
        edge_table = dict(sorted(edge_table.items(), key=lambda x: x[1][1]))

        updated_edge_table = {}
        counter = 0
        
        
        # Rename keys of edge table dictionary based on position
        for edge_table_item in edge_table.values():
               
            updated_edge_table["Edge {}".format(counter)] = edge_table_item
    
            counter += 1

        
        
        
        return updated_edge_table

    """
    Given a polygon and color, it uses the polygon fill algorithm described on page 65 of the textbook
    Starting at the lowest y value, it determines the left and right edge and then for every row of pixels it paints the pixels between the edges
    
    This algorithm uses backface culling to determine eliminate faces the "camera" cannot see and z buffering to determine if the pixel that is about to be drawn is behind another pixel that already exists
    
    
    """
    def fill_polygon(self, poly, color, vertex_normals):
        illumination_color = color
        # Deep copy so the original polygon is not modified
        polygon = copy.deepcopy(poly)
        
        # If the polygon is not visible then no need to fill it
        if not Matrix_Calculations.back_face_culling_algorithm(self.viewpoint, polygon):
            return

        # Project and convert to display coordinates
        displayPolygon = self.project_and_convert_to_display_coordinates(polygon)

        # Compute edge table        
        edge_table = self.compute_edge_table(displayPolygon, vertex_normals)
        
        # If edge table is empty or has only one edge, return
        if len(edge_table) == 0 or len(edge_table) == 1:
            return

        # Get all Y_start values from edge table
        y_start_values = list(map(lambda x: edge_table[x][1], edge_table))
        
        # Get all Y_end values from edge table
        y_end_values = list(map(lambda x: edge_table[x][2], edge_table))
        
        
        # Find smallest y_start value and largest y_end value
        first_fill_line = min(y_start_values)
        last_fill_line = max(y_end_values)
        

        # Initialize counter variables
        i, j, next_x = 0, 1, 2
        
        # Set first two edges' x-coordinates
        # Get i-th edge's x-coordinate
        current_edge_x = edge_table.get("Edge {}".format(i))[0]
        current_edge_2_x = edge_table["Edge {}".format(j)][0]

        # Set first two edges' z-coordinates
        current_edge_z = edge_table.get("Edge {}".format(i))[4]
        current_edge_2_z = edge_table.get("Edge {}".format(j))[4]
        
        # Set first two edges' intensity_diffuse values
        current_edge_intensity_diffuse = edge_table.get("Edge {}".format(i))[6]
        current_edge_2_intensity_diffuse = edge_table.get("Edge {}".format(j))[6]
        
        # Set first two edges' intensity_specular values
        current_edge_intensity_specular = edge_table.get("Edge {}".format(i))[8]
        current_edge_2_intensity_specular = edge_table.get("Edge {}".format(j))[8]
        
        # Set first two edges' normal_x values
        current_edge_normal_x = edge_table.get("Edge {}".format(i))[10]
        current_edge_2_normal_x = edge_table.get("Edge {}".format(j))[10]
        
        # Set first two edges' normal_y values
        current_edge_normal_y = edge_table.get("Edge {}".format(i))[12]
        current_edge_2_normal_y = edge_table.get("Edge {}".format(j))[12]
        
        # Set first two edges' normal_z values
        current_edge_normal_z = edge_table.get("Edge {}".format(i))[14]
        current_edge_2_normal_z = edge_table.get("Edge {}".format(j))[14]
        
        
        # For each row of pixels
        for line_rows in range(int(first_fill_line), int(last_fill_line)):
            
            # Initialize left and right edges
            left_edge = None
            right_edge = None
            
            left_z_edge = None
            right_z_edge = None
            
            left_intensity_edge_diffuse = None
            right_intensity_edge_diffuse = None
            
            left_intensity_edge_specular = None
            right_intensity_edge_specular = None
            
            left_normal_x_edge = None
            right_normal_x_edge = None
            
            left_normal_y_edge = None
            right_normal_y_edge = None
            
            left_normal_z_edge = None
            right_normal_z_edge = None
            
            # Determine which edge is Left and which is Right
            if current_edge_x < current_edge_2_x:
                left_edge = current_edge_x
                right_edge = current_edge_2_x
                
                left_z_edge = current_edge_z
                right_z_edge = current_edge_2_z
                
                left_intensity_edge_diffuse = current_edge_intensity_diffuse
                right_intensity_edge_diffuse = current_edge_2_intensity_diffuse

                left_intensity_edge_specular = current_edge_intensity_specular
                right_intensity_edge_specular = current_edge_2_intensity_specular
                
                left_normal_x_edge = current_edge_normal_x
                right_normal_x_edge = current_edge_2_normal_x

                left_normal_y_edge = current_edge_normal_y
                right_normal_y_edge = current_edge_2_normal_y
                
                left_normal_z_edge = current_edge_normal_z
                right_normal_z_edge = current_edge_2_normal_z
                
            else:
                left_edge = current_edge_2_x
                right_edge = current_edge_x
                
                left_z_edge = current_edge_2_z
                right_z_edge = current_edge_z
                
                left_intensity_edge_diffuse = current_edge_2_intensity_diffuse
                right_intensity_edge_diffuse = current_edge_intensity_diffuse
                
                left_intensity_edge_specular = current_edge_2_intensity_specular
                right_intensity_edge_specular = current_edge_intensity_specular
                
                left_normal_x_edge = current_edge_2_normal_x
                right_normal_x_edge = current_edge_normal_x
                
                left_normal_y_edge = current_edge_2_normal_y
                right_normal_y_edge = current_edge_normal_y
                
                left_normal_z_edge = current_edge_2_normal_z
                right_normal_z_edge = current_edge_normal_z
                
            # The initial Z for the current fill line
            z_value = left_z_edge
            dZ_fill_line = 0
            
            # The initial intensity for the current fill line
            intensity_value_diffuse = left_intensity_edge_diffuse
            dIntensity_fill_line_diffuse = 0
            
            intensity_value_specular = left_intensity_edge_specular
            dIntensity_fill_line_specular = 0
            
            normal_x_value = left_normal_x_edge
            dNormal_x_fill_line = 0
            
            normal_y_value = left_normal_y_edge
            dNormal_y_fill_line = 0
            
            normal_z_value = left_normal_z_edge
            dNormal_z_fill_line = 0
            
            # Compute dZ for the fill line. Can be 0 if line is 1 pixel long
            if (right_z_edge - left_z_edge) != 0:
                try:
                    dZ_fill_line = (right_z_edge-left_z_edge)/(right_edge - left_edge)
                except:
                    dZ_fill_line = 0
            else:
                dZ_fill_line = 0
            
            
            
            try:    
                # Compute dIntensity for the fill line. Can be 0 if line is 1 pixel long
                if (right_intensity_edge_diffuse - left_intensity_edge_diffuse) != 0:
                    try:
                        dIntensity_fill_line_diffuse = (right_intensity_edge_diffuse-left_intensity_edge_diffuse)/(right_edge - left_edge)
                    except:
                        dIntensity_fill_line_diffuse = 0
                else:
                    dIntensity_fill_line_diffuse = 0
                    
                if (right_intensity_edge_specular - left_intensity_edge_specular) != 0:
                    try:
                        dIntensity_fill_line_specular = (right_intensity_edge_specular-left_intensity_edge_specular)/(right_edge - left_edge)
                        
                    except:
                        dIntensity_fill_line_specular = 0
                        
                else:
                    dIntensity_fill_line_specular = 0
            except:
                pass
            
            
            
            try:
                
                # Compute dNormal_x for the fill line. Can be 0 if line is 1 pixel long
                if (right_normal_x_edge - left_normal_x_edge) != 0:
                    try:
                        dNormal_x_fill_line = (right_normal_x_edge-left_normal_x_edge)/(right_edge - left_edge)
                        
                    except:
                        dNormal_x_fill_line = 0
                        
                else:
                    dNormal_x_fill_line = 0
                    
                # Compute dNormal_y for the fill line. Can be 0 if line is 1 pixel long
                if (right_normal_y_edge - left_normal_y_edge) != 0:
                    try:
                        dNormal_y_fill_line = (right_normal_y_edge-left_normal_y_edge)/(right_edge - left_edge)
                        
                    except:
                        dNormal_y_fill_line = 0
                        
                else:
                    
                    dNormal_y_fill_line = 0
                    
                # Compute dNormal_z for the fill line. Can be 0 if line is 1 pixel long
                if (right_normal_z_edge - left_normal_z_edge) != 0:
                    try:
                        dNormal_z_fill_line = (right_normal_z_edge-left_normal_z_edge)/(right_edge - left_edge)
                        
                    except:
                        dNormal_z_fill_line = 0
                        
                else:
                    dNormal_z_fill_line = 0
                    
            except:
                pass
            
                
            

            # For each x from left_edge to right_edge, draw a line
            for x in range(int(left_edge), int(right_edge)):
                
                # Check the z buffer to see if the pixel is behind another pixel
                try:
                    if z_value < self.z_buffer[x][line_rows]:
                        
                        if(self.current_render_mode == "Flat Shading"):
                            # Update surface normal in the illumination model
                            self.illumination_model.set_surface_normal(Matrix_Calculations.get_surface_normal(poly))
                            
                            # Get illumination color
                            illumination_color = self.illumination_model.get_hexcode()
                        
                        elif(self.current_render_mode == "Gouraud Shading"):
                            # Get color based on current intensity
                            if(len(poly) == 8):
                                self.illumination_model.set_surface_normal(Matrix_Calculations.get_surface_normal(poly))
                                illumination_color = self.illumination_model.get_hexcode()
                            else:
                                illumination_color = self.illumination_model.get_hexcode_intensity(self.illumination_model.get_ambient_component(), intensity_value_diffuse, intensity_value_specular)
                        
                        elif(self.current_render_mode == "Phong Shading"):
                            # Get color based on current intensity
                            if(len(poly) == 8):
                                self.illumination_model.set_surface_normal(Matrix_Calculations.get_surface_normal(poly))
                                illumination_color = self.illumination_model.get_hexcode()
                                
                            else:
                                # Get surface normal based on interpolated values
                                current_normal = [normal_x_value, normal_y_value, normal_z_value]
                                
                                # Update illumination model
                                self.illumination_model.set_surface_normal(current_normal)
                                
                                illumination_color = self.illumination_model.get_hexcode()
                                
                                 
                        # Draw pixel
                        self.canvas.create_line(x, line_rows, x + 1, line_rows, fill=illumination_color)
                        
                        
                        # If Fill_Tracing is enabled, pause current thread and then contine
                        # if self.current_render_mode == "Fill_Tracing":
                        # If checked
                        if self.is_checked.get():
                            time.sleep(self.render_speed)
                            self.canvas.update()
                            
                        self.z_buffer[x][line_rows] = z_value
                    z_value = z_value + dZ_fill_line
                    try:
                        intensity_value_diffuse = intensity_value_diffuse + dIntensity_fill_line_diffuse
                        intensity_value_specular = intensity_value_specular + dIntensity_fill_line_specular
                        
                        normal_x_value = normal_x_value + dNormal_x_fill_line
                        normal_y_value = normal_y_value + dNormal_y_fill_line
                        normal_z_value = normal_z_value + dNormal_z_fill_line
                        
                    except:
                        pass
                # If its painting at the edge just ignore and move on 
                except IndexError:
                    pass
                    
            # Update x-values and z-values with dX and Dz
            current_edge_x = current_edge_x + edge_table["Edge {}".format(i)][3]
            current_edge_2_x = current_edge_2_x + edge_table["Edge {}".format(j)][3]
            
            current_edge_z = current_edge_z + edge_table["Edge {}".format(i)][5]
            current_edge_2_z = current_edge_2_z + edge_table["Edge {}".format(j)][5]

            # Update intensity values with dIntensity
            try:
                current_edge_intensity_diffuse = current_edge_intensity_diffuse + edge_table["Edge {}".format(i)][7]
                current_edge_2_intensity_diffuse = current_edge_2_intensity_diffuse + edge_table["Edge {}".format(j)][7]
            
                current_edge_intensity_specular = current_edge_intensity_specular + edge_table["Edge {}".format(i)][9]
                current_edge_2_intensity_specular = current_edge_2_intensity_specular + edge_table["Edge {}".format(j)][9]

                # Update normal values with dNormal
                current_edge_normal_x = current_edge_normal_x + edge_table["Edge {}".format(i)][11]
                current_edge_2_normal_x = current_edge_2_normal_x + edge_table["Edge {}".format(j)][11]
                
                current_edge_normal_y = current_edge_normal_y + edge_table["Edge {}".format(i)][13]
                current_edge_2_normal_y = current_edge_2_normal_y + edge_table["Edge {}".format(j)][13]
                
                current_edge_normal_z = current_edge_normal_z + edge_table["Edge {}".format(i)][15]
                current_edge_2_normal_z = current_edge_2_normal_z + edge_table["Edge {}".format(j)][15]
            except:
                pass
            # When the bottom of an edge is reached, switch to the next edge
            if(line_rows >= edge_table["Edge {}".format(i)][2] and line_rows < last_fill_line):
                i = next_x
                current_edge_x = edge_table["Edge {}".format(i)][0]
                current_edge_z = edge_table["Edge {}".format(i)][4]
                current_edge_intensity_diffuse = edge_table["Edge {}".format(i)][6]
                current_edge_intensity_specular = edge_table["Edge {}".format(i)][8]
                
                current_edge_normal_x = edge_table["Edge {}".format(i)][10]
                current_edge_normal_y = edge_table["Edge {}".format(i)][12]
                current_edge_normal_z = edge_table["Edge {}".format(i)][14]
                next_x += 1
                
            if(line_rows >= edge_table["Edge {}".format(j)][2] and line_rows < last_fill_line):
                j = next_x
                current_edge_2_x = edge_table["Edge {}".format(j)][0]
                current_edge_2_z = edge_table["Edge {}".format(j)][4]
                current_edge_2_intensity_diffuse = edge_table["Edge {}".format(j)][6]
                current_edge_2_intensity_specular = edge_table["Edge {}".format(j)][8]

                current_edge_2_normal_x = edge_table["Edge {}".format(j)][10]
                current_edge_2_normal_y = edge_table["Edge {}".format(j)][12]
                current_edge_2_normal_z = edge_table["Edge {}".format(j)][14]
                next_x += 1

    
    """
    Generate a z buffer for the canvas with a maximum distance of 500
    """
    def get_z_buffer(self):
        
        # Create z buffer array based on canvas width and height
        # Initialize with max distance which is 500 in this setup
        # or 20000 for bresenham's algorithm
        if self.drawing_mode:
            return [[500 for x in range(self.canvas_width)] for y in range(self.canvas_height)]
        else:
            # Bug with increasing z values, easiest fix for now
            return [[20000 for x in range(self.canvas_width)] for y in range(self.canvas_height)]
     
    # Redraw canvas with all objects except the currently selected object
    def redraw_canvas(self):
        
        # Wipe canvas
        self.canvas.delete("all")
        
        # Draw the rest of the objects except the current object with black lines
        for object in self.object_list:
            if(object != self.current_object):
                for line in object["Lines"]:
                    for item in line:
                        self.canvas.delete(item)
                    
                    object["Lines"] = self.drawObject(object["Polyhedron"], False, object["Vertex_Normals"])
        
        
        # Draw the current object with red lines
        self.current_object["Lines"] = self.drawObject(self.current_object["Polyhedron"], True, object["Vertex_Normals"])

        # Compute the vertex normals if applicable
        try:
            current_vertex_normals = Matrix_Calculations.calculate_vertex_normals(self.current_object["Polyhedron"])
            self.current_object["Vertex_Normals"] = current_vertex_normals
        except:
            pass
    
    """
    Changes render modes based on the appropriate keyboard input
    """
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
            
        # If 3 is pressed then the render mode is Polygon_Fill
        elif event.char == "3":
            
            self.render_mode_index = 2
            self.current_render_mode = self.render_modes[self.render_mode_index]
            self.z_buffer = self.get_z_buffer()
            self.redraw_canvas()
            
        # If 4 is pressed then the render mode is Flat Shading
        elif event.char == "4":
            
            self.render_mode_index = 3
            self.current_render_mode = self.render_modes[self.render_mode_index]
            self.z_buffer = self.get_z_buffer()
            self.redraw_canvas()
            
        # If 5 is pressed then the render mode is Gouraud Shading
        elif event.char == "5":
            
            self.render_mode_index = 4
            self.current_render_mode = self.render_modes[self.render_mode_index]
            self.z_buffer = self.get_z_buffer()
            self.redraw_canvas()
        
        
        # If 6 is pressed then the render mode is Phong Shading        
        elif event.char == "6":
            
            self.render_mode_index = 5
            self.current_render_mode = self.render_modes[self.render_mode_index]
            self.z_buffer = self.get_z_buffer()
            self.redraw_canvas()
     
    """
    Changes between self implemented and built in line drawing
    """
    def toggle_drawing_mode(self):
        # If the current drawing mode is built in, change to self implemented
        self.drawing_mode = not self.drawing_mode
        self.z_buffer = self.get_z_buffer()
        self.redraw_canvas()
        
    
    """
    Toggles the fill tracing mode
    """
    def toggle_line_rendering(self):
        self.z_buffer = self.get_z_buffer()
        self.redraw_canvas()

    def submit_light_settings(self):
        # Update illumination model based on light variables
        self.light_variables = []
        try:
            self.light_variables.append(float(self.diffuse_constant_text.get()))
            self.light_variables.append(float(self.specular_constant_text.get()))
            self.light_variables.append(float(self.specular_index_text.get()))
            self.light_variables.append(float(self.ambient_intensity_text.get()))
            self.light_variables.append(float(self.point_light_intensity_text.get()))
            self.light_variables.append(float(self.point_light_x_text.get()))
            self.light_variables.append(float(self.point_light_y_text.get()))
            self.light_variables.append(float(self.point_light_z_text.get()))
            self.light_variables.append(float(self.distance_text.get()))
            
       
        
            self.illumination_model.set_diffuse_constant(self.light_variables[0])
            self.illumination_model.set_specular_constant(self.light_variables[1])   
            self.illumination_model.set_specular_index(self.light_variables[2])     
            self.illumination_model.set_ambient_intensity(self.light_variables[3])
            self.illumination_model.set_point_light_intensity(self.light_variables[4])
            light_vector = [self.light_variables[5], self.light_variables[6], self.light_variables[7]]
            self.illumination_model.set_light_vector(light_vector)
            self.illumination_model.set_distance(self.light_variables[8])

            self.z_buffer = self.get_z_buffer()
            self.redraw_canvas()
        
        except:
            pass
    """
    Creates new window for illumination settings
    """ 
    def new_window(self):
        
        self.light_settings_window = Toplevel(self.root)
        self.light_settings_window.title("Lighting Settings")
        self.light_settings_window.geometry("400x250")
        
        self.light_variables = []        
        
        # Create the widgets for the new window
        self.diffuse_constant_label = Label(self.light_settings_window, text="Diffuse Constant")
        self.diffuse_constant_label.grid(row=0, column=0)
        
        # Create text box for diffuse constant
        self.diffuse_constant_text = Entry(self.light_settings_window, width=10)
        # Set the default value to 0.5
        self.diffuse_constant_text.insert(0, "0.5")
        # Get the value from the text box and convert to float
        self.light_variables.append(float(self.diffuse_constant_text.get()))
        
        self.diffuse_constant_text.grid(row=0, column=1)

        # Create text box for specular constant
        self.specular_constant_label = Label(self.light_settings_window, text="Specular Constant")
        self.specular_constant_label.grid(row=1, column=0)
        
        self.specular_constant_text = Entry(self.light_settings_window, width=10)
        # Set the default value to 0.5
        self.specular_constant_text.insert(0, "0.5")
        # Get the value from the text box and convert to float
        self.light_variables.append(float(self.specular_constant_text.get()))
        
        self.specular_constant_text.grid(row=1, column=1)
        
        # Create text box for specular index
        self.specular_index_label = Label(self.light_settings_window, text="Specular Index")
        self.specular_index_label.grid(row=2, column=0)
        
        self.specular_index_text = Entry(self.light_settings_window, width=10)
        # Set the default value to 10
        self.specular_index_text.insert(0, "10")
        # Get the value from the text box and convert to float
        self.light_variables.append(float(self.specular_index_text.get()))
        
        self.specular_index_text.grid(row=2, column=1)
        
        # Create text box for ambient intensity
        self.ambient_intensity_label = Label(self.light_settings_window, text="Ambient Intensity")
        self.ambient_intensity_label.grid(row=3, column=0)
        
        self.ambient_intensity_text = Entry(self.light_settings_window, width=10)
        # Set the default value to 0.3
        self.ambient_intensity_text.insert(0, "0.3")
        # Get the value from the text box and convert to float
        self.light_variables.append(float(self.ambient_intensity_text.get()))
        
        self.ambient_intensity_text.grid(row=3, column=1)
        
        # Create text box for point light intensity
        self.point_light_intensity_label = Label(self.light_settings_window, text="Point Light Intensity")
        self.point_light_intensity_label.grid(row=4, column=0)
        
        self.point_light_intensity_text = Entry(self.light_settings_window, width=10)
        # Set the default value to 0.7
        self.point_light_intensity_text.insert(0, "0.7")
        # Get the value from the text box and convert to float
        self.light_variables.append(float(self.point_light_intensity_text.get()))
        
        self.point_light_intensity_text.grid(row=4, column=1)
        
        # Create 3 text boxes for the point light x y z coordinates
        self.point_light_x_label = Label(self.light_settings_window, text="Point Light X")
        self.point_light_x_label.grid(row=5, column=0)

        self.point_light_x_text = Entry(self.light_settings_window, width=10)
        # Set the default value to 1
        self.point_light_x_text.insert(0, "1")
        # Get the value from the text box and convert to float
        self.light_variables.append(float(self.point_light_x_text.get()))
        self.point_light_x_text.grid(row=5, column=1)
        
        self.point_light_y_label = Label(self.light_settings_window, text="Point Light Y")
        self.point_light_y_label.grid(row=6, column=0)
    
        self.point_light_y_text = Entry(self.light_settings_window, width=10)
        # Set the default value to 1
        self.point_light_y_text.insert(0, "1")
        # Get the value from the text box and convert to float
        self.light_variables.append(float(self.point_light_y_text.get()))
        self.point_light_y_text.grid(row=6, column=1)
        
        self.point_light_z_label = Label(self.light_settings_window, text="Point Light Z")
        self.point_light_z_label.grid(row=7, column=0)
        
        self.point_light_z_text = Entry(self.light_settings_window, width=10)
        # Set the default value to 1
        self.point_light_z_text.insert(0, "-1")
        # Get the value from the text box and convert to float
        self.light_variables.append(float(self.point_light_z_text.get()))
        
        self.point_light_z_text.grid(row=7, column=1)
        
        # Create text box for distance
        self.distance_label = Label(self.light_settings_window, text="Distance")
        self.distance_label.grid(row=8, column=0)
        
        self.distance_text = Entry(self.light_settings_window, width=10)
        # Set the default value to 1
        self.distance_text.insert(0, "1")
        # Get the value from the text box and convert to float
        self.light_variables.append(float(self.distance_text.get()))
        self.distance_text.grid(row=8, column=1)
   
        # Submit button
        self.submit_button = Button(self.light_settings_window, text="Enter", command=self.submit_light_settings)
        self.submit_button.grid(row=9, column=0)

    """
    Updates the current slider value and assigns it to the global render speed variable
    """
    def update_slider_value(self, event):
        self.render_speed = self.slider.get()
       