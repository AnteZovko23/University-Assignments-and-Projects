from tkinter import *
import Scene
import Polyhedron
import Scene
import random

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

# Create the polyhedron maker
polyhedron_maker = Polyhedron.Polyhedron()

# Get tetrahedron
Tetrahedron, TetrahedronPointCloud = polyhedron_maker.tetrahedron({'apex': [150,50,100, 1],
                                        'base1': [200,-50,50, 1],
                                        'base2': [200,-50,150, 1],
                                        'base3': [100,-50,150, 1],
                                        'base4': [100,-50,50, 1]})

# Get cube
Cube, CubePointCloud = polyhedron_maker.cube({'base1': [-100,-50,50, 1],
                                        'base2': [-100,-50,150, 1],
                                        'base3': [-200,-50,50, 1],
                                        'base4': [-200,-50,150, 1],
                                        'base5': [-100,50,50, 1],
                                        'base6': [-100,50,150, 1],
                                        'base7': [-200,50,50, 1],
                                        'base8': [-200,50,150, 1]})

# Get cube
Cube_2, CubePointCloud_2 = polyhedron_maker.cube({'base1': [200,-50,50, 1],
                                        'base2': [200,-50,150, 1],
                                        'base3': [100,-50,50, 1],
                                        'base4': [100,-50,150, 1],
                                        'base5': [200,50,50, 1],
                                        'base6': [200,50,150, 1],
                                        'base7': [100,50,50, 1],
                                        'base8': [100,50,150, 1]})


# Get octahedron
Octahedron, OctahedronPointCloud = polyhedron_maker.octahedron({'apex': [159.93019510416048,30.707126467728294,66.07157714430433, 1],
                                        'apex2': [140.0698048958393,-130.70712646772844,93.92842285569596, 1],
                                        'base1': [217.03175864852074,-64.575184240332,43.3352030207445, 1],
                                        'base2': [188.32872406967402, -43.09626555484705,147.32939449255878, 1],
                                        'base3': [111.67127593032583,-56.90373444515309,12.670605507441493, 1],
                                        'base4': [82.9682413514790,-35.424815759668114,116.6647969792558, 1]})

# Get Pyramid
Pyramid, PyramidPointCloud = polyhedron_maker.pyramid()

# Crate the scene
scene = Scene.Scene(canvas_width, canvas_height, viewpoint)


PyramidColor = ["black", "red", "green", "blue", "yellow"]
CubeColor = ["black", "#cccccc", "#999999", "#666666","#333333", "white"]


# Add the polyhedra to the scene

scene.add_object(Cube, CubePointCloud, CubeColor)
scene.add_object(Pyramid, PyramidPointCloud, PyramidColor)
# scene.add_object(Cube_2, CubePointCloud_2, CubeColor)

# scene.add_object(Tetrahedron, TetrahedronPointCloud, generate_colors(Tetrahedron))
scene.add_object(Octahedron, OctahedronPointCloud, generate_colors(Octahedron))


# Start the mainloop
scene.get_root().mainloop()

