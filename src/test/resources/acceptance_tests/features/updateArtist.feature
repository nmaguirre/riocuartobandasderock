Feature: The application responds appropriately to all events that correspond to the various forms of update on artists to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin

    Scenario: Update one artist on artist's database with one entry
        Given that the artist's database has "1" entry
        and the artist entry has name "Matias" and surname "Serra" and nickname ""
        When I modify this artist and set name "Jacinto"
        Then the artist's database should have 1 entry
        And the artist entry has name "Jacinto" and surname "Serra" and nickname ""
