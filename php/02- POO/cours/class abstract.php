<?php

abstract class Vehicule{
    protected $attribute;

    public function __construct($attribute){
        $this->attribute = $attribute;
    }

    public function getAttribute(){ return $this->attribute; }

    // méthode abstaite : ne declarer que l'en tete
    public abstract function setAttribute($attribute);
}

class Voiture extends Vehicule{ 

    public function setAttribute($attribute){
        $this->attribute = $attribute;
    }
}

$vroum = new Voiture("Mon attribut");
echo $vroum->getAttribute();