# ProjectTopicModeller

This application allows you to check if more than one text file is about the same topic or not.

it does this by looking at the  frequency of words within each separate file and is ranked from common to least common

Then intersects these lists which tells us if there is any common topics between then

a grade of 19 percent or higher found between between common files makes it common

## Classes

### Control

This is where the program is started. it calls the UI class which gives a graphical user interface to the user.

### ConvertArrays

converts multiple array lists into a 2d array
for use in other classes going forward

### FileProcessor

class that processes the text file and
strips it for the top common words and returns it to whatever instantiated it
###  Gui

This is the graphical user interface of the program.

It uses a range of static Buttons and dynamic Buttons and functions in order to allow for dynamically changing content such as deleting file addresses.

No layout is actually used as I wanted to generate a look and feel similar to the old xbox 360 tile system.


### Intersect
 This finds the intersection of more than one file.
 
 It also contains other functions to return common words and how many common words were actually found from the inserted files.

### SaveResults

Saves the results of ExamineFile function to a file
of a users choosing


## Core Functionality

### Add Stop Words
### Find Frequency of words per file
### Examine Files
### identify similarities
### Save File

## Optional Functionality

### Enhanced GUI
### Summary button on click
### Range Slider
### Dynamic Delete
### Delete Last File
### Reset Program
### App Drawer
### Use file Chooser for selecting and saving 




## Foresight
overall if I had more time I would have liked to implement a lemmatisation that cuts down words to there
root Stanford.edu has a library for this.

I would also like to do more with java FX instead of java swing I feel like I could get a better and 
cleaner graphical user interface.

On top of this would be a total code restructuring of how I implement my classes. I Feel the way
I use them isn't exactly the main way of doing it 


## Video
https://youtu.be/qV9P0NxMRXs
