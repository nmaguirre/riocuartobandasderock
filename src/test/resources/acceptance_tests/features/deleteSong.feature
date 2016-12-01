Feature: The application responds appropriately to all events that correspond to the various forms of delete songs in the system

	Background:
		Given that the application has been started
		
	Scenario: Delete a song on an empty song's database
		Given that the song's database is empty
		When I delete a song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7"
		Then the song's database should have 0 entry 
		
	Scenario: Delete a song that exists in database
		Given that the song's database have one song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7"
		When I delete a song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7"
		Then the song's database should have 0 entry
		
	Scenario: Delete a song that doesn't exist in database
		Given that the song's database have one song with UUID "44f464ce-b000-11e6-80f5-76304dec7eb7"
		When I delete a song with UUID "c6039a6c-b000-11e6-80f5-76304dec7eb7"
		Then the song's database should have 1 entry