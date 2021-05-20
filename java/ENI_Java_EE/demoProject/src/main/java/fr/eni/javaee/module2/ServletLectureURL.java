package fr.eni.javaee.module2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletLectureURL", value = "/ServletLectureURL")
public class ServletLectureURL extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String protocole = request.getScheme();
        String nomServeur = request.getServerName();
        int port = port = request.getServerPort();
        String nomApp = request.getContextPath();
        String cheminRSS = request.getServletPath();

        StringBuilder str = new StringBuilder();
        str.append(protocole).append(System.lineSeparator());
        str.append(nomServeur).append(System.lineSeparator());
        str.append(port).append(System.lineSeparator());
        str.append(nomApp).append(System.lineSeparator());
        str.append(cheminRSS).append(System.lineSeparator());

        response.getWriter().append(str.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
