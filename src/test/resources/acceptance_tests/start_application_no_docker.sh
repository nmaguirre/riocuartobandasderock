#This script should start the application and store the pid in a hidden file like .saved_pid
#To save the pid to a file you should use echo $! > file right after executing the process
mvn -f ../../../../pom.xml exec:java -Dexec.args="--db-port 5432 --db-host localhost" -Dexec.mainClass=ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.Bootstrap > log_app_out.txt 2> log_app_err.txt &
echo $! > .saved_pid

