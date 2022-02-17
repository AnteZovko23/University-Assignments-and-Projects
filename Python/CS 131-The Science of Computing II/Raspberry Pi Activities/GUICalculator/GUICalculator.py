from Tkinter import *
###########################################################################################
# Name: Ante Zovko
# Date: February 8th, 2020
# Description: Regular Calculator using the Tkinter library
###########################################################################################

# Main Frame inherits from Frame
class MainGUI(Frame):
    def __init__(self, parent):
        Frame.__init__(self, parent, bg="white")

        self.master = parent

        self.setupGUI()

    def setupGUI(self):
        # Window for entering numbers
        self.display = Label(self, text="", anchor=E, bg="white", height=1, width=13, font=("Arial", 45))
        self.display.grid(row=0, column=0, columnspan=4, sticky=E+W+N+S)

        for row in range(6):
            Grid.rowconfigure(self, row, weight=1)
        for col in range(4):
            Grid.columnconfigure(self, col, weight=1)

        # All of the button image paths
        imgPaths = ["Images/lpr.gif", "Images/rpr.gif", "Images/clr.gif", "Images/bak.gif", "Images/7.gif",
         "Images/8.gif", "Images/9.gif", "Images/div.gif", "Images/4.gif", "Images/5.gif",
          "Images/6.gif", "Images/mul.gif", "Images/1.gif", "Images/2.gif", "Images/3.gif",
           "Images/sub.gif", "Images/0.gif", "Images/dot.gif", "", "Images/add.gif", "Images/eql-wide.gif",
            "", "Images/pow.gif", "Images/mod.gif", ""]
        
        commands = []

        # Commands for the buttons are the names of the images
        for imgPath in imgPaths:
            commands.append(imgPath[7:])        
        

        # Creates the buttons as a 6x4 grid
        counter = 0
        for i in range(1,7):
            for j in range(4):
                self.buttonSetup(imgPaths[counter], i, j, commands[counter])
                counter += 1

        # Menu
        menu = Menu(self.master, bg="lightblue")
        self.master.config(menu=menu)

        file = Menu(menu, tearoff=0)
        file.add_command(label="Exit", command=self.clientExit)


        menu.add_cascade(label="File", menu=file)

        
        self.pack(fill="both", expand=1)
        

    # Creates the buttons
    # Params: Path of the images, place in grid, and respective command
    def buttonSetup(self, imgPath, i, j, command):
        
        img = PhotoImage(file=imgPath)


        if(imgPath == ""):
            pass

        elif(imgPath == "Images/eql-wide.gif"):
            button = Button(self, width=10, height=100 ,bg="#437af9",highlightbackground="#437af9", highlightcolor="#437af9", border="7" ,image=img ,activebackground="#437af9", command = lambda: self.process(command))            
            button.image = img
            button.grid(row=i, column=j, columnspan=2, sticky=N+S+E+W)

        else:
            button = Button(self, width=10, height=50 ,bg="white",highlightbackground="black" ,border="7" ,image=img ,activebackground="black", command= lambda: self.process(command))
            button.image = img
            button.grid(row=i, column=j, sticky=N+S+E+W)

    # Terminates the program when called
    def clientExit(self):
        raise SystemExit(0)

    # Handles the function of each button   
    # If exprIsResult is True, the next button click erases the display's contents
    # Display limited to 14 chars for input, 11 for output
    def process(self, button):
        global exprIsResult
        if(len(self.display["text"]) < 14 or exprIsResult == 1):

            
            

            if(button == "lpr.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "("

            elif(button == "rpr.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += ")"

            elif(button == "clr.gif"):
                self.display["text"] = ""
            
            elif(button == "bak.gif"):
                self.display["text"] = self.display["text"][:(len(self.display["text"])-1)]
            
            elif(button == "7.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "7"

            elif(button == "8.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "8"
            
            elif(button == "9.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "9"

            elif(button == "div.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "/"
            
            elif(button == "4.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "4"
            
            elif(button == "5.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "5"

            elif(button == "6.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "6"

            elif(button == "mul.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "*"
            
            elif(button == "1.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "1"

            elif(button == "2.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "2"

            elif(button == "3.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "3"
            
            elif(button == "sub.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "-"

            elif(button == "0.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "0"

            elif(button == "dot.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "."

            elif(button == "add.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "+"
        
            elif(button == "eql-wide.gif"):
                exprIsResult = 1

                expr = self.display["text"]
                
                # Handles Python 2.7 integer divison
                # Checks if there is a "/" in the expression
                # Splits the string and evaluates the expression as two floats
                if("/" in expr):
                    try:    
                        nums = expr.split("/")
                        numerator = float(nums[0])
                        denominator = float(nums[1])
                        expr = ("{}/{}".format(numerator, denominator))
                    except:
                        self.display["text"] = "ERROR"
    
                try:
                    result = eval(expr)

                except:
                    self.display["text"] = "ERROR"
                
                else:
                    result = str(result)
                    # Cuts the ".0" off if necessary
                    if(result.endswith(".0")):
                        result = result[0:len(result) - 2]
                   
                    # Limits output to 11 chars
                    # If the result is longer, adds ...
                    # The display remains 14
                    if(len(result) > 11):
                        result = result[:11]
                        result += "..."
                        self.display["text"] = str(result)
                    else:
                        self.display["text"] = str(result)

            elif(button == "pow.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "**"

            elif(button == "mod.gif"):
                if(exprIsResult):
                    self.display["text"] = ""

                    exprIsResult = 0
                
                self.display["text"] += "%"
            
            elif(button == ""):
                pass
        # If the user reaches the input limit(14), he can still erase, clear and evaluate
        else:

            if(button == "clr.gif"):
                self.display["text"] = ""
            
            elif(button == "bak.gif"):
                self.display["text"] = self.display["text"][:(len(self.display["text"])-1)]
            
            elif(button == "eql-wide.gif"):
                exprIsResult = 1

                

                expr = self.display["text"]

                expr = self.display["text"]
                if("/" in expr):

                    try:    
                        nums = expr.split("/")
                        numerator = float(nums[0])
                        denominator = float(nums[1])
                        expr = ("{}/{}".format(numerator, denominator))
                    except:
                        self.display["text"] = "ERROR"

                try:
                    result = eval(expr)

                except:
                    self.display["text"] = "ERROR"
                
                else:
                    result = str(result)
                    if(result.endswith(".0")):
                        result = result[0:len(result) - 2]

                    if(len(result) > 11):
                        result = result[:11]
                        result += "..."
                        self.display["text"] = str(result)
                    else:
                        self.display["text"] = str(result)
            

        
        


# Centers the window based on screen dimensions
def center_window(window, width, height):
    # get screen width and height
    screen_width = window.winfo_screenwidth()
    screen_height = window.winfo_screenheight()

    # calculate position x and y coordinates
    x = (screen_width/2) - (width/2)
    y = (screen_height/2) - (height/2)
    window.geometry('%dx%d+%d+%d' % (width, height, x, y))
    window.resizable(0,0)


window = Tk()
window.title("Calculator")
center_window(window, 485,768)

exprIsResult = 0 


p = MainGUI(window)
window.mainloop()