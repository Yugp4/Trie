# Trie
Create a "Trie" of words that are organized by prefixes

-------------------CODED IN JAVA----------------

In this application the user can create a input a text file of words and create a resulting "Trie" tree of words that they have entered.  These words are organized by their prefixes.

One feature in this app is the building Trie which build a trie out of the words that are given from input and would then return the root node of the Trie tree that has all the words organized by their given prefixes.

Another feature this app provides is that it returns the list of words from a given prefix.  This algorithm takes in a string input and returns all the words in the trie that start with the entered prefix.  The prefix could even be an entire word itself!

In addition to the "Trie.java" and "TrieNode.java" files another file is provided called "TrieApp.java" that is a driver for this application which accepts the inputs given from the user which is not created by me but provided to me by my professor.

To run this program:
Create a text file and on the first line enter the number of words that you will be entering.  Once the number has been written, on every new line put one new word.

For example: 

3

apt

apartment

apple

Once the trie is built and printed the next line will ask you to enter a word for the completion list.  Enter a prefix of the word(s) you want returned and the program will give you the word(s).

For example:
Input: ap
Output: apt, apartment, apple


