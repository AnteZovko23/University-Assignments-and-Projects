from tkinter import *
from traceback import print_tb

from pyrsistent import v
import Scene
import Polyhedron
import Scene
import random
import Illumination_Model
import Matrix_Calculations
"""
Name: Ante Zovko

CWID: 103-55-122

Date: February 31th, 2022

Assignment 3: Back face culling; polygon filling; Z-buffering

The program renders a Pyramid and two cubes in a 3D viewing system and
with the ability to switch between Wireframe, Polygon Fill and Wireframe, Polygon Fill, and Fill Tracing modes


Reference Material: Computer Graphics Lecture Notes by Dr. Mike O'Neal

"""

"""
Generates a list of hex values for color given the number of faces in an object

"""
def generate_colors(Object_definition):

    # Get number of faces
    number_of_faces = len(Object_definition)
    
    # Generate list of random colors based on the number of faces
    colors = []
    r = lambda: random.randint(0,255)

    # Generate random hex value for color
    for i in range(number_of_faces):
        colors.append('#%02X%02X%02X' % (r(),r(),r()))
    
    
    return colors


canvas_width = 400
canvas_height = 400

viewpoint = [0, 0, -500]
light_vector = [1, 1, -1]

ambient_intensity = 0.3
point_light_intensity = 0.7
diffuse_constant = 0.5
specular_constant = 0.5
specular_index = 8

distance = 1

illumination_model = Illumination_Model.Illumination_Model(view_vector=viewpoint, light_vector=light_vector, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance)


# Create the polyhedron maker
polyhedron_maker = Polyhedron.Polyhedron()

# # Get tetrahedron
# Tetrahedron, TetrahedronPointCloud = polyhedron_maker.tetrahedron({'apex': [150,50,100, 1],
#                                         'base1': [200,-50,50, 1],
#                                         'base2': [200,-50,150, 1],
#                                         'base3': [100,-50,150, 1],
#                                         'base4': [100,-50,50, 1]})

# # Get cube
# Cube, CubePointCloud = polyhedron_maker.cube({'base1': [-100,-50,50, 1],
#                                         'base2': [-100,-50,150, 1],
#                                         'base3': [-200,-50,50, 1],
#                                         'base4': [-200,-50,150, 1],
#                                         'base5': [-100,50,50, 1],
#                                         'base6': [-100,50,150, 1],
#                                         'base7': [-200,50,50, 1],
#                                         'base8': [-200,50,150, 1]})

# # Get cube
# Cube_2, CubePointCloud_2 = polyhedron_maker.cube({'base1': [200,-50,50, 1],
#                                         'base2': [200,-50,150, 1],
#                                         'base3': [100,-50,50, 1],
#                                         'base4': [100,-50,150, 1],
#                                         'base5': [200,50,50, 1],
#                                         'base6': [200,50,150, 1],
#                                         'base7': [100,50,50, 1],
#                                         'base8': [100,50,150, 1]})


# # Get octahedron
# Octahedron, OctahedronPointCloud = polyhedron_maker.octahedron({'apex': [159.93019510416048,30.707126467728294,66.07157714430433, 1],
#                                         'apex2': [140.0698048958393,-130.70712646772844,93.92842285569596, 1],
#                                         'base1': [217.03175864852074,-64.575184240332,43.3352030207445, 1],
#                                         'base2': [188.32872406967402, -43.09626555484705,147.32939449255878, 1],
#                                         'base3': [111.67127593032583,-56.90373444515309,12.670605507441493, 1],
#                                         'base4': [82.9682413514790,-35.424815759668114,116.6647969792558, 1]})

# # Get Pyramid
# Pyramid, PyramidPointCloud = polyhedron_maker.pyramid()
PyramidColor = ["black", "red", "green", "blue", "yellow"]
CubeColor = ["black", "#cccccc", "#999999", "#666666","#333333", "white"]

# Crate the scene
scene = Scene.Scene(canvas_width, canvas_height, viewpoint, illumination_model)

"""
Precomputes vertex normals of a cylinder
"""
def calculate_vertex_normals(Object_definition):
    # Poly 9 and 10 are the base and top of the cylinder
    poly_8 = Object_definition[8]
    poly_9 = Object_definition[9]
    
    poly_8_normal = Matrix_Calculations.get_surface_normal(poly_8)
    poly_9_normal = Matrix_Calculations.get_surface_normal(poly_9)
    
    poly_8_normal = Matrix_Calculations.get_normalized_vector(poly_8_normal)
    poly_9_normal = Matrix_Calculations.get_normalized_vector(poly_9_normal)
    
    # Get first 8 polygons
    Object_definition = Object_definition[:8]
    surface_normals = []
    # Calculate surface normals for each polygon
    for poly in Object_definition:
        surface_normals.append(Matrix_Calculations.get_surface_normal(poly))
        
    # Calculate vertex normals by adding surface normals
    vertex_normals = []
    # Perform addition between two adjacent surface normals
    for i in range(len(surface_normals)):
        vertex_normals.append(Matrix_Calculations.add(surface_normals[i], surface_normals[(i+1)%len(surface_normals)]))
        
    
    for i in range(len(vertex_normals)):
        vertex_normals[i] = Matrix_Calculations.get_normalized_vector(vertex_normals[i])
        
    # Group vertex normals as previous and next vertex normals starting at 1
    vertex_normals_grouped = {}

    for i in range(0, len(vertex_normals)):
        vertex_normals_grouped[f"Polygon {i}"] = [vertex_normals[(i-1)%len(vertex_normals)], vertex_normals[i]]

    # Assign polygon 9 and 10
    vertex_normals_grouped["Polygon 8"] = [poly_8_normal]
    vertex_normals_grouped["Polygon 9"] = [poly_9_normal]
    
    return vertex_normals_grouped
        
    





Cylinder, CylinderPointCloud = polyhedron_maker.cylinder()
vertex_normals = calculate_vertex_normals(Cylinder)
scene.add_object(Cylinder, CylinderPointCloud, generate_colors(Cylinder), vertex_normals=vertex_normals)

# scene.add_object(Cube, CubePointCloud, CubeColor)
# scene.add_object(Pyramid, PyramidPointCloud, PyramidColor)
# scene.add_object(Cube_2, CubePointCloud_2, CubeColor)

# scene.add_object(Tetrahedron, TetrahedronPointCloud, generate_colors(Tetrahedron))
# scene.add_object(Octahedron, OctahedronPointCloud, generate_colors(Octahedron))


# Start the mainloop
scene.get_root().mainloop()

