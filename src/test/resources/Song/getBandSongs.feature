Feature: Get all Band Songs

# base case, the list is empty

Scenario: The band hasn't songs in the database
	Given That the band the name "Los Redondos"
	And the band the name "Los Redondos" hasn't song in the database
	When I get all band songs
	Then I return a empty list

Scenario: The band doesn't exist in the database
	Given that the artist with the name "Los Redondos" doesn't exist in the database
	When I get all songs of the band with the name "Los redondos"
	Then An exception is thrown
	
Scenario: The band has 1 song in the database
	Given that the band with the name "Los Redondos" has 1 song in the database
	And That the song with the name "Jijiji" of the artist "Los Redondos"
	When I get all songs of the artist the name "Los Redondos" 
	Then I return a list with all songs of "Los Redondos"
	And The song with the name "Jijiji" is in my list