#This script should start the application and store the pid in a hidden file like .saved_pid
#To save the pid to a file you should use echo $! > file right after executing the process

linux="./start_application_docker_linux.sh"
darwin="./start_application_docker_darwin.sh"
non64Bits="./start_application_no_docker.sh"

./architectureSwitch.sh "$linux" "$darwin" "$non64Bits"
