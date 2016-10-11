Feature: The application responds appropriately to all events that correspond to the various forms of adding new artists to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin

    Scenario: Add a new artist on an empty artist's database 
        Given that the artist's database is empty
        When I add an artist with name "Matias" and surname "Serra"
        Then the artist's database should have 1 entry
        And the entry should have name "Matias" and surname "Serra" 

