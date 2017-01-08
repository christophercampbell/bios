bios
====

A data driven taxononmy viewer webapp. This is a work in progress and must evolve or go extinct.

![Tree example screenshot](docs/screenshot-1.png)

- [x] Schema
- [x] ORM
- [x] Tree viewer from node
- [x] Example data
- [ ] Entry form for new nodes
- [ ] Add more tasks
- [ ] Add links to example species images
- [ ] Style user pages

## User pages

This project 

https://christophercampbell.github.io/bios/

Perobscurus

https://christophercampbell.github.io


## Requirements

	java jdk
	gradle
	mysql
	    	
## Create a database

    $ cd bios
    $ mysql -u root -p
    > create database taxonomy;
    > use taxonomy;
    > source src/main/sql/taxa-schema.sql
    > exit

## Build & run

	$ cd bios
	$ gradle jettyRun 

## Navigate

http://localhost:8080/bios/tree.html
