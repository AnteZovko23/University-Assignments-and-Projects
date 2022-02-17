######################################################################
# Name: Ante Zovko
# Date: 1/21/2020
# Description: Room escape game
#              Implementation using OOP
#              Goal is the earn points and escape the house by exploring
#              and using items
######################################################################
######################################################################
# the blueprint for a room
class Room(object):
# the constructor
    def __init__(self, name, unlocked = True):
# rooms have a name, exits (e.g., south), exit locations
# (e.g., to the south is room n), items (e.g., table), item
# descriptions (for each item), and grabbables (things that
# can be taken into inventory)
        self.name = name
        self.unlocked = unlocked
        self.exits = []
        self.exitLocations = []
        self.items = []
        self.itemDescriptions = []
        self.itemUnlocked = []
        self.grabbables = []
        self.itemGrabPairs = {
            "key": "table",
            "book": "desk",
            "6-pack": "brew_rig",
            "knife": "fridge",
            "wrench": "toolbox",
            "dungeon-key":"fireplace",
            "fire-extinguisher":"desk",
            "master-key":"table"
        }

# getters and setters for the instance variables
    @property
    def name(self):
        return self._name
        
    @name.setter
    def name(self, value):
        self._name = value
    
    @property
    def unlocked(self):
        return self._unlocked
        
    @unlocked.setter
    def unlocked(self, value):
        if(type(value) == bool):
            self._unlocked = value

    @property
    def exits(self):
        return self._exits

    @exits.setter
    def exits(self, value):
        self._exits = value

    @property
    def exitLocations(self):
        return self._exitLocations

    @exitLocations.setter
    def exitLocations(self, value):
        self._exitLocations = value

    @property
    def items(self):
        return self._items

    @items.setter
    def items(self, value):
        self._items = value

    @property
    def itemDescriptions(self):
        return self._itemDescriptions

    @itemDescriptions.setter
    def itemDescriptions(self, value):
        self._itemDescriptions = value

    @property
    def itemUnlocked(self):
        return self._itemUnlocked

    @itemUnlocked.setter
    def itemUnlocked(self, value):
        self._itemUnlocked = value

    @property
    def grabbables(self):
        return self._grabbables

    @grabbables.setter
    def grabbables(self, value):
        self._grabbables = value


    # adds an exit to the room
    # the exit is a string (e.g., north)
    # the room is an instance of a room
    def addExit(self, exit, room):
    # append the exit and room to the appropriate lists
        self._exits.append(exit)
        self._exitLocations.append(room)

    # adds an item to the room
    # the item is a string (e.g., table)
    # the desc is a string that describes the item (e.g., it is made
    # of wood)
    # the state is a boolean that describes if it is unlocked
    def addItem(self, item, desc, state):
    # append the item and description to the appropriate lists
        self._items.append(item)
        self._itemDescriptions.append(desc)
        self._itemUnlocked.append(state)
    
    # removes item
    def delItem(self, item):
        del self._itemDescriptions[self._items.index(item)]
        del self._itemUnlocked[self._items.index(item)]
        self._items.remove(item)

    # adds a grabbable item to the room
    # the item is a string (e.g., key)
    def addGrabbable(self, item):
    # append the item to the list
        self._grabbables.append(item)

    # removes a grabbable item from the room
    # the item is a string (e.g., key)
    def delGrabbable(self, item):
    # remove the item from the list
        self._grabbables.remove(item)


    def __str__(self):
        # first, the room name
        s = "You are in the {}.\n".format(self.name)
        # next, the items in the room
        s += "You see: "
        for item in self.items:
            s += item + " "
        s += "\n"

        # next, the exits from the room
        s += "Exits: "
        for exit in self.exits:
            s += exit + " "
        s += "\nScore: {}".format(score)
        return s

