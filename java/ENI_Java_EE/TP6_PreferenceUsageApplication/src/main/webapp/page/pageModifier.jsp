<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 25/05/2021
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LA page</title>
</head>
<body style="background-color: <%= session.getAttribute("color") %>; color:white">
    <h1>LA page</h1>
    <a href="<%= request.getContextPath()%>">retour</a>
</body>
</html>
