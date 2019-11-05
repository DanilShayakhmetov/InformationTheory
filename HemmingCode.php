<?php


/**
 * Created by PhpStorm.
 * User: dev
 * Date: 02.11.19
 * Time: 18:00
 */


function controlBitsINDX()
{
    $ctrlIndxArr = array();
    for ($i = 0; $i <= 128; $i++) {
        array_push($ctrlIndxArr, pow(2, $i));
    }
    return $ctrlIndxArr;
}


function insertEmptyBits($message)
{
    $preparedMSG = str_split($message);
    foreach(controlBitsINDX() as $indx){
    if($indx<count($preparedMSG)&&isset($preparedMSG[$indx])){
        array_splice($preparedMSG,$indx-1,0,0);
    }
    }
    return $preparedMSG;

}


function encodeMessage($message)
{
    $encodedMSG = insertEmptyBits($message);
    foreach (controlBitsINDX() as $indx){
        if(controlBitsCalculation($indx, insertEmptyBits($message))==1){
            $encodedMSG[$indx - 1] = 1;
        }
    }
    $result = implode($encodedMSG);
    return $result;
}


function controlBitsCalculation($indx, $messageBlock)
{
    $checkSum = 0;
    $i = $indx-1;
        while ($i+2 < count($messageBlock)){
            $checkSum += array_sum(array_slice($messageBlock, $i, $indx ));
            $i += 2*($indx + 1);
        }
        return $checkSum % 2;
}


function decodeMessage($message)
{
    $preparedMSG = str_split($message);
    $correctedMSG =extractInformationBits(correctErr(getIncorrectBitINDX($preparedMSG),$preparedMSG));
    return implode($correctedMSG);
}


function getIncorrectBitINDX($messageBlock)
{
    $errINDX = array();
    foreach (controlBitsINDX() as $indx){
        if(controlBitsCalculation($indx,$messageBlock)==1&&$indx<=count($messageBlock)){
            array_push($errINDX,$indx);
        }
    }
    return $errINDX;
}


function extractInformationBits($messageBlock)
{
    $i = 0;
    $result = array();
    foreach ($messageBlock as $index=>$bit){
        if(!in_array($index,controlBitsINDX())){
            array_push($result, $messageBlock[$index]);
        }
    }
    return $result;
}


function correctErr($errINDX, $messageBlock)
{
    if(empty($errINDX)){
        print 'Message is correct';
        return $messageBlock;
    }
    switch ($messageBlock[array_sum($errINDX)]){
        case 0:
            $messageBlock[array_sum($errINDX)] = 1;
            return $messageBlock;
        case 1:
            $messageBlock[array_sum($errINDX)] = 0;
            return $messageBlock;
    }

}










$basicArr = insertEmptyBits('0100010000111101');
//print implode($basicArr);
encodeMessage('0100010000111101');

//getIncorrectBitINDX(encodeMessage('0100010000111101'));
$basicArr = str_split('100110000110001011101');
getIncorrectBitINDX($basicArr);
print implode($basicArr);

print controlBitsCalculation(1,$basicArr);
print controlBitsCalculation(2,$basicArr);