# creates the rooms
def createRooms():
    # r1 through r8 are the eight rooms in the mansion
    # currentRoom is the room the player is currently in (which can
    # be one of r1 through r8)
    global currentRoom
    global r7
    global r6
    global r0

    # create the rooms
    r0 = Room("Main Door", False)
    r1 = Room("Starting Room", True)
    r2 = Room("Living Room", True)
    r3 = Room("Library", True)
    r4 = Room("Work Room", True)
    r5 = Room("Kitchen", True)
    r6 = Room("Bathroom", True)
    r7 = Room("Dungeon", False)
    r8 = Room("Attic", True)


    # add exits to room 1
    r1.addExit("west", r0)
    r1.addExit("east", r2) # -> to the east of room 1 is room 2
    r1.addExit("south", r3)
    r1.addExit("up", r8)
    
    # add items to room 1
    r1.addItem("chair", "It is made of wicker and no one is sitting on it.", True)
    r1.addItem("table", "It is made of oak. A golden key rests on it.", True)
    r1.addItem("main-door", "It is a heavy steel door. Your only hope is to find the master key.", False)
    r1.addGrabbable("key")


    # add exits to room 2
    r2.addExit("west", r1)
    r2.addExit("south", r4)
    r2.addExit("east", r5)
    r2.addExit("down", r7)

    # add items to room 2
    r2.addItem("rug", "It is nice and Indian. It also needs to be vacuumed", True)
    r2.addItem("fireplace", "It is full of ashes. A dungeon-key is in the fire.", False)
    r2.addGrabbable("dungeon-key")


    # add exits to room 3
    r3.addExit("north", r1)
    r3.addExit("east", r4)
    r3.addExit("west", r6)

    # add items to room 3
    r3.addItem("bookshelves", "They are empty. Go figure.", True)
    r3.addItem("statue", "There is nothing special about it.", True)
    r3.addItem("desk", "The statue is resting on it. So is a book.", True)
    r3.addGrabbable("book")


    # add exits to room 4
    r4.addExit("north", r2)
    r4.addExit("west", r3)
    
    # add items to room 4
    r4.addItem("brew_rig", "Gourd is brewing some sort of oatmeal stout on the brew rig. A 6-pack is resting beside it.", True)
    r4.addGrabbable("6-pack")
    r4.addItem("toolbox", "A toolbox with a wrench inside", False)
    r4.addGrabbable("wrench")

    
    # add exits to room 5
    r5.addExit("west", r2)

    # add items to room 5
    r5.addItem("fridge", "Old and rusty fridge. You see a knife and rotten milk", True)
    r5.addItem("oven", "Old oven, looks broken. If only you had something to fix it.", True)
    r5.addGrabbable("knife")


    # add exits to room 6
    r6.addExit("east", r3)

    # add items to room 6
    r6.addItem("mirror", "You look in the mirror. Being trapped has really worn you down", True)
    r6.addItem("toilet", "If you are thirsty enough, you can always use toilet-water", True)


    # add exits to room 7
    r7.addExit("up", r2)

    # add items to room 7
    r7.addItem("serial-killer", "The dead body of the serial killer. Better make sure he is really dead.", False)
    r7.addItem("table", "The Serial-Killers work table. Pictures of his victims. There is a key labeled \"master-key\".", True)
    r7.addGrabbable("master-key")


    # add exits to room 8
    r8.addExit("down", r1)

    # add items to room 8
    r8.addItem("desk", "You see fire-extinguisher", True)
    r8.addItem("broken-tv", "Broken TV, covered in dust", True)
    r8.addGrabbable("fire-extinguisher")

    
    currentRoom = r1

# displays an appropriate "message" when the player wins
def win():
    print("\n\n")
    print("  .-----------.")
    print("  \'=== === ===\'")
    print("  .-\:      /-.")
    print(" | (|:.     |) |")       
    print("  \'-|:.     |-\'")         
    print("    \::.    /")          
    print("     \'::. .\'")           
    print("       ) (")             
    print("     _.\' \'._")           
    print("    `\"\"\"\"\"\"\"`")
    print("\nCongratulations!")
    print("You won.")
    print("Your score was: {}".format(score))
      

