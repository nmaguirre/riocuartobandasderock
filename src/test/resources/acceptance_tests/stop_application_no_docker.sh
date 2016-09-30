#This scripts kills the application whose PID is stored in the hidden file .saved_pid

kill -9 `cat .saved_pid 2>/dev/null` 2>/dev/null && rm .saved_pid -f
