#!/bin/bash

if [ $# != 2 ]; then
    echo "Usage: sh update_scripts.sh <host> <port>"
else 
    darwin=false;
    case "`uname`" in
      Darwin*) darwin=true ;;
    esac
    if $darwin; then
        sedi="/usr/bin/sed -i ''"
    else
          sedi="sed -i"
    fi
    new_host="$1"
    new_port="$2"
    host_decl="new_host=$1"
    port_decl="new_port=$2"
    echo "Updating db_execute_docker_darwin.sh..."
    $sedi "1s/.*/$host_decl/" db_execute_docker_darwin.sh
    $sedi "2s/.*/$port_decl/" db_execute_docker_darwin.sh
    echo "Updating db_execute_docker_linux.sh..."
    $sedi "1s/.*/$host_decl/" db_execute_docker_linux.sh
    $sedi "2s/.*/$port_decl/" db_execute_docker_linux.sh
    echo "Updating db_execute_no_docker.sh..."
    $sedi "1s/.*/$host_decl/" db_execute_no_docker.sh
    $sedi "2s/.*/$port_decl/" db_execute_no_docker.sh
    echo "Updating db_is_up_docker_darwin.sh..."
    $sedi "1s/.*/$host_decl/" db_is_up_docker_darwin.sh
    $sedi "2s/.*/$port_decl/" db_is_up_docker_darwin.sh
    echo "Updating db_is_up_docker_linux.sh..."
    $sedi "1s/.*/$host_decl/" db_is_up_docker_linux.sh
    $sedi "2s/.*/$port_decl/" db_is_up_docker_linux.sh
    echo "Updating db_is_up_no_docker.sh..."
    $sedi "1s/.*/$host_decl/" db_is_up_no_docker.sh
    $sedi "2s/.*/$port_decl/" db_is_up_no_docker.sh
    echo "Updating start_application_docker_darwin.sh..."
    $sedi "1s/.*/$host_decl/" start_application_docker_darwin.sh
    $sedi "2s/.*/$port_decl/" start_application_docker_darwin.sh
    echo "Updating start_application_docker_linux.sh..."
    $sedi "1s/.*/$host_decl/" start_application_docker_linux.sh
    $sedi "2s/.*/$port_decl/" start_application_docker_linux.sh
    echo "Updating start_application_no_docker.sh..."
    $sedi "1s/.*/$host_decl/" start_application_no_docker.sh
    $sedi "2s/.*/$port_decl/" start_application_no_docker.sh
    echo "Updating start_db_docker_darwin.sh..."
    $sedi "1s/.*/$host_decl/" start_db_docker_darwin.sh
    $sedi "2s/.*/$port_decl/" start_db_docker_darwin.sh
    echo "Updating start_db_docker_linux.sh..."
    $sedi "1s/.*/$host_decl/" start_db_docker_linux.sh
    $sedi "2s/.*/$port_decl/" start_db_docker_linux.sh
    echo "Updating start_db_no_docker.sh..."
    $sedi "1s/.*/$host_decl/" start_db_no_docker.sh
    $sedi "2s/.*/$port_decl/" start_db_no_docker.sh
fi