# displays an appropriate "message" when the player dies
# yes, this is intentionally obfuscated!
def death():
    print " " * 17 + "u" * 7
    print " " * 13 + "u" * 2 + "$" * 11 + "u" * 2
    print " " * 10 + "u" * 2 + "$" * 17 + "u" * 2
    print " " * 9 + "u" + "$" * 21 + "u"
    print " " * 8 + "u" + "$" * 23 + "u"
    print " " * 7 + "u" + "$" * 25 + "u"
    print " " * 7 + "u" + "$" * 25 + "u"
    print " " * 7 + "u" + "$" * 6 + "\"" + " " * 3 + "\"" + "$" * 3 + "\"" + " " * 3 + "\"" + "$" * 6 + "u"
    print " " * 7 + "\"" + "$" * 4 + "\"" + " " * 6 + "u$u" + " " * 7 + "$" * 4 + "\""
    print " " * 8 + "$" * 3 + "u" + " " * 7 + "u$u" + " " * 7 + "u" + "$" * 3
    print " " * 8 + "$" * 3 + "u" + " " * 6 + "u" + "$" * 3 + "u" + " " * 6 + "u" + "$" * 3
    print " " * 9 + "\"" + "$" * 4 + "u" * 2 + "$" * 3 + " " * 3 + "$" * 3 + "u" * 2 + "$" * 4 + "\""
    print " " * 10 + "\"" + "$" * 7 + "\"" + " " * 3 + "\"" + "$" * 7 + "\""
    print " " * 12 + "u" + "$" * 7 + "u" + "$" * 7 + "u"
    print " " * 13 + "u$\"$\"$\"$\"$\"$\"$u"
    print " " * 2 + "u" * 3 + " " * 8 + "$" * 2 + "u$ $ $ $ $u" + "$" * 2 + " " * 7 + "u" * 3
    print " u" + "$" * 4 + " " * 8 + "$" * 5 + "u$u$u" + "$" * 3 + " " * 7 + "u" + "$" * 4
    print " " * 2 + "$" * 5 + "u" * 2 + " " * 6 + "\"" + "$" * 9 + "\"" + " " * 5 + "u" * 2 + "$" * 6
    print "u" + "$" * 11 + "u" * 2 + " " * 4 + "\"" * 5 + " " * 4 + "u" * 4 + "$" * 10
    print "$" * 4 + "\"" * 3 + "$" * 10 + "u" * 3 + " " * 3 + "u" * 2 + "$" * 9 + "\"" * 3 + "$" * 3 + "\""
    print " " + "\"" * 3 + " " * 6 + "\"" * 2 + "$" * 11 + "u" * 2 + " " + "\"" * 2 + "$" + "\"" * 3
    print " " * 11 + "u" * 4 + " \"\"" + "$" * 10 + "u" * 3
    print " " * 2 + "u" + "$" * 3 + "u" * 3 + "$" * 9 + "u" * 2 + " \"\"" + "$" * 11 + "u" * 3 + "$" * 3
    print " " * 2 + "$" * 10 + "\"" * 4 + " " * 11 + "\"\"" + "$" * 11 + "\""
    print " " * 3 + "\"" + "$" * 5 + "\"" + " " * 22 + "\"\"" + "$" * 4 + "\"\""
    print " " * 5 + "$" * 3 + "\"" + " " * 25 + "$" * 4 + "\""
######################################################################
# START THE GAME!!!
score = 0
inventory = [] # nothing in inventory...yet
usables = []

createRooms() # add the rooms to the game

# Usable items and the items they can be used on
usablePairs = {
    "wrench": "oven",
    "key": "toolbox",
    "fire-extinguisher":"fireplace",
    "toilet-water": "toilet",
    "knife":"serial-killer",
    "dungeon-key": r7,
    "master-key": r0
}


