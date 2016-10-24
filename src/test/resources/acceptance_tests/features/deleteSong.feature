Feature: I delete Song
	The application receive a song that I want to delete from the database

	
Scenario: I delete a song that doesn't exist
	Given That the song with the name "Jijiji" doesn't exist in the database
	And The database contains 1 song with the name "Un Angel"
	When I delete a song with the name "Jijiji"
	Then An exception is thrown
	And The database contains 1 song with the name "Un Angel"   
	
Scenario: I delete a song that exist
	Given That the song with name "Jijiji" is in the database
	And The song with name "Un Angel" is in the database 
	When I delete a song with the name "Jijiji"
	Then I delete a song with the name "Jijiji" from database
	And The database contains 1 song with the name "Un Angel"
	
Scenario: I delete a song that doesn't exist
	Given That the song with the name "Jijiji"
	And The database is empty
	When I delete a song with the name "Jijij"
	Then An exception is thrown
	And the database is empty