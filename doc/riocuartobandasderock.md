FORMAT: 1A

# Rio Cuarto bandas de rock

Rio Cuarto bandas de rock is a simple API that allows users to navigate through information of rock bands from Rio Cuarto.

# Group Albums
Resources related to albums in the API
## Album Collection [/albums]
### List all albums [GET]
This will list all the albums in the database
+ Response 200 (application/json)
    + Body

            [{
                "AlbumID": "1",
                "title": "Perdido",
                "releaseDate": "2012-01-01",
                "songs": 
                      [{
                          "idSong": "1",
                          "name": "Cancion A",
                          "duration": "200"
                      },{
                          "idSong": "3",
                          "name": "Cancion V",
                          "duration": "160"
                      },{
                          "idSong": "5",
                          "name": "Cancion E",
                          "duration": "240"
                      }],
                "band": "5"
            },{
                "AlbumID": "2",
                "title": "Mañana",
                "releaseDate": "2013-01-01",
                "songs": 
                      [{
                          "idSong": "4",
                          "name": "Cancion A",
                          "duration": "200"
                      }, {
                          "idSong": "2",
                          "name": "Cancion F",
                          "duration": "160"
                      }, {
                          "idSong": "8",
                          "name": "Cancion G",
                          "duration": "240"
                      }],
                "band": "4"
            }]
                      

### Create a new album [POST]

You may add a new band using this action. It takes a JSON object containing a title, a release date with format yyyy-mm-dd, an array of songs and the id of the band which it belongs to.

+ title (string) - Album title
+ release_date (string) - Album release date with format yyyy-mm-dd
+ songs (array[object]) - JSON array of songs in the album
+ band_id (int) - Id of the album band

+ Request (application/json)
    + Body

            {
              "title": "Mañana",
              "releaseDate": "2013-01-01",
              "songs": 
                [{
                  "idSong": "4",
                  "name": "Cancion A",
                  "duration": "200"
                }, {
                  "idSong": "2",
                  "name": "Cancion F",
                  "duration": "160"
                }, {
                  "idSong": "8",
                  "name": "Cancion G",
                  "duration": "240"
                }],
              "band": "4"
            }


+ Response 201 (application/json)

## Album [/albums/findByTitle/{title}]

+ Parameters
  + title (string) - Title of the album

### List all albums with a specific title [GET]

This will list all the albums in the database with a specific title

+ Response 200 (application/json)
    + Body

            [
              {
                "AlbumID": "1",
                "title": "Perdido",
                "releaseDate": "2012-01-01",
                "songs": 
                  [{
                    "idSong": "1",
                    "name": "Cancion A",
                    "duration": "200"
                  }, {
                    "idSong": "3",
                    "name": "Cancion V",
                    "duration": "160"
                  }, {
                    "idSong": "5",
                    "name": "Cancion E",
                    "duration": "240"
                  }],
                "band": "5"
              }, {
                "AlbumID": "5",
                "title": "Perdido",
                "releaseDate": "2014-01-01",
                "songs": 
                  [{
                    "idSong": "12",
                    "name": "Cancion A",
                    "duration": "200"
                  }, {
                    "idSong": "32",
                    "name": "Cancion F",
                    "duration": "160"
                  }, {
                    "idSong": "48",
                    "name": "Cancion G",
                    "duration": "240"
                  }],
                "band": "1"
              }
            ]



## Album [/albums/findByReleaseDate/{release_date}]

+ Parameters
  + release_date (string) - Release date of the album

### List all albums with a specific release date [GET]

This will list all the albums in the database with a specific release date

+ Response 200 (application/json)
    + Body

            [{
                "AlbumID": "1",
                "title": "Perdido",
                "releaseDate": "2012-01-01",
                "songs": 
                  [{
                    "idSong": "1",
                    "name": "Cancion A",
                    "duration": "200"
                  }, {
                    "idSong": "3",
                    "name": "Cancion V",
                    "duration": "160"
                  }, {
                    "idSong": "5",
                    "name": "Cancion E",
                    "duration": "240"
                  }],
                "band": "5"
              }, {
                "AlbumID": "6",
                "title": "Madrugando",
                "releaseDate": "2012-01-01",
                "songs":
                  [{
                    "idSong": "142",
                    "name": "Cancion A",
                    "duration": "200"
                  }, {
                    "idSong": "31",
                    "name": "Cancion F",
                    "duration": "160"
                  }, {
                    "idSong": "45",
                    "name": "Cancion G",
                    "duration": "240"
                  }],
                "band": "75"
              }]  

## Album [/albums/{album_id}]

+ Parameters
  + album_id (number) - ID of the album in the form of an integer

### Search for a specific album [GET]

This will return a specific album

+ Response 200 (application/json)
  + Body

              {
                "AlbumID": "6",
                "title": "Madrugando",
                "releaseDate": "2012-01-01",
                "songs": 
                  [{
                    "idSong": "142",
                    "name": "Cancion A",
                    "duration": "200"
                  }, {
                    "idSong": "31",
                    "name": "Cancion F",
                    "duration": "160"
                  }, {
                    "idSong": "45",
                    "name": "Cancion G",
                    "duration": "240"
                  }],
                "band": "75"
              }

### Delete an album [DELETE]

This will delete the specified album

+ Response 200 

### Update an album [PUT]

This will update the information of a specific albun

+ Response 200

# Group Songs
Resources related to songs in the API
## Songs Collection [/songs/]
### List all songs [GET]
This will list all the songs in the database
+ Response 200 (application/json)
    + Body

            [{
                "idSong": "1",
                "name": "Jijiji",
                "duration": "336",
                "album": "7"
            },{
                "idSong": "7",
                "name": "Estadio Azteca",
                "duration": "229",
                "album": "3"
            }]
            
### Create a new Song [POST]

You may add a new song using this action. It takes a JSON object containing a name, a duration in seconds and the id of the album which it belongs to.

+ name (string) - Song name
+ duration (int) - Song duration
+ album_id (int) - Id of the song album

+ Request (application/json)
    + Body

            {
                "idSong": "8",
                "name": "Los Dinosaurios",
                "duration": "218",
                "album_id": "4"
            }


+ Response 201 (application/json)

## Song [/songs/findbyname/{name}]

+ Parameters
  + name (string) - name of the song

### List all songs with a specific name [GET]

This will list all the songs in the database with a specific name

+ Response 200 (application/json)
    + Body

            [
              {
                "idSong": "9",
                "name": "Los Dinosaurios",
                "duration": "218",
                "album_id": "4"
            }, {
               "idSong": "10",
                "name": "Los Dinosaurios",
                "duration": "229",
                "album_id": "9"
            }]
            
## Song [/songs/findbyduration/{duration}]

+ Parameters
  + duration (int) - duration of the song

### List all songs with a specific duration [GET]

This will list all the songs in the database with a specific duration

+ Response 200 (application/json)
    + Body

            [
              {
                "idSong": "9",
                "name": "Los Dinosaurios",
                "duration": "218",
                "album_id": "4"
            }, {
               "idSong": "14",
                "name": "Mujer Amante",
                "duration": "218",
                "album_id": "10"
            }]

## Song [/songs/{song_id}]

+ Parameters
  + song_id (number) - ID of the song in the form of an integer

### Deleta a song with a specific id [DELETE]

This will delete the specified song

+ Response 200

