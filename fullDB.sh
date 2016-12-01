#!/bin/bash
new_host=localhost
new_port=7500
path="/home/agustin/workspace/riocuartobandasderock/src/main/resources/database"

psql -h $new_host -p $new_port -U rock_db_owner -d rcrockbands -c "\COPY AlbumDB FROM $path/Album.csv DELIMITERS ',' CSV ;"

psql -h $new_host -p $new_port -U rock_db_owner -d rcrockbands -c "\COPY artistDB FROM $path/artist.csv DELIMITERS ',' CSV ;"

psql -h $new_host -p $new_port -U rock_db_owner -d rcrockbands -c "\COPY SongDB FROM $path/Song.csv DELIMITERS ',' CSV ;"

psql -h $new_host -p $new_port -U rock_db_owner -d rcrockbands -c "\COPY BandDB FROM $path/Bandas.csv DELIMITERS ',' CSV ;"
