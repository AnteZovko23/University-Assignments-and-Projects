from tkinter import *
from Checkerboard import checkerboard

import Scene
import Polyhedron
import Scene
import Illumination_Model
import Matrix_Calculations
"""
Name: Ante Zovko

CWID: 103-55-122

Date: February 15th, 2022

Assignment 4: Lighting and Shading Models

The program renders a Cylinder in a 3D viewing system
with the ability to switch between Wireframe, Polygon Fill and Wireframe, Polygon Fill, and Fill Tracing modes

The program also allows the user to choose between 3 shading algorithms: Flat, Gouraud, and Phong
Shading is based on the phong illumination model


Reference Material: Computer Graphics Lecture Notes by Dr. Mike O'Neal

"""

canvas_width = 500
canvas_height = 400

# Constants for the illumination model
viewpoint = [0, 0, -500]
light_vector = [1, 1, 0]

ambient_intensity = 0.6
point_light_intensity = 0.5

# illumination_model = Illumination_Model.Illumination_Model(view_vector=viewpoint, light_vector=light_vector, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance)

# Create the polyhedron maker
polyhedron_maker = Polyhedron.Polyhedron()


# sky_box_color = [0.53 * 255, 0.81 * 255, 0.92 * 255]
sky_box_color = [0.53 * 255, 0.81 * 255, 0.92 * 255]

# Convert sky_box_color values to hex   
# sky_box_color_hex = "#%02X%02X%02X" % (int(sky_box_color[0]), int(sky_box_color[1]), int(sky_box_color[2]))


# Crate the scene
scene = Scene.Scene(canvas_width, canvas_height, viewpoint, sky_box_color)
checkerboard = scene.get_checkerboard()
diffuse_constant = 0.5
specular_constant = 0.5
specular_index = 8
distance = 1
weight_local = 0.5
weight_for_reflections = 0.5
checkerboard.set_reflection_constants(view_vector=viewpoint, light_vector=Matrix_Calculations.get_normalized_vector(light_vector), ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance, weight_local=weight_local, weight_for_reflections=weight_for_reflections)
scene.ray_tracing_add_object(checkerboard)
# checkerboard.generate_checkerboard()
scene.render_image()
# checkerboard.generate_checkerboard()

# Start the mainloop
scene.get_root().mainloop()


