import Matrix_Calculations

Viewpoint = [0, 0, -500]


apex = [0,-100,100]
base1 = [50,-150,50]
base2 = [50,-150,150]
base3 = [-50,-150,150]
base4 = [-50,-150,50]

# Definition of the five polygon faces using the meaningful point names
# Polys are defined in clockwise order when viewed from the outside
frontpoly = [apex,base1,base4]
rightpoly = [apex,base2,base1]
backpoly = [apex,base3,base2]
leftpoly = [apex,base4,base3]
bottompoly = [base1,base2,base3,base4]

# Definition of the object
Pyramid = [bottompoly, frontpoly, rightpoly, backpoly, leftpoly]


for polygon in Pyramid:
    print(Matrix_Calculations.back_face_culling_algorithm(Viewpoint, polygon))