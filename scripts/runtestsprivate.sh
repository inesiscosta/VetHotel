#!/bin/bash

let total=0;
let correct=0;

cd src
javac -cp ./lib/po-uilib.jar:. `find hva -name "*.java"`

for x in tests/tests_private/*.in; do
  if [ -e ${x%.in}.import ]; then
    java -cp ./lib/po-uilib.jar:. -Dimport=${x%.in}.import -Din=$x -Dout=${x%.in}.outhyp hva.app.App;
  else
    java -cp ./lib/po-uilib.jar:. -Din=$x -Dout=${x%.in}.outhyp hva.app.App;
  fi

  diff -cwB ${x%.in}.out ${x%.in}.outhyp > ${x%.in}.diff ;
  if [ -s ${x%.in}.diff ]; then
    echo -n "F"
  failures=$failures"Fail: $x: See file src/${x%.in}.diff\n" ;
  else
    let correct++;
    echo -n "."
    rm -f ${x%.in}.diff ${x%.in}.outhyp ; 
  fi
  let total++;
done

let res=100*$correct/$total
echo ""
echo "Total Tests = " $total
echo "Passed = " $res"%"
printf "$failures"
echo "Done."
find . -name "*.class" -type f -delete
find . -name "*.dat" -type f -delete

if [ $correct -ne $total ]; then
  exit 1
fi
