Feature: delete album

	Background:
        Given that the application has been started
        And I have successfully logged in as admin
	
	Scenario: I delete an album that doesn't exist
		Given that the album with name "Perdidos" doesn't exist in database
		When I delete an album with "title" "Perdidos"
		Then An exception is throw
	
	Scenario: I delete an album that exist
		Given that the database contains an album named "Perdidos" and release date "2000-12-27"
		When I delete an album with "title" "Perdidos"
		Then the album's database should have 0 entry