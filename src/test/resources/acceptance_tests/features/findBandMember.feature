Feature: The application responds appropriately to all events that correspond to the various forms of Find new band's to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin
      
	Scenario: Search the artist bands on an empty bandMember's database by artist name, surname and nickname
        Given that the artist's database is empty
        And that the band's database have 1 entries
        And that the bandMember's database is empty
        When I search the artist bands by artist with name "Matias" and surname "Serra" and nickname "", the result should have 0 entry
       
	Scenario: Search the artist bands on an empty bandMember's database by artist name, surname and nickname
        Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "" 
        And that the band's database is empty
        And that the bandMember's database is empty
        When I search the artist bands by artist with name "Matias" and surname "Serra" and nickname "", the result should have 0 entry
        
	Scenario: Search the artist bands on an empty bandMember's database by artist name, surname and nickname
        Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "" 
        And that the band's database have 1 entries
        And that the bandMember's database have one bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band 1"
        When I search the artist bands by artist with name "Matias" and surname "Serra" and nickname "", the result should have 1 entry, and the entry is the band whit name "Band 1"
        
	Scenario: Search the artist of a band on an empty bandMember's database by band name
        Given that the artist's database is empty
        And that the band's database is empty
        And that the bandMember's database is empty
        When I search the artist of a band by band with name "Band 1", the result should have 0 entry
        
	Scenario: Search the artist of a band on an empty bandMember's database by band name
        Given that the artist's database is empty
        And that the band's database have 1 entries
        And that the bandMember's database is empty
        When I search the artist of a band by band with name "Band 1", the result should have 0 entry
        
	Scenario: Search the artist of a band on an empty bandMember's database by band name
        Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "" 
        And that the band's database have 1 entries
        And that the bandMember's database have one bandMember with artist name "Matias" and artist surname "Serra" and artist nickname "" and band name "Band 1"
        When I search the artist of bands by band with name "Band 1", the result should have 1 entry, and the entry is the artist whit name "Matias" and surname "Serra" and nickname ""