# play forever (well, at least until the player dies or asks to quit or wins)
while (True):
    # set the status so the player has situational awareness
    # the status has room and inventory information
    status = "{}\nYou are carrying: {}\n".format(currentRoom, inventory)
    # if the current room is None, then the player is dead
    # this only happens if the player goes south when in room 4
    if (currentRoom == None):
        status = "You are dead. \nScore: {}".format(score)
    # display the status
    print "========================================================="
    print status

    # if the current room is None (and the player is dead), exit the
    # game
    if (currentRoom == None):
        death()
        break
    # if the current room is r0, exit the
    # game and display win message
    if(currentRoom == r0):
        win()
        break
    # prompt for player input
    # the game supports a simple language of <verb> <noun>
    # valid verbs are go, look, and take
    # valid nouns depend on the verb
    # we use raw_input here to treat the input as a string instead of
    # a numeric value
    action = raw_input("What to do? ")
    # set the user's input to lowercase to make it easier to compare
    # the verb and noun to known values
    action = action.lower()
    # exit the game if the player wants to leave (supports quit,
    # exit, and bye)
    if (action == "quit" or action == "exit" or action == "bye"):
        break
    # set a default response
    response = "I don't understand. Try verb noun. Valid verbs are go, look, and take"
    # split the user input into words (words are separated by spaces)
    words = action.split()
    # the game only understands two word inputs
    if (len(words) == 2):
        # isolate the verb and noun
        verb = words[0]
        noun = words[1]
        # the verb is: go
        if (verb == "go"):
            # set a default response
            response = "Invalid exit."

            # If in the dungeon and the player didn't use the knife on the killer
            # the player dies
            if(currentRoom == r7 and not(r7.itemUnlocked[0])):
                currentRoom = None
                print("\nSerial-Killer killed you.")
                continue
               


            # check for valid exits in the current room
            for i in range(len(currentRoom.exits)):
                # a valid exit is found and it is unlocked
                if (noun == currentRoom.exits[i] and currentRoom.exitLocations[i].unlocked):
                    # change the current room to the one that is
                    # associated with the specified exit
                    currentRoom = currentRoom.exitLocations[i]
                    # set the response (success)
                    response = "Room changed."
                    
                    # no need to check any more exits
                    break
                elif(noun == currentRoom.exits[i]):
                    response = "The {} is locked".format(currentRoom.exitLocations[i].name)
                    break
        # the verb is: look
        elif (verb == "look"):
            # set a default response
            response = "I don't see that item."

            # If in the dungeon and the player looks at something, the killer kills him
            if(currentRoom == r7 and not(r7.itemUnlocked[0]) and noun in currentRoom.items):
                print("\nYou looked at the {} while the serial-killer was killing you.\nNice last image".format(noun))
                currentRoom = None
                continue

            # check for valid items in the current room
            for i in range(len(currentRoom.items)):
                # a valid item is found and unlocked
                if (noun == currentRoom.items[i] and currentRoom.itemUnlocked[i]):
                # set the response to the item's description
                    response = currentRoom.itemDescriptions[i]
                    # no need to check any more items
                    break
                elif(noun == currentRoom.items[i]):
                    if(noun == "fireplace" or noun == "main-door"):
                        response = currentRoom.itemDescriptions[i]
                    else:
                        response = "The {} is locked".format(currentRoom.items[i])
                    break
        # the verb is: take
        elif (verb == "take"):
            # set a default response
            response = "I don't see that item or cannot get it yet."

            # If in the dungeon and the player tries to take the master key without fighting the killer
            if(currentRoom == r7 and not(r7.itemUnlocked[0]) and noun in currentRoom.grabbables):
                print("\nYou tried to take the {} while the serial-killer was killing you.\nDid you really think that would work?".format(noun))
                currentRoom = None
                continue

            # check for valid grabbable items in the current room
            for grabbable in currentRoom.grabbables:
                # a valid grabbable item is found
                if (noun == grabbable and currentRoom.itemUnlocked[currentRoom.items.index(currentRoom.itemGrabPairs[noun])]):
                    # add the grabbable item to the player's
                    # inventory
                    inventory.append(grabbable)
                    score += 1

                    # Special case where the grabbable item is also usable
                    if(grabbable in usablePairs.keys()):
                        usables.append(grabbable)

                    # remove the grabbable item from the room
                    currentRoom.delGrabbable(grabbable)
                    
                    # Update item when grabbable is grabbed
                    currentRoom.delItem(currentRoom.itemGrabPairs[grabbable])
                    currentRoom.addItem(currentRoom.itemGrabPairs[grabbable], "{}, you have taken a {} from it.".format(currentRoom.itemGrabPairs[grabbable].capitalize(), grabbable), True)

                    # set the response (success)
                    response = "Item grabbed."
                    # no need to check any more grabbable items
                    break
        elif (verb == "use"):
            # set a default response
            response = "Cannot use that item."

            # Special death case if the player drinks toilet water
            if(noun == "toilet-water" and currentRoom == r6):
                    score = 0
                    print("\n\nYou drink toilet water and...\n\nYou die\nWhat a surprise that toilet water from an abandoned building\nis poisonus\nYou lose all points because you should know better!")
                    currentRoom = None
                    continue
            
            # If in the dungeon and the player tries to use something other than a knife, he dies
            if(currentRoom == r7 and not(r7.itemUnlocked[0]) and noun != "knife" and noun in usables):
                currentRoom = None
                print("\nYou used a {} to fight a serial-killer\nIt was not effective at all".format(noun))
                continue

            # check for valid usable items to be used in the current room
            try:
                if((usablePairs[noun] in currentRoom.items or usablePairs[noun] in currentRoom.exitLocations) and noun in usables):

                    response = "{} used on the {}\nThe {} is unlocked now".format(noun.capitalize(), usablePairs[noun], usablePairs[noun])
                    
                    # Special case for attacking the killer
                    if(usablePairs[noun] == "serial-killer"):
                        score += 4
                        currentRoom.itemUnlocked[currentRoom.items.index(usablePairs[noun])] = True
                        response = "{} used on the {}\nThe {} is dead now.".format(noun.capitalize(), usablePairs[noun], usablePairs[noun])
                    # Usable used on either dungeon door or main door
                    elif(usablePairs[noun] in currentRoom.exitLocations):
                        if(noun == "dungeon-key"):
                            score += 3
                            r7.unlocked = True
                            response = "{} used on the {}\nThe {} is unlocked now".format(noun.capitalize(), "Dungeon Door", "door")
                        else:
                            score += 5
                            r0.unlocked = True
                            response = "{} used on the {}\nThe {} is unlocked now".format(noun.capitalize(), "Main Door", "door")
                    # usable used on regular items
                    elif(usablePairs[noun] in currentRoom.items):
                        score += 2
                        currentRoom.itemUnlocked[currentRoom.items.index(usablePairs[noun])] = True

                    inventory.remove(noun)
                    usables.remove(noun)


                    # Special death case when the player tries to fix the oven
                    if(usablePairs[noun] == "oven"):
                        response = "While fixing the oven faulty wires cause a short-circuit and it explodes"
                        currentRoom = None
            except:
                print("\nItem not in inventory.")
                continue
    # display the response
    print "\n{}".format(response)