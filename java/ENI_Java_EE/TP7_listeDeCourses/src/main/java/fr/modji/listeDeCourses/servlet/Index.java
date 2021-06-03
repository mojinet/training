package fr.modji.listeDeCourses.servlet;

import fr.modji.listeDeCourses.bo.Liste;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Index", value = "/Accueil")
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // on créer les listes puis les ajoute dans une List
        Liste listeCourse = new Liste("ma liste 1");
        Liste listeCourse2 = new Liste("ma liste 2");
        List<Liste> listes = new ArrayList<>();
        listes.add(listeCourse);
        listes.add(listeCourse2);

        // envois a la jsp
        request.setAttribute("listes", listes);

        // affiche la page index.jsp
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
