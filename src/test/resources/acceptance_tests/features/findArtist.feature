Feature: The application responds appropriately to all events that correspond to the various forms of Find new artists to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin
        
	Scenario: Search artists on an empty artist's database by name
        Given that the artist's database is empty
        When I search an artist with name "Matias" , the result of the search should have 0 entry
        
	Scenario: Search artists on an empty artist's database by surname
        Given that the artist's database is empty
        When I search an artist with surname "Serra" , the result of the search should have 0 entry
        
	Scenario: Search artists on an empty artist's database by nickname
        Given that the artist's database is empty
        When I search an artist with nickname "Pitillo" , the result of the search should have 0 entry
        
	Scenario: Search artists on an not empty artist's database by name, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with name "Matia" , the result of the search should have 0 entry
  
	Scenario: Search artists on an not empty artist's database by surname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with surname "Sera" , the result of the search should have 0 entry
        
	Scenario: Search artists on an not empty artist's database by nickname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with nickname "loca" , the result of the search should have 0 entry
        
	Scenario: Search artists on an not empty artist's database by nickname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with name "Matias" , the result of the search should have 1 entry
        
        
	Scenario: Search artists on an not empty artist's database by nickname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with surname "Serra" , the result of the search should have 1 entry
	
	Scenario: Search artists on an not empty artist's database by nickname, and the result is No content
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I search an artist with nickname "locato" , the result of the search should have 1 entry
        
	        
	Scenario: Search artists on an empty artist's database
        Given that the artist's database is empty
        When I list the artists from the database , the result of the search should have 0 entry
        
	Scenario: Search artists on an artist's database with one artist
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When I list the artists from the database , the result of the search should have 1 entry
         