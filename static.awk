BEGIN{
}
{
    operateCount[$1] = operateCount[$1] + 1;
    staticArr[$1,jsonTypeArr[0],operateCount[$1]] = $2;
    staticArr[$1,jsonTypeArr[1],operateCount[$1]] = $3;
    staticArr[$1,jsonTypeArr[2],operateCount[$1]] = $4;
    staticArr[$1,jsonTypeArr[3],operateCount[$1]] = $5;
    totalArr[$1,jsonTypeArr[0]] += $2;
    totalArr[$1,jsonTypeArr[1]] += $3;
    totalArr[$1,jsonTypeArr[2]] += $4;
    totalArr[$1,jsonTypeArr[3]] += $5;
    
}
END{
    for (operateName in operateCount)
    {
        title = operateName","jsonName","loopNum"*"runNum;
        size = length(title);
        printf("%*s%*s%*s\n",(70 - size)/2,"",size,title,(70 - size)/2,"");
        printf("%s\n","--------------------------------------------------------------------------------");
        printf("%-10s%15s%15s%15s%15s\n","No.",jsonTypeArr[0],jsonTypeArr[1],jsonTypeArr[2],jsonTypeArr[3]);
        printf("%s\n","--------------------------------------------------------------------------------");
        for (count = 1; count <= operateCount[operateName]; count++)
        {
            printf("%-10s%15s%15s%15s%15s\n",count,staticArr[operateName,jsonTypeArr[0],count],staticArr[operateName,jsonTypeArr[1],count],staticArr[operateName,jsonTypeArr[2],count],staticArr[operateName,jsonTypeArr[3],count]);
        }

        printf("%s\n","--------------------------------------------------------------------------------")
        printf("%-10s%15s%15s%15s%15s\n","Avg",totalArr[operateName,jsonTypeArr[0]]/operateCount[operateName],totalArr[operateName,jsonTypeArr[1]]/operateCount[operateName],totalArr[operateName,jsonTypeArr[2]]/operateCount[operateName],totalArr[operateName,jsonTypeArr[3]]/operateCount[operateName]);
        printf("%s\n","--------------------------------------------------------------------------------")
        printf("\n\n")
    }
}