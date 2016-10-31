Feature: The application responds appropriately to all events that correspond to the various forms of Find an album in the system. 

    Background:
        Given that the application has been started
        And I have successfully logged in as admin
        
	Scenario: Search album on an empty album's database by title
        Given that the album's database is empty
        When I search an album with "title" "Perdidos" , the result of the search should have 0 entry
        
	Scenario: Search album on an not empty album's database by title, and the result is No content
		Given that the album's database have one album with title "Perdidos" and release date "2000-12-27"
        When I search an album with "title" "Perdidos" , the result of the search should have 1 entry

    Scenario: Search album on an empty album's database by release date
        Given that the album's database is empty
        When I search an album with "releaseDate" "2000-12-27" , the result of the search should have 0 entry
        
    Scenario: Search album on an not empty album's database by title, and the result is No content
        Given that the album's database have one album with title "Perdidos" and release date "2000-12-27"
        When I search an album with "releaseDate" "2000-12-27" , the result of the search should have 1 entry