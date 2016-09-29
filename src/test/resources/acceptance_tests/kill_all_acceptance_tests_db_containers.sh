#This script should stop the database


linux="./kill_all_acceptance_tests_db_containers_docker_linux.sh"
darwin="./kill_all_acceptance_tests_db_containers_docker_darwin.sh"
non64Bits="./kill_all_acceptance_tests_db_containers_no_docker.sh"

./architectureSwitch.sh "$linux" "$darwin" "$non64Bits"
