new_host=192.168.99.100
new_port=5432
#This script should test if the database is running

psql -h $new_host -p $new_port  -U rock_db_owner -d rcrockbands -c "select 1;" > /dev/null 2> /dev/null
