#This script should start the database on a separate thread and redirect all the output to the console
#the script should be something like start db &>/dev/null
docker run -d -p 5432:5432 rcrockbands_db_container:latest &>/dev/null
