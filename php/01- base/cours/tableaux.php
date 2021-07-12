<?php

// création d'un tableau et ajout d'éléments
$tab[] = 'zero';
$tab[] = 'un';

// création d'un tableau et ajout d'element à des indice définie
$tab2[120] = 'cent vingts';
$tab2[10] = 'diz';
$tab2[-1] = 'moin un';

// création d'un tableau à partir d'une chaine de caracteres
$maChaine = 'truc, bidule, ordure, chose, machin';
$maListe = explode(',', $maChaine);

$maChaine = implode(' mais aussi ', $maListe);

//tableau associatif
$tabAsso = [
    'nom'=>'marley', 
    'prenom' => 'Bob',
    'notes' => [10,20,30]
];



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
    <h1>tableaux</h1>
    <pre>
        <?= print_r($tabAsso); ?>
    </pre>
</body>
</html>