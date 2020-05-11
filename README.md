# Client-Server-Socket-Communication
Network Application Course Assignment

Ishan Chaurasia - 101011068
Reece Pillenger - 101013264

This submission for Assignment 1 of NET 4005 contains 4 files. Server and Client .java. This README and PROTOCOL.txt files.
PROTOCOL.txt describes how the Server and Client communicate.
Server.java file sets up the socket and has files that the client will request.
It is coded to take the request from client and load that file into a buffer and send it over the stream.
Client.java file sends the request to the server.
It recieves the bytes of the file request and converts it into a file and stores it.

The Server is programmed for multithreading and is able to perform 10 requests while putting the 11th in queue.
This code was tested on the same virtual machine as demonstrated in class. Client and Server were both run from different directories.
