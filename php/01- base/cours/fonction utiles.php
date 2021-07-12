<?php

# upper & lower case
echo strtolower("BONJOUR") . '<br>'; // bonjour
echo strtoupper("bonjour") . '<br>'; // BONJOUR
echo ucwords("bonjour") . '<br>';    // Bonjour

# formatage
echo sprintf('%02d/%02d/%04d', 7, 2, 1988) . '<br>'; // 07/02/1988
echo number_format(1234.567, 1, ',', ' ') . '<br>'; // 1 234.6 

# recherche dans chaine de caracteres
echo strpos("couc@ou@",'@') . '<br>'; // 4 premiere occurence
echo strrpos("cou@cou@",'@') . '<br>'; // 7 derniere occurence
echo stripos("couAcouA",'a') . '<br>'; // 3 premiere sans tenir compte casse
echo strripos("cou@cou@",'@') . '<br>'; // 7 derniere sans tenir compte casse

# timestamps UNIX
echo mktime(11,45,30,4,12,2017) . '<br>'; // time stamps depuis le 10 avril 2017 à 11h45 et 30s
echo mktime(0,0,0,1,1,1970) . '<br>'; // timestamps depuis la création (1er janvier 1970);

# date
echo date("d/m/Y H:i:s", mktime(11,45,30,4,10,2014)) . '<br>'; // formate une date depuis un timestamps
echo date("d/m/Y H:i:s", 100000) . '<br>'; // retourne la date apres 100000s apres la création du timestamps