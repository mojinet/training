<%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 24/05/2021
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="style/style.css">
</head>
<body class="container">
    <h1 class="display-1" >Ajout repas</h1>

    <form action="<%= request.getContextPath() %>/ajoutRepas" method="post">
        <div class="mb-3">
            <label for="date">Date</label>
            <input type="date" name="date" id="date">
        </div>

        <div class="mb-3">
            <label for="time">Heure</label>
            <input type="time" name="time" id="time">
        </div>

        <div class="mb-3">
            <label for="repas">Repas</label>
            <textarea name="repas" id="repas" cols="30" rows="10"></textarea>
        </div>

        <div class="mb-3">
            <button type="submit" class="btn btn-primary">Valider</button>
            <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/';" class="btn btn-danger" value="Retour">
        </div>

    </form>

</body>
</html>
