<?php
//declaration d'un espace de nom
//namespace monProjet;

// on créer nos class (ou required/include)
require_once 'class.php';

// et quand on veut utiliser une de nos classe on spécifie le namespace
$uneInstance = new mesPersonnages\Personnage("bob",2);