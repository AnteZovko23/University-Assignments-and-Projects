from tkinter import *
from Checkerboard import checkerboard
import Scene
import Scene
import Sphere

"""
Name: Ante Zovko

CWID: 103-55-122

Date: February 21st, 2022

Assignment 5: Ray Tracing

The program renders 5 spheres in a 3D viewing system by tracing individual rays from the viewpoint to each of the spheres


Reference Material: Computer Graphics Lecture Notes by Dr. Mike O'Neal

"""

canvas_width = 600
canvas_height = 400

# Constants for the illumination model
viewpoint = [0, 0, -500]
light_vector = [1, 1, 0]
lightSource = [500,500,0]

# Illumination Constants for the environment
ambient_intensity = 0.2
point_light_intensity = .9

# Color of the sky box
sky_box_color = [0.55, 0.8, 0.996]


# Crate the scene
scene = Scene.Scene(canvas_width, canvas_height, viewpoint, sky_box_color)
checkerboard_instance = checkerboard()
diffuse_constant = 0.6
specular_constant = 0.4
specular_index = 8
distance = 1
weight_local = .65
weight_for_reflections = .35
checkerboard_instance.set_reflection_constants(view_vector=viewpoint, point_light_source=lightSource, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance, weight_local=weight_local, weight_for_reflections=weight_for_reflections)
scene.ray_tracing_add_object(checkerboard_instance)

# Sphere 1
diffuse_constant = 1
specular_constant = 1
specular_index = 16
distance = 1
weight_local = .45
weight_for_reflections = .55
sphere_instance = Sphere.Sphere(center_point=[250, -100, 400], radius=100, local_color=[1, 0.5, 0.5])
sphere_instance.set_reflection_constants(view_vector=viewpoint, point_light_source=lightSource, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance, weight_local=weight_local, weight_for_reflections=weight_for_reflections)
scene.ray_tracing_add_object(sphere_instance)

# Sphere 2
diffuse_constant = 1
specular_constant = 1
specular_index = 5
distance = 1
weight_local = .2
weight_for_reflections = .8
sphere_instance = Sphere.Sphere(center_point=[-210, -50, 600], radius=150, local_color=[0.5, 1, 0.5])
sphere_instance.set_reflection_constants(view_vector=viewpoint, point_light_source=lightSource, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance, weight_local=weight_local, weight_for_reflections=weight_for_reflections)
scene.ray_tracing_add_object(sphere_instance)

# Sphere 3
diffuse_constant = 1
specular_constant = .2
specular_index = 5
distance = 1
weight_local = .7
weight_for_reflections = .3
sphere_instance = Sphere.Sphere(center_point=[-450, -150, 600], radius=50, local_color=[0.5, 0.5, 1])
sphere_instance.set_reflection_constants(view_vector=viewpoint, point_light_source=lightSource, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance, weight_local=weight_local, weight_for_reflections=weight_for_reflections)
scene.ray_tracing_add_object(sphere_instance)

# Sphere 4
diffuse_constant = 1
specular_constant = .2
specular_index = 5
distance = 1
weight_local = .25
weight_for_reflections = 0
weight_for_refractions = .75
sphere_instance = Sphere.Sphere(center_point=[-210, -150, 60], radius=50, local_color=[1, 1, 1])
sphere_instance.set_reflection_constants(view_vector=viewpoint, point_light_source=lightSource, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance, weight_local=weight_local, weight_for_reflections=weight_for_reflections, weight_for_refractions=weight_for_refractions)
scene.ray_tracing_add_object(sphere_instance)

# Sphere 5
diffuse_constant = 1
specular_constant = 1
specular_index = 5
distance = 1
weight_local = .25
weight_for_reflections = 0
weight_for_refractions = .75
sphere_instance = Sphere.Sphere(center_point=[100, -150, 90], radius=50, local_color=[1, 1, 1])
sphere_instance.set_reflection_constants(view_vector=viewpoint, point_light_source=lightSource, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance, weight_local=weight_local, weight_for_reflections=weight_for_reflections, weight_for_refractions=weight_for_refractions)
scene.ray_tracing_add_object(sphere_instance)

# Start ray tracing
scene.render_image()

# Window name

# Start the mainloop
scene.get_root().mainloop()


