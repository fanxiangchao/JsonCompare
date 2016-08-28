#!/bin/bash

function jsonCompare()
{
    jsonType=("JsonLib" "FastJson" "Gson" "Jackson")

    logFile="jsoncompare_run_"$1".log"
    logFilterFile="jsoncompare_filter_"$1".log"
    logMergeFile=$LOG_MERGE_FILE_PREFIX$1".log"
    
    rm -f $logFile
    
    for type in ${jsonType[@]}
    do
        echo $type" "$1
        java -cp $CLASSPATH com.json.compare.JsonCompareMain $type $1 $2 >> $logFile 
    done

    sed -n "/$GS/p" $logFile | awk -F$GS '{print $2;}' > $logFilterFile
    awk -F$RS -f ../../merge.awk -f ../../comm.awk $logFilterFile >> $logMergeFile
}

function runJsonCompare()
{
    mergeFile=$LOG_MERGE_FILE_PREFIX$1".log"

    rm -f $mergeFile
    
    for ((i=0;i<$3;i++))
    do
        echo "NO."$i" jsonCompare "$1" "$2
        jsonCompare $1 $2
    done
    awk -F$RS -v jsonName="SimpleUser.json" -v runNum=$runNum -v loopNum=$loopNum -f ../../static.awk -f ../../comm.awk $mergeFile >> $LOG_STATIC_FILE_LOG
}

GS=$'\035'
RS=$'\036'
LOG_MERGE_FILE_PREFIX="jsoncompare_merge_"
LOG_STATIC_FILE_LOG="jsoncompare_static.log"
echo $PWD
#java -cp .:$PWD/lib/*:$CLASSPATH:$PWD/target/classes  com.json.compare.JsonCompareMain
CLASSPATH=".:$PWD/lib/*:$CLASSPATH"
echo $CLASSPATH
cd target/classes/

#mergeFile=$LOG_MERGE_FILE_PREFIX"SimpleUser.json"".log"
#staticFile=$LOG_STATIC_FILE_PREFIX"SimpleUser.json"".log"
runNum=100000
loopNum=10

#rm -f $mergeFile

jsonNameArr=("SimpleUser.json" "GroupUserBean.json")

if [ -f $LOG_STATIC_FILE_LOG ]
then
    echo "clean "$LOG_STATIC_FILE_LOG
    rm -f $LOG_STATIC_FILE_LOG
fi

for jsonFile in ${jsonNameArr[@]}
do
    echo "compare "$jsonFile
    runJsonCompare $jsonFile $runNum $loopNum
done

#for ((i=0;i<$loopNum;i++))
#do
#    echo "NO."$i" jsonCompare ""SimpleUser.json "$runNum
#    jsonCompare "SimpleUser.json" $runNum
#done

#awk -F$RS -v jsonName="SimpleUser.json" -v runNum=$runNum -v loopNum=$loopNum -f ../../static.awk -f ../../comm.awk $mergeFile >> $staticFile

#jsonCompare "SimpleUser.json"

