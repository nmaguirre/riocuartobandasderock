#!/bin/bash
new_host=localhost
new_port=5432

mvn clean
mvn compile
echo "============= STARTING DOCKER ============="
docker run -d -p $new_port:5432 rcrockbands_db_container:latest > .docker_saved_pid
echo "========= INSERT INFO IN DATABASE ========="
psql -h $new_host -p $new_port -U rock_db_owner -d rcrockbands -c "\COPY AlbumDB FROM src/main/resources/database/Album.csv DELIMITERS ',' CSV ;"
psql -h $new_host -p $new_port -U rock_db_owner -d rcrockbands -c "\COPY artistDB FROM src/main/resources/database/artist.csv DELIMITERS ',' CSV ;"
psql -h $new_host -p $new_port -U rock_db_owner -d rcrockbands -c "\COPY SongDB FROM src/main/resources/database/Song.csv DELIMITERS ',' CSV ;"
psql -h $new_host -p $new_port -U rock_db_owner -d rcrockbands -c "\COPY BandDB FROM src/main/resources/database/Bandas.csv DELIMITERS ',' CSV ;"
echo "============ STARTING MAIN APP ============"
mvn exec:java -Dexec.mainClass=ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.Bootstrap -Dexec.args="-dbPort $new_port -dbHost $new_host"
echo "============= STOPING DOCKER  ============="
docker stop $(cat .docker_saved_pid)
rm .docker_saved_pid
