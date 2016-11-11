Feature: The application responds appropiately to all events that correspond to the various forms of listing all albums in the system

	Background:
		Given that the application has been started

	Scenario: List all albums on an empty album's table
		Given that the album's database is empty
		When I list all the albums the result of the search should have 0 entries

	Scenario: List all albums on a populated album's table
		Given that the album's database have one album with title "Madrugando" and release date "2016-10-31"
		Given that the album's database have one album with title "Despierta" and release date "2010-10-10"
		When I list all the albums the result of the search should have 2 entries