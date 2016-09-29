#This script should start the application and store the pid in a hidden file like .saved_pid
#To save the pid to a file you should use echo $! > file right after executing the process
mvn -f ../../../../pom.xml exec:java -Dexec.args="--db-port 5432 --db-host 192.168.99.100" -Dexec.mainClass=ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.BootStrap > log_app_out.txt 2> log_app_err.txt &
echo $! > .saved_pid
