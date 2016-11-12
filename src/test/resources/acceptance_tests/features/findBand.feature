Feature: The application responds appropriately to all events that correspond to the various forms of Find a band into the system.

  Background:
    Given that the application has been started

  Scenario: Search bands on an empty band's database by name
    Given that the bands' database is empty
    When I search a band with name "Ska-p" and the band doesn't exist in the database
    Then the band with name "Ska-p" shouldn't be on bands' database

  Scenario: Search bands on an empty band's database by genre
    Given that the bands' database is empty
    When I search a band with genre "ska/punk" and the band doesn't exist in the database
    Then the band with genre "ska/punk" shouldn't be on bands' database

  Scenario: Search bands on an empty band's database by name and genre
    Given that the bands' database is empty
    When I search a band with name "Ska-p" and genre "ska/punk" and the band doesn't exist in the database
    Then the band with name "Ska-p" and genre "ska/punk" shouldn't be on bands' database

  Scenario: Search bands on a not empty band's database by name
    Given that the bands' database have 5 entries
    When I search a band with name "Band1" and the band exist in the database
    Then the band with name "Band1" should be on bands' database

  Scenario: Search bands on a not empty band's database by genre
    Given that the bands' database have 5 entries
    When I search a band with genre "Nu Metal" and the band exist in the database
    Then the band with genre "Nu Metal" should be on bands' database

  Scenario: Search bands on a not empty band's database by name and genre
    Given that the bands' database have 5 entries
    When I search a band with name "Band1" and genre "Nu Metal" and the band exist in the database
    Then the band with name "Band1" and genre "Nu Metal" should be on bands' database

  Scenario: Search bands on a not empty band's database by name
    Given that the bands' database have 5 entries
    When I search a band with name "Ska-p" and the band doesn't exist in the database
    Then the band with name "Ska-p" shouldn't be on bands' database

  Scenario: Search bands on a not empty band's database by genre
    Given that the bands' database have 5 entries
    When I search a band with genre "Ska-p" and the band doesn't exist in the database
    Then the band with genre "Ska-p" shouldn't be on bands' database

  Scenario: Search bands on a not empty band's database by name and genre
    Given that the bands' database have 5 entries
    When I search a band with name "Ska-p" and genre "ska/punk" and the band doesn't exist in the database
    Then the band with name "Ska-p" and genre "ska/punk" shouldn't be on bands' database