Feature: The application responds appropriately to all events that correspond to the various forms of adding new bands to the system.

    Background:
        Given that the application has been started
        And I have successfully logged in as admin

    Scenario: Add a new band on an empty bands' database
        Given that the bands' database is empty
        When I add a band with name "70 Balcones" and genre "Rock"
        Then the bands' database should have 1 entry
        And the entry should have name "70 Balcones" and genre "Rock"

