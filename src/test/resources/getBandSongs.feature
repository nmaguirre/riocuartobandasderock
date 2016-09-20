Feature: Get all Band Songs
	All band songs should not show until that user press List Song button
	If the band not have songs then I say No songs
	If the band have songs then I show it

# base case, the list is empty

Scenario: The band no have songs in my list
	Given The band no have songs 
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
