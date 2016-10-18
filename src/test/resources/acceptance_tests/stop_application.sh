#!/bin/bash

#This scripts kills the application whose PID is stored in the hidden file .saved_pid

linux="./stop_application_docker_linux.sh"
darwin="./stop_application_docker_darwin.sh"
non64Bits="./stop_application_no_docker.sh"

./architectureSwitch.sh "$linux" "$darwin" "$non64Bits"
