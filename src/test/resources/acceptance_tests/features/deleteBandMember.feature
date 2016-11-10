Feature: The application responds appropriately to all events that correspond to the various forms of delete bandMembers to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin
        
	Scenario: Remove a bandMember on a not empty bandMember's database 
        Given that the bandMember's database have 3 entries
        And the bandMember's database have a bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band1" 
        When I remove a bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band1"
        Then the bandMember's database should have 2 entries
        And the database shouldn't have a bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band1"

    Scenario: Remove a bandMember on a empty bandMember's database
    	Given that the bandMember's database is empty
    	When I remove a bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band1"
    	Then the bandMember's database should have 0 entries

    Scenario: Remove a bandMember that is not in the bandMember's database
    	Given that the bandMember's database have 7 entries
    	And the bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band1"
    	When I remove a bandMember with nartist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band1"
    	Then the bandMember's database should have 7 entries