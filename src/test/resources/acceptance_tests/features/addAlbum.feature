Feature: The application responds appropriately to all events that correspond to the various forms of adding new albums to the system. 

    Background:
		Given that the application has been started

	Scenario: Add an album with empty title and empty release date on empty database
		Given that the album's database is empty
		When I try to add an album named "" and release date ""
		Then the album's database should have 0 entry	
	
	Scenario: Add a new album on an empty album's database 
		Given that the album's database is empty
		When I add an album with name "Perdido" and release date "2000-12-27"
		Then the album's database should have 1 entry
		And the entry should have name "Perdido" and release date "2000-12-27" 

    Scenario: Add an existing album
		Given that the database contains an album named "Perdido" and release date "2000-12-27"
		When I try to add an album named "Perdido" and release date "2000-12-27" 
		Then the system informs that album named "Perdido" and release date "2000-12-27" already exists in the database
		And the album's database does not change and maintain 1 entry

    Scenario: Add an album with the same title but distinct release date
		Given that the database contains an album named "Perdido" and release date "2000-12-27"
		When I try to add an album named "Perdido" and release date "2001-12-27"
		Then the album's database should have 2 entry

	Scenario: Add an album with the same release date but distinct title
		Given that the database contains an album named "Perdido" and release date "2000-12-27"
		When I try to add an album named "Encontrado" and release date "2000-12-27"
		Then the album's database should have 2 entry
	
	Scenario: Add an album with the distinct title and distinct release date
		Given that the database contains an album named "Perdido" and release date "2000-12-27"
		When I try to add an album named "Encontrado" and release date "2001-09-11"
		Then the album's database should have 2 entry
	
	Scenario: Add an album with empty title and empty release date 		
		Given that the database contains an album named "Perdido" and release date "2000-12-27"
		When I try to add an album named "" and release date ""
		Then the album's database should have 1 entry

	