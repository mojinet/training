<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Suivis des repas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body class="container">
    <h1>Gestion des repas</h1>
    <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ajoutRepas';" class="btn btn-primary" value="ajouter un nouveau repas">
    <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/visualisationRepas';" class="btn btn-primary" value="Visualiser les repas">
</body>
</html>