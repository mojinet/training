<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%-- META --%>
    <jsp:include page="/page/include/meta.html"/>
    <title>Accueil</title>
</head>
<body>
    <main class="l_main">
        <%-- HEADER --%>
        <jsp:include page="/page/include/header.html"/>

        <%-- MAIN CONTENT --%>
        <div class="l_main_content container">
            <h1>Accueil</h1>
            <p>Bienvenue sur le template javaEE</p>
            <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ExempleBdd';" class="btn btn-primary" value="Voir exemple BDD MySQL">
        </div>

        <%-- FOOTER --%>
        <jsp:include page="/page/include/footer.html"/>

    </main>
</body>
</html>