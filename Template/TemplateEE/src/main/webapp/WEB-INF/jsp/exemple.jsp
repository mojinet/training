<%@ page import="fr.modji.TemplateEE.bo.Exemple" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <h1>Gestion d'une table</h1>

    <h2>Notice</h2>
    <p class="alert alert-warning" role="alert" >Ceci est un template pour MySQL, le login par défaut est root et le mot de passe vide, pour le modifier allez dans context.xml</p>
    <p class="alert alert-warning" role="alert" >Pour que le test fonctionne il faut créer une base de données bdd_test et une table exemple, script disponible
        <a href="<%= request.getContextPath() %>/rss/bdd_test.sql" target="_blank">ICI</a></p>

    <!-- Table -->
    <h2>Mes articles</h2>
    <table class="table table-primary">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Nom</th>
            <th> Actions </th>
        </tr>
        </thead>

        <tbody>
        <% for (Exemple exemple : (ArrayList<Exemple>) request.getAttribute("exemples")) { %>
        <tr>
            <th scope="row"><%= exemple.getId() %></th>
            <td><%= exemple.getName() %></td>
            <td>
                <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ExempleBdd?del=<%= exemple.getId() %>';" class="btn btn-danger" value="Suprimer">
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <!-- Ajouter une entrée-->
    <h2>Ajout d'une entrée en base de données</h2>
    <form action="<%= request.getContextPath() %>/ExempleBdd" method="post">
        <label for="nom">Nom</label>
        <input type="text" name="nom" id="nom">
        <input type="submit" class="btn btn-warning" value="Ajouter">
    </form>
