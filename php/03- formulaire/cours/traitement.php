<?php
// si le formulaire à envoyer un champs en GET et qu'il n'est pas vide
if (isset($_GET['name']) && (!empty($_GET['name']))){
    // suprime les caracteres speciaux
    $nom = htmlspecialchars($_GET['name']);
}
// request recupere GET ou POST
if (isset($_REQUEST['password']) && (!empty($_REQUEST['password']))){
    $password = htmlspecialchars($_REQUEST['password']);
}
if (isset($_GET['checkPassword'])){
    $check = htmlspecialchars($_GET['password'] === $_GET['checkPassword']);
}
?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Traitement</h1>
    <h2>Bonjour <?= $nom ?> !</h2>
    <span>Votre mot de passe est : <?= $password ?></span><br>
    <span>La méthode de vérification renvois : <?= $check ?></span>
</body>
</html>