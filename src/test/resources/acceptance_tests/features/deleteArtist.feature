Feature: The application responds appropriately to all events that correspond to the various forms of delete on artists to the system. 

    Background:
        Given that the application has been started
        #And I have successfully logged in as admin

    Scenario: delete one artist on artist's database with one entry
        Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "locato"
        When delete this artist with name "Matias" and surname "Serra" and nickname "locato" , the result is "OK"
        Then the artist's database should have 0 entry
        
	Scenario: delete one artist on empty artist's database 
		Given that the artist's database is empty
		When delete the artist with UUID= "91ff2946-187e-4114-a185-712600ef1622" , the result is "CONFLICT"		