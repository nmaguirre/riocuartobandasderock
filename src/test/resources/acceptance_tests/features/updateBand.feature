Feature: The application responds appropriately to all events that correspond to the various forms of update bands to the system.

    Background:
      Given that the application has been started
      And I have successfully logged in as admin

    Scenario: Add a band on an empty bands' database
      Given that the bands' database is empty
      When I update the band with name "70 Balcones" and genre "Rock" to name "71 Balcones" and genre "Rock"
      Then the bands' database should have 0 entries

    Scenario: Update a band on a not empty bands' database
      Given that the bands' database have 1 entries
      And the bands' database have a band with name "Metalica" and genre "Heaby Metal"
      When I update a band with name "Metallica" and genre "Heavy Metal"
      Then the bands' database should have 1 entries
      And the band with name "Metallica" and genre "Heavy Metal" should be on bands' database

    Scenario: Update a band that is not exist in the bands' database
      Given that the bands' database have 5 entries
      And the bands' database havn't a band with name "Band1" and genre "Nu Metal"
      When I update the band with name "Band1" and genre "Nu Metal" to name "Band2" and genre "Nu Metal"
      Then the bands' database should have 5 entries
      And the band with name "Band2" and genre "Nu Metal" shouldn't be on bands' database

    Scenario: Update a band that have a same name but difrent genre that other in the bands' database
      Given that the bands' database have 2 entries
      And the bands' database have a band with name "ACDC" and genre "Rock"
      And the bands' database have a band with name "ACDC" and genre "Hard Rock"
      When I update the band with name "ACDC" and genre "Hard Rock" to name "ACDC" and genre "Rock"
      Then the bands' database should have 2 entries
      And the band with name "ACDC" and genre "Hard Rock" should be on bands' database

    Scenario: Update a band that have a same genre but difrent name that other in the bands' database
      Given that the bands' database have 2 entries
      And the bands' database have a band with name "ACDC" and genre "Rock"
      And the bands' database have a band with name "U2" and genre "Rock"
      When I update the band with name "ACDC" and genre "Rock" to name "U2" and genre "Rock"
      Then the bands' database should have 2 entries
      And the band with name "U2" and genre "Rock" should be on bands' database
