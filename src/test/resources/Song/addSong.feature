Feature: Add a song
	
Scenario: Add a song with an inexistent genre
	Given that I want to add a song with the genre "gen"
	And the genre "gen" doesn't exists
	When I add the song 
	Then an exception is thrown, "The genre "gen" doesn't exists"
	
	
Scenario: Add a song of an inexistent band
	Given that I want to add a song of the band "ban"
	And the band "ban" doesn't exists
	When I add the song
	Then an exception is thrown, "The band "ban" doesn't exists"
        
        
Scenario: Add a song of an inexistent author
	Given that I want to add a song of the author "aut"
	And the author "aut" doesn't exists
	When I add the song
	Then an exception is thrown, "The author "aut" doesn't exists"    
        
        
Scenario: Add a song of an inexistent album
	Given that I want to add a song of the album "alb"
	And the album "alb" doesn't exists
	When I add the song
	Then an exception is thrown, "The album "alb" doesn't exists"
        

Scenario: Add an existing song
	Given the song called "son" from the album "alb", performed by the bands "listOfBands" exists in database
	When I add a song with the name "son" in the album "alb" of the bands "listofBands"
	Then an exception is thrown, "The song already exists"
	

Scenario: Add a song
	When I add a song with the name "son", genre "gen", author "aut", duration "dur", album "alb" and bands "listofBands"
	Then the song has name "son"
	Then the song has genre "gen"
	Then the song belongs to "aut"
	Then the song has duration "dur"
	Then the song it is performed by "listofBands"
	
	
