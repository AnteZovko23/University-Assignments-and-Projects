from tkinter import *
import Matrix_Calculations

"""
Author: Ante Zovko
Date: February 21st, 2022

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
    def __init__(self, canvas_width, canvas_height, viewpoint, sky_box_color):
        
        # Initialize the canvas
        self.root = Tk()
        outerframe = Frame(self.root)
        outerframe.pack()
        
        self.canvas_width = canvas_width
        self.canvas_height = canvas_height
        self.viewpoint = viewpoint
        self.canvas = Canvas(outerframe, width=self.canvas_width, height=self.canvas_height)
        self.canvas.pack()
        
        self.object_list = []
        self.sky_box_color = sky_box_color

    """
    Driver code for the trace ray function that iterates over every pixel
    """
    def render_image(self):
        
        # Start points
        top = round(self.canvas_height/2)
        bottom = round(-self.canvas_height/2)
        left = round(-self.canvas_width/2)
        right = round(self.canvas_width/2)
        
        # Iterate over every pixel
        for y in range(top, bottom, -1):
            for x in range(left, right, 1):
                # print("a")
                ray = Matrix_Calculations.compute_unit_vector(self.viewpoint, [x, y, 0])
                color = self.trace_ray(self.viewpoint, ray, 4)
                # Paint the pixel
                self.canvas.create_line(right+x, top-y, right+x+1, top-y, fill=self.get_hexcode_intensity(color))
    
    """
    Adds object to the scene
    """
    def ray_tracing_add_object(self, object):
        
        self.object_list.append(object)
    
    """
    Recursively traces a ray from the viewer to the scene and returns the color of the ray
    """
    def trace_ray(self, start_point, ray, depth):
        # Black if no intersection    
        if depth == 0: return [0, 0, 0]
        
        t_min = 999999 # Set to a large number
        
        # Iterate over all objects in the scene
        for object in self.object_list:
            
            # Check if the ray intersects with the object and using the t value, find nearest intersection
            if object.get_intersection(start_point=start_point, ray=ray) != []:
                if object.get_t_value() < t_min:
                    t_min = object.get_t_value()
                    nearest_object = object
                    
        # Return sky color if no intersection
        if t_min == 999999: return self.sky_box_color
        
        # Determine the local color and the weight of the object
        color = nearest_object.get_local_color()
        intersection_point = nearest_object.get_intersection_point()
        point_light_source = nearest_object.illumination_model.get_point_light_source()
        
        # Get light vector
        light_vector = Matrix_Calculations.compute_unit_vector(intersection_point, point_light_source)
        nearest_object.illumination_model.set_light_vector(light_vector)
        
        # Try to compute the surface normal, if it fails, the object is a plane
        try:
            # Get surface normal of a sphere based on a point and center
            surface_normal = Matrix_Calculations.compute_unit_vector(nearest_object.get_center_point(), intersection_point)
            nearest_object.set_surface_normal(surface_normal)
        except AttributeError:
            pass
        
        # Get intensity of the light source
        intensity = nearest_object.illumination_model.get_intensity()

        # Check if the object is in shadow
        if self.in_shadow(nearest_object, nearest_object.get_intersection_point(), nearest_object.illumination_model.get_point_light_source()):
            intensity *= 0.25
            
        # Compute local color
        local_color = [color[0] * intensity * 2, color[1] * intensity * 2, color[2] * intensity * 2]
        local_weight = nearest_object.get_weight()
        
        # Color returned from the ray
        reflection_weight = nearest_object.get_reflection_weight()
        reflection_color = [0, 0, 0]

        # Check if the object is reflective trace the reflection ray
        if(nearest_object.get_reflection_weight() > 0):
            reflection_color = self.trace_ray(nearest_object.get_intersection_point(), nearest_object.get_reflection_ray(), depth - 1)

        # Refraction color
        refraction_weight = nearest_object.get_refraction_weight()
        refraction_color = [0, 0, 0]
        
        # If the object is refractive, trace the refraction ray
        if refraction_weight == 0:
            refraction_color = [0, 0, 0]
        else:
            refraction_color = self.trace_ray(nearest_object.get_intersection_point(), nearest_object.get_refraction_ray(), depth - 1)
        
        # Compute return color
        return_color = [0, 0, 0]
        
        for i in range(3):
            return_color[i] = local_color[i] * local_weight  + reflection_color[i] * reflection_weight + refraction_color[i] * refraction_weight
            
        return return_color
    
    
    
    """
    Checks if there is an object between the given object and the light source for shadows
    """
    def in_shadow(self, start_object, start_point, light_source):
        ray = Matrix_Calculations.compute_unit_vector(start_point, light_source)
        
        for object in self.object_list:
            if object != start_object and object.get_intersection(start_point, ray) != []:
                return True
            
        return False
    
    
     # generate a color hex code string from the illumination components
    def get_hexcode_intensity(self, color):

        R_combinedColorCode = self.RGBcolorHexCode(color[0])
        G_combinedColorCode = self.RGBcolorHexCode(color[1])
        B_combinedColorCode = self.RGBcolorHexCode(color[2])
        colorString = "#" + R_combinedColorCode + G_combinedColorCode + B_combinedColorCode
        return colorString
        
    """
    Generate hex code string from intensity
    """
    def RGBcolorHexCode(self, intensity):
        hexString = str(hex(round(255* intensity)))
        if hexString[0] == "-": # illumination intensity should not be negative
            # print("illumination instensity is Negative. Setting to 00. Did you check for negative NdotL?")
            trimmedHexString = "00"
        else:
            trimmedHexString = hexString[2:] # get rid of "0x" at beginning of hex strings
            # convert single digit hex strings to two digit hex strings
            if len(trimmedHexString) == 1: trimmedHexString = "0" + trimmedHexString
            # we will use the green color component to display our monochrome illumination results
            # If it is 3 digits, set to FF
            if len(trimmedHexString) == 3: trimmedHexString = "FF"
        return trimmedHexString
    
    """
    Gets the root of the scene
    """
    def get_root(self):
        return self.root