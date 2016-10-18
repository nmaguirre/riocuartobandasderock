Feature: The application responds appropriately to all events that correspond to the various forms of adding new bands to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin

    Scenario: Add a new band on an empty bands' database 
        Given that the bands' database is empty
        When I add a band with name "70 Balcones" and genre "Rock"
        Then the bands' database should have 1 entry
        And the entry should have name "70 Balcones" and genre "Rock" 
    
    Scenario: Add a new band on a not empty bands' database
    	Given that the bands' database is not empty
    	And the band with name "Metallica" and genre "Heavy Metal" is not in bands' datebase
    	And the bands' database have 5 entries
    	When I add a band with name "Metallica" and genre "Heavy Metal"
    	Then the bands' database should have 6 entries
    	And the band with name "Metallica" and genre "Heavy Metal" should be on bands' database

    Scenario: Add a band that is alredy in the bands' database 
    	Given that the bands' database is not empty
    	And the band with name "Carajo" and genre "Nu Metal" is in dands' database
    	And the bands' database have 4 entries
    	When I add a band with name "Carajo" and genre "Nu Metal" 
    	Then the bands' database should have 4 entries

    Scenario: Add a band that have a same name but difrent genre that other in the bands' database
    	Given that the bands' database have a band with name "ACDC" adn genre "Rock"
    	And the band with name "ACDC" and genre "Hard Rock" is not in bands' datebase
    	And the bands' database have 2 entries
    	When I add a band with name "ACDC" and genre "Hard Rock"
    	Then the bands' database should have 3 entries
    	And the band with name "ACDC" and genre "Hard Rock" should be on bands' database

    Scenario: Add a band that have a same genre but difrent name that other in the bands' database
    	Given that the bands' database have a band whit name "Led Zeppelin" and genre "Rock"
    	And the band with name "The Beatles" and genre "Rock" is not in bands' datebase
    	And the bands' database have 3 entries
    	When I add a band with name "The Beatles" and genre "Rock" 
    	Then the bands' database should have 4 entries
    	And the band with name "The Beatles" and genre "Rock" should be on bands' database

    	