<?php
class Personnage{
    // attribute
    protected $name;
    private $defense;
    private $id;
    private static $nbInstance = 0;

    //constructeur
    public function __construct($name, $defense){
        $this->name = $name;
        $this->defense = $defense;
        static::$nbInstance++; // access à un attribut static avec static::$nomAttribut
        $this->id = static::$nbInstance;
    }

    //tostring
    public function __toString(){ 
        return "Id: " . $this->id . " nom: " . $this->getName() . " force : " . $this->getDefense();
    }

    // method 
    public function getName(){ return $this->name; }
    public function getDefense(){ return $this->defense; }
}

class Guerrier extends Personnage{
    private $puissance;

    public function __construct($name, $defense, $puissance){ 
        parent::__construct($name, $defense); // access au parent avec parent::nomMethode()
        $this->puissance = $puissance;
    }

    public function getPuissance(){ return $this->puissance;}

    public function __toString(){ 
        return parent::__toString() . " puissance: " . $this->getPuissance();
    }
}

$perso = new Personnage("bob",10);
echo $perso . '<br>';
echo $perso->getName(); // affiche "bob"
$perso2 = new Guerrier("henrie",10,10);
echo $perso2 . '<br>';