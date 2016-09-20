Feature: Delete Song
	
Scenario: The song no exist 
	Given the user want to delete a song that no exist
	When the user press Delete Song button
	Then I say The song no exist
	
Scenerio: The song exist
	Given the user want to delete a exist song
	When the user press Delete Song button
	Then I remove the song 