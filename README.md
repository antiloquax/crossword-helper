This is a java version of the c program I wrote to solve anagrams and find words with missing letters.
Like the C version, this just reads through the whole dictionary on each iteration through the loop.
I may look at using an array and a binary search, but for this particular problem, this seems to be fast enough as it is.

I did use regular expressions in the Java version, instead of the custom compare function I wrote in the C version.

The dictionaries included are from the 12dict project.
The file "anag.txt" was created by the preparedict.c program in the C repository. It simplry sorts the letters in a word and then saves each line in the dictionary as the original word followed by the alphabetised version.


