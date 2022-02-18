import Matrix_Calculations


"""
Author: Ante Zovko
Date: February 15th, 2022

This program implements the phong illumination model with real-time updates once a parameter is changed
"""

class Illumination_Model(object):

    def __init__(self, light_vector, surface_normal=None,  point_light_intensity=0.7, ambient_intensity=0.3, diffuse_constant=0.5, specular_constant=0.5, specular_index=2, view_vector=[0, 0, -1], distance=1):

        # Given Constants
        if surface_normal == None:
            self.surface_normal = None
        else:
            self.surface_normal = Matrix_Calculations.get_normalized_vector(surface_normal)
        
        self.light_vector = Matrix_Calculations.get_normalized_vector(light_vector)
        self.view_vector = Matrix_Calculations.get_normalized_vector(view_vector)    
            
        self.point_light_intensity = point_light_intensity
        self.ambient_intensity = ambient_intensity
        self.diffuse_constant = diffuse_constant
        self.specular_constant = specular_constant
        self.specular_index = specular_index
        self.distance = distance
        
        
        # Calculated Components
        if surface_normal == None:
            self.reflection_vector = None
        else:
            self.reflection_vector = Matrix_Calculations.get_normalized_vector(self.calculate_reflection_vector())
            
        self.ambient_component = self.calculate_ambient_component()
        self.diffuse_component = self.calculate_diffuse_component()
        self.specular_component = self.calculate_specular_component()
        
    """
    Calculates ambient component
    """
    def calculate_ambient_component(self):
        
        return self.ambient_intensity * self.diffuse_constant
    
    """
    Calculates diffuse component
    """
    def calculate_diffuse_component(self):
        
        if self.surface_normal == None:
            # print("Surface normal has not been set. Please set the surface normal before calculating the diffuse component.")
            return None
        
        NdotL = Matrix_Calculations.get_dot_product(self.surface_normal, self.light_vector)
        
        if NdotL < 0:
            NdotL = 0    
        
        return self.point_light_intensity * self.diffuse_constant * NdotL/self.distance
    
    """
    Calculates the specular component
    """
    def calculate_specular_component(self):
        
        if self.reflection_vector == None:
            # print("Reflection vector has not been set. Please set the reflection vector before calculating the specular component.")
            return None
        
        RdotV = Matrix_Calculations.get_dot_product(self.reflection_vector, self.view_vector)
        
        if RdotV < 0:
            RdotV = 0
        
        return self.point_light_intensity * self.specular_constant * ((RdotV) ** self.specular_index)
    
    """
    Calculates the reflection vector
    """
    def calculate_reflection_vector(self):
        # Check if the surface normal has been set
        if self.surface_normal == None:
            return None
        
        R = []
    
        # Normalize N and L
        N = Matrix_Calculations.get_normalized_vector(self.surface_normal)
        L = Matrix_Calculations.get_normalized_vector(self.light_vector)
        
        twoCosPhi = 2 * Matrix_Calculations.get_dot_product(N, L)
        
        # Check angle between N and L
        if twoCosPhi > 0:
            for i in range(3):
                R.append(N[i] - (L[i] / twoCosPhi))
        elif twoCosPhi == 0:
            for i in range(3):
                R.append(-L[i])
        else:
            for i in range(3):
                R.append(-N[i] + (L[i] / twoCosPhi))
                
        return Matrix_Calculations.get_normalized_vector(R)

    def ray_tracing_calculate_reflection_vector(self, traced_ray):
        # Check if the surface normal has been set
        if self.surface_normal == None:
            return None
        
        R = []
        # Normalize N and L
        N = Matrix_Calculations.get_normalized_vector(self.surface_normal)
        L = Matrix_Calculations.get_normalized_vector(traced_ray)
        
        twoCosPhi = 2 * (N[0] * -L[0] + N[1] * -L[1] + N[2] * -L[2])
        
        # Check angle between N and L
        if twoCosPhi > 0:
            for i in range(3):
                R.append(N[i] + (L[i] / twoCosPhi))
        elif twoCosPhi == 0:
            for i in range(3):
                R.append(L[i])
        else:
            raise Exception("Error: The angle between the surface normal and the ray is greater than 90 degrees.")
            # for i in range(3):
            #     R.append(-N[i] - (L[i] / twoCosPhi))
                            
        return Matrix_Calculations.get_normalized_vector(R)
        
    
    # generate a color hex code string from the illumination components
    def get_hexcode(self):
        
        ambient = self.ambient_component
        diffuse = self.diffuse_component
        specular = self.specular_component
        
        combinedColorCode = self.colorHexCode(ambient + diffuse + specular)
        specularColorCode = self.colorHexCode(specular)
        colorString = "#" + combinedColorCode + specularColorCode + specularColorCode
        return colorString
    

    
    # generate a color hex code string from the illumination components
    def get_hexcode_intensity(self, ambient, diffuse, specular):

        combinedColorCode = self.colorHexCode(ambient + diffuse + specular)
        specularColorCode = self.colorHexCode(specular)
        colorString = "#" + specularColorCode + combinedColorCode + specularColorCode
        return colorString

    """
    Generate hex code string from intensity
    """
    def colorHexCode(self, intensity):
        hexString = str(hex(round(255* intensity)))
        if hexString[0] == "-": # illumination intensity should not be negative
            # print("illumination instensity is Negative. Setting to 00. Did you check for negative NdotL?")
            trimmedHexString = "00"
        else:
            trimmedHexString = hexString[2:] # get rid of "0x" at beginning of hex strings
            # convert single digit hex strings to two digit hex strings
            if len(trimmedHexString) == 1: trimmedHexString = "0" + trimmedHexString
            # we will use the green color component to display our monochrome illumination results
        return trimmedHexString
    
    """
    Recalculates the components of the illumination model
    """
    def recalculate_components(self):
        # Calculated Components
        if self.reflection_vector == None:
            self.reflection_vector = self.calculate_reflection_vector()
        else:
            self.reflection_vector = Matrix_Calculations.get_normalized_vector(self.calculate_reflection_vector())
        
        self.ambient_component = self.calculate_ambient_component()
        self.diffuse_component = self.calculate_diffuse_component()
        self.specular_component = self.calculate_specular_component()
    
    
    ################ Setters #############################
    def set_surface_normal(self, surface_normal):
        self.surface_normal = Matrix_Calculations.get_normalized_vector(surface_normal)
        self.recalculate_components()
    
    def set_light_vector(self, light_vector):
        self.light_vector = Matrix_Calculations.get_normalized_vector(light_vector)
        self.recalculate_components()

    
    def set_view_vector(self, view_vector):
        self.view_vector = Matrix_Calculations.get_normalized_vector(view_vector)
        self.recalculate_components()

        
    def set_distance(self, distance):
        self.distance = distance
        self.recalculate_components()

        
    def set_point_light_intensity(self, point_light_intensity):
        self.point_light_intensity = point_light_intensity
        self.recalculate_components()

        
    def set_ambient_intensity(self, ambient_intensity):
        self.ambient_intensity = ambient_intensity
        self.recalculate_components()

        
    def set_diffuse_constant(self, diffuse_constant):
        self.diffuse_constant = diffuse_constant
        self.recalculate_components()
        
    def set_specular_constant(self, specular_constant):
        self.specular_constant = specular_constant
        self.recalculate_components()

        
    def set_specular_index(self, specular_index):
        self.specular_index = specular_index
        self.recalculate_components()

    
    ################ Getters #############################
    def get_surface_normal(self):
        return self.surface_normal
    
    def get_light_vector(self):
        return self.light_vector
    
    def get_view_vector(self):
        return self.view_vector
    
    def get_distance(self):
        return self.distance
    
    def get_point_light_intensity(self):
        return self.point_light_intensity
    
    def get_ambient_intensity(self):
        return self.ambient_intensity
    
    def get_diffuse_constant(self):
        return self.diffuse_constant
    
    def get_specular_constant(self):
        return self.specular_constant
    
    def get_specular_index(self):
        return self.specular_index
    
    def get_reflection_vector(self):
        return self.reflection_vector

    def get_ambient_component(self):
        return self.ambient_component

    def get_diffuse_component(self):
        return self.diffuse_component
    
    def get_specular_component(self):
        return self.specular_component
    
    def get_intensity(self):
        return self.ambient_component + self.diffuse_component + self.specular_component


    