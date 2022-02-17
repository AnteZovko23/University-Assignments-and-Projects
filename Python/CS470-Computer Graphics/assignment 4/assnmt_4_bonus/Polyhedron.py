"""
Author: Ante Zovko
Date: January 31, 2022

This program is allows for the creation of a 3D wire-frame tetrahedron, cube, cylinder and octahedron

A 1 was added for every point for matrix calculations

"""
class Polyhedron(object):

    """
    Returns a tetrahedron object
    
    The point_dictionary specifies the individual points
    By default if will be in the center of the coordinate system
    
    The polygons are defined in clockwise order when viewed from the outside
    
    Returns a tuple of the object and the point cloud
    
    """
    def tetrahedron(self, point_dictionary={'apex': [0,50,100, 1],
                                        'base1': [50,-50,50, 1],
                                        'base2': [50,-50,150, 1],
                                        'base3': [-50,-50,50, 1],
                                        }):
        
        
        # Definition  of the five underlying points
        apex = point_dictionary['apex']
        base1 = point_dictionary['base1']
        base2 = point_dictionary['base2']
        base3 = point_dictionary['base3']
        
        
        # Definition of the faces using the meaningful point names
        frontpoly = [apex,base1,base3]
        rightpoly = [apex,base2,base1]
        backpoly = [apex,base3,base2]
        leftpoly = [apex,base3,base2]
        bottompoly = [base1,base2,base3]

        # Definition of the object
        Tetrahedron = [bottompoly, frontpoly, rightpoly, backpoly, leftpoly]

        # Definition of the Tetrahedron's underlying point cloud.  No structure, just the points.
        TetrahedronPointCloud = [apex, base1, base2, base3]

        return Tetrahedron, TetrahedronPointCloud
    
        
    """
    Returns a cube object
    
    The point_dictionary specifies the individual points
    By default if will be in the center of the coordinate system
    
    The polygons are defined in clockwise order when viewed from the outside
    
    Returns a tuple of the object and the point cloud
    
    """
    def cube(self, point_dictionary={   'base1': [50,-50,50, 1],
                                        'base2': [50,-50,150, 1],
                                        'base3': [-50,-50,50, 1],
                                        'base4': [-50,-50,150, 1],
                                        'base5': [50,50,50, 1],
                                        'base6': [50,50,150, 1],
                                        'base7': [-50,50,50, 1],
                                        'base8': [-50,50,150, 1]}):
        
        
        # Definition  of the five underlying points
        base1 = point_dictionary['base1']
        base2 = point_dictionary['base2']
        base3 = point_dictionary['base3']
        base4 = point_dictionary['base4']
        base5 = point_dictionary['base5']
        base6 = point_dictionary['base6']
        base7 = point_dictionary['base7']
        base8 = point_dictionary['base8']
        
        

        bottompoly = [base1, base2, base4, base3]
        toppoly = [base5,base7,base8,base6]
        frontpoly = [base1, base3, base7, base5]
        rightpoly = [base1, base5, base6, base2]
        leftpoly = [base3, base4, base8, base7]
        backpoly = [base2, base6, base8, base4]
        
        # Definition of the object
        Cube = [bottompoly, frontpoly, rightpoly, backpoly, leftpoly, toppoly]

        # Definition of the Cube's underlying point cloud.  No structure, just the points.
        CubePointCloud = [base1, base2, base3, base4, base5, base6, base7, base8]
        
        return Cube, CubePointCloud

    """
    Returns an octahedron object
    
    The point_dictionary specifies the individual points
    By default if will be in the center of the coordinate system
    
    The polygons are defined in clockwise order when viewed from the outside
    
    Returns a tuple of the object and the point cloud
    
    """    
    def octahedron(self, point_dictionary={'apex': [0,25,100, 1],
                                        'apex2': [0,-125,100, 1],
                                        'base1': [50,-50,50, 1],
                                        'base2': [50,-50,150, 1],
                                        'base3': [-50,-50,50, 1],
                                        'base4': [-50,-50,150, 1]}):
        
        
        # Definition  of the underlying points
        apex = point_dictionary['apex']
        apex2 = point_dictionary['apex2']
        base1 = point_dictionary['base1']
        base2 = point_dictionary['base2']
        base3 = point_dictionary['base3']
        base4 = point_dictionary['base4']
        
        
        # Definition of the polygon faces
        topfrontpoly = [apex,base1,base3]
        toprightpoly = [apex,base2,base1]
        topbackpoly = [apex,base4,base2]
        topleftpoly = [apex,base3,base4]
        bottomfrontpoly = [apex2, base3, base1]
        bottomrightpoly = [apex2, base1, base2]
        bottombackpoly = [apex2, base2, base4]
        bottomleftpoly = [apex2, base4, base3]
        
        
        # Definition of the object
        Octahedron = [topfrontpoly, toprightpoly, topbackpoly, topleftpoly, bottombackpoly, bottomfrontpoly, bottomrightpoly, bottomleftpoly]

        # Definition of the Octahedron's underlying point cloud.
        OctahedronPointCloud = [apex, base1, base2, base3, base4, apex2]

        return Octahedron, OctahedronPointCloud
    
    """
    Returns a pyramid object
    
    The point_dictionary specifies the individual points
    By default if will be in the center of the coordinate system
    
    The polygons are defined in clockwise order when viewed from the outside
    
    Returns a tuple of the object and the point cloud
    
    """    
    def pyramid(self):
        
        # Definition  of the five underlying points
        apex = [0,50,100, 1]
        base1 = [50,-50,50, 1]
        base2 = [50,-50,150, 1]
        base3 = [-50,-50,150, 1]
        base4 = [-50,-50,50, 1]

        # Definition of the five polygon faces using the meaningful point names
        # Polys are defined in clockwise order when viewed from the outside
        frontpoly = [apex,base1,base4]
        rightpoly = [apex,base2,base1]
        backpoly = [apex,base3,base2]
        leftpoly = [apex,base4,base3]
        bottompoly = [base1,base2,base3,base4]

        # Definition of the object
        Pyramid = [bottompoly, frontpoly, rightpoly, backpoly, leftpoly]

        # Definition of the Pyramid's underlying point cloud.  No structure, just the points.
        PyramidPointCloud = [apex, base1, base2, base3, base4]

        return Pyramid, PyramidPointCloud                
    
    
    def cylinder(self):
        # ***************************** Initialize Cylinder Object ***************************
        # Definition of the 16 underlying points
        front1 = [-50,120.7107,50, 1]
        front2 = [50,120.7107,50, 1]
        front3 = [120.7107,50,50, 1]
        front4 = [120.7107,-50,50,1]
        front5 = [50,-120.7107,50, 1]
        front6 = [-50,-120.7107,50, 1]
        front7 = [-120.7107,-50,50, 1]
        front8 = [-120.7107,50,50, 1]
        back1 = [-50,120.7107,450, 1]
        back2 = [50,120.7107,450, 1]
        back3 = [120.7107,50,450, 1]
        back4 = [120.7107,-50,450, 1]
        back5 = [50,-120.7107,450, 1]
        back6 = [-50,-120.7107,450, 1]
        back7 = [-120.7107,-50,450, 1]
        back8 = [-120.7107,50,450, 1]
        # Definition of the ten polygon faces using the meaningful point names
        # Polys are defined in clockwise order when viewed from the outside
        northPoly = [front1, back1, back2, front2]
        northEastPoly = [front2, back2, back3, front3]
        eastPoly = [front3, back3, back4, front4]
        southEastPoly = [front4, back4, back5, front5]
        southPoly = [front5, back5, back6, front6]
        southWestPoly = [front6, back6, back7, front7]
        westPoly = [front7, back7, back8, front8]
        northWestPoly = [front8, back8, back1, front1]
        frontPoly = [front1, front2, front3, front4, front5, front6, front7, front8]
        backPoly = [back1, back8, back7, back6, back5, back4, back3, back2]
        
        # Point cloud
        CylinderPointCloud = [front1, front2,   front3, front4, front5, front6, front7, front8, back1, back2, back3, back4, back5, back6, back7, back8]
        
        # Definition of the cylinder object
        Cylinder = [northPoly, northEastPoly, eastPoly, southEastPoly, southPoly, southWestPoly, westPoly, northWestPoly,
        frontPoly, backPoly]
        
        return Cylinder, CylinderPointCloud