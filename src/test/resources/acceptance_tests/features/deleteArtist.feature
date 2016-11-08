Feature: The application responds appropriately to all events that correspond to the various forms of delete on artists to the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin

    Scenario: delete one artist on artist's database with one entry
        Given that the artist's database have one artist with name "Matias" and surname "Serra" and nickname "matu"
        When delete this artist with name "Matias" and surname "Serra" and nickname "matu"
        Then the artist's database should have 0 entry