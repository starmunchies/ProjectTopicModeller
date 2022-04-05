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
## Optional Functionality
## Foresight
## Video
https://youtu.be/qV9P0NxMRXs
