
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
        mode = tmpNum % 1000;
        if (mode < 10)
        {
            result = ",00"mode""result;
        }
        else if (mode < 100)
        {
            result = ",0"mode""result;
        }
        else
        {
            result = ","mode""result;
        }

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