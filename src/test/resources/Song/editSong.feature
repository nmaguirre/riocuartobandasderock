Feature: Edit Song

Scenario: Invalide song
	Given the user want to edit a song with incomplete or wrong information
	When the user press Edit Song button
	Then I say Invalide Song
	
Scenario: The song no exist 
	Given the user want to edit a song that no exist
	When the user press Edit Song button
	Then I say The song no exist
	
Scenerio: The song exist
	Given the user want to edit a exist song
	When the user press Edit Song button
	And the user insert a new name, genere, band, duration, author and album
	Then I change the song with the new information