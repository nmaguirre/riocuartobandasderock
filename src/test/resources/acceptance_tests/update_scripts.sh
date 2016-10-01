#!/bin/bash

if [ $# != 2 ]; then
    echo "Usage: sh update_scripts.sh <host> <port>"
else 
    new_host="$1"
    new_port="$2"
    host_decl="new_host=$1"
    port_decl="new_port=$2"
    echo "Updating db_execute_docker_darwin.sh..."
    sed -i '' "1s/.*/$host_decl/" db_execute_docker_darwin.sh
    sed -i '' "2s/.*/$port_decl/" db_execute_docker_darwin.sh
    echo "Updating db_execute_docker_linux.sh..."
    sed -i '' "1s/.*/$host_decl/" db_execute_docker_linux.sh
    sed -i '' "2s/.*/$port_decl/" db_execute_docker_linux.sh
    echo "Updating db_execute_no_docker.sh..."
    sed -i '' "1s/.*/$host_decl/" db_execute_no_docker.sh
    sed -i '' "2s/.*/$port_decl/" db_execute_no_docker.sh
    echo "Updating db_is_up_docker_darwin.sh..."
    sed -i '' "1s/.*/$host_decl/" db_is_up_docker_darwin.sh
    sed -i '' "2s/.*/$port_decl/" db_is_up_docker_darwin.sh
    echo "Updating db_is_up_docker_linux.sh..."
    sed -i '' "1s/.*/$host_decl/" db_is_up_docker_linux.sh
    sed -i '' "2s/.*/$port_decl/" db_is_up_docker_linux.sh
    echo "Updating db_is_up_no_docker.sh..."
    sed -i '' "1s/.*/$host_decl/" db_is_up_no_docker.sh
    sed -i '' "2s/.*/$port_decl/" db_is_up_no_docker.sh
    echo "Updating start_application_docker_darwin.sh..."
    sed -i '' "1s/.*/$host_decl/" start_application_docker_darwin.sh
    sed -i '' "2s/.*/$port_decl/" start_application_docker_darwin.sh
    echo "Updating start_application_docker_linux.sh..."
    sed -i '' "1s/.*/$host_decl/" start_application_docker_linux.sh
    sed -i '' "2s/.*/$port_decl/" start_application_docker_linux.sh
    echo "Updating start_application_no_docker.sh..."
    sed -i '' "1s/.*/$host_decl/" start_application_no_docker.sh
    sed -i '' "2s/.*/$port_decl/" start_application_no_docker.sh
    echo "Updating start_db_docker_darwin.sh..."
    sed -i '' "1s/.*/$host_decl/" start_db_docker_darwin.sh
    sed -i '' "2s/.*/$port_decl/" start_db_docker_darwin.sh
    echo "Updating start_db_docker_linux.sh..."
    sed -i '' "1s/.*/$host_decl/" start_db_docker_linux.sh
    sed -i '' "2s/.*/$port_decl/" start_db_docker_linux.sh
    echo "Updating start_db_no_docker.sh..."
    sed -i '' "1s/.*/$host_decl/" start_db_no_docker.sh
    sed -i '' "2s/.*/$port_decl/" start_db_no_docker.sh
fi
