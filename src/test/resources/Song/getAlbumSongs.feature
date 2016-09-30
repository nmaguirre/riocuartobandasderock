Feature: Get album songs


Scenario: The album doesn't have songs in the list
	Given the album "alb" from the band "ban"
	And "alb" doesn't have any songs
	When I request the "alb" songs
	Then I return the message "The album "alb" doesn't have songs"


Scenario: Get all album songs
        Given the album "alb" with a list of songs, from the band "ban"
        When I request the "alb" songs
        Then I return all the songs of the list
