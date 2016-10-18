Feature: Get all Artist Songs

# base case, 

Scenario: The artist hasn't songs in the database
	Given that the artist with the name "Indio Solari" 
	And The band the name "Indio Solari" hasn't songs in the database 
	When I get all songs of the artist with the name "Indio Solari"
	Then A message is shown

Scenario: The artist doesn't exist in the database
	Given that the artist with the name "Indio Solari" doesn't exist in the database
	When I get all songs of the artist with the name "Indio Solari"
	Then An exception is thrown

Scenario: The artist has 1 song in the database
	Given that the artist with the name "Indio Solari" has 1 song in the database
	And That the song with the name "Jijiji" of the artist "Indio Solari"
	When I get all songs of the artist the name "Indio Solari" 
	Then I return a list with all songs of "Indio Solari"
	And The song with the name "Jijiji" is in my list
	
	