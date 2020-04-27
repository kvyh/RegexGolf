(https://xkcd.com/1313/)# Regex Golf

## Table of contents
* [What is Regex](#what-is-regex)
* [What is Regex Golf](#what-is-regex-golf)
* [Setup](#setup)
	-[From Release](#from-release)
	-[With Android Studio](#with-android-studio)
* [How to Contribute](#how-to-contribute)
* [Acknowledgements](#acknowledgements)

## What is Regex

A regex is a text string that uses special syntax to describe patterns to find in a body of text.
For instance, the regex **foo** matches the string foo, the regex **\[A-Z\]+:\\d+** matches string fragments like F:1 and GO:30

The above is from http://www.rexegg.com/ which contains a great introduction and quick reference for regex.

## What is Regex Golf

Regex Golf is a fun way to learn and test regex knowledge.
The goal is to write a regex string that finds all of one group of strings, and doesn't find any of a different group.

As an example: regex of "a" would satisfy the puzzle below. All of the desired lines have "a" and none of the undesired lines do.

|Match these lines ...	|and none of these	|
|-----------------------|-------------------|
|	and					|	send			|
|	ace					|	feel			|
|	a					|	brie			|
|	bean				|	blur			|

Regex golf also features in [xkcd 1313](https://xkcd.com/1313/)

## Setup

### From Release

Releases can be found at the bottom of the page on mobile. 
On desktop it is the 4th item in the bar above the list of files. 
Find the most recent release, in the Assets drop-down there is a .apk file.
Download this file to your phone and run it from the downloads folder.

Android may say your "phone is not allowed to install unknown apps from this source." Select settings and allow from "My Files."
Now try again from your downloads folder and it should install.

### With Android Studio

Running the app on your phone this way requires developer options enabled: [How To Enable](https://developer.android.com/studio/debug/dev-options).

Using GitHub: fork this repository. In the AndroidStudioProjects on your Machine run "git clone https:github.com/your-username/RegexGolf" (On Windows if you don't have git, download from the Git [website](https://git-scm.com/download/win). Then right-click in the AndroidStudioProjects folder and select one of the Git options to clone with.)

In Android Studio select File->Open and select the RegexGolf project. To run on your phone, connect it by usb and press the play button in the middle of the taskbar.
You can also open the app on a virtual device when your phone isn't connected.

## How to contribute

Instructions for opening this project in Android Studio [above](#with-android-studio).
You can add puzzles or functionality by forking this repository and making modifications. Submit a pull request and I'll be able to add your changes to the main branch.

Puzzles are located in app/src/main/res/raw/default_problems.json. Repeat id numbers shouldn't cause problems, but try to avoid it if possible.


## Acknowledgements
Chris Laws gave me a fantastic introduction to programming and introducing me to RegEx golf.

Harvard's CS50 class for having a great Java-for-Android track.

regex.alf.nu by Erling Ellingsen for UI inspiration and some puzzles.
