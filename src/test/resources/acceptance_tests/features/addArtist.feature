Feature: The application responds appropriately to all events that correspond to the various forms of adding new artists to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin

    Scenario: Add a new artist on an empty artist's database 
        Given that the artist's database is empty
        When I add an artist with name "Matias" and surname "Serra" and nickname ""
        Then the artist's database should have 1 entry
        And the entry should have name "Matias" and surname "Serra"
        
	
    Scenario: Add a new artist with the same name that an artist that stores in the database but diferent surname
        Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname ""
        When I add an artist with name "Matias" and surname "" and nickname ""
        Then the artist's database should have 2 entry 

	Scenario: Add a new artist with the the same name, surname and nickname that an artist that store before in the database
		Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname ""
        When I add an artist with name "Matias" and surname "Serra" and nickname ""
        Then the artist's database should have 1 entry
        And the entry should have name "Matias" and surname "Serra"
        
        