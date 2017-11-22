import re

import tkinter as tk

class RegExTester(tk.Frame):

    def __init__(self, master= None):
        tk.Frame.__init__(self, master)

        master.title("RegexTester")
        master.minsize(500, 500)

        inputL= tk.Label(master, text="input")
        inputL.anchor("sw")
        inputL.pack()

        outputL = tk.Label(master, text="output")
        outputL.anchor("e")
        outputL.pack()


        regexL = tk.Label(master, text ="regex")
        regexL.anchor("nw")
        regexL.pack()

        executeB = tk.Button(master, text= "Proceed", command= self.findall)
        executeB.pack()

        regexE = tk.Entry(master, text ="more ?")
        regexE.pack()




    def findall(self):
        print("lel")
gui = tk.Tk()

regexT = RegExTester(gui)

gui.mainloop()