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
lightSource = [500,500,0]
ambient_intensity = 1
point_light_intensity = 1

# Convert sky_box_color values to hex   
# sky_box_color_hex = "#%02X%02X%02X" % (int(sky_box_color[0]), int(sky_box_color[1]), int(sky_box_color[2]))


# Crate the scene
diffuse_constant = 1
specular_constant = .2
specular_index = 5
distance = 1
weight_local = .7
weight_for_reflections = .3
sphere_instance = Sphere.Sphere(center_point=[-300, -150, 100], radius=50, local_color=[0.5, 0.5, 1])
sphere_instance.set_reflection_constants(view_vector=viewpoint, point_light_source=lightSource, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance, weight_local=weight_local, weight_for_reflections=weight_for_reflections)
sphere_instance.illumination_model.set_surface_normal([0, 1, 0])



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