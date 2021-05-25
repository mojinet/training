<%@ page import="fr.modji.TP5_SuivisDesRepas.bo.Repas" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.modji.TP5_SuivisDesRepas.bo.Aliments" %><%--
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
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body class="container">
    <h1>Visualiser les repas</h1>
    <h2>Historique</h2>
    <div class="historique">
        <table class="table table-primary">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Date</th>
                <th scope="col">Heure</th>
                <th scope="col">Aliments</th>
            </tr>
            </thead>

            <tbody>
            <% for (Repas rp : (ArrayList<Repas>) request.getAttribute("repas")) { %>
            <tr>
                <th scope="row"><%= rp.getId() %></th>
                <td><%= rp.getDate() %></td>
                <td><%= rp.getHeure() %></td>
                <td><% for (Aliments alim : rp.getAliments()) { %>
                    <%= alim.getAliment() + " " %>
                <% } %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <h2>Sous-menu</h2>
    <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ajoutRepas';" class="btn btn-primary" value="ajouter un nouveau repas">
    <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/';" class="btn btn-primary" value="Retour a l'accueil">
</body>
</html>
