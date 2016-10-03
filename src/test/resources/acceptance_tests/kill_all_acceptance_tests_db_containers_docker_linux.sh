#This script should stop the database
docker ps|grep rcrockbands_db_container|cut -d ' '  -f1|xargs docker kill 2> /dev/null >/dev/null
