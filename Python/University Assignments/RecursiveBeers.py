###########################################################################################
# Name: Ante Zovko
# Date:  October 23, 2019
# Description: Demonstrates the difference between iteration and recursion using
#              the bottles of beer on the wall game
###########################################################################################

# The algorithm implemented iteratively

#def passSomeBeers(n):
#	while (n > 0):
#		print "{} bottles of beer on the wall.".format(n)
#		print "{} bottles of beer.".format(n)
#		print "Take one down, pass it around."
#		n = n - 1
#		print "{} bottles of beer on the wall.".format(n)
#		print


# The algorithm implemented recursively
def passSomeBeers(n):

    if(n < 1):
        print("The parameter needs to be bigger than 0")
        raise SystemExit(0)

    if(n == 1):
        print("{} bottle of beer on the wall.".format(n))
        print("{} bottle of beer.".format(n))
        print("Take on down, pass it around.")
        n -= 1
        print("{} bottles of beer on the wall.".format(n))
        print
    elif(n == 2):
        print("{} bottles of beer on the wall.".format(n))
        print("{} bottles of beer.".format(n))
        print("Take on down, pass it around.")
        n -= 1
        print("{} bottle of beer on the wall.".format(n))
        print
        passSomeBeers(n)
    else:
        print("{} bottles of beer on the wall.".format(n))
        print("{} bottles of beer.".format(n))
        print("Take on down, pass it around.")
        n -= 1
        print("{} bottles of beer on the wall.".format(n))
        print
        passSomeBeers(n)
   



###############################################
# MAIN PART OF THE PROGRAM
###############################################
passSomeBeers(99)
