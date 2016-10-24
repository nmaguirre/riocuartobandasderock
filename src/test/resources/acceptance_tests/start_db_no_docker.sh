new_host=localhost
new_port=7500
#This script should start the database on a separate thread and redirect all the output to the console
#the script should be something like start db &>/dev/null 
psql -h $new_host -p $new_port -U postgres < ./../db_container/setup.sql 
psql -h $new_host -p $new_port -U postgres -d rcrockbands < ./../db_container/schema.sql 

