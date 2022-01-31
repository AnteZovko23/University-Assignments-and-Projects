from tkinter import *
import Scene
import Polyhedron
import Scene
import random

"""
Name: Ante Zovko

CWID: 103-55-122

Date: January 31th, 2022

Assignment 3: Back face culling; polygon filling; Z-buffering

The program renders a Pyramid and two cubes in a 3D viewing system and
with the ability to switch between Wireframe, Polygon Fill and Wireframe, and Polygon Fill modes

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
Octahedron, OctahedronPointCloud = polyhedron_maker.octahedron()

# Get Pyramid
Pyramid, PyramidPointCloud = polyhedron_maker.pyramid()

# Crate the scene
scene = Scene.Scene(canvas_width, canvas_height, viewpoint)


PyramidColor = ["black", "red", "green", "blue", "yellow"]
CubeColor = ["black", "#cccccc", "#999999", "#666666","#333333", "white"]


# Add the polyhedra to the scene

scene.add_object(Cube, CubePointCloud, CubeColor)
scene.add_object(Pyramid, PyramidPointCloud, PyramidColor)
scene.add_object(Cube_2, CubePointCloud_2, CubeColor)

# scene.add_object(Tetrahedron, TetrahedronPointCloud, generate_colors(Tetrahedron))
# scene.add_object(Octahedron, OctahedronPointCloud, generate_colors(Octahedron))


# Start the mainloop
scene.get_root().mainloop()

