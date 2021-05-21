<%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 21/05/2021
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>Le gagnant est ... <%= request.getAttribute("gagnant")%></p>
    <a href="<%= request.getContextPath() %>/ServletTentative">Recommencer, j'adore ce jeu !</a>
</body>
</html>
