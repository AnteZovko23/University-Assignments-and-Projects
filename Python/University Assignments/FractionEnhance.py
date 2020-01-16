######################################################################################################################
# Name: 
# Date: 
# Description: 
######################################################################################################################

# the fraction class
class Fraction(object):
    def __init__(self, num = 0, den = 1):
        self.num = num
        self.den = den
        self.fractionReduce()

####### Class Methods #######


    def fractionReduce(self):
        commonFactor = 1
        for i in range(2, abs(self.num * self.den)):
            if(self.num % i == 0 and self.den % i == 0):
                commonFactor = i
                self.num /= commonFactor
                self.den /= commonFactor

    def getDecimalValue(self):
        return (float(self.num))/float((self.den))

###################################
    
###### Getters and Setters ######

    @property
    def num(self):
        return self._num

    @num.setter
    def num(self, value):
        self._num = value

    @property
    def den(self):
        return self._den
    
    @den.setter
    def den(self, value):
        if(value == 0):
            self._den = 1
        else:
            self._den = value

#####################################

########## Special Functions/Overloading #########

    def __add__(self, other):
        den = self.den * other.den
        num = (self.num * (den / self.den)) + (other.num * (den / other.den))
        
        addedFraction = Fraction(num, den)

        addedFraction.fractionReduce()
        

        return addedFraction

    
    def __sub__(self, other):
        den = self.den * other.den
        num = (self.num * (den / self.den)) - (other.num * (den / other.den))
        
        subtractedFraction = Fraction(num, den)

        subtractedFraction.fractionReduce()

        return subtractedFraction

    def __mul__(self, other):
        num = self.num * other.num
        den = self.den * other.den

        multipliedFraction = Fraction(num, den)

        multipliedFraction.fractionReduce()

        return multipliedFraction

    def __div__(self, other):
        if(other.getDecimalValue() == 0):
            return ("ERROR! Cannot divide by 0")
        else:
            num = self.num * other.den
            den = self.den * other.num

            dividedFraction = Fraction(num, den)

            dividedFraction.fractionReduce()

            return dividedFraction
    

    def __str__(self):
        return("{}/{} ({})".format(self.num, self.den, self.getDecimalValue()))

    
    
#####################################
                
        

    
   
            
# ***DO NOT MODIFY OR REMOVE ANYTHING BELOW THIS POINT!***
# the main part of the program
# create some fractions
f1 = Fraction()
f2 = Fraction(5, 8)
f3 = Fraction(3, 4)
f4 = Fraction(1, 0)

# # display them
print "f1:", f1
print "f2:", f2
print "f3:", f3
print "f4:", f4


# play around
f3.num = 5
f3.den = 8
f1 = f2 + f3
f4.den = 88
print(f1)
f2 = f1 - f1
f3 = f1 * f1
f4 = f4 / f3

# display them again
print
print "f1:", f1
print "f2:", f2
print "f3:", f3
print "f4:", f4

