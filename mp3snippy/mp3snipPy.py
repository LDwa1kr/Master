import re
from pydub import AudioSegment
import os

time =[]
f = None
tit = None
music = None
tags= {'artist':'', 'album':''}

class tooLong(Exception):
    def __init__(self, msg):
        self.msg = msg

def split():
	print("#########################################################")

# open titles and timestamps 
def topen():
    global f
    f = input("enter timestamps and titles \n")
    f = re.sub("[-\"\'/\,.;\n\r\(\)]"," ", f )

# set titles for songs
def settitles():
    global tit
    tit = re.split("\d\d:\d\d:\d\d|\d:\d\d:\d\d|\d\d:\d\d|\d:\d\d", f)
    for i in tit:
        tit[tit.index(i)] = re.sub("^\s*|\s*$", "", tit[tit.index(i)])
    tit = [x for x in tit if x ]
    for i in tit:
        if not i:
            tit.remove(tit.index(i))
        tit[tit.index(i)] += ".mp3"

# split to get list of timestamps (in ms)
def settime():
    global time
    global tit
    num = re.findall("\d\d:\d\d:\d\d|\d:\d\d:\d\d|\d\d:\d\d|\d:\d\d", f)
    split()
    print("\nReadable timestamps and tracknames: \n")
    for el in tit:
        print(num[tit.index(el)] +"      "+ el)

    try:
        for i in num:
            temp = i.split(":")
            if temp.__len__() == 2:
                time.append(\
                    ((int(temp[0])*60)+(int(temp[1])))*1000)
            elif temp.__len__() == 3:
                time.append( \
                    ((int(temp[0]) * 3600) + (int(temp[1])*60) + int(temp[2]))*1000)
            else:
                print(temp.__len__())
                raise tooLong("Wrong timestamps, does Your mp3 have more than 10 h ? ")
    except tooLong as too:
        print(too.msg)



def mopen():
    global music
    try:
        music = input("enter mp3 name (in the current working directory)\n")
        if (music[-4:] != '.mp3'):
            music += ".mp3"
        os.path.exists(music)
        music = AudioSegment.from_mp3(music)
    except IOError:
        print ("I/O Error, no such file.")
        exit(1)
    except:
        print("ERROR: Unexpected error")

    print("All set up. Be patient, mp3s of size ~ 100MB take around 2 minutes to cut")

def sanity():
    global tit
    global time
    split()
    print("Number of tracks: " + str(len(tit)) +" and timestamps:  "+ str(len(time)))
    try:
        assert len(tit) == len(time)
    except AssertionError as ass:
        print("ERROR: the number of timestamps is not equal to the number of tracks")
        exit(1)
    print("sanity test passed! :)")

def addtags():
    global tags
    split()
    artist = input("add artist metadata (hit ENTER to cancel)\n")
    album = input("add album metadata (hit ENTER to cancel)\n")
    if (artist != ''):
    	tags['artist'] = artist
    if (album != ''):
    	tags['album'] = album

def create():
    global music
    global tit
    global tags
    assert tit is not None
    assert music is not None
    for i in time:
        temp = None # temporary variable for track export
        temp1 = time.index(i)# temporary variable for keeping track of timestamps
        if temp1 <(len(time)-1):
            temp = music[time[temp1]:time[temp1+1]]
            temp.export(tit[temp1], format="mp3", tags=tags)
            
        else:
            temp = music[time[temp1]:]
            temp.export(tit[temp1], format="mp3", tags=tags)
            
    print("Done, have a nice day")

def main():
    topen()
    settitles()
    settime()
    sanity()
    addtags()
    mopen()
    create()
    

main()
