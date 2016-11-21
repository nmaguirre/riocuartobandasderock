Feature: The application responds appropriately to all events that correspond to the various forms of updating albums in the system

	Background:
		Given that the application has been started
		And I have successfully logged in as admin

	Scenario: Update an album on an empty database
		Given that the album's database is empty
		When I try to update an album with name "Perdidos" and release date "1999-10-22" to name "Encontrados" and release date "1999-10-02"
		Then the album's database remains empty

	Scenario: Update a non-existing album on a non-empty database.
		Given that the album's database have 2 entries
		And the album named "Perdidos" doesn't exist in database
		When I update the album with name "Perdidos" to name "Encontrados"
		Then the album's database have 2 entries
		And the album named "Perdidos" doesn't exist in database

	Scenario: Update the name of an existing album in database
		Given that the album's database contains an album with title "Perdidos" and release date "2000-12-27"
		When I update the album name from "Perdidos" to "Encontrados" keeping "2000-12-27" as release date
		Then the database contains an album named "Encontrados" with release date "2000-12-27"

	Scenario: Update the release date of an existing album in database
		Given that the album's database contains an album with title "Perdidos" and release date "2000-12-27"
		When I update the album release date from "2000-12-27" to "1999-10-02" keeping "Perdidos" as name
		Then the database contains an album named "Encontrados" with release date "1999-10-02"

	Scenario: Update the name and the release date of an existing album in database
		Given that the album's database contains an album with title "Perdidos" and release date "2000-12-27"
		When I update the album with name "Perdidos" and release date "2000-12-27" to name "Encontrados" and release date "1999-10-02"
		Then the database contains an album named "Encontrados" with release date "1999-10-02"