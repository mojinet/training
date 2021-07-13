<?php

class Distance{
    private $distance;

    public function __construct($distance) {
        $this->distance = $this->setDistance($distance);
    }

    public function setDistance($distance) { 
        if ($distance > 0){
            $this->distance = $distance;
        }else{
            // leve une exception
            throw new Exception("Une distance ne peut pas etre négative !", 123);
        }
    }
}

$dist1 = new Distance(-2); // leve une erreur fatal 

// utiliser un try...catch au cas ou l'execution peut déclencher une erreur
try{
    $dist2 = new Distance(-2);
}catch(Exception $e){
    echo $e->getMessage();
}finally{
    // sera toujours executer
}