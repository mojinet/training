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
            <h2>Votre panier</h2>
            <h3>Liste vegan</h3>
        </div>
        <div class="main_content_t">
            <!-- Java -->
            <div class="main_content__items__item_t">
                <input type="checkbox" name="chk_article" id="chk_article1">
                <label for="chk_article1">Article A</label>
            </div>

            <div class="main_content__items__item_t">
                <input type="checkbox" name="chk_article" id="chk_article2">
                <label for="chk_article2">Article B</label>
            </div>

            <div class="main_content__items__item_t">
                <input type="checkbox" name="chk_article" id="chk_article3">
                <label for="chk_article3">Article C</label>
            </div>
            <!-- Java -->
        </div>
    </section>
    <footer>
        <a href="<%= request.getContextPath() %>/Accueil"><img class="arrow_reverse" src="<%= request.getContextPath()%>/rss/img/004-right-arrow.png" alt=""></a>
        <a href=""><img src="<%= request.getContextPath()%>/rss/img/refresh.png" alt=""></a>
    </footer>
</main>
</body>
</html>