Feature: The application responds appropriately to all events that correspond to the various forms of adding new bandMembers to the system. 

	Background:
        Given that the application has been started
        And I have successfully logged in as admin

	Scenario: Add a new bandMember on an empty bandMember's database empty, band's database empty and artist's database not empty
        Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname ""
        And that the band's database is empty
        And that the bandMember's database is empty
        When I add an bandMember with artist name "Matias" and surname "Serra" and nickname "" and band name "Band 1"
        Then the bandMember's database should have 0 entry

	Scenario: Add a new bandMember on an empty bandMember's database empty, band's database not empty and artist's database empty
        Given that the artist's database is empty
        And that the band's database have 1 entry
        And that the bandMember's database is empty
        When I add an bandMember with artist name "Matias" and surname "Serra" and nickname "" and band name "Band 1"
        Then the bandMember's database should have 0 entry

	Scenario: Add a new bandMember on an empty bandMember's database empty, band's database and artist's database empty
        Given that the artist's database is empty
        And that the band's database is empty
        And that the bandMember's database is empty
        When I add an bandMember with artist name "Matias" and surname "Serra" and nickname "" and band name "Band 1"
        Then the bandMember's database should have 0 entry

	Scenario: Add a new bandMember on an empty bandMember's database empty, band's database and artist's database not empty
        Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname ""
        And that the bands' database have 1 entries
        And that the bandMember's database is empty
        When I add an bandMember with artist name "Matias" and surname "Serra" and nickname "" and band name "Band 1"
        Then the bandMember's database should have 1 entry
        And the entry should have artist name "Matias" and surname "Serra" and nickname "" and band name "Band 1"
        
	Scenario: Add a new bandMember on an not empty bandMember's database, band's database and artist's database not empty
        Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname ""
        And that the band's database have 1 entry
        And that the bandMember's have 1 entry with artist name "Matias" and surname "Serra" and nickname "" and band name "Band 1"
        When I add an bandMember with artist name "Matias" and surname "Serra" and nickname "" and band name "Band 1"
        Then the bandMember's database should have 1 entry
        
        