package fr.modji.listeDeCourses.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NouveauPanier", value = "/NouveauPanier")
public class NouveauPanier extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // recupere l'id a suprimer si il existe et suprime
        if (request.getParameter("del") != null){
            //debug
            System.out.println("id " + request.getParameter("del") + " va etre supprimé");
        }

        // affiche la page index.jsp
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/jsp/nouveauPanier.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //recupere les input
        String nomListe = request.getParameter("nom");
        String nomArtcile =  request.getParameter("article");
        // debug
        System.out.println("nom de la liste : " + nomListe + " ajoute l'article :" + nomArtcile);

        //redirection sur le get
        doGet(request,response);
    }
}
