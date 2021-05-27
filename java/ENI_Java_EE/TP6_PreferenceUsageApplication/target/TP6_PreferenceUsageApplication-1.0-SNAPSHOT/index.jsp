<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% List<String> test = (ArrayList<String>) application.getAttribute("color"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body class="container">
    <h1>Accueil</h1>
    <form action="<%= request.getContextPath() %>/pageModifier" method="post">
        <label for="color">Couleur</label>
        <select name="color" id="color">
            <option value="<%= test.get(0) %>"><%= test.get(0) %></option>
            <option value="<%= test.get(1) %>"><%= test.get(1) %></option>
            <option value="<%= test.get(2) %>"><%= test.get(2) %></option>
            <option value="<%= test.get(3) %>"><%= test.get(3) %></option>
        </select>
        <input type="submit" value="valider">
    </form>
    <p>Tu es venu <%= request.getAttribute("nbVisite")%> fois !</p>
    <input type="button" onclick="window.location.href = 'page/pageModifier.jsp';" class="btn btn-primary" value="Voir la page">
</body>
</html>