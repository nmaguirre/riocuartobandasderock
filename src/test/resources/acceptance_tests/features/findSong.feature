Feature: The application responds appropriately to all events that correspond to the various forms of Find new songs to the system. 

    Background:
        Given that the application has been started
        
	Scenario: Search songs by name on an empty song's database
        Given that the song's database is empty
        When I search a song with name "Tres Marias" , the result of the search should have 0 entry
        
    Scenario: Search songs by duration on an empty song's database
        Given that the song's database is empty
        When I search a song with duration "1" , the result of the search should have 0 entry    
        
	Scenario: Search a non-existing song by name in song's database, and the result is No content
		Given that the song's database have one song with name "Insaciable" and duration "400"
        When I search a song with name "Insacia" , the result of the search should have 0 entry
        
   	Scenario: Search a non-existing song by duration in song's database, and the result is No content
		Given that the song's database have one song with name "Insaciable" and duration "400"
        When I search a song with duration "200" , the result of the search should have 0 entry
        
    Scenario: Search an existing song by name in song's database
		Given that the song's database have one song with name "Insaciable" and duration "400"
        When I search a song with name "Insaciable" , the result of the search should have 1 entry    
  
	Scenario: Search an existing song by duration in song's database
		Given that the song's database have one song with name "Insaciable" and duration "400"
        When I search a song with duration "400" , the result of the search should have 1 entry
        
        
          