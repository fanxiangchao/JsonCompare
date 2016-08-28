
function formatNum(num)
{
    if (num < 1000)
    {
        return num;
    }

    tmpNum = int(num);
    result = "";
    
    while (tmpNum > 1000)
    {
        result = ","(tmpNum % 1000)""result;
        tmpNum = int(tmpNum / 1000);
    }

    result = (tmpNum != 0 ? tmpNum""result : result);
    return result;
}

BEGIN{
    jsonTypeArr[0] = "JSONLIB";
    jsonTypeArr[1] = "FASTJSON";
    jsonTypeArr[2] = "GSON";
    jsonTypeArr[3] = "JACKSON";
}
{
}
END{
}