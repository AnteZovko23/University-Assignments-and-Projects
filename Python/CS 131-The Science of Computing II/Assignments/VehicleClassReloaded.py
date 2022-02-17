#########################################################
# Name: Ante Zovko
# Date: 1/27/2020
# Description: Implements vehicle, truck and car classes using OOP
#########################################################

# the vehicle class
# a vehicle has a year, make, and model
# a vehicle is instantiated with a make and model
class Vehicle(object):

    def __init__(self, make, model, year = 2000):
        self.make = make
        self.model = model
        if(year >= 2000 and year <= 2018):
            self._year = year
        else:
            self._year = 2000
       

    ###### Getters and Setters ######
    
    @property
    def make(self):
        return self._make
        
    @make.setter
    def make(self, value):
        self._make = value

    @property
    def model(self):
        return self._model

    @model.setter
    def model(self, value):
        self._model = value

    @property
    def year(self):
        return self._year

    @year.setter
    def year(self, value):
        if(value >= 2000 and value <= 2018):
            self._year = value
    
    def __str__(self):
        return "{} {} {}".format(self.year, self.make, self.model)


# the truck class
# a truck is a vehicle
# a truck is instantiated with a make and model
class Truck(Vehicle):
    def __init__(self, make, model, year = 2000):
        super(Truck, self).__init__(make, model, year)


# the car class
# a car is a vehicle
# a car is instantiated with a make and model
class Car(Vehicle):
    def __init__(self, make, model, year = 2000):
        super(Car, self).__init__(make, model, year)


# the Dodge Ram class
# a Dodge Ram is a truck
# a Dodge Ram is instantiated with a year
# all Dodge Rams have the same make and model
class DodgeRam(Truck):
    make = "Dodge"
    model = "Ram"

    def __init__(self, year):
        super(DodgeRam, self).__init__(DodgeRam.make, DodgeRam.model, year)


# the Honda Civic class
# a Honda Civic is a car
# a Honda Civic is instantiated with a year
# all Honda Civics have the same make and model
class HondaCivic(Car):
    make = "Honda"
    model = "Civic"

    def __init__(self, year):
        super(HondaCivic, self).__init__(HondaCivic.make, HondaCivic.model, year)



# the main part of the program
ram = DodgeRam(2016)
print ram

civic1 = HondaCivic(2007)
print civic1

civic2 = HondaCivic(1999)
print civic2

