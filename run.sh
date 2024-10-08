#!/bin/bash

cd src

find .  -name "*.class" -type f -delete

javac -cp ./lib/po-uilib.jar:. `find hva -name "*.java"`

java -cp ./lib/po-uilib.jar:. hva.app.App

wait

find .  -name "*.class" -type f -delete
