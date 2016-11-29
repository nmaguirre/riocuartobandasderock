Feature: The application responds appropriately to all events that correspond to the various forms of deleting albums in the system

	Background:
		Given that the application has been started
		#And I have successfully logged in as admin

	Scenario: Delete an album on an empty database
		Given that the album's database is empty
		When I try to delete an album with name "Perdidos" and release date "1999-10-22"
		Then the album's database remains empty

	Scenario: Delete an album that doesn't exist on a non-empty database
		Given that the album's database have 1 entry
		And the album named "Perdidos" doesn't exist in the album's database
		When I delete the album named "Perdidos"
		Then the album's database have 1 entry
		And the album named "Perdidos" doesn't exist in database
	
	Scenario: I delete an existent album in database
		Given that the database contains an album with name "Perdidos" and release date "2000-12-27"
		When I delete the album with name "Perdidos" and release date "2000-12-27"
		Then the album's database doesn't contain an album with name "Perdidos" and release date "2000-12-27"