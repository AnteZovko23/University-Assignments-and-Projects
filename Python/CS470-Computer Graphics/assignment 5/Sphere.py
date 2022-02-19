import Illumination_Model
import math
class Sphere(object):
    def __init__(self, center_point, radius, local_color):        

        self.center_point = center_point
        self.radius = radius
        self.local_color = local_color


        self.illumination_model = None
        self.t_value = None
        self.diffuse_constant = None
        self.specular_constant = None
        self.specular_index = None
        self.weight_local = None
        self.weight_for_reflections = None
        self.intersection_point = None
        self.current_ray = None
        
        
    
    def get_intersection(self, start_point, ray):
        
        self.current_ray = ray
       
        # Ray-sphere intersection equation
        
        a = ray[0]**2 + ray[1]**2 + ray[2]**2
        
        b = 2 * ray[0] * (start_point[0] - self.center_point[0]) + 2 * ray[1] * (start_point[1] - self.center_point[1]) + 2 * ray[2] * (start_point[2] - self.center_point[2])
        
        c = self.center_point[0]**2 + self.center_point[1]**2 + self.center_point[2]**2 + start_point[0]**2 + start_point[1]**2 + start_point[2]**2 + 2 * (-self.center_point[0] * start_point[0] - self.center_point[1] * start_point[1] - self.center_point[2] * start_point[2]) - self.radius**2
        
        # Solve quadratic equation
        discriminant = b**2 - 4 * a * c
        
        t = 0
        
        if discriminant < 0:
            return []
        
        elif discriminant == 0:
            t = -b / (2 * a)
            
        else:
            t1 = (-b + math.sqrt(discriminant)) / (2 * a)
            t2 = (-b - math.sqrt(discriminant)) / (2 * a)
            
            # Choose the smallest t value
            if t1 < t2:
                t = t1
            else:
                t = t2
        
        if t < 0.001:
            return []
        

        self.set_t_value(t)

        # Get the intersection point
        # Intersection point
        X = start_point[0] + t * ray[0]
        Y = start_point[1] + t * ray[1]
        Z = start_point[2] + t * ray[2]

        if Z > 1000 or Z < 0:
            return []

        self.intersection_point = [X, Y, Z]
        
        return [X, Y, Z]
        
    
    
    #### Getters and Setters ####    
    def get_t_value(self):
        return self.t_value
    
    def set_t_value(self, value):
        self.t_value = value
        
    def set_reflection_constants(self, view_vector, ambient_intensity, point_light_intensity, diffuse_constant, specular_constant, specular_index, distance, weight_local, weight_for_reflections, point_light_source=None, light_vector=None):
        
        self.illumination_model = Illumination_Model.Illumination_Model(view_vector=view_vector, point_light_source=point_light_source, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance)
        self.weight_local = weight_local
        self.weight_for_reflections = weight_for_reflections
    
    def set_surface_normal(self, normal):
        self.illumination_model.set_surface_normal(normal)
    
    def get_local_color(self):
        return self.local_color
    
    def set_local_color(self, color):
        self.local_color = color
    
    def get_center_point(self):
        return self.center_point
    
    def set_center_point(self, point):
        self.center_point = point
    
    def get_radius(self):
        return self.radius
    
    def set_radius(self, radius):
        self.radius = radius
    
    def get_reflection_weight(self):
        return self.weight_for_reflections
    
    def get_weight(self):
        return self.weight_local
    
    def get_intersection_point(self):
        return self.intersection_point
    
    def get_reflection_ray(self):
        return self.illumination_model.ray_tracing_calculate_reflection_vector(self.current_ray)
