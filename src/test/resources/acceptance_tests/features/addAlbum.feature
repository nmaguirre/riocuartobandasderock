Feature: The application responds appropriately to all events that correspond to the various forms of adding new albums to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin

    Scenario: Add a new album on an empty album's database 
        Given that the album's database is empty
        When I add an album with name "Perdido en un sueno" and release date "2002"
        Then the album's database should have 1 entry
        And the entry should have name "Perdido en un sueno" and release date "2002" 

