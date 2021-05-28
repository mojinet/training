package fr.modji.listeDeCourses.servlet;

import fr.modji.listeDeCourses.bo.Item;
import fr.modji.listeDeCourses.bo.Liste;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DetailPanier", value = "/DetailPanier")
public class DetailPanier extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // on créer une liste
        Liste listeCourse = new Liste("ma liste1");
        listeCourse.addItem(new Item("saucisse"));
        listeCourse.addItem(new Item("asperge"));
        listeCourse.addItem(new Item("cafe"));
        listeCourse.addItem(new Item("biberon"));

        //on envois a la jsp
        request.setAttribute("liste", listeCourse);

        // affiche la page index.jsp
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/jsp/detailPanier.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
