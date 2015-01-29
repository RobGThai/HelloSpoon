#!/bin/bash
echo 'Running Spoon runner'
java -jar spoon-runner-1.1.2-jar-with-dependencies.jar --apk $1 --test-apk $2 --sdk $3

