<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Formulaire</h1>
    <form action="traitement.php" action="GET">
        <label for="name">Nom</label>
        <input type="text" name="name">
        <label for="password">Mot de passe</label>
        <input type="password" name="password">
        <label for="checkPassword">Retapper le mot de passe</label>
        <input type="password" name="checkPassword">
        <input type="submit" value="Envoyer">
    </form>
</body>
</html>