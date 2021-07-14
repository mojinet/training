<?php

class UneClasse{

    // appeler lors du clone de l'instance
    public function __clone(){ 
        // on peut par exemple incrémenter un attribut ::static
    }
}

$a = new UneClasse();
// on clone notre instance de UneClasse
$b = clone $a;

