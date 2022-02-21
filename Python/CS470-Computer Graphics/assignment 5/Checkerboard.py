import Illumination_Model

"""
Author: Ante Zovko
Date: February 21st, 2022

This program models a checkerboard with the ability to trace rays from the viewpoint to the checkerboard 
"""
class checkerboard(object):
    def __init__(self, x_start=-800, y_value=-200, z_start=0, x_end=800, z_end=1000):
        self.illumination_model = None
        self.x_start = x_start
        self.y_value = y_value
        self.z_start = z_start
        self.x_end = x_end
        self.z_end = z_end
        self.anchor_point = [0, self.y_value, 0]
        
        # Illumination Constants
        self.t_value = None
        self.diffuse_constant = None
        self.specular_constant = None
        self.specular_index = None
        self.weight_local = None
        self.weight_for_reflections = None
        self.local_color = None
        self.intersection_point = None
        self.current_ray = None
    
    """
    Returns red if true, white if false
    """
    def get_color_from_colorflag(self, colorflag):

        if colorflag:
            return [1, 0, 0]
        else:
            return [1 , 1 , 1 ]

    """
    Calculates the intersection between a ray and the checkerboard if it exists
    """
    def get_intersection(self, start_point, ray):
        
        self.current_ray = ray
        
        # Get surface normal and a known point
        surface_normal = self.get_surface_normal()
        anchor_point = self.get_anchor_point()
        
        # Calculate D
        A, B, C = surface_normal[0], surface_normal[1], surface_normal[2]
        a, b, c = anchor_point[0], anchor_point[1], anchor_point[2]
        
        D = A * a + B * b + C * c
        
        # Calculate t
        # If the ray is parallel to the surface, return []
        if (A * ray[0] + B * ray[1] + C * ray[2]) == 0:
            return []
        
        t = -(A * start_point[0] + B * start_point[1] + C * start_point[2] - D) / (A * ray[0] + B * ray[1] + C * ray[2])
        
        # If t is negative, the ray is going away from the surface, so return []
        if t <= 0.001:
            return []
        
        self.set_t_value(t)
        
        # Intersection point
        X = start_point[0] + t * ray[0]
        Y = start_point[1] + t * ray[1]
        Z = start_point[2] + t * ray[2]
        
        # If the intersection is above the horizon or below the floor, return []
        if Z > self.z_end or Z < 0:
            return []
        
        self.intersection_point = [X, Y, Z]
        self.set_local_color()
        return [X, Y, Z]
        
        
    #### Getters and Setters ####
    def get_anchor_point(self):
            
        return self.anchor_point
    
    def get_surface_normal(self):
        return [0, 1, 0]
    
    def get_t_value(self):
        return self.t_value
    
    def set_t_value(self, value):
        self.t_value = value
        
    def set_reflection_constants(self, view_vector, ambient_intensity, point_light_intensity, diffuse_constant, specular_constant, specular_index, distance, weight_local, weight_for_reflections, point_light_source=None, light_vector=None):
        
        self.illumination_model = Illumination_Model.Illumination_Model(view_vector=view_vector, point_light_source=point_light_source, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance)
        self.illumination_model.set_surface_normal(self.get_surface_normal())
        self.weight_local = weight_local
        self.weight_for_reflections = weight_for_reflections
    
    """
    Sets local color based on the intersection point
    """
    def set_local_color(self):
        
        x, y, z = self.intersection_point[0], self.intersection_point[1], self.intersection_point[2]

        if x >= 0:
            colorflag = True
        else:
            colorflag = False
            
        if abs(x) % 200 > 100:
            colorflag = not colorflag
            
        if abs(z) % 200 > 100:
            colorflag = not colorflag
        # current_point = [x, y, z]
        color = self.get_color_from_colorflag(colorflag)
        
        self.local_color = color
        
    def get_local_color(self):
        return self.local_color
    
    def get_reflection_weight(self):
        return self.weight_for_reflections
    
    def get_refraction_weight(self):
        return 0
    
    def get_weight(self):
        return self.weight_local
    
    def get_intersection_point(self):
        return self.intersection_point
    
    def get_reflection_ray(self):
        return self.illumination_model.ray_tracing_calculate_reflection_vector(self.current_ray)
    
    def get_refraction_ray(self):
        return [0, 0, 0]
    
    def set_anchor_point(self, value):
        self.anchor_point = value
        