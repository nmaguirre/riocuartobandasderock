Feature: The application responds appropriately to all events that correspond to the various forms of adding new songs to the system.

	Background:
        	Given that the application has been started

	Scenario: Add a new song on an empty song's database
        	Given that the song's database is empty
        	When I add a song with name "Stairway to Heaven" and duration "400"
        	Then the song's database should have 1 entry
        	And the entry should have name "Stairway to Heaven" and duration "400"

	Scenario: Add a new song with the same name and different duration that a song stored in database
        	Given that the song's database have one song with name "Jijiji" and duration "5"
        	When I add a song with name "Jijiji" and duration "2"
        	Then the song's database should have 2 entry

	Scenario: Add a new song with the same name and duration that a song stored in the database
	     	Given that the song's database have one song with name "Jijiji" and duration "5"
	        When I add a song with name "Jijiji" and duration "5"
	        Then the song's database should have 2 entry

	Scenario: Add a new song without duration in a database empty
			Given that the song's database is empty
			When I add a song with name "Stairway to Heaven" and duration "" 
			Then the song's database should have 1 entry 

	Scenario: Add a new song without name in a database empty
			Given that the song's database is empty
			When I add a song with name "" and duration "400"
			Then the song's database should have 0 entry  
	
	Scenario: Add a new song without name and duration in a the database empty
			Given that the song's database is empty
			When I add a song with name "" and duration ""
			Then the song's database should have 0 entry

	Scenario: Add a new song without duration in a database not empty
			Given that the song's database have one song with name "Jijiji" and duration "5"
			When I add a song with name "Stairway to Heaven" and duration "" 
			Then the song's database should have 2 entry 

	Scenario: Add a new song without name in a database not empty
			Given that the song's database have one song with name "Jijiji" and duration "5"
			When I add a song with name "" and duration "10"  
			Then the song's database should have 1 entry
		
	Scenario: Add a new song without name and duration in a database not empty
			Given that the song's database have one song with name "Jijiji" and duration "5"
			When I add a song with name "" and duration "" 
			Then the song's database should have 1 entry
	
