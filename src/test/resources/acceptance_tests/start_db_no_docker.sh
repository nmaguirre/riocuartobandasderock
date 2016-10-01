new_host=192.168.99.100
new_port=5432
#This script should start the database on a separate thread and redirect all the output to the console
#the script should be something like start db &>/dev/null
/etc/init.d/postgresql start && sudo -u postgres psql < ./../db_container/setup.sql && /etc/init.d/postgresql stop
/etc/init.d/postgresql start && sudo -u postgres psql -d rcrockbands < ./../db_container/schema.sql && /etc/init.d/postgresql stop
/usr/lib/postgresql/9.5/bin/postgres -D /var/lib/postgresql/9.5/main -c config_file=/etc/postgresql/9.5/main/postgresql.conf


