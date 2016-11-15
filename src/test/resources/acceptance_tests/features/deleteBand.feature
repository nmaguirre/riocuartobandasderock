Feature: The application responds appropriately to all events that correspond to the various forms of adding new bands to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin

    Scenario: Remove a band on a not empty bands' database 
        Given that the bands' database have 3 entries
        And the bands' database have a band with name "Band1" and genre "Nu-Metal"
        When I remove a band with name "Band1" and genre "Nu-Metal"
        Then the bands' database should have 2 entries
        And the database shouldn't have a band with  name "Band1" and genre "Nu-Metal"

    Scenario: Remove a band on a empty bands' database
    	Given that the bands' database is empty
    	When I remove a band with name "Megadeth" and genre "Trash-Metal"
    	Then the bands' database should have 0 entries

    Scenario: Remove a band that is not in the bands' database
    	Given that the bands' database have 7 entries
    	And the band with name "Eruca-Sativa" and genre "Rock-Alternativo" is not in bands' datebase
    	When I remove a band with name "Eruca-Sativa" and genre "Rock-Alternativo"
    	Then the bands' database should have 7 entries

    Scenario: Remove a band that are the bands' database
        Given that the bands' database have 7 entries
        And the bands' database have a band with name "Band1" and genre "Nu-Metal"
        When I remove a band with name "Band1" and genre "Nu-Metal"
        Then the bands' database should have 6 entries