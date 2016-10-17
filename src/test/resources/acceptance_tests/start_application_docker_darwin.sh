new_host=localhost
new_port=7500
#This script should start the application and store the pid in a hidden file like .saved_pid
#To save the pid to a file you should use echo $! > file right after executing the process
mvn -f ../../../../pom.xml exec:java -Dexec.args="-dbPort $new_port -dbHost $new_host" -Dexec.mainClass=ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.Bootstrap > log_app_out.txt 2> log_app_err.txt &
echo $! > .saved_pid
