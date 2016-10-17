new_host=localhost
new_port=7500
#This script should test if the database is running

psql -h $new_host -p $new_port  -U rock_db_owner -d rcrockbands -c "select 1;" > /dev/null 2> /dev/null
