#This scripts take a string representing an database query
#and it will append the query in a log file and redirect the error stream to an error log file

#1 : The query to execute

#echo "$1"
args=\""$1"\"
#echo "$args"

linux="./db_execute_docker_linux.sh $args"
darwin="./db_execute_docker_darwin.sh $args"
non64Bits="./db_execute_no_docker.sh $args"

./architectureSwitch.sh "$linux" "$darwin" "$non64Bits"
