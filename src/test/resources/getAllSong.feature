Feature: Get all songs
	Songs should not show until that user press List Songs button
	If I not have songs then I say No songs
	If I have songs then I show it

# base case, the list is empty

Scenario: I no have songs in my list
	Given I no have songs 
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
