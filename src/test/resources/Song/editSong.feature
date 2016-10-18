Feature: Edit Song

Scenario: Edit a song that doesn't exist in the database
	Given That the song with id "001" doesn't exist in the database
	When I edit a song with id "001"
	Then A error is obtained
	
Scenario: Edit a song but the database is empty
	Given That the song with id "001"
	And The database is empty
	When I edit a song with id "001"
	Then A error is obtained 

# Scenarios with an attribute that doesn't exist

Scenario: Edit a song with an inexistent genre
	Given That the song with id "001", genre "rock", name "Jijiji" exist in the database
	And The genre "gen" doesn't exist in the database
	When I edit the song with id "001"
	And Change the song's genre "rock" with "gen"
	Then A error is obtained  
	
Scenario: Edit a song with an inexistent band
	Given That the song with id "001", band "Los Redondos", name "Jijiji" exist in the database
	And The band "ban" doesn't exist in the database
	When I edit a song with id "001"
	And Change the song's band "Los Redondos" with "ban"
	Then A error is obtained  
	
Scenario: Edit a song with an inexistent author
	Given That the song with id "001", the author "Juan", name "Jijiji" exist in the database
	And The author "aut" doesn't exist in the database
	When I edit a song with id "001"
	And Change the song's author "Juan" with "aut"
	Then A error is obtained   		

Scenario: Edit a song with an inexistent album
	Given That the song with id "001", the album "Oktober", name "Jijiji" exist in the database
	And The album "alb" doesn't exist in the database
	When I edit a song with id "001"
	And Change the song's album "Oktober" with "alb"
	Then A error is obtained 

#
	
Scenario: Edit song's genre
	Given That the song with id "001", genre "rock", name "Jijiji" exist in the database
	And The genre "gen" exist in the database
	When I edit the song with id "001"
	And Change the song's genre "rock" with "gen"
	Then The song with id "001", genre "gen", name "Jijiji" exist in the database  
	
Scenario: Edit song's band
	Given That the song with id "001", band "Los Redondos", name "Jijiji" exist in the database
	And The band "ban" exist in the database
	When I edit a song with id "001"
	And Changes the song's band "Los Redondos" with "ban"
	Then The song with id "001", band "ban", name "Jijiji" exist in the database  
	
Scenario: Edit song's author
	Given That the song with id "001", the author "Juan", name "Jijiji" exist in the database
	And The author "aut" exist in the database
	When I edit a song with id "001"
	And Changes the song's author "Juan" with "aut"
	Then The song with id "001", author "aut", name "Jijiji" exist in the database   		

Scenario: Edit a song's album 
	Given That the song with id "001", the album "Oktober", name "Jijiji" exist in the database
	And The album "alb" exist in the database
	When I edit a song with id "001"
	And Changes the song's album "Oktober" with "alb"
	Then The song with id "001", album "alb", name "Jijiji" exist in the database 
	
	
	
	
	
	
	
	