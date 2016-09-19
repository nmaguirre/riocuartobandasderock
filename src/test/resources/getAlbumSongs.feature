Feature: Get all Album Songs
	All album songs should not show until that user press List Song button
	If the album not have songs then I say No songs
	If the album have songs then I show it

# base case, the list is empty

Scenario: The album no have songs in my list
	Given The album no have songs 
	When the user press List Songs
	Then I say No Songs

# uses the scenario outline for implent others cases

Scenario Outline: The list contain <songs>
	Given there <songs> in the list
	When the user press List Songs
	Then I show you <return>

	Example:
	| songs            | show            |
	| one song         | the unique song |
	| two o more songs | all songs       |
