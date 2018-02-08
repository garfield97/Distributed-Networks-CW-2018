#!/bin/bash

export SECPOLICY="file:./policy"
#java -cp . -Djava.security.policy=$SECPOLICY rmi.RMIServer
export HOSTNAME=$(hostname -I | cut -f1 -d ' ')

echo "talk to $HOSTNAME"

java -cp . -Djava.security.policy=$SECPOLICY -Djava.rmi.server.hostname=$HOSTNAME rmi.RMIServer
