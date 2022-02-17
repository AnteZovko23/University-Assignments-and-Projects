from tkinter import *

import Scene
import Polyhedron
import Scene
import random
import Illumination_Model
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

# Constants for the illumination model
viewpoint = [0, 0, -500]
light_vector = [1, 1, -1]

ambient_intensity = 0.3
point_light_intensity = 0.7
diffuse_constant = 0.5
specular_constant = 0.5
specular_index = 10
distance = 1

illumination_model = Illumination_Model.Illumination_Model(view_vector=viewpoint, light_vector=light_vector, ambient_intensity=ambient_intensity, point_light_intensity=point_light_intensity, diffuse_constant=diffuse_constant, specular_constant=specular_constant, specular_index=specular_index, distance=distance)


# Create the polyhedron maker
polyhedron_maker = Polyhedron.Polyhedron()


# # Get Pyramid
# Pyramid, PyramidPointCloud = polyhedron_maker.pyramid()
PyramidColor = ["black", "red", "green", "blue", "yellow"]
CubeColor = ["black", "#cccccc", "#999999", "#666666","#333333", "white"]

# Crate the scene
scene = Scene.Scene(canvas_width, canvas_height, viewpoint, illumination_model)


Cylinder, CylinderPointCloud = polyhedron_maker.cylinder()
scene.add_object(Cylinder, CylinderPointCloud, generate_colors(Cylinder))



# Start the mainloop
scene.get_root().mainloop()






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
# scene.add_object(Cube, CubePointCloud, CubeColor)
# scene.add_object(Pyramid, PyramidPointCloud, PyramidColor)
# scene.add_object(Cube_2, CubePointCloud_2, CubeColor)

# scene.add_object(Tetrahedron, TetrahedronPointCloud, generate_colors(Tetrahedron))
# scene.add_object(Octahedron, OctahedronPointCloud, generate_colors(Octahedron))