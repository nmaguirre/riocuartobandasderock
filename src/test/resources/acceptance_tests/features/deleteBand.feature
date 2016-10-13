Feature: The application responds appropriately to all events that correspond to the various forms of adding new bands to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin

    Scenario: Remove a band on a not empty bands' database 
        Given that the bands' database is not empty
        When I remove a band with name "70 Balcones" and genre "Rock"
        Then the bands' database should have 1 entry less
        And the database shouldn't have a band with  name "70 Balcones" and genre "Rock" 
    
