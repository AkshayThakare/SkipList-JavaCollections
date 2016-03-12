# SkipList-JavaCollections
Source code for the Skip List Project using Java Collections
CS 5V81.001: Implementation of data structures and algorithms
Project 1

Akshay Thakare (ast140230)

Description: Implement the skip list data structure, Compare its performance with Java's TreeMap.

A Skip List is a data structure that allows fast search within an ordered sequence of elements. Fast search is made possible by maintaining a linked hierarchy of subsequences, each skipping over fewer elements.  [http://en.wikipedia.org/wiki/Skip_list]
Skip list consists of nodes linked only in forward direction. A node is made up of data and array of links which points to the next element on that level.
Classes in the Project:
1.	SkipListImpl
Class implementing the SkipNode interface
2.	SkipNode
Class for creating the node of the list

3.	AlreadyExistsException
Exception thrown if element to be added is already present.

4.	ListIterator
Class for creating iterator to iterate over the list.

5.	Tree
Class implementing TreeSet for adding, removing and checking if an element is present in the list. This class is just for checking the performance of TreeSet with skip list.

6.	FindResult
Class used to create an object for returning the result of find function. It will return the node whose data is found and the array of visited nodes to reach to this node. This array is used in case of add and remove.

7.	SkipList
This is an interface which is implemented by SkipListImpl.


The methods implemented are add ,ceiling ,contains ,findIndex ,first ,floor ,isEmpty ,last ,rebuild ,remove and size.

Comparison with TreeMap:
SkipList gives better performance when compared with TreeMap.  If skiplist is implemented with rebuild function then it will further improve its performance.
Comparison Table:
Sr No	Input File 	Skip List Output 	Time taken by Skip List	Tree Set Output
(returned number, Time)	Time taken by Tree Set
1	Input_50	46	(46, 1451)	46	(46, 1751)
2	Input_100	91	(91, 1632)	91	(91, 1715)
3	Input_200	187	(187, 1416)	187	(187, 1688)
4	Input_1000	925	(925, 1643)	925	(925, 1620)
5	Input_10000	221	(221, 1772)	221	(221, 1906)
6	Input_50000	922	(922, 3481)	922	(922, 2782)




















