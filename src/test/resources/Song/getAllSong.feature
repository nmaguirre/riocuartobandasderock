Feature: Get all songs

# base case, 

Scenario: I haven't songs in the database
	Given That the database is empty 
	When I get all songs from database
	Then A message is shown

Scenario: I have 1 song in the database
	Given That the database contains 1 song
	And That the song with name "Jijiji" is in the database 
	When I get all songs from the database
	Then I return a list with all songs from the database
	And The song with name "Jijiji" is in the list

Scenario: I have 2 songs in the database
	Given That the database contains 2 songs
	And That the song with name "Jijiji" is in the database
	And That the song with name "Un Angel" is in the database 
	When I get all songs from the database
	Then I return a list with all songs from the database
	And The song with name "Jijiji" is in the list
	And The song with name "Un Angel" is in the list

