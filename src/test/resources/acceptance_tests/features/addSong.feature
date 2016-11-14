Feature: The application responds appropriately to all events that correspond to the various forms of adding new songs to the system. 

    Background:
        Given that the application has been started

    Scenario: Add a new song on an empty song's database 
        Given that the song's database is empty
        When I add a song with name "Stairway to Heaven" and duration "400"
        Then the song's database should have 1 entry
        And the entry should have name "Stairway to Heaven" and duration "400" 

