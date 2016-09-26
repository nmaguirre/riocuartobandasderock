CREATE TABLE album (
    id int primary key,
    title text not null,
    releaseDate date
);

CREATE TABLE song (
    id int primary key, 
    name text not null, 
    duration int
);

CREATE TABLE album_songs (
    album_id int references album(id),
    song_id int references song(id) 
);

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO rock_db_owner;
