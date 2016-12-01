Feature: The application responds appropriately to all events that correspond to the various forms of delete bandMembers to the system. 

    Background:
        Given that the application has been started
        #And I have successfully logged in as admin
        
	Scenario: Remove a bandMember on a not empty bandMember's database 
        Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname ""
        And that the bands' database have 1 entries
        And that the bandMember's database have one bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band1" 
        When I remove a bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band1" , the result is "OK"
        Then the bandMember's database should have 0 entries

    Scenario: Remove a bandMember on a empty bandMember's database
    	Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname ""
        And that the bands' database have 1 entries
    	And that the bandMember's database is empty
    	When I remove a bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band1" , the result is "CONFLICT"
    	Then the bandMember's database should have 0 entries

    #Scenario: Remove a bandMember that is not in the bandMember's database
    #    Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname ""
    #    And that the bands' database have 1 entries
    #    And that the bandMember's database have one bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band1"
    #	 When I remove a bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band2" , the result is "CONFLICT"
