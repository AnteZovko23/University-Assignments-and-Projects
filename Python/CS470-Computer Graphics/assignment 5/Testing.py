import Illumination_Model
import Checkerboard
import Matrix_Calculations
import Sphere

# sky_box_color = [0.53 * 255, 0.81 * 255, 0.92 * 255]
sky_box_color = [0.53 * 255, 0.81 * 255, 0.92 * 255]
canvas_width = 400
canvas_height = 300

# Constants for the illumination model
viewpoint = [0, 0, -500]
light_vector = [1, 1, 0]

ambient_intensity = 1
point_light_intensity = 1

# Convert sky_box_color values to hex   
# sky_box_color_hex = "#%02X%02X%02X" % (int(sky_box_color[0]), int(sky_box_color[1]), int(sky_box_color[2]))


# Crate the scene
diffuse_constant = 0.5
specular_constant = 0.5
specular_index = 8
distance = 1
weight_local = 0.5
weight_for_reflections = 0.5
sphere = Sphere.Sphere(radius=100, center_point=[0, -300, 300], local_color=[1, 0, 0])
ray = Matrix_Calculations.compute_unit_vector(viewpoint, [0, -200, 0])
X, Y, Z = sphere.get_intersection(viewpoint, ray)
import math
radius = 100
# Calculate z using the equation z = sqrt(radius^2 - x^2 - y^2) if not negative

# checkerboard.set_reflection_constants(view_vector=viewpoint, light_vector=light_vector, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance, weight_local=weight_local, weight_for_reflections=weight_for_reflections)
# checkerboard.set_anchor_point([0, -400, 0])
# print(checkerboard.illumination_model.ray_tracing_calculate_reflection_vector([0.7, -0.7, 0]))
# light_vector = [1, 1, 0]

# ip = 0.5
# kd = 0.5

# d = 1

# light = Illumination_Model.Illumination_Model(surface_normal=surface_normal, light_vector=light_vector, point_light_intensity=ip, diffuse_constant=kd, distance=d)

# print(light.triColorHexCode())