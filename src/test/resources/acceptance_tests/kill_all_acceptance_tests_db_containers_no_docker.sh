#This script should stop the database
new_host=localhost
new_port=5432

sudo service postgresql --full-restart
dropdb -h $new_host -p $new_port -U postgres --if-exists rcrockbands
dropuser -h $new_host -p $new_port -U postgres --if-exists rock_db_owner
