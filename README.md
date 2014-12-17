bios
====

A data driven taxononmy viewer webapp. This is a work in progress and must evolve or go extinct.

## Requirements

	java jdk
	gradle
	mysql
	
## Recommendations

    Intellij

## Generate Intellij project

    $ cd bios 
    $ gradle idea
    $ open bios.ipr
    	
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

