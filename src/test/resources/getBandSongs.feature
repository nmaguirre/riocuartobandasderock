Feature: Get all Band Songs
	All band songs should not show until that user press List Song button
	If the band not have songs then I say No songs
	If the band have songs then I show it

# base case, the list is empty

Scenario: The band hasn't songs in the database
	Given That the band the name "Los Redondos"
	And the band the name "Los Redondos" hasn't song in the database
	When I get all band songs
	Then I return a empty list

# uses the scenario outline for implement others cases

Scenario Outline: The list contain <songs>
	Given there <songs> in the list
	When the user press List Songs
	Then I show you <return>

	Example:
	| songs | show            |
	| 1     | the unique song |
	| 2     | all songs       |
