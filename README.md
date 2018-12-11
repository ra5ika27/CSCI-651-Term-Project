# CSCi-651-Term-Project
========================
 Poptrie Implementation
========================

Authors : Rasika Rajeev Thorat, Sudheeksha Garg, Hannah Miller
Date    : 2018-12-10


Overview
========

The test files are in the format:
   `next hop/subnet mask` `target IP`


Quick start
===========
- There are two main files for this project.
  - Poptrie
  - Poptrie with leaf compression
- The file required to create the data structure is by default "Test_file.txt".
- If required, the test file has to be changed in the main, Poptrie_Main or
  Poptrie_MainLeaf
- All the java files have to be compiled by running command javac *.java
  in the file directory. 
- To find the next hop of an IP address, run the main file with IP address as the argument.

Implementation
==============
- To run Poptrie, run command java Poptrie_Main IP_Address
  For eg. java Poptrie_Main 1.0.0.0
- To run Poptrie with leaf compression, run command java Poptrie_MainLeaf IP_Address
  For eg. java Poptrie_MainLeaf 1.0.0.0

Results
=======

- The results return the next hop of IP address is found.
- It returns "IP address not found" if IP address is not found.
- The time taken to run the lookup algorithm is also displayed.
