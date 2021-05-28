<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.modji.listeDeCourses.bo.Liste" %>
<%@ page import="fr.modji.listeDeCourses.bo.Item" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Boostrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <title>Detail panier</title>
</head>
<body>
<main class="l_main">
    <%@include file="/page/include/header.html"%>

    <div class="l_main_content">
        <div class="main_title"><h1>Mon panier : Liste 1</h1></div>
        <div class="main_section">
            <!-- Liste des éléments du panier -->
            <% Liste maListe = (Liste) request.getAttribute("liste"); %>
            <%= maListe.getNom() %>
            <% for (Item item: maListe.getItemList() ) { %>
            <input type="checkbox" name="chk" id="chk">
            <span><%= item.getNom() %></span>
            <% } %>
        </div>
    </div>

    </div>

    <footer class="l_footer">
        <a href="<%= request.getContextPath()%>/Accueil"><img src="<%= request.getContextPath()%>/rss/img/004-next.png" alt=""></a>
        <a href="#"><img src="<%= request.getContextPath()%>/rss/img/005-refresh.png" alt=""></a>
    </footer>
</main>
</body>
</html>