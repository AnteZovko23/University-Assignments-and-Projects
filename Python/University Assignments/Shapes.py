######################################################################################################################
# Name: Ante Zovko
# Date: 4/20/2020
# Description: Demonstration of Polymorphism by drawing different shapes
#              in the console
######################################################################################################################

# The shape class has a height and width
# If the parameters are less than 0 they are reset to 1
class Shape(object):
    def __init__(self, width = 1, height = 1):
        if(width > 0):
            self.width = width
        else:
            self.width = 1

        if(height > 0):
            self.height = height
        else:
            self.height = 1

######## Getters/Setters #########
    @property
    def width(self):
        return self._width

    @property
    def height(self):
        return self._height

    @width.setter
    def width(self, value):
        if(value > 0):
            self._width = value

    @height.setter
    def height(self, value):
        if(value > 0):
            self._height = value
        
###############################

    # Prints the shapes
    def __str__(self):
        drawing = ""
        for i in range(self.height):
            drawing += "* " * self.width + "\n"

        return drawing
            
# Rectangle Class
# Inherits from Shape
# Has width and Height
class Rectangle(Shape):
    def __init__(self, width, height):
        super(Rectangle, self).__init__(width, height)

# Square Class
# Inherits from Shape
# Width and Length the same - Area
class Square(Shape):
    def __init__(self, area):
        super(Square, self).__init__(area, area)


# Triangle Class
# Inherits from Shape
# Width and Length the same - Area
class Triangle(Shape):
    def __init__(self, area):
        super(Triangle, self).__init__(area, area)

    # Prints the shape
    def __str__(self):
        drawing = ""
        for i in range(self.width):
            drawing += "* " * (self.width - i) + "\n"

        return drawing
        
# Parallelogram Class
# Inherits from Shape
# Has width and Height
class Parallelogram(Shape):
    def __init__(self, width, height):
        super(Parallelogram, self).__init__(width, height)

    # Prints the shape
    def __str__(self):
        drawing = ""
        for i in range(self.height, 0, -1):
            drawing += "  " * (i - 1) + "* " * self.width + "\n"
        
        return drawing

        

##########################################################
# create and display several shapes
r1 = Rectangle(12, 4)
print r1
s1 = Square(6)
print s1
t1 = Triangle(7)
print t1
p1 = Parallelogram(10, 3)
print p1
r2 = Rectangle(0, 0)
print r2
p1.width = 2
p1.width = -1
p1.height = 2
print p1
