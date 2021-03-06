LDwa1kr presents :

Mp3snipPy
  A simple script for cutting a long full-album mp3 downloaded from youtube into tracks, provided You have the timestamps.
  
Dependencies :
- Python 3.*
- PyDub
  - Download it here: https://github.com/jiaaro/pydub
  - or just pip install pydub
- ffmpeg
  - downlaod it here: https://github.com/FFmpeg/FFmpeg
  - or using your package manager, eg. : 
        sudo apt-get install ffmpeg
        
Important :
- The script (in its current iteration) must be placed in the directory where the mp3 is present
- Most of the tested timestamps formats worked with just copy-paste from youtube descriptions, but there are several limitations:
  - You must provide the timestamps in the following formats:
    - timestamp title timestamp title timestamp title etc.
    - title timestamp title timestamp title timestamp etc.
  - Only one timestamp per track, digits separated by a colon ":"
  - Currently the script only support mp3 up to 10h long
  - Whitespaces, brackets, commas  etc. in titles are ignored
  - The name of full album mp3 must be :
    - unique, and not repeated in any song title
  
- Example of processable timestamps:
    <br/>Neo-Born Virus 00:00 
    <br/>Colossal Human Consumption 00:51 
    <br/>Genetic Mutations 03:04 
    <br/>Worm Putrefaction 06:52
    <br/>Post-Incubation Period 09:21 
    <br/>Lethal Injection 12:28 
    <br/>Viral Disease 15:32 
    <br/>Food For The Maggots (Putrid Pile Cover) 18:48
  
  Planned additions :
   - gui used for confirmation and possible editing of timestamp-title sets
   - creating subdirectories for cut mp3s
   - album cover and metadata processing from the original mp3  
