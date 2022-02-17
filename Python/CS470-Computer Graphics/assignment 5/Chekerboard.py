
class checkerboard(object):
    def __init__(self, illumination_model, scene, sky_box_color, x_start=-800, y_value=-200, z_start=0, x_end=800, z_end=800):
        self.scene = scene
        self.illumination_model = illumination_model
        self.sky_box_color = sky_box_color
        self.x_start = x_start
        self.y_value = y_value
        self.z_start = z_start
        self.x_end = x_end
        self.z_end = z_end
    
    
    #### Assignment 5 ####
    
    def get_color_from_colorflag(self, colorflag):
        
        if colorflag:
            return [255 * self.illumination_model.get_intensity(), 0, 0]
        else:
            return [255 * self.illumination_model.get_intensity(), 255 * self.illumination_model.get_intensity(), 255 * self.illumination_model.get_intensity()]
        

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
                # print(color_hex)
                # Project the point onto the screen
                color = self.scene.illumination_model.get_hexcode()
                self.scene.canvas.create_line(display_coordinates_point[0], display_coordinates_point[1], display_coordinates_point[0] + 1, display_coordinates_point[1], fill=color_hex)