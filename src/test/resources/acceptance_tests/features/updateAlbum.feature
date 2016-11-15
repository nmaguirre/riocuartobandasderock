Feature: update album

	Background:
		Given that the application has been started
		And I have successfully logged in as admin

	Scenario: update album on an empty database.
		Given that the album's database is empty
		When I update the album named "Perdidos" by an album named "Encontrados" and release date "1999-10-02"
		Then an exception is throw

	Scenario: update an album that doesn't exist.
		Given that the album with name "Perdidos" doesn't exist in database
		When I update the album named "Perdidos" by an album named "Encontrados" and release date "1999-10-02"
		Then an exception is throw

	Scenario: update the name of album that exist in database.
		Given that the database contains an album named "Perdidos" and release date "2000-12-27"
		When I update the album named "Perdidos" with new "title" "Encontrados"
		Then the database contains an album named "Encontrados" and release date "2000-12-27"

	Scenario: update the release date of album that exist in database.
		Given that the database contains an album named "Perdidos" and release date "2000-12-27"
		When I update the album named "Perdidos" with new "releaseDate" "1999-10-02"
		Then the database contains an album named "Perdidos" and release date "1999-10-02"

	Scenario: update the name and release date of album that exist in database.
		Given that the database contains an album named "Perdidos" and release date "2000-12-27"
		When I update the album named "Perdidos" by an album named "Encontrados" and release date "1999-10-02"
		Then the database contains an album named "Encontrados" and release date "1999-10-02"
