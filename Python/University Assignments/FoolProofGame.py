from random import randint, choice

# Name: Ante Zovko
# Date 2/4/2020
# TODO: Documentation

class Coin(object):

    headsSide = "Heads"
    tailsSide = "Tails"
        

    def flip(self):
        randomNumber = randint(0, 1)

        if(randomNumber == 1):
            return self.headsSide
        else:
            return self.tailsSide

firstCoin = Coin()
secondCoin = Coin()


def game(numOfGames, tossesPerGame):
    groupA = 0
    groupB = 0
    prof = 0
    allGames = []

    for i in range(numOfGames):   
        for j in range(tossesPerGame):
            groupA, groupB, prof = coinTossTracker(groupA, groupB, prof)

        allGames.append(groupA)
        allGames.append(groupB)
        allGames.append(prof)

        groupA, groupB, prof = 0, 0, 0

    return allGames
        
        

def coinTossTracker(groupA, groupB, prof):
    if(firstCoin.flip() == "Heads" and secondCoin.flip() == "Heads"):
        return groupA + 1, groupB, prof

    elif(firstCoin.flip() == "Tails" and secondCoin.flip() == "Tails"):
        return groupA, groupB + 1, prof

    else:
        return groupA, groupB, prof + 1


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

def outputLayout(numOfGames, numOfTosses, allGames):

    groupAWins = 0
    groupBWins = 0
    profWins = 0
    
    numOfTosses = float(numOfTosses)
    flNumOfGames = float(numOfGames)

    for i in range(numOfGames):
        print("Game {}\n Group A: {} ({}%); Group B: {} ({}%); Prof: {} ({}%);".format(i, allGames[i*3], round((allGames[i*3]/numOfTosses)*100, 2),allGames[(i*3)+1], 
        round((allGames[(i*3)+1]/numOfTosses)*100, 2),allGames[(i*3)+2], round((allGames[(i*3)+2]/numOfTosses)*100, 2)))
        
        wins = [allGames[i*3], allGames[(i*3)+1], allGames[(i*3)+2]]
        if(wins.count(max(wins)) > 1):
            maxPositions = list_duplicates_of(wins, max(wins))
            winner = choice(maxPositions)
        else:
            winner = wins.index(max(wins))

        if(winner == 0):
            groupAWins += 1
        elif(winner == 1):
            groupBWins += 1
        elif(winner == 2):
            profWins += 1

    print("Wins: Group A: {} ({}%); Group B: {} ({}%); Prof: {} ({}%)".format(groupAWins, round((groupAWins/flNumOfGames)*100, 2), groupBWins, round((groupBWins/flNumOfGames)*100, 2),
    profWins, round((profWins/flNumOfGames)*100), 2))


# Found on:
# https://stackoverflow.com/questions/5419204/index-of-duplicates-items-in-a-python-list
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

