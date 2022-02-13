from decimal import DivisionByZero
import math

from numpy import mat

"""
Author: Ante Zovko
Date: January 31th, 2022

This program provides useful matrix calculation functions like matrix multiplication and
calculating the visual center of a 3D object

It also provides the translation, scaling, and rotation matrices for left-handed system where +Z is away from the viewer
and rotation is occuring CCW when looking towards the origin from the negative region.

"""


"""
Given a point cloud of a 3D object, this function returns the visual center's x, y, and z coordinates
"""
def calculate_visual_center(point_cloud):
    
    # Get all x-coordinates from point cloud using a lambda function
    x_points = list(map(lambda point: point[0], point_cloud))
    
    # Get all y-coordinates from point cloud using a lambda function
    y_points = list(map(lambda point: point[1], point_cloud))
    
    # Get all z-coordinates from point cloud using a lambda function
    z_points = list(map(lambda point: point[2], point_cloud))
    
    
    # Get the minimum and maximum
    x_min = min(x_points)
    x_max = max(x_points)
    y_min = min(y_points)
    y_max = max(y_points)
    z_min = min(z_points)
    z_max = max(z_points)
    
    # Calculate the visual center
    x_center = (x_min + x_max)/2
    y_center = (y_min + y_max)/2
    z_center = (z_min + z_max)/2
    
    
    return (x_center, y_center, z_center)



"""
Given two matrices, this function returns the product of the two matrices
"""
def matrix_multiplication(first_matrix, second_matrix):
   
    # If first matrix is a point expand its dimension
    if not isinstance(first_matrix[0], list):
        first_matrix = [first_matrix]
        
        
    results = []    
    
    # For each column in the second matrix
    for cols in range(len(second_matrix[0])):
        # For each row in the first matrix
        for row in first_matrix:
            # Create a temporary sum
            temp_sum = 0
            # For each element in the row of the first matrix
            for index in range(len(row)):
                # Add the product of the row element and the column element of the second matrix to the temporary sum
                temp_sum += row[index] * second_matrix[index][cols]
            
            results.append(temp_sum)
            temp_sum = 0
    
    
    product = []
    
    # Prepare product matrix
    for i in range(len(first_matrix)):
        product.append([])

    # Fill product matrix
    # Above algorithm yields a 1D list, but the result must be a matrix with the number of rows of the first matrix 
    # and the number of columns of the second matrix
    for rows in range(len(first_matrix)):
        for cols in range(len(second_matrix[0])):
            # Append the appropriate value to the product matrix
            # The formula is always current rows + (number of rows in first matrix) * current cols
            product[rows].append(results[rows+(len(first_matrix) * cols)])
    
    
    
    return product
    
    
"""
Returns translation matrix
"""    
def get_translation_matrix(point):
    
    translation_matrix = [[       1,        0,        0, 0],
                          [       0,        1,        0, 0],
                          [       0,        0,        1, 0],
                          [point[0], point[1], point[2], 1]]
    
    return translation_matrix
        

"""
Returns scaling matrix
"""
def get_scaling_matrix(point):
    
    scaling_matrix = [[point[0],        0,        0, 0],
                      [       0, point[1],        0, 0],
                      [       0,        0, point[2], 0],
                      [       0,        0,        0, 1]]
    
    return scaling_matrix
    
    
"""
Returns rotation matrix
"""
def get_rotation_matrix(axis, angle):
    
    if axis == 'x':
        rotation_matrix = [[1,                                         0,                                        0, 0],
                           [0,  round(math.cos(math.radians(angle)), 10), round(math.sin(math.radians(angle)), 10), 0],
                           [0, round(-math.sin(math.radians(angle)), 10), round(math.cos(math.radians(angle)), 10), 0],
                           [0,                                         0,                                        0, 1]]
        
    elif axis == 'y':
        rotation_matrix = [[round(math.cos(math.radians(angle)), 10), 0, round(-math.sin(math.radians(angle)), 10), 0],
                           [                                       0, 1,                                         0, 0],
                           [round(math.sin(math.radians(angle)), 10), 0,  round(math.cos(math.radians(angle)), 10), 0],
                           [                            0, 0,                              0, 1]]
    
    elif axis == 'z':
        rotation_matrix = [[ round(math.cos(math.radians(angle)), 10), round(math.sin(math.radians(angle)), 10), 0, 0],
                           [round(-math.sin(math.radians(angle)), 10), round(math.cos(math.radians(angle)), 10), 0, 0],
                           [0                                        , 0                                       , 1, 0],
                           [0                                        , 0                                       , 0, 1]]
        
        
    return rotation_matrix



"""

Assignment 3 additions

"""

"""
Given 3 points, returns 2 vectors that share a common point (P0)
"""
def get_vectors(P0, P1, P2):
    
    # Get vectors
    P = [P1[0] - P0[0], P1[1] - P0[1], P1[2] - P0[2]]
    Q = [P2[0] - P0[0], P2[1] - P0[1], P2[2] - P0[2]]
    return P, Q

"""
Given a vector, returns the magnitude of the vector
"""
def get_magnitude(vector):
    
    # Get magnitude
    magnitude = math.sqrt(vector[0]**2 + vector[1]**2 + vector[2]**2)
    
    return magnitude

"""
Given a vector, returns the unit vector
"""
def get_normalized_vector(vector):
    
    # Get vector magnitude
    magnitude = get_magnitude(vector)
    
    # Divide each element in the vector by its magnitude
    try:
        vector_normalized = list(map(lambda x: x/magnitude, vector))
    except ZeroDivisionError:
        vector_normalized = [0,0,0]
    return vector_normalized

