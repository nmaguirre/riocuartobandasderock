#This script should test if the database is running

psql -h 192.168.99.100 -p 5432  -U rock_db_owner -d rcrockbands -c "select 1;" > /dev/null 2> /dev/null

