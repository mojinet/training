package fr.modji.listeDeCourses.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NouveauPanier", value = "/NouveauPanier")
public class NouveauPanier extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // affiche la page index.jsp
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/jsp/nouveauPanier.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}