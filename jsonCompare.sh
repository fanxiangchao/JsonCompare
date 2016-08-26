#!/bin/bash

function jsonCompare()
{
    jsonType=("JsonLib" "FastJson" "Gson" "Jackson")

    logFile="jsoncompare_run_"$1".log"
    logFilterFile="jsoncompare_filter_"$1".log"
    logMergeFile="jsoncompare_merge_"$1".log"
    
    rm -f $logFile
    
    for type in ${jsonType[@]}
    do
        java -cp $CLASSPATH com.json.compare.JsonCompareMain $type $1 10000 >> $logFile 
    done

    sed -n "/$GS/p" $logFile > $logFilterFile
    awk -F$RS -f ../../merge.awk -f ../../comm.awk $logFilterFile >> $logMergeFile
}

GS=$'\035'
RS=$'\036'
echo $PWD
#java -cp .:$PWD/lib/*:$CLASSPATH:$PWD/target/classes  com.json.compare.JsonCompareMain
CLASSPATH=".:$PWD/lib/*:$CLASSPATH"
echo $CLASSPATH
cd target/classes/

for ((i=0;i<10;i++))
do
    jsonCompare "SimpleUser.json"
done

#jsonCompare "SimpleUser.json"
