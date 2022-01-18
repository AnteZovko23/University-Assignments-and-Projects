from tkinter import *
import Scene
import Polyhedron
import Scene

"""
Name: Ante Zovko

CWID: 103-55-122

Date: January 11th, 2022

Assignment 2: 3D In-place Transformations and Perspective Projection

The program renders a Tetrahedron, Cube, and Octahedron in a 3D wire-frame viewing system and
allows for in-place translation, scaling and rotation

Reference Material: Computer Graphics Lecture Notes by Dr. Mike O'Neal

"""
canvas_width = 400
canvas_height = 400
d = 500

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

# Get octahedron
Octahedron, OctahedronPointCloud = polyhedron_maker.octahedron()

# Crate the scene
scene = Scene.Scene(canvas_width, canvas_height, d)

# Add the polyhedra to the scene
scene.add_object(Tetrahedron, TetrahedronPointCloud)
scene.add_object(Cube, CubePointCloud)
scene.add_object(Octahedron, OctahedronPointCloud)


# Start the mainloop
scene.get_root().mainloop()
