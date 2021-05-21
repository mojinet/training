<%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 21/05/2021
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Declaration -->
<%!
    private int nombre;
    private int getNombre(){return this.nombre;}
    private void setNombre(int nb){this.nombre = nb;}
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!-- Instruction java -->
<% this.setNombre(50);%>
<!-- Affichage d'une variable -->
<span>Le nombre est : <%= getNombre() %></span>

</body>
</html>
