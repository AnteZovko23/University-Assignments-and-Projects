######################################################################
# Name: Ante Zovko
# Date: 4/14/2020
# Description: Room escape game
#              Implementation using OOP and GUI using Tkinter
#              Goal is the earn points and escape the house by exploring
#              and using items
######################################################################
######################################################################
from Tkinter import *


# The blueprint for a room
# rooms have a name, exits (e.g., south), exit locations
# (e.g., to the south is room n), items (e.g., table), item
# descriptions (for each item), images,  and grabbables (things that
# can be taken into inventory)
class Room(object):
    def __init__(self, name, image, unlocked = True):
        self.name = name
        self.image = image
        self.exits = {}
        self.items = {}
        self.grabbables = []
        self.unlocked = unlocked
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
        self.itemUnlocked = {}
       
########### Getters/Setters ###############
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
        self._unlocked = value

    @property
    def itemUnlocked(self):
        return self._itemUnlocked

    @itemUnlocked.setter
    def itemUnlocked(self, value):
        self._itemUnlocked = value

    @property
    def image(self):
        return self._image

    @image.setter
    def image(self, value):
        self._image = value

    @property
    def exits(self):
        return self._exits

    @exits.setter
    def exits(self, value):
        self._exits = value

    @property
    def items(self):
        return self._items

    @items.setter
    def items(self, value):
        self._items = value

    @property
    def grabbables(self):
        return self._grabbables

    @grabbables.setter
    def grabbables(self, value):
        self._grabbables = value
##########################################


    # adds an exit to the room
    def addExit(self, exit, room):
        self._exits[exit] = room

    # Append the item and description to the appropriate
    # dictionary
    # Append if it is locked or unlocked
    def addItem(self, item, desc, state):
        self._items[item] = desc
        self._itemUnlocked[item] = state

    # Removes item
    def delItem(self, item):
        del self._items[item]
        del self._itemUnlocked[item]

    # Adds Grabbable
    def addGrabbable(self, item):
        self._grabbables.append(item)

    # Delets Grabbable
    def delGrabbable(self, item):
        self._grabbables.remove(item)

    # Prints the status of the game: Inventory, score, exits
    def __str__(self):
        s = "You are in {}.\n".format(self.name)

        s += "You see: "
        for item in self.items.keys():
            s += item + " "
        s += "\n"
        s += "Exits: "

        for exit in self.exits.keys():
            s += exit + " "
        s += "\nScore: {}".format(Game.score)
        return s