"""
Given two vectors, returns the cross product of the two vectors
"""
def get_cross_product(P, Q):
    
    # Get the first vector's x, y, and z coordinates
    Px = P[0]
    Py = P[1]
    Pz = P[2]
    
    # Get the second vector's x, y, and z coordinates
    Qx = Q[0]
    Qy = Q[1]
    Qz = Q[2]
    
    # Calculate the cross product
    Nx = Py * Qz - Pz * Qy
    Ny = Pz * Qx - Px * Qz
    Nz = Px * Qy - Py * Qx
    
    N = [Nx, Ny, Nz]
        
    # Return the cross product
    return N


"""
Given two vectors, returns the dot product of the two vectors
"""
def get_dot_product(N_norm, point):
    
    # Get the normal's x, y, and z coordinates
    Nx_norm = N_norm[0]
    Ny_norm = N_norm[1]
    Nz_norm = N_norm[2]
    
    # Get the point's x, y, and z coordinates
    Px = point[0]
    Py = point[1]
    Pz = point[2]
    
    # Calculate the dot product
    dot_product = Nx_norm * Px + Ny_norm * Py + Nz_norm * Pz
    
    return dot_product
    

"""
Given the viewpoint of the "eyeball" and a polygon, returns the if the polygon is visible from the viewpoint

The backface culling algorthim is done by computing the normal vector of the polygon, checking the position of plane in the space, and checking if the dot product of the normal vector and the view point - D is negative or 0
"""    
def back_face_culling_algorithm(Viewpoint, Polygon):
    
    # Get first three vertices of the polygon
    Polygon = Polygon[:3]
    
    # 1. Compute normal vector of the polygon and normalize it
    N_norm = get_surface_normal(Polygon)
    
        
    # 2. Compute the Plane Offset

    # Get position of the plane containing the polygon
    D = get_dot_product(N_norm, Polygon[0])
    
    
    # 3. Determine the Visibility of the Polygon from Viewpoint
    
    visibility_computation = get_dot_product(N_norm, Viewpoint) - D
    
    # Return visibility
    return visibility_computation > 0


"""
Given a polygon, returns the normalized surface normal of the polygon
"""
def get_surface_normal(Polygon):
    P, Q = get_vectors(Polygon[0], Polygon[1], Polygon[2])
    
    N = get_cross_product(P, Q)
    
    N_norm = get_normalized_vector(N)
    
    return N_norm
    
   
def add(matrix_1, matrix_2):
    
    # If first matrix is a point expand its dimension
    if not isinstance(matrix_1[0], list):
        matrix_1 = [matrix_1]
       
    # If second matrix is a point expand its dimension
    if not isinstance(matrix_2[0], list):
        matrix_2 = [matrix_2]   
        
    # Get the number of rows and columns in the matrices
    num_rows = len(matrix_1)
    num_cols = len(matrix_1[0])

    # Initialize the sum matrix
    sum_matrix = []

    # Add the elements of the matrices
    for i in range(num_rows):
        sum_matrix.append([])
        for j in range(num_cols):
            sum_matrix[i].append(matrix_1[i][j] + matrix_2[i][j])
            
    return sum_matrix[0]

def subtract(matrix_1, matrix_2):
    
    # If first matrix is a point expand its dimension
    if not isinstance(matrix_1[0], list):
        matrix_1 = [matrix_1]
       
    # If second matrix is a point expand its dimension
    if not isinstance(matrix_2[0], list):
        matrix_2 = [matrix_2] 
    
 
            
    # Get the number of rows and columns in the matrices
    num_rows = len(matrix_1)
    

    num_cols = len(matrix_1[0])

        
    # Initialize the difference matrix
    difference_matrix = []
    
    # Subtract the elements of the matrices
    for i in range(num_rows):
        difference_matrix.append([])
        for j in range(num_cols):
            difference_matrix[i].append(matrix_1[i][j] - matrix_2[i][j])
            
    return difference_matrix[0]




"""
Precomputes vertex normals of a cylinder
"""
def calculate_vertex_normals(Object_definition):
    # Poly 9 and 10 are the base and top of the cylinder
    poly_8 = Object_definition[8]
    poly_9 = Object_definition[9]
    
    poly_8_normal = get_surface_normal(poly_8)
    poly_9_normal = get_surface_normal(poly_9)
    
    poly_8_normal = get_normalized_vector(poly_8_normal)
    poly_9_normal = get_normalized_vector(poly_9_normal)
    
    # Get first 8 polygons
    Object_definition = Object_definition[:8]
    surface_normals = []
    # Calculate surface normals for each polygon
    for poly in Object_definition:
        surface_normals.append(get_surface_normal(poly))
        
    # Calculate vertex normals by adding surface normals
    vertex_normals = []
    # Perform addition between two adjacent surface normals
    for i in range(len(surface_normals)):
        vertex_normals.append(add(surface_normals[i], surface_normals[(i+1)%len(surface_normals)]))
        
    
    for i in range(len(vertex_normals)):
        vertex_normals[i] = get_normalized_vector(vertex_normals[i])
        
    # Group vertex normals as previous and next vertex normals starting at 1
    vertex_normals_grouped = {}

    for i in range(len(vertex_normals)):
        vertex_normals_grouped[f"{i}"] = [vertex_normals[(i-1)%len(vertex_normals)],vertex_normals[(i-1)%len(vertex_normals)], vertex_normals[i], vertex_normals[i]]

    # Assign polygon 9 and 10
    vertex_normals_grouped["8"] = [poly_8_normal]
    vertex_normals_grouped["9"] = [poly_9_normal]
    
    return vertex_normals_grouped
        