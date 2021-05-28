<%@ page import="fr.modji.listeDeCourses.bo.Liste" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Boostrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <title>Accueil</title>
</head>
<body>
    <main class="l_main">
        <%@include file="/page/include/header.html"%>

        <div class="l_main_content">
            <div class="main_title"><h1>Mes liste de courses</h1></div>
            <div class="main_section">
                <!-- Liste des courses -->
                <%
                    List<Liste> listes = (List<Liste>) request.getAttribute("listes");
                    for (Liste liste: listes ) { %>
                <div class="list_item">
                    <h2> <%= liste.getNom() %> </h2>
                    <div class="list_item_logos">
                        <a href="<%= request.getContextPath()%>/DetailPanier?id=<%= liste.getId() %>"><img src="rss/img/001-groceries.png" alt=""></a>
                        <a href="<%= request.getContextPath()%>/Accueil?del=<%= liste.getId() %>"><img src="rss/img/002-cancel.png" alt=""></a>
                    </div>
                </div>
                <% } %>
            </div>
        </div>

        <footer class="l_footer">
            <a href="<%= request.getContextPath()%>/NouveauPanier"><img src="rss/img/003-add.png" alt=""></a>
        </footer>
    </main>
</body>
</html>