<?php
class Test{

    private $att1;
    private $att2;
    private $att3;

    public function __construct($par1,$par2,$par3) {$this->att1 = $par1;$this->att2 = $par2;$this->att3 = $par3;}

    // appeler lors de la serialization : ici on ne serialize que $att1 et $att2
    public function __sleep() {
        return [$this->att1, $this->att2];
    }

    // appeler lors de la deserialisation
    public function __wakeup(){ 

    }
}
//creation de l'instance
$class = new Test(2,5,7);
// serialise
$serialInt = serialize($class);
// deserialise
$unserialize = unserialize($serialInt);