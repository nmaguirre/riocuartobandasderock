CREATE TABLE artistDB (
    artistID text primary key,
    name text,
    surname text,
    nickname text
);

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO rock_db_owner;
