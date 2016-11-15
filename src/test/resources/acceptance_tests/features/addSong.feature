Feature: The application responds appropriately to all events that correspond to the various forms of adding new songs to the system.

    Background:
        Given that the application has been started

    Scenario: Add a new song on an empty song's database
        Given that the song's database is empty
        When I add a song with name "Stairway to Heaven" and duration "400"
        Then the song's database should have 1 entry
        And the entry should have name "Stairway to Heaven" and duration "400"


    Scenario: Add a new song with the same name that an song that stores in the database but diferent duration
        Given that the song's database have one song with name "Jijiji" and duration "5"
        When I add an song with name "Jijiji" and duration "2"
        Then the song's database should have 2 entry

       Scenario: Add a new song with the same name, duration that an song that store before in the database
     		Given that the song's database have one song with name "Jijiji" and duration "5"
        When I add an song with name "Jijiji" and duration "5"
        Then the song's database should have 2 entry
