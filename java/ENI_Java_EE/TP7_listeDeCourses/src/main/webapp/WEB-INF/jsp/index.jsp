<%@ page import="fr.modji.listeDeCourses.bo.Liste" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css">
    <title>Document</title>
</head>
<body>
<main>
    <header>
        <img src="<%= request.getContextPath()%>/rss/img/shop.png" alt="">
        <h1>Courses</h1>
    </header>
    <section>
        <div class="main_title">
            <h2>Listes prédéfinies</h2>
        </div>
        <div class="main_content">
            <!-- Java -->
            <% List<Liste> listes = (List<Liste>) request.getAttribute("listes");
                for (Liste liste: listes ) { %>

            <div class="list_item">
                <div class="list_item__name"><%= liste.getNom() %></div>
                <div>
                    <a href="<%= request.getContextPath()%>/DetailPanier?id=<%= liste.getId() %>"><img src="<%= request.getContextPath()%>/rss/img/003-shopping-cart.png" alt=""></a>
                    <a href="#"><img src="<%= request.getContextPath()%>/rss/img/002-eraser.png" alt=""></a>
                </div>
            </div>

            <% } %>
            <!-- Java -->
        </div>
    </section>
    <footer>
        <a href="<%= request.getContextPath()%>/NouveauPanier"><img src="<%= request.getContextPath()%>/rss/img/001-plus.png" alt=""></a>
    </footer>
</main>
</body>
</html>