# Game Class
# Inherits from Frame
class Game(Frame):
    def __init__(self, parent):
        Frame.__init__(self, parent)
    
    # r1 through r8 are the eight rooms in the mansion
    # currentRoom is the room the player is currently in (which can
    # be one of r1 through r8)
    # r0 is where the player wins the game
    def createRooms(self):

        global currentRoom
        global r7
        global r6
        global r0

        r0 = Room("Main Door", "Images/MainDoor.gif", False)
        r1 = Room("Starting Room", "Images/StartingRoom.gif")
        r2 = Room("Living Room", "Images/LivingRoom.gif")
        r3 = Room("Library", "Images/Library.gif")
        r4 = Room("Work Room", "Images/WorkRoom.gif")
        r5 = Room("Kitchen", "Images/Kitchen.gif")
        r6 = Room("Bathroom", "Images/Bathroom.gif")
        r7 = Room("Dungeon", "Images/Dungeon.gif", False)
        r8 = Room("Attic", "Images/Attic.gif")


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
        # set room 1 as the current room at the beginning of the
        #    game
        Game.currentRoom = r1
        # initialize the player's inventory
        Game.inventory = []

        # Usable and usable pairs
        Game.usables = []
        Game.usablePairs = {
            "wrench": "oven",
            "key": "toolbox",
            "fire-extinguisher":"fireplace",
            "toilet-water": "toilet",
            "knife":"serial-killer",
            "dungeon-key": r7,
            "master-key": r0
        }
        # Grabbable Pairs
        Game.itemGrabPairs = {
            "key": "table",
            "book": "desk",
            "knife": "fridge",
            "wrench": "toolbox",
            "dungeon-key":"fireplace",
            "fire-extinguisher":"desk",
            "master-key":"table"
        }
        # Score
        Game.score = 0

    def setupGUI(self):
        # organize the GUI
        self.pack(fill=BOTH, expand=1)
        # setup the player input at the bottom of the GUI

        # the widget is a Tkinter Entry
        # set its background to white and bind the return key to the
        # function process in the class
        # push it to the bottom of the GUI and let it fill
        # horizontally
        # give it focus so the player doesn't have to click on it
        Game.player_input = Entry(self, bg="white")
        Game.player_input.bind("<Return>", self.process)
        Game.player_input.pack(side=BOTTOM, fill=X)
        Game.player_input.focus()
        # setup the image to the left of the GUI
        # the widget is a Tkinter Label
        # don't let the image control the widget's size
        img = None
        Game.image = Label(self, width=WIDTH / 2, image=img)
        Game.image.image = img
        Game.image.pack(side=LEFT, fill=Y)
        Game.image.pack_propagate(False)
        # setup the text to the right of the GUI
        #    first, the frame in which the text will be placed
        text_frame = Frame(self, width=WIDTH / 2)
        # the widget is a Tkinter Text
        # disable it by default
        # don't let the widget control the frame's size
        Game.text = Text(text_frame, bg="lightgrey", state=DISABLED)
        Game.text.pack(fill=Y, expand=1)
        text_frame.pack(side=RIGHT, fill=Y)
        text_frame.pack_propagate(False)

    def setRoomImage(self):
        if (Game.currentRoom == None):
        # if dead, set the skull image
            Game.img = PhotoImage(file="Images/Skull.gif")
        # If in r0, Outside image
        elif(Game.currentRoom == r0):
            Game.img = PhotoImage(file="Images/Outside.gif")
        else:
        # otherwise grab the image for the current room
            Game.img = PhotoImage(file=Game.currentRoom.image)
        # display the image on the left of the GUI
        Game.image.config(image=Game.img)
        Game.image.image = Game.img

    def setStatus(self, status):
        # enable the text widget, clear it, set it, and disabled it
        Game.text.config(state=NORMAL)
        Game.text.delete("1.0", END)
        if (Game.currentRoom == None):
        # if dead, let the player know
            Game.text.insert(END, "You are dead. The only thing you can do now is quit.\nScore: {}".format(Game.score))
        # If the player won, let them know
        elif(Game.currentRoom == r0):
            Game.text.insert(END, "Congratulations!. You escaped. \nScore: {}".format(Game.score))
        else:
        # otherwise, display the appropriate status
            Game.text.insert(END, str(Game.currentRoom) + "\nYou are carrying: " + str(Game.inventory) + "\n\n" + status)
        Game.text.config(state=DISABLED)

    # Start the game
    def play(self):
        self.createRooms()
        self.setupGUI()
        self.setRoomImage()
        self.setStatus("")

    def process(self, event):
        # grab the player's input from the input at the bottom of
        # the GUI
        action = Game.player_input.get()
        # set the user's input to lowercase to make it easier to
        # compare the verb and noun to known values
        action = action.lower()
        # set a default response
        response = "I don't understand. Try verb noun. Valid verbs are go, look, use, and take"
        # exit the game if the player wants to leave (supports quit,
        # exit, and bye)
        if (action == "quit" or action == "exit" or action == "bye" or action == "sionara!"):
            exit(0)
        # if the player is dead if goes/went south from room 4
        if (Game.currentRoom == None):
        # clear the player's input
            Game.player_input.delete(0, END)
            return
        # split the user input into words (words are separated by
        # spaces) and store the words in a list
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
                try:
                    # Get the next room
                    nextRoom = Game.currentRoom.exits[noun]

                    # Condition where the player dies if he sees the serial killer and tries to leave
                    if(Game.currentRoom == r7 and not (r7.itemUnlocked["serial-killer"])):
                        Game.currentRoom = None
                        self.setStatus("")
                        self.setRoomImage()
                        Game.player_input.delete(0, END)

                    # check for valid exits in the current room and if they are unlocked
                    elif (noun in Game.currentRoom.exits and nextRoom.unlocked):
                        # if one is found, change the current room to
                        # the one that is associated with the
                        # specified exit
                        Game.currentRoom = Game.currentRoom.exits[noun]
                        # set the response (success)
                        response = "Room changed."
                    # Response if the room is locked
                    elif(not(nextRoom.unlocked)):
                        response = "The {} is locked!".format(nextRoom.name)
                except:
                    pass
                
            # the verb is: look
            elif (verb == "look"):
                # set a default response
                response = "I don't see that item."

                # Condition where the player dies if he tries to look at something in the dungeon
                if(Game.currentRoom == r7 and not(r7.itemUnlocked["serial-killer"] and noun in Game.currentRoom.items)):
                    Game.currentRoom = None
                    self.setStatus("")
                    self.setRoomImage()
                    Game.player_input.delete(0, END)
                

                # check for valid items in the current room and if the item is unlocked
                elif (noun in Game.currentRoom.items and Game.currentRoom.itemUnlocked[noun]):
                    # if one is found, set the response to the
                    # item's description
                    response = Game.currentRoom.items[noun]
                
                # Special messages for fireplace and main door
                elif(noun in Game.currentRoom.items):
                    if(noun == "fireplace"):
                        response = "A key seems to be hidden in the fire. If only there was a way to extinguish it. "
                    
                    elif(noun == "main-door"):
                        response = "The door to freedom. There has to be a key \nsomewhere."
                        
                    else:
                        response = "The {} is locked".format(noun)



            # the verb is: take
            elif (verb == "take"):
                # set a default response
                response = "I don't see that item or cannot get it yet."
                # check for valid grabbable items in the current # room

                # Condition where the player dies if he decides to take something from the dungeon
                if(Game.currentRoom == r7 and not(r7.itemUnlocked["serial-killer"]) and noun in Game.currentRoom.grabbables):
                    Game.currentRoom = None
                    self.setStatus("")
                    self.setRoomImage()
                    Game.player_input.delete(0, END)
                    
                try:

                    for grabbable in Game.currentRoom.grabbables:
                    # a valid grabbable item is found and the item associated with it is unlocked
                        if (noun == grabbable and Game.currentRoom.itemUnlocked[Game.itemGrabPairs[noun]]):
                        # add the grabbable item to the player's
                        # inventory
                            Game.inventory.append(grabbable)
                            Game.score += 1
                            # remove the grabbable item from the
                            # room
                            Game.currentRoom.delGrabbable(grabbable)

                            # Checks if the item is also usable
                            if(grabbable in Game.usablePairs.keys()):
                                Game.usables.append(grabbable)

                            # set the response (success)
                            response = "Item grabbed."
                            # no need to check any more grabbable
                            # items
                            break
                except:
                    pass

            # The verb is use
            elif(verb == "use"):
                response = "Cannot use that item."

                # If the player drinks toilet water, they die
                if(noun == "toilet-water" and Game.currentRoom == r6):
                    Game.score = 0
                    Game.currentRoom = None
                    self.setStatus("")
                    self.setRoomImage()
                    Game.player_input.delete(0, END)
                
                # If the player decides to use something other than a knife on the killer, they die
                if(Game.currentRoom == r7 and not(r7.itemUnlocked["serial-killer"]) and noun != "knife" and noun in Game.usables):
                    Game.currentRoom = None
                    self.setStatus("")
                    self.setRoomImage()
                    Game.player_input.delete(0, END)

                # try:
                try:
                    # Checks if the item is usable on anything in the room
                    if((Game.usablePairs[noun] in Game.currentRoom.items.keys() or Game.usablePairs[noun] in Game.currentRoom.exits.values()) and noun in Game.usables):

                        response = "{} used on the {}\nThe {} is unlocked now".format(noun.capitalize(), Game.usablePairs[noun], Game.usablePairs[noun])

                        # If the knife is used on the killer
                        # Changes the image
                        if(Game.usablePairs[noun] == "serial-killer"):
                            Game.score += 4
                            Game.currentRoom.itemUnlocked["serial-killer"] = True
                            Game.currentRoom.image = PhotoImage("Images/DeadDungeon.gif")
                            response = "{} used on the {}\nThe {} is dead now.".format(noun.capitalize(), Game.usablePairs[noun], Game.usablePairs[noun])
                        

                        elif(Game.usablePairs[noun] in Game.currentRoom.exits.values()):
                            #If the item is dungeon-key, unlocks the dungeon
                            if(noun == "dungeon-key"):
                                Game.score += 3
                                r7.unlocked = True
                                response = "{} used on the {}\nThe {} is unlocked now".format(noun.capitalize(), "Dungeon Door", "door")
                                
                            # If the item is master-key, unlocks the main door
                            else:
                                Game.score += 5
                                r0.unlocked = True
                                response = "{} used on the {}\nThe {} is unlocked now".format(noun.capitalize(), "Main Door", "door")
                        
                        # Other usable items
                        elif(Game.usablePairs[noun] in Game.currentRoom.items.keys()):
                            Game.score += 2
                            Game.currentRoom.itemUnlocked[Game.usablePairs[noun]] = True
                            
                        # Remove from inventory and usables
                        Game.inventory.remove(noun)
                        Game.usables.remove(noun)
                        
                        # If the player uses the wrench on the oven, they die (Short-circuit)
                        if(Game.usablePairs[noun] == "oven"):
                            Game.currentRoom = None
                            self.setStatus("")
                            self.setRoomImage()
                            Game.player_input.delete(0, END)
                
                except:
                    response = "Item not in inventory"




        # display the response on the right of the GUI
        #    display the room's image on the left of the GUI
        # clear the player's input
        self.setStatus(response)
        self.setRoomImage()
        Game.player_input.delete(0, END)


# the default size of the GUI is 800x600
WIDTH = 800
HEIGHT = 600
# create the window
window = Tk()
window.title("Room Adventure")
# create the GUI as a Tkinter canvas inside the window
g = Game(window)
# play the game
g.play()
# wait for the window to close
window.mainloop()