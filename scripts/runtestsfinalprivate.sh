#!/bin/bash

let total=0;
let correct=0;

cd src
javac -cp ./lib/po-uilib.jar:. `find hva -name "*.java"`


for x in tests/tests_final_private/*.in; do
  if [ -e ${x%.in}.import ]; then
    java -cp ./lib/po-uilib.jar:. -Dimport=${x%.in}.import -Din=$x -Dout=${x%.in}.outhyp hva.app.App;
  else
    java -cp ./lib/po-uilib.jar:. -Din=$x -Dout=${x%.in}.outhyp hva.app.App;
  fi

  sed -i '' '/^[[:space:]]*$/d' ${x%.in}.outhyp

  diff -w ${x%.in}.out ${x%.in}.outhyp > /dev/null
  res=$? ;
  if [ $res -ne 0 ]; then
    diff -wB ${x%.in}.out ${x%.in}.outhyp > /dev/null
    res=$? ;
  fi
  if [ $res -ne 0 ]; then 
    diff -cwB ${x%.in}.out ${x%.in}.outhyp > ${x%.in}.diff ;
  else
    rm -f ${x%.in}.diff ${x%.in}.outhyp ;
  fi
  if [ -s ${x%.in}.diff ]; then
    echo -n "F"
    failures=$failures"Fail: $x: See file src/${x%.in}.diff\n" ;
   else
    let correct++;
    echo -n "."
  fi
  let total++;
done

rm -f saved*
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
