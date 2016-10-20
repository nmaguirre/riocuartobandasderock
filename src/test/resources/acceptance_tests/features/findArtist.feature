Feature: The application responds appropriately to all events that correspond to the various forms of Find new songs to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin
        
<<<<<<< HEAD
	Scenario: Search songs on an empty song's database by name
        Given that the song's database is empty
        When I search a song with name "Tres Marias" , the result of the search should have 0 entry
        
	Scenario: Search song on an not empty song's database by name, and the result is No content
		Given that the song's database have one song with name "Insaciable" and duration "8:00"
        When I search a song with name "Insaciable" , the result of the search should have 1 entry
  
	Scenario: Search song on an not empty artist's database by duration, and the result is No content
		Given that the song's database have one artist with name "Insaciable" and duration "8:00" 
        When I search a song with duration "8:00" , the result of the search should have 1 entry
        
	
=======
	Scenario: Search artists on an empty artist's database by name
        Given that the artist's database is empty
        When I search an artist with "name" "Matias" , the result should have 0 entry
        
	Scenario: Search artists on an empty artist's database by surname
        Given that the artist's database is empty
        When I search an artist with "surname" "Serra" , the result should have 0 entry
        
	Scenario: Search artists on an empty artist's database by nickname
        Given that the artist's database is empty
        When I search an artist with "nickname" "Pitillo" , the result should have 0 entry
        
	Scenario: Search artists on an not empty artist's database by name, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with "name" "Matia" , the result should have 0 entry
  
	Scenario: Search artists on an not empty artist's database by surname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with "surname" "Sera" , the result should have 0 entry
        
	Scenario: Search artists on an not empty artist's database by nickname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with "nickname" "loca" , the result should have 0 entry
        
	Scenario: Search artists on an not empty artist's database by nickname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with "name" "Matias" , the result should have 1 entry
        
        
	Scenario: Search artists on an not empty artist's database by nickname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with "surname" "Serra" , the result should have 1 entry
	
	Scenario: Search artists on an not empty artist's database by nickname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with "nickname" "locato" , the result should have 1 entry
           
	Scenario: Search artists on an not empty artist's database by nickname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with "nickname" "" , the result should have 0 entry
                
	Scenario: Search artists on an empty artist's database
        Given that the artist's database is empty
        When I list the artists from the database , the result of the search should have 0 entry
        
	Scenario: Search artists on an artist's database with one artist
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I list the artists from the database , the result of the search should have 1 entry
>>>>>>> 99c3563ea8d8b9d776bd7ea91b429854f659a6a5
         