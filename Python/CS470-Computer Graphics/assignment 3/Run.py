from tkinter import *
import Scene
import Polyhedron
import Scene
import random

"""
Name: Ante Zovko

CWID: 103-55-122

Date: January 11th, 2022

Assignment 2: 3D In-place Transformations and Perspective Projection

The program renders a Tetrahedron, Cube, and Octahedron in a 3D wire-frame viewing system and
allows for in-place translation, scaling and rotation

Reference Material: Computer Graphics Lecture Notes by Dr. Mike O'Neal

"""

# Generate a list of random colors based on the number of faces
def generate_colors(Object_definition):

    # Get number of faces
    number_of_faces = len(Object_definition)
    
    # Generate list of random colors based on the number of faces
    colors = []
    r = lambda: random.randint(0,255)

    # Generate random hex value for color
    for i in range(number_of_faces):
        colors.append('#%02X%02X%02X' % (r(),r(),r()))

    
    
    # For each value in colors replace the 0x with #
    
    # colors = list(map(lambda x: x.replace("0x", "#"), colors))
    
    
    
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

# # Add the polyhedra to the scene

scene.add_object(Pyramid, PyramidPointCloud, PyramidColor)
scene.add_object(Cube, CubePointCloud, CubeColor)
# scene.add_object(Cube_2, CubePointCloud_2, CubeColor)

# scene.add_object(Tetrahedron, TetrahedronPointCloud, generate_colors(Tetrahedron))
# scene.add_object(Octahedron, OctahedronPointCloud, generate_colors(Octahedron))


# Start the mainloop
scene.get_root().mainloop()

