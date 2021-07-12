<?php

// fonction par copie de valeur
function multiplie($valeur){
    return $valeur *= 12;
}

$v1 = 5;
echo multiplie($v1, $v2);
echo $v1; // 5

// fonction par reference
function multiplie2(&$valeur){
    return $valeur *= 12;
}

$v1 = 5;
echo multiplie2($v1, $v2);
echo $v1; // 60

// fonction avec variable statique
function testStat(){
    static $varStat = 1;
    return $varStat++;
}

echo testStat(); // 1
echo testStat(); // 2
echo testStat(); // 3

// fonction avec nom variable
$multiplication = 'multiplie';
echo $multiplication(10); // 120

//fonction anonyme stocké dans une variable
$soustraction = function($v1, $v2){ 
    return $v1 - $v2;
};

//fonction typé
function exemple(int $v1, int $v2):int{
    return $v1 + $v2;
}

echo exemple(1.5, 5,5); // 6 car conversion en int 