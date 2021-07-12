<?php

// structure conditionnel 
if (true){
    // instruction
}elseif (true){
    // instruction
}else{
    // instruction
}

switch ($var1){
    case 1 : 
        echo 'c\'est 1';
        break;
    case 2:
    case 3 : 
        echo '2 ou 3';
        break;
    default : 
        echo 'pas 1, 2 ou 3';
        break;
}

// Boucles
//while
while(false){

}
// do...while
do{

} while(false);
// for
for($i = 0; $i < 0; $i++){

}
// foreach
foreach ($tableau as $value){
    //instruction sur $value
}
// foreach clé/valeur
foreach ($tableau as $key => $value){
    //instruciton sur $key et $value
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Condition et boucle</h1>
</body>
</html>