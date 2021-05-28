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
            <h1>Bienvenue sur le template Java EE</h1>
            <p>En cours de rédaction</p>
            <h2>Fonctionnalités du template</h2>
            <ul>
                <li>Mise en place du pattern DAO : une classe Exemple à été montrer pour voir le fonctionnement</li>
                <li>Gestion d'un template de page : il n'y as plus qu'a créer le contenu principale sans recopier à chaque fois header/footer</li>
                <li>Gestion des erreur sur le template : les erreurs sont rediriger vers la servletError qui gerent les erreurs et renvois la page correspondante à l'erreur en utilisant le template</li>
            </ul>

            <h2>Comment ça marche ?</h2>
            <h3>Création d'une nouvelle page</h3>
            <ol>
                <li>Créer une servlet qui sera une copie de ServletTemplate</li>
                <li>Modifier le contenu de pageTitle qui sera le titre de la page</li>
                <li>Modifier le contenu de pageLink qui représente le chemin vers votre page</li>
            </ol>

            <h3>Modification du header/footer</h3>
            <p>Allez dans page/include et modifier les fichier suivants pour personnalisé votre template</p>
            <ul>
                <li>meta.html ou sera tout le contenu de vos méta communes à toute les pages</li>
                <li>header.html</li>
                <li>footer.html</li>
            </ul>

            <h3>Gestions des pages d'erreur</h3>
            <p>Dans web.xml est définie les erreur 4XX et 5XX les plus communes mais vous pouvez en ajouter</p>
            <ol>
                <li>Si l'erreur n'est pas reppertorier dans le web.xml l'ajouter</li>
                <li>Créer une page qui doit se nommée [le numéro de l'erreur].jsp dans le dossier jsp/errorPage ( voir l'exemple avec la page 404.jsp)</li>
            </ol>


            <h2>Exemple</h2>
            <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ExempleBdd';" class="btn btn-primary" value="Voir exemple BDD MySQL">
            <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ExempleBdvd';" class="btn btn-primary" value="Voir exemple page erreur">
        </div>

        <%-- FOOTER --%>
        <jsp:include page="/page/include/footer.html"/>

    </main>
</body>
</html>