#Darwing 64 bit implementation of db_execute.sh

#This scripts take a string representing an database query
#and it will append the query in a log file and redirect the error stream to an error log file

#1 : The query to execute

args="$@"

echo "Darwin 64 bit implementation of db_execute.sh : $args"
