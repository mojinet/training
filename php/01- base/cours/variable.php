<?php
/************************/
/* Variable & constante */
/************************/

// Déclare une variable : pas besoin d'initialisé
$var1 =  '52';
$var2 = 'var1';
// Declare une constante pas de $
const UNE_CONSTANTE = 10;

// Copie une variable par référence avec le &
$var3 = &$var1;
// Variable avec nom dynamique avec le $
echo $$var2; // affichera 52

//fonctions utile
var_dump($var1);            // renvois le type
isset($var1);               // test si la variable est définie
empty($var1);               // test si la variable est vide
unset($var1);               // suprimme une variable
settype($var1, 'integer');  // convertion explicite en integer

?>