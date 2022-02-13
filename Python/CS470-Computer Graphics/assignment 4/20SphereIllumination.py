import math
from tkinter import *
import Illumination_Model

CanvasWidth = 800
CanvasHeight = 600

my_illumination_model = Illumination_Model.Illumination_Model()

Ia = .3 # intensity of the ambient light in the scene
my_illumination_model.set_ambient_intensity(Ia)

Ip = .7 # intensity of the point light source in the scene
my_illumination_model.set_point_light_intensity(Ip)

L = [1, 1, 1] # Lighting vector, 45 degree angle, Light is behind viewer's right shoulder [Right Hand Viewing System]
my_illumination_model.set_light_vector(L)

V = [0, 0 , 1] # View vector, points towards viewer/ center of projection [Right Hand Viewing System]
my_illumination_model.set_view_vector(V)

def render20Spheres():
    # Sphere Characteristics
    xCenter = 150       # xCenter of first sphere on each row
    yCenter = 112       # yCenter of first sphere on FIRST row
    radius = 50
    SpecIndex = [2, 4, 8, 16]
    Kd = 0      # Kd of first sphere on each row
    my_illumination_model.set_diffuse_constant(Kd)
    Ks = 1      # Ks of first sphere on each row
    my_illumination_model.set_specular_constant(Ks)

    # There will be four rows of spheres
    for row in range(4):
        my_illumination_model.set_specular_index(SpecIndex[row])
        # There will be five spheres per row
        for col in range(5):
            # Paint a sphere
            renderSphere(xCenter, yCenter, radius)
            # Prep for next sphere on row
            Kd += 0.25
            my_illumination_model.set_diffuse_constant(Kd)
            Ks -= 0.25
            my_illumination_model.set_specular_constant(Ks)
            xCenter += 125
        # Prep for next row of spheres
        xCenter = 150       # reset xCenter
        yCenter += 125      # increment yCenter for next row
        Kd = 0              # reset Kd
        my_illumination_model.set_diffuse_constant(Kd)
        Ks = 1                  # reset Ks
        my_illumination_model.set_specular_constant(Ks)

def renderSphere(xCenter, yCenter, radius):

    # render a sphere
    rSquare = radius**2
    for y in range(-radius, radius+1):
        ySquare = y**2
        for x in range(-radius, radius+1):
            xSquare = x**2
            if ((xSquare + ySquare) <= rSquare):

                z = round(math.sqrt(rSquare - xSquare - ySquare))
                my_illumination_model.set_surface_normal([x, y, z])


                # my_illumination_model.calculate_reflection_vector()

                

                color = my_illumination_model.get_hexcode()

                # -y instead of +y since y is upside down in Tkinter display coordinates
                w.create_line(xCenter+x, yCenter-y, xCenter+x+1, yCenter-y, fill = color)


# Define a drawing canvas and render the 20 spheres on it
root = Tk()
outerframe = Frame(root)
outerframe.pack()
w = Canvas(outerframe, width = CanvasWidth, height = CanvasHeight)
render20Spheres()
w.pack()
root.mainloop()
















            
