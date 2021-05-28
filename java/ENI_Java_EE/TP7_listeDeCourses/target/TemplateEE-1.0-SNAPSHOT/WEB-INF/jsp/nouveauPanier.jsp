<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Boostrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <title>Nouvelle liste</title>
</head>
<body>
<main class="l_main">
    <%@include file="/page/include/header.html"%>

    <div class="l_main_content">
        <div class="main_title"><h1>Nouvelle liste</h1></div>
        <div class="main_section">

            <!-- Input nom de la liste -->
            <form action="NouveauPanier" method="post">
                <label for="nom">Nom</label>
                <input type="text" name="nom" id="nom" required>


            <!-- Affiche les item de la liste-->
                <div class="list_newPanier_items" style="border: black 1px solid; height: 300px; margin: 100px 0;">
                    <div class="list_item">
                        <p>Article 1</p>
                        <a href="<%= request.getContextPath()%>/NouveauPanier?del=1">
                            <img src="<%=request.getContextPath()%>/rss/img/002-cancel.png" alt="">
                        </a>
                    </div>
                    <div class="list_item">
                        <p>Article 2</p>
                        <a href="<%= request.getContextPath()%>/NouveauPanier?del=2">
                            <img src="<%=request.getContextPath()%>/rss/img/002-cancel.png" alt="">
                        </a>
                    </div>
                </div>
            <!-- Ajout d'un item -->
                <label for="article">Article</label>
                <input type="text" name="article" id="article" required>
                <button type="submit">
                    <img src="<%=request.getContextPath() %>/rss/img/003-add.png" alt="">
                </button>
            </form>
        </div>
    </div>

    </div>

    <footer class="l_footer">
        <a href="<%= request.getContextPath()%>/Accueil"><img src="<%= request.getContextPath()%>/rss/img/004-next.png" alt=""></a>
    </footer>
</main>
</body>
</html>