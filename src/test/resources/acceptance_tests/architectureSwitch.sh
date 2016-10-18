#!/bin/bash

#This script takes 3 scripts (A, B, C)
#If this script is ran in a 32Bit OS it will run C
#If this script is ran in a 64Bit-Darwin OS it will run B
#If this script is ran in a 64Bit-Linux OS it will run 

#$1 The A script
#$2 The B script
#$3 The C script

#Example : ./architectureSwitch.sh "echo Linux64" "echo Darwin64" "echo 32BitOS"

DEBUG=0

if [ "$DEBUG" == "1" ]; then echo "$1"; fi
if [ "$DEBUG" == "1" ]; then echo "$2"; fi
if [ "$DEBUG" == "1" ]; then echo "$3"; fi

if [ `getconf LONG_BIT` = "64" ]
then
    if [ "$DEBUG" == "1" ]; then echo "I'm 64-bit"; fi
    if [[ "$OSTYPE" == "linux-gnu" ]]; then
        if [ "$DEBUG" == "1" ]; then echo "I'm 64-bit Linux"; fi
	set -f	
	$1
	set +f
    elif [[ "$OSTYPE" == "darwin"* ]]; then
        if [ "$DEBUG" == "1" ]; then echo "I'm 64-bit Darwin"; fi
	set -f    	
	$2
	set +f
    else
        echo "Unknown OS : $OSTYPE."
        exit 1
    fi
else
    if [ "$DEBUG" == "1" ]; then echo "I'm 32-bit"; fi
    set -f
    $3
    set +f
fi
