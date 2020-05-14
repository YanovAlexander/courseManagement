#!/bin/bash
# This script we are using to start application in AWS.
export SPRING_PROFILE=aws
export ACCESS_KEY=YOUR_ACCESS_KEY
export ACCESS_SECRET=YOUR_ACCESS_SECRET
export LOG=/tmp/courseManagment.log

nohup java -Dspring.profiles.active=$SPRING_PROFILE -Daws.access.key=$ACCESS_KEY -Daws.access.secret=$ACCESS_SECRET -Dlog.path=$LOG -jar courseManagment-1.0.war &
