Feature: Add song
	
Scenario: Invalide song
	When The user insert a song with incomplete or wrong information
	Then I say Invalide Song


Scenario: Existing Song
	When The user insert a song that exist
	Then I say Existing Song

Scenario: Add song
	When The user insert a song with name, genere, band, duration, author and album
	Then I have a song
	And I add the song
	