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
                <!-- Item 1 -->
                <div class="list_item">
                    <h2>Liste 1</h2>
                    <div class="list_item_logos">
                        <a href="<%= request.getContextPath()%>/DetailPanier"><img src="rss/img/001-groceries.png" alt=""></a>
                        <a href="<%= request.getContextPath()%>/Accueil"><img src="rss/img/002-cancel.png" alt=""></a>

                    </div>
                </div>
                <!-- Item 2 -->
                <div class="list_item">
                    <h2>Liste 2</h2>
                    <div class="list_item_logos">
                        <img src="rss/img/001-groceries.png" alt="">
                        <img src="rss/img/002-cancel.png" alt="">
                    </div>
                </div>
            </div>
            </div>
            
        </div>

        <footer class="l_footer">
            <a href="<%= request.getContextPath()%>/NouveauPanier"><img src="rss/img/003-add.png" alt=""></a>
        </footer>
    </main>
</body>
</html>