package fr.modji.listeDeCourses.servlet;

import fr.modji.listeDeCourses.bo.Item;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        //Recupere le nom de la liste
        String listName = request.getParameter("nom_list");
        System.out.println(listName);
        String articleName = request.getParameter("add_item");
        System.out.println(articleName);

        // enregistre en base de données : insert into...
        // recupere l'id de la liste

        // Recupere les items deja presente en bdd : selectAll(id)
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("pomme"));
        itemList.add(new Item("poire"));
        itemList.add(new Item("mûre"));
        itemList.add(new Item("banane"));

        // envois la réponse
        request.setAttribute("listName", listName);
        request.setAttribute("itemList", itemList);

        //redirection sur le get
        doGet(request,response);
    }
}
