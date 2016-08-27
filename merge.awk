BEGIN{
}
{
    record[$1,$2]=$3;
    if (operateNameArr[$1] == 0)
    {
        operateNameArr[$1] = 1;
    }
}
END{
    for (operateName in operateNameArr)
    {
        printf("%s",operateName);

        for (idx=0; idx < length(jsonTypeArr); idx++)
        {
            jsonType = jsonTypeArr[idx];
            printf("%c%ld",FS,record[operateName,jsonType]);
        }
        printf("\n");
    }
}