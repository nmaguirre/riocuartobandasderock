Feature: The application responds appropriately to all events that correspond to the various forms of Find new artists to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin
        
	Scenario: Search songs on an empty song's database by name
        Given that the song's database is empty
        When I search a song with name "Tres Marias" , the result of the search should have 0 entry
        
	Scenario: Search song on an not empty song's database by name, and the result is No content
		Given that the song's database have one song with name "Insaciable" and duration "400"
        When I search a song with name "Insaciable" , the result of the search should have 1 entry
  
	Scenario: Search song on an not empty song's database by duration, and the result is No content
		Given that the song's database have one song with name "Insaciable" and duration "400" 
        When I search a song with duration "400" , the result of the search should have 1 entry
        
    
    Scenario: Search song on an not empty song's database 
        Given that the song's database have one song with name "Insaciable" and duration "400" and belong to the album "Porfiado"
        And that the album's database contains an album named "Porfiado" with the song "Insaciable"
        When I search a song with name "Insaciable" , the result of the search should have 1 entry
	    And the entry should have name "Insaciable", duration "400" and belongs to the album "Porfiado"
        
          