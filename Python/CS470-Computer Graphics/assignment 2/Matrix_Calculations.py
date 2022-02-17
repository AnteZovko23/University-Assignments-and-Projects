import math

"""
Author: Ante Zovko
Date: January 12th, 2022

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
    
    # print(x_center, y_center, z_center)
    
    return (x_center, y_center, z_center)



"""
Given two matrices, this function returns the product of the two matrices
"""
def matrix_multiplication(first_matrix, second_matrix):
    
    # If first matrix is a point expand its dimension and append a 1 to the last column
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