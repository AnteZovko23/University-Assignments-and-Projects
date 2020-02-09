from random import randint, choice

###########################################################################################
# Name: Ante Zovko
# Date: February 8th, 2020
# Description: A game of heads and tails with two coins where Group A gets a point if 
#              the result is two heads, Group B gets a point if the result is two tails
#              and the Prof gets a point if it is heads/tails or tails/heads
###########################################################################################


# The coin class
class Coin(object):

    headsSide = "Heads"
    tailsSide = "Tails"
        
    # Randomly returns heads or tails when called
    def flip(self):
        randomNumber = randint(0, 1)

        if(randomNumber == 1):
            return self.headsSide
        else:
            return self.tailsSide

# Two coin objects
firstCoin = Coin()
secondCoin = Coin()

# The game
# Params: Number of games and tosses per each game
# Returns: The result of every game as a list
def game(numOfGames, tossesPerGame):
    groupA = 0
    groupB = 0
    prof = 0
    allGames = []

    # The outer loop keeps track of games
    for i in range(numOfGames):   
        # The inner loop keeps track of tosses in each game
        for j in range(tossesPerGame):
            groupA, groupB, prof = coinTossTracker(groupA, groupB, prof)

        # Append each result to the list
        allGames.append(groupA)
        allGames.append(groupB)
        allGames.append(prof)
        
        # Reset scores for next game
        groupA, groupB, prof = 0, 0, 0

    return allGames
        
        
# Keeps track of the score for the game
# Params: The players' current score
# Return: The players' current score and the
# the player who won incremented by 1
def coinTossTracker(groupA, groupB, prof):
    if(firstCoin.flip() == "Heads" and secondCoin.flip() == "Heads"):
        return groupA + 1, groupB, prof

    elif(firstCoin.flip() == "Tails" and secondCoin.flip() == "Tails"):
        return groupA, groupB + 1, prof

    else:
        return groupA, groupB, prof + 1

# Handles Input from the client-side
# Params: The message for the user
def inputValidation(message):
    while True:
        try:
            desiredVar = int(raw_input(message))
            if(desiredVar <= 0):
                print("Please enter a number bigger than 0!\n")
                continue
            else:
                return desiredVar

        except ValueError:
            print("Please enter a number!\n")
            continue

# Creates the layout that displays the statistics
# about the game(s)
# Params: Number of games, tosses and the results of each game
def outputLayout(numOfGames, numOfTosses, allGames):

    # Keeps track of total wins
    groupAWins = 0
    groupBWins = 0
    profWins = 0
    
    # Casts to float for division
    numOfTosses = float(numOfTosses)
    flNumOfGames = float(numOfGames)

    # Displays each game and its statistics
    for i in range(numOfGames):
        print("Game {}\n Group A: {} ({}%); Group B: {} ({}%); Prof: {} ({}%);".format(i, allGames[i*3], round((allGames[i*3]/numOfTosses)*100, 2),allGames[(i*3)+1], 
        round((allGames[(i*3)+1]/numOfTosses)*100, 2),allGames[(i*3)+2], round((allGames[(i*3)+2]/numOfTosses)*100, 2)))
        
        # Creates a list with the three current scores
        wins = [allGames[i*3], allGames[(i*3)+1], allGames[(i*3)+2]]
        
        # Checks if there are duplicates by counting how many maxes there are
        if(wins.count(max(wins)) > 1):
            # Gets the duplicates positions in the list
            maxPositions = list_duplicates_of(wins, max(wins))
            # Picks a random winner
            winner = choice(maxPositions)
        else:
            # Winner is the player with the highest index
            winner = wins.index(max(wins))

        # Increments total wins for the
        # corresponding player
        if(winner == 0):
            groupAWins += 1
        elif(winner == 1):
            groupBWins += 1
        elif(winner == 2):
            profWins += 1

    # Displays the total statistics
    print("Wins: Group A: {} ({}%); Group B: {} ({}%); Prof: {} ({}%)".format(groupAWins, round((groupAWins/flNumOfGames)*100, 2), groupBWins, round((groupBWins/flNumOfGames)*100, 2),
    profWins, round((profWins/flNumOfGames)*100), 2))


# Finds the positions of duplicates in a list
# Params: list, item to find
# Returns: List of duplicate positions
# Found on: https://stackoverflow.com/questions/5419204/index-of-duplicates-items-in-a-python-list
def list_duplicates_of(seq,item):
    start_at = -1
    locs = []
    while True:
        try:
            loc = seq.index(item,start_at+1)
        except ValueError:
            break
            print("Here")
        else:
            locs.append(loc)
            start_at = loc
    return locs

# Main
numOfGames = inputValidation("How many games? ")
numOfTosses = inputValidation("How many coin tosses per game? ")

outputLayout(numOfGames, numOfTosses, game(numOfGames, numOfTosses))

