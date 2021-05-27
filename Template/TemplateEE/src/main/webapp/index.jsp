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

        <div class="l_main_content container">
            <h1>Accueil</h1>
            <p>Bienvenue sur le template javaEE</p>
            <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ExempleBdd';" class="btn btn-primary" value="Voir exemple BDD MySQL">
        </div>

        <%@include file="/page/include/footer.html"%>
    </main>
</body>
</html>