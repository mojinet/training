<%@ page import="fr.modji.listeDeCourses.bo.Item" %>
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
            <h2>Nouvelle liste</h2>
        </div>
        <div class="main_content">
            <form class="main_content_add" action="#" method="POST">
                <div>
                    <label for="nom_list">Nom de la liste</label>
                    <input type="text" value="${requestScope.listName}" id="nom_list" name="nom_list" required>
                </div>

                <div class="main_content__items">
                    <!-- Java -->
                    <% List<Item> itemList = (List<Item>) request.getAttribute("itemList");
                        if (itemList != null){
                        for (Item item: itemList) { %>
                        <div class="main_content__items__item">
                            <p><%= item.getNom() %></p>
                            <a href=""><img src="<%= request.getContextPath()%>/rss/img/002-eraser.png" alt=""></a>
                        </div>
                    <% }} %>
                    <!-- Java -->
                </div>
                <div>
                    <label for="add_item">Article</label>
                    <input type="text" id="add_item" name="add_item">
                    <button type="submit"><img src="<%= request.getContextPath()%>/rss/img/001-plus.png" alt=""></button>
                </div>
            </form>
        </div>
    </section>
    <footer>
        <a href="<%= request.getContextPath() %>/Accueil"><img src="<%= request.getContextPath()%>/rss/img/004-right-arrow.png" alt=""></a>
    </footer>
</main>
</body>
</html>