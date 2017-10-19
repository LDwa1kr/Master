LDwa1kr presents :

Mp3snipPy
  A simple script for cutting a long full-album mp3 downloaded from youtube into tracks, provided You have the timestamps.
  
Dependencies :
- Python 3.*
- PyDub is required
  - Download it here: https://github.com/jiaaro/pydub
  - or just pip install pydub
- ffmpeg is required
  - downlaod it here: https://github.com/FFmpeg/FFmpeg
  - or using your package manager, eg. : 
        sudo apt-get install ffmpeg
        
Important :
- The script (in its current iteration) must be placed in the directory where the mp3 is present
- Most of the tested timestamps formats worked with just copy-paste from youtube descriptions, but there several limitations:
  - You must provide the timestamps in the following formats:
    - timestamp title timestamp title timestamp title etc.
    - title timestamp title timestamp title timestamp etc.
  - Only one timestamp per track, digits separated by a colon ":"
  - Whitespaces are ignored, so no worries with \n and \r
  - The name of full album mp3 must be :
    - unique, and not repeated in any song title
  
-Example of processable timestamps:
    Neo-Born Virus 00:00 
    Colossal Human Consumption 00:51 
    Genetic Mutations 03:04 
    Worm Putrefaction 06:52
    Post-Incubation Period 09:21 
    Lethal Injection 12:28 
    Viral Disease 15:32 
    Food For The Maggots (Putrid Pile Cover) 18:48
  
  Planned additions :
   - gui used for confirmation and possible editing of timestamp-title sets
   - creating subdirectories for cut mp3s
   - album cover and metadata processing from the original mp3  
