<?php
// redirection vers une autre page : doit etre la premiere instruction
//header('Location: redirection.php');

// demaré une session : le temps de la navigation à placer en haut
session_start(); // generera un identifiant dans un cookie
// stocké une valeur
$_SESSION['nom'] = 'bob';
// acceder à la valeur
echo $_SESSION['nom'];

// création d'un cookie
setcookie('unCookie', 'une valeur', time() + (30*24*60*60)); // restera ici 30jours
// supression d'un cookie
setcookie('unAutreCookie'); // suprimera le cookie avec le nom unCookie
// consulter un cookie
echo $_COOKIE['unCookie'];

?>

<!doctype html>
<html lang=fr>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<a href=""></a>

</body>
</html>
