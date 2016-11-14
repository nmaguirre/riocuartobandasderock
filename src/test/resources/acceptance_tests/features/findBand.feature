Feature: The application responds appropriately to all events that correspond to the various forms of Find a band into the system.

  Background:
    Given that the application has been started

  Scenario: Search bands on an empty band's database by name
    Given that the bands' database is empty
    When I search a band with name "Ska-p", the application must return 0 results

  Scenario: Search bands on an empty band's database by genre
    Given that the bands' database is empty
    When I search a band with genre "ska/punk", the application must return 0 results

  Scenario: Search bands on an empty band's database by name and genre
    Given that the bands' database is empty
    When I search a band with name "Ska-p", and genre "ska/punk", the application must return 0 results

  Scenario: Search bands on a not empty band's database by name
    Given that the bands' database have 5 entries
    When I search a band with name "Ska-p", the band exist in the database, then the application must return 1 results 

  Scenario: Search bands on a not empty band's database by genre
    Given that the bands' database have 5 entries
    When I search a band with genre "ska/punk", the band exist in the database, then the application must return 1 results 

  Scenario: Search bands on a not empty band's database by name and genre
    Given that the bands' database have 5 entries
    When I search a band with name "Ska-p", and genre "ska/punk", the band exist in the database, the application must return 1 results

  Scenario: Search bands on a not empty band's database by name
    Given that the bands' database have 5 entries
    When I search a band with name "Ska-p", the band doesn't exist in the database, then the application must return 0 results

  Scenario: Search bands on a not empty band's database by genre
    Given that the bands' database have 5 entries
    When I search a band with genre "Ska-p", the band doesn't exist in the database, then the application must return 0 results

  Scenario: Search bands on a not empty band's database by name and genre
    Given that the bands' database have 5 entries
    When I search a band with name "Ska-p", and genre "ska/punk", the band doesn't exist in the database, the application must return 0 results 