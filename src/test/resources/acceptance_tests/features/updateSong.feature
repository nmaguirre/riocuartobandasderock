Feature: The application responds appropriately to all events that correspond to the various forms of update on songs to the system. 


	Background:
        	Given that the application has been started
	        #And I have successfully logged in as admin

	Scenario: Update a song on an empty song's database
			Given that the song's database is empty and exist an album "alb"
			When I update a song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7" and name "Jijiji" and duration "266" and album "alb"
			Then the song's database should have 0 entry 
		
        	
	Scenario: Update the name of an existing song in database
			Given that the song's database have one song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7" and name "Jijiji" and duration "266" and belongs to the album "alb" 
			When I update the song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7" to name "Stairway to Heaven" and duration "226" and album "alb"
			Then the song's database should have one song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7", name "Stairway to Heaven" and duration "266" exist in database
	
	Scenario: Update the duration of an existing song in database
			Given that the song's database have one song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7" and name "Jijiji" and duration "266" and belongs to the album "alb"
			When I update the song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7" and name "Jijiji" to duration "339" and album "alb"
			Then the song's database should have one song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7", name "Jijiji" and duration "339" exist in database
	
	Scenario: Update the name and the duration of an existing song in database
			Given that the song's database have one song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7" and name "Jijiji" and duration "266" and belongs to the album "alb"
			When I update the song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7" to name "Stairway to Heaven" and duration "339" and album "alb"
			Then the song's database should have one song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7", name "Stairway to Heaven" and duration "339" exist in database