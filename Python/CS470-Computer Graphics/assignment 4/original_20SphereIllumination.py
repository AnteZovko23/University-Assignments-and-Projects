import math
from tkinter import *

CanvasWidth = 800
CanvasHeight = 600

Ia = .3 # intendity of the ambient light in the scene
Ip = .7 # intensity of the point light source in the scene
L = [1, 1, 1] # Lighting vector, 45 degree angle, Light is behind viewer's right shoulder [Right Hand Viewing System]
V = [0, 0 ,1] # View vector, points towards viewer/ center of projection [Right Hand Viewing System]

def render20Spheres(Ia, Ip, L, V):
    # Sphere Characteristics
    xCenter = 150       # xCenter of first sphere on each row
    yCenter = 112       # yCenter of first sphere on FIRST row
    radius = 50
    SpecIndex = [2, 4, 8, 16]
    Kd = 0      # Kd of first sphere on each row
    Ks = 1      # Ks of first sphere on each row

    # There will be four rows of spheres
    for row in range(4):
        # There will be five spheres per row
        for col in range(5):
            # Paint a sphere
            renderSphere(xCenter, yCenter, radius, Kd, Ks, SpecIndex[row], Ia, Ip, L, V)
            # Prep for next sphere on row
            Kd += 0.25
            Ks -= 0.25
            xCenter += 125
        # Prep for next row of spheres
        xCenter = 150       # reset xCenter
        yCenter += 125      # increment yCenter for next row
        Kd = 0                  # reset Kd
        Ks = 1                  # reset Ks

def renderSphere(xCenter, yCenter, radius, Kd, Ks, specIndex, Ia, Ip, L, V):
    # insure vectors passed to renderSphere are normalized unit vectors
    L = normalize(L)
    V = normalize(V)
    # ambient diffuse component of illumination model
    ambient = Ia * Kd
    # render a sphere
    rSquare = radius**2
    for y in range(-radius, radius+1):
        ySquare = y**2
        for x in range(-radius, radius+1):
            xSquare = x**2
            if ((xSquare + ySquare) <= rSquare):
                # x**2 + y**2 + z**2 = r**2
                z = round(math.sqrt(rSquare - xSquare - ySquare))
                N = normalize([x, y, z])
                NdotL = N[0]*L[0] + N[1]*L[1] + N[2]*L[2]
                if NdotL < 0 : NdotL = 0
                diffuse = Ip * Kd * NdotL
                R = reflect(N, L) # return vector is normalized in "reflect"
                RdotV = R[0]*V[0] + R[1]*V[1] + R[2]*V[2]
                if RdotV < 0: RdotV = 0
                specular = Ip * Ks * RdotV**specIndex
                color = triColorHexCode(ambient, diffuse, specular)
                # -y instead of +y since y is upside down in Tkinter display coordinates
                w.create_line(xCenter+x, yCenter-y, xCenter+x+1, yCenter-y, fill = color)

# generate a color hex code string from the illumination components
def triColorHexCode(ambient, diffuse, specular):
    combinedColorCode = colorHexCode(ambient + diffuse + specular)
    specularColorCode = colorHexCode(specular)
    colorString = "#" + specularColorCode + combinedColorCode + specularColorCode
    return colorString

def colorHexCode(intensity):
    hexString = str(hex(round(255* intensity)))
    if hexString[0] == "-": # illumination intensity should not be negative
        print("illumination instensity is Negative. Setting to 00. Did you check for negative NdotL?")
        trimmedHexString = "00"
    else:
        trimmedHexString = hexString[2:] # get rid of "0x" at beginning of hex strings
        # convert single digit hex strings to two digit hex strings
        if len(trimmedHexString) == 1: trimmedHexString = "0" + trimmedHexString
        # we will use the green color component to display our monochrome illumination results
    return trimmedHexString

# Calculate a 3-D reflection vecotr, R, given surface normal, N, and lighting vector, L
def reflect(N, L):
    R = []
    N = normalize(N)
    L = normalize(L)
    twoCosPhi = 2* (N[0]*L[0] + N[1]*L[1] + N[2]* L[2])
    if twoCosPhi > 0:
        for i in range(3):
            R.append(N[i] - (L[i]/twoCosPhi))
    elif twoCosPhi == 0:
        for i in range(3):
            R.append( -L[i])
    else:
        for i in range(3):
            R.append(- N[i] + (L[i]/twoCosPhi))
    return normalize(R)

# Convert an N dimensional vector into a unit vector (i.e., normalize the vector)
def normalize(vector):
    sumOfSquares = 0
    for i in range(len(vector)):
        sumOfSquares += vector[i]**2
    magnitude = math.sqrt(sumOfSquares)
    vect = []
    for i in range(len(vector)):
        vect.append(vector[i]/magnitude)
    return vect


# Define a drawing canvas and render the 20 spheres on it
root = Tk()
outerframe = Frame(root)
outerframe.pack()
w = Canvas(outerframe, width = CanvasWidth, height = CanvasHeight)
render20Spheres(Ia, Ip, L, V)
w.pack()
root.mainloop()
















            
