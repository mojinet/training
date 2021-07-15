<?php

try{
    $urlDB = "mysql:host=localhost;dbname=papeterie";               // chaine de connexion
    $options = [PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"];  // définir l'encodage sur utf8
    // création d'une instance de connexion
    $pdo = new PDO($urlDB, 'root','',$options);
    // choix méthode d'information en cas d'erreur
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}catch(PDOException $e){
    $msg = 'ERREUR PDO dans ' . $e->getFile() . ' : ' . $e->getLine() . ' : ' . $e->getMessage();
    die($msg);
}

// requete non préparées avec SELECT
$stmt = "SELECT * FROM `dbo.articles;`";
$response = $pdo->query($stmt)->fetchAll(); // recupere toute les entrée de la BDD

// requete non préparées avec INSERT
$stmt = "UPDATE `dbo.articles` SET marque = 'bidule' WHERE idArticle = 44;";
$count = $pdo->exec($stmt); // renvois le nombre de ligne affecté

// requete préparé avec parametre non nommé
$id = 45;
$stmt = 'SELECT * FROM `dbo.articles` WHERE idArticle = ?;';
$prep = $pdo->prepare($stmt);       // prépare la requete
$prep->bindValue(1, $id);     // bind les valeurs qui ne seront plus interprété
$prep->execute();                   // execute
$response = $prep->fetch();         // recupere les resultats

// pour parametre nommé
$id = 45;
$stmt = 'SELECT * FROM `dbo.articles` WHERE idArticle = :id;';
$prep = $pdo->prepare($stmt);       // prépare la requete
$prep->bindValue(':id', $id); // bind les valeurs qui ne seront plus interprété
$prep->execute();                   // execute
$response = $prep->fetch();         // recupere les resultats

?>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>Connexion BDD</h1>

</body>
</html>
