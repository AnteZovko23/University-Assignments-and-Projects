import Illumination_Model
class checkerboard(object):
    def __init__(self, scene, sky_box_color, x_start=-800, y_value=-200, z_start=0, x_end=800, z_end=1000):
        self.scene = scene
        self.illumination_model = None
        self.sky_box_color = sky_box_color
        self.x_start = x_start
        self.y_value = y_value
        self.z_start = z_start
        self.x_end = x_end
        self.z_end = z_end
        self.anchor_point = [0, self.y_value, 0]
        self.t_value = None
        self.diffuse_constant = None
        self.specular_constant = None
        self.specular_index = None
        self.weight_local = None
        self.weight_for_reflections = None
        self.local_color = None
        self.intersection_point = None
        self.current_ray = None
        
    #### Assignment 5 ####
    
    # def get_color_from_colorflag(self, colorflag):
        
    #     if colorflag:
    #         return [255 * self.illumination_model.get_intensity(), 0, 0]
    #     else:
    #         return [255 * self.illumination_model.get_intensity(), 255 * self.illumination_model.get_intensity(), 255 * self.illumination_model.get_intensity()]
        
    # def get_color_from_colorflag(self, colorflag):
    
    #     if colorflag:
    #         return [255 , 0, 0]
    #     else:
    #         return [255 , 255 , 255 ]
    
    def get_color_from_colorflag(self, colorflag):

        if colorflag:
            return [1 * 255, 0, 0]
        else:
            return [1 * 255 , 1 * 255 , 1 * 255 ]

    def generate_checkerboard(self):
        x_start = self.x_start
        y_value = self.y_value
        z_start = self.z_start
        
        x_end = self.x_end
        z_end = self.z_end
        
        start_point = [x_start, y_value, z_start]
        end_point = [x_end, y_value, z_end]
        
        # # # Project points
        projected_start_point = self.scene.project(start_point)
        projected_end_point = self.scene.project(end_point)
        
        
        # # Convert to display coordinates
        display_start_point = self.scene.convertToDisplayCoordinates(projected_start_point)
        display_end_point = self.scene.convertToDisplayCoordinates(projected_end_point)
        self.sky_box_color = [0.53 * 255, 0.81 * 255, 0.92 * 255]
        # Convert self.sky_box_color values to hex   
        self.sky_box_color_hex = "#%02X%02X%02X" % (int(self.sky_box_color[0]), int(self.sky_box_color[1]), int(self.sky_box_color[2]))
        surface_normal = [0, 1, 0]
        # Paint sky box
        for y in range(0, 300):
            for x in range(0, 800):
                self.scene.canvas.create_line(x, y, x+1, y+1, fill=self.sky_box_color_hex)
        
        color = None
        self.illumination_model.set_surface_normal(surface_normal)
        # For each row starting at z_start and ending at z_end
        for z in range(z_start, z_end*2):
            
            # For each column starting at x_start and ending at x_end
            for x in range(int(round(display_start_point[0])), int(round(display_end_point[0]))):
                # Determine if the color is red or white
                if x >= 0:
                    colorflag = True
                else:
                    colorflag = False
                    
                if abs(x) % 200 > 100:
                    colorflag = not colorflag
                    
                if abs(z) % 200 > 100:
                    colorflag = not colorflag

                current_point = [x, y_value, z]
                
                
                # Project the point
                projected_point = self.scene.project(current_point)
                
                # Convert to display coordinates
                display_coordinates_point = self.scene.convertToDisplayCoordinates(projected_point)
                
                # Get color
                color = self.get_color_from_colorflag(colorflag)
                
                color_hex = "#%02X%02X%02X" % (int(color[0]), int(color[1]), int(color[2]))
                # Project the point onto the screen
                self.scene.canvas.create_line(display_coordinates_point[0], display_coordinates_point[1], display_coordinates_point[0] + 1, display_coordinates_point[1], fill=color_hex)
                
        

    
    def get_intersection(self, start_point, ray):
        
        self.current_ray = ray
        
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
        
        print(t)
        self.set_t_value(t)
        
        # Intersection point
        X = start_point[0] + t * ray[0]
        Y = start_point[1] + t * ray[1]
        Z = start_point[2] + t * ray[2]
        
        if Z > self.z_end:
            return []
        
        self.intersection_point = [X, Y, Z]
        self.set_local_color()
        return [X, Y, Z]
        
        
    #### Getters and Setters ####
    """
    Returns an anchor point, which in this case is the point where it touches the viewing plane. (0, -150, 0) by default.
    """
    def get_anchor_point(self):
            
        return self.anchor_point
    
    def get_surface_normal(self):
        return [0, 1, 0]
    
    def get_t_value(self):
        return self.t_value
    
    def set_t_value(self, value):
        self.t_value = value
        
    def set_reflection_constants(self, view_vector, light_vector, ambient_intensity, point_light_intensity, diffuse_constant, specular_constant, specular_index, distance, weight_local, weight_for_reflections):
        
        self.illumination_model = Illumination_Model.Illumination_Model(view_vector=view_vector, light_vector=light_vector, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance)
        self.illumination_model.set_surface_normal(self.get_surface_normal())
        self.weight_local = weight_local
        self.weight_for_reflections = weight_for_reflections
    
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
    
    def get_weight(self):
        return self.weight_local
    
    def get_intersection_point(self):
        return self.intersection_point
    
    def get_reflection_ray(self):
        return self.illumination_model.ray_tracing_calculate_reflection_vector(self.current_ray)
    
    def set_anchor_point(self, value):
        self.anchor_point = value
        