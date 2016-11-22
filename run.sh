#!/bin/bash
new_host=localhost
new_port=5432

mvn clean
mvn compile
echo "============= STARTING DOCKER ============="
docker run -d -p $new_port:5432 rcrockbands_db_container:latest > .docker_saved_pid
echo "============ STARTING MAIN APP ============"
mvn exec:java -Dexec.mainClass=ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.Bootstrap -Dexec.args="-dbPort $new_port -dbHost $new_host"
echo "============= STOPING DOCKER  ============="
docker stop $(cat .docker_saved_pid)
rm .docker_saved_pid
