import re
from pydub import AudioSegment
import os
#test cases

time =[]
f = None
tit = None
music = None

class tooLong(Exception):
    def __init__(self, msg):
        self.msg = msg

# title&timestamps open
def topen():
    global f
    f = input("enter timestamps and titles name (in this version of script it has to be in the current working directory)\n")
    #f = open(f).read()
    f = re.sub("[-\"\'/\,.;\n\r\(\)]"," ", f )
    print("The index :\n" + f)

def mopen():
    global music
    try:
        music = input("enter mp3 name (in this version of script it has to be in the current working directory)\n")
        os.path.exists(music)
        music = AudioSegment.from_mp3(music)
    except IOError:
        print ("I/O Error, no such file. Remember to include the extension \".mp3\"")
        exit(1)
    except:
        print("ERROR: Unexpected error")

    print("All set up. Be patient, mp3s of size ~ 100MB take around 2 minutes to cut")

#regex titles
def settitles():
    global tit
    #tit = re.findall("(?<=\d\s)(.*)(?=\s)", f)
    #tit =re.sub("([\s])", "", f)
    #tit = re.split("[?\s]\d:\d\d[?\s]|[?*\s]\d\d:\d\d:\d\d|[?*\s]\d\d:\d\d", f)
    tit = re.split("\d\d:\d\d:\d\d|\d:\d\d:\d\d|\d\d:\d\d|\d:\d\d", f)
    for i in tit:
        tit[tit.index(i)] = re.sub("^\s*|\s*$", "", tit[tit.index(i)])
    tit = [x for x in tit if x ]
    for i in tit:
        if not i:
            tit.remove(tit.index(i))
        tit[tit.index(i)] += ".mp3"
    print("Tracknames: ")
    print(tit)

#split to get list of hours, minutes and seconds
def settime():
    # regex numbers
    global time
    num = re.findall("\d\d:\d\d:\d\d|\d:\d\d:\d\d|\d\d:\d\d|\d:\d\d", f)
    print("Readable timestamps:")
    print(num)
    try:
        for i in num:
            temp = i.split(":")
            if temp.__len__() == 2: #for timestamp of track, assign h/m/s to stacks of h/m/s
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


#array with timestamps stacks in h/m/s format
def sanity():
    global tit
    global time
    print("Number of tracks: " + str(len(tit)) +" and timestamps:  "+ str(len(time)))
    try:
        assert len(tit) == len(time)
    except AssertionError as ass:
        print("ERROR: the number of timestamps is not equal to the number of tracks")
        exit(1)
    print("sanity test passed")

def create():
    global music
    global tit
    assert tit is not None
    assert music is not None
    for i in time:
        temp = None # temporary variable for track export
        temp1 = time.index(i)# temporary variable for keeping track of timestamps
        if temp1 <(len(time)-1):
            temp = music[time[temp1]:time[temp1+1]]
            temp.export(tit[temp1], format="mp3")
            pass
        else:
            temp = music[time[temp1]:]
            temp.export(tit[temp1], format="mp3")
            pass
    print("Done, have a nice day")

def main():
    topen()
    settime()
    settitles()
    sanity()
    mopen()
    create()
    pass

main()