#!/bin/bash

#This script should start the database on a separate thread and redirect all the output to the console
#the script should be something like start db &>/dev/null

linux="./start_db_docker_linux.sh"
darwin="./start_db_docker_darwin.sh"
non64Bits="./start_db_no_docker.sh"

echo "corriendo start db..."
./architectureSwitch.sh "$linux" "$darwin" "$non64Bits"
