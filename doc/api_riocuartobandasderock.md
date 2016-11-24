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


# Group Artist
Resources related to artist in the API

## Artist Collection [/artists]

### List all artists [GET]
This will list all the albums in the database

+ Response 200 (application/json)
  + Body
            [
              {
                "ArtistID": "1",
                "name": "Matias",
                "surname": "Serra",
                "nickname": "lolo"
              }, {
                "ArtistID": "2",
                "name": "Matias",
                "surname": "Pirotto",
                "nickname": "saca"
              }, {
                "ArtistID": "3",
                "name": "Leandro",
                "surname": "Pirotto",
                "nickname": "chancha"
              }, {
                "ArtistID": "4",
                "name": "Rocky",
                "surname": "Balboa",
                "nickname": "chancha"
              }, {
                "ArtistID": "5",
                "name": "Juan",
                "surname": "Gonzalez",
                "nickname": "yupau"
              },
            ]
                      

### Create a new artist [POST]

You may add a new artist using this action. It takes a JSON object containing a name, surname and nickname of the artist.

+ name (string) - artist name
+ surname (string) - artist surname
+ nickname (string) - artist nickname

+ Request (application/json)
   + Body

            {
              "name": "Mariano",
              "surname": "Pirotto",
              "nickname": "arqueroConSuerte"
            }


+ Response 201 (application/json)

## Artist [/artists/findbyname/{name}]

+ Parameters
  + name (string) - name of the artist

### List all artists with a specific name [GET]

This will list all the artists in the database with a specific name

+ Response 200 (application/json)
   + Body
            [
              {
                "ArtistID": "1",
                "name": "Matias",
                "surname": "Serra",
                "nickname": "lolo"
              }, {
                "ArtistID": "2",
                "name": "Matias",
                "surname": "Pirotto",
                "nickname": "saca"
              }
            ]
## Artist [/artists/findbysurname/{surname}]

+ Parameters
  + surname (string) - name of the artist

### List all artists with a specific surname [GET]

This will list all the artists in the database with a specific surname

+ Response 200 (application/json)
   + Body
            [
               {
                "ArtistID": "2",
                "name": "Matias",
                "surname": "Pirotto",
                "nickname": "lolo"
              }, {
                "ArtistID": "3",
                "name": "Leandro",
                "surname": "Pirotto",
                "nickname": "chancha"
              }
            ]

## Artist [/artists/findbynickname/{nickname}]

+ Parameters
  + nickname (string) - nickname of the artist

### List all artists with a specific nickname [GET]

This will list all the artists in the database with a specific nickname

+ Response 200 (application/json)
   + Body
            [
              {
                "ArtistID": "3",
                "name": "Leandro",
                "surname": "Pirotto",
                "nickname": "chancha"
              }, {
                "ArtistID": "4",
                "name": "Rocky",
                "surname": "Balboa",
                "nickname": "chancha"
              }
            ] 

## Artist [/artists/{id}]

+ Parameters
  + id (string) - ID of the artist in the form of an string

### Search for a specific artist by his ID [GET]

This will return a specific artist

+ Response 200 (application/json)
  + Body

            [
              {
                "ArtistID": "1",
                "name": "Matias",
                "surname": "Serra",
                "nickname": "lolo"
              }
            ]

### Delete an artist [DELETE]

This will delete the specified artist

+ Response 200 

### Update an artist [PUT]

This will update the information of a specific artist

+ Response 200
            
## Artist [/artists/findbyallattributes/{?name}{&surname,nickname}]

+ Parameters
  + name (string) - name of the artist in the form of an string
  + surname (string) - surname of the artist in the form of an string
  + nickname (string) - nickname of the artist in the form of an string


### Search for a specific artist by his attributes [GET]

This will return a specific artist

+ Response 200 (application/json)
  + Body

            [
              {
                "ArtistID": "1",
                "name": "Matias",
                "surname": "Serra",
                "nickname": "lolo"
              }
            ]
            
## Artist [GET /artists/getbands/{?artistName}{&artistSurname,artistNickname}]

+ Parameters
  + artistName (string) - name of the artist in the form of an string
  + artistSurname (string) - surname of the artist in the form of an string
  + artistNickname (string) - nickname of the artist in the form of an string
            
### Search the bands of a specific artist by his attributes [GET]

This will list all the bands in the database with a specific artist

+ Response 200 (application/json)
  + Body

            [
              {
                "BandID": "1",
                "name": "Band1",
                "genre": "Rock"
              }
            ]   

## Artist [/artists/getbands/{artistID}]

+ Parameters
  + artistID (string) - ID of the artist in the form of an string
            
### Search the bands of a specific artist by his attributes [GET]

This will list all the bands in the database with a specific artist

+ Response 200 (application/json)
  + Body

            [
              {
                "BandID": "1",
                "name": "Band1",
                "genre": "Rock"
              }
            ]  
 

# Group BandMember
Resources related to bandMember in the API

## BandMember [/bandmembers]

### Create a new artist [POST]

You may add a new bandMember using this action. It takes a JSON object containing a name, surname and nickname of the artist.

+ artistID (string) - artist ID
+ bandID (string) - band ID

+ Request (application/json)
    + Body

            {
              "artistID": "1",
              "bandID": "12"
            }

+ Response 201 (application/json)

## BandMember [/bandmembers/{artistID}/{bandID}]

+ Parameters
  + artistID (string) - ID of the artist in the form of an string
  + bandID (string) - ID of the band in the form of an string


### Delete an bandMember [DELETE]

This will delete the specified bandMember

+ Response 200 