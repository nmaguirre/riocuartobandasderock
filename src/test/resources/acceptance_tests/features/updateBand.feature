Feature: The application responds appropriately to all events that correspond to the various forms of update bands to the system.

    Background:
      Given that the application has been started
      And I have successfully logged in as admin

    Scenario: Update a band on an empty bands' database
      Given that the bands' database is empty
      When I update the band with name "70 Balcones" and genre "Rock" to name "71 Balcones" and genre "Rock"
      Then the bands' database should have 0 entries

    Scenario: Update a band on a not empty bands' database (name and genre)
      Given that the bands' database have 1 entries
      And the bands' database have a band with name "Band1" and genre "Nu-Metal"
      When I update the band with name "Band1" and genre "Nu-Metal" to name "Metallica" and genre "Heavy-Metal"
      Then the bands' database should have 1 entries
      And the band with name "Metallica" and genre "Heavy-Metal" should be on bands' database

    Scenario: Update a band's name
      Given that the bands' database have 5 entries
      And the bands' database have a band with name "Band1" and genre "Nu-Metal"
      And the band with name "Metallica" and genre "Nu-Metal" is not in bands' datebase
      When I update the band with name "Band1" and genre "Nu-Metal" to name "Metallica" and genre "Nu-Metal"
      Then the bands' database should have 5 entries
      And the band with name "Metallica" and genre "Nu-Metal" should be on bands' database
      And the database shouldn't have a band with  name "Band1" and genre "Nu-Metal"

    Scenario: Update a band's genre
      Given that the bands' database have 5 entries
      And the bands' database have a band with name "Band1" and genre "Nu-Metal"
      And the band with name "Metallica" and genre "Nu-Metal" is not in bands' datebase
      When I update the band with name "Band1" and genre "Nu-Metal" to name "Band1" and genre "Heavy-Metal"
      Then the bands' database should have 5 entries
      And the band with name "Metallica" and genre "Heavy-Metal" should be on bands' database

    Scenario: Update a band that have a same genre but difrent name that other in the bands' database
      Given that the bands' database have 2 entries
      And the bands' database have a band with name "Band1" and genre "Nu-Metal"
      And the bands' database have a band with name "Band2" and genre "Nu-Metal"
      When I update the band with name "Band1" and genre "Nu-Metal" to name "Band2" and genre "Nu-Metal"
      Then the bands' database should have 2 entries
      And the band with name "Band1" and genre "Nu-Metal" should be on bands' database
      And the band with name "Band2" and genre "Nu-Metal" should be on bands' database
