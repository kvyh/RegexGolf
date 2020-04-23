(https://xkcd.com/1313/)# Regex Golf

## Table of contents
* [What is Regex](#what-is-regex)
* [What is Regex Golf](#what-is-regex-golf)
* [Setup](#setup)
* [How to Contribute](#how-to-contribute)
* [Acknowledgements](#acknowledgements)

## What is Regex

A regex is a text string that uses special syntax to describe patterns to find in a body of text.
For instance, the regex **foo** matches the string foo, the regex **\[A-Z\]+:\\d+** matches string fragments like F:1 and GO:30

The above is from http://www.rexegg.com/ which contains a great introduction and quick reference for regex.

## What is Regex Golf(https://xkcd.com/1313/)

Regex Golf is a fun way to learn and test regex knowledge.
The goal is to write a regex string that finds all of one group of strings, and doesn't find any of a different group.

As an example: regex of "a" would satisfy the puzzle below. All of the desired lines are found by the pattern "a" and none of the undesired lines are.

|Match these lines ...	|and none of these   |
|-----------------------|--------------------|
|	and		|	send	     |
|	ace		|	feel         |
|	a		|	brie         |
|	bean		|	blur         |

Regex golf also features in [xkcd 1313](https://xkcd.com/1313/)

## Setup




## How to contribute

You can add puzzles or functionality by forking this repository and making modifications. Submit a pull request and I'll be able to add your changes to the main branch.

Puzzles are located in app/src/main/res/raw/default_problems.json. Repeat id numbers shouldn't cause problems, but try to avoid it if possible.


## Acknowledgements
Chris Laws gave me a fantastic introduction to programming and introducing me to RegEx golf.

Harvard's CS50 class for having a great Java-for-Android track.

regex.alf.nu by Erling Ellingsen for UI inspiration and some puzzles.
