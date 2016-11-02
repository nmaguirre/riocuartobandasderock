CREATE TABLE AlbumDB (
	albumID text primary key,
	title text not null,
	releaseDate date
);

CREATE TABLE artistDB (
    artistID text primary key,
    name text,
    surname text,
    nickname text
);

CREATE TABLE SongDB (
    idSong int primary key,
    id text not null,
    name text not null, 
    duration int,
    CONSTRAINT fkidAlbum foreign key (id) references Album
);

CREATE TABLE BandDB (
    bandID text primary key,
    name text not null,
    genre text not null
);

CREATE TABLE BandMemberDB (
	artistID text REFERENCES artistDB (artistID),
	bandID text REFERENCES BandDB (bandID)	
);



GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO rock_db_owner;
