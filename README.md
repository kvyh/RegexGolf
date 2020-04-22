##Regex Golf

#What is Regex
A regex is a text string that uses special syntax to describe patterns to find in a body of text.
For instance, the regex **foo** matches the string foo, the regex **\[A-Z\]+:\\d+** matches string fragments like F:1 and GO:30
The above is from http://www.rexegg.com/ which contains a great introduction and quick reference for regex.

#What is Regex Golf
Regex Golf is a fun way to learn and test regex knowledge.
The goal is to write a regex string that finds all of one group of strings, and doesn't find any of a different group.
As an example: regex of "a" would satisfy the puzzle below. All of the desired lines are found by the pattern "a" and none of the other lines are.
Match these lines ...		and none of these
	and							send
	ace							feel
	a							brie
	bean						blur

Regex golf also features in [xkcd 1313]:https://xkcd.com/1313/



Acknowledgements:
Chris Laws for teaching me programming and introducing me to RegEx golf
Harvard's CS50 class for having a great Java introduction
regex.alf.nu by Erling Ellingsen for UI inspiration and some puzzles
