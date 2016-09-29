#This script should test if the database is running

linux="./db_is_up_docker_linux.sh"
darwin="./db_is_up_docker_darwin.sh"
non64Bits="./db_is_up_no_docker.sh"

./architectureSwitch.sh "$linux" "$darwin" "$non64Bits"
