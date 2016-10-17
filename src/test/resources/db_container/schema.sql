CREATE TABLE artistDB (
    artistID text primary key,
    name text,
    surname text,
    nickname text
);

CREATE TABLE Songs (
    idSong int primary key,
    nameSong text not null,
    duration int
);

CREATE TABLE bandDB (
    id text primary key,
    name text not null,
    genre text not null
);



GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO rock_db_owner;
