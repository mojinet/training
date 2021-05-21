package fr.modji.TP4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletTestShifu", value = "/ServletTestShifu")
public class ServletTestShifu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // on recupere le parametre de l'utilisateur
        String choice = request.getParameter("choice");
        System.out.println("user à choisie " + choice);

        // choix aléatoire
        int rand = (int) (Math.random()*3) +1;
        String computChoice = "";
        switch (rand){
            case 1 -> computChoice = "chi";
            case 2 -> computChoice = "fou";
            default -> computChoice = "mi";
        }
        System.out.println("IA à choisie : " + computChoice);

        // On test qui gagne
        String gagnant = "";
        if (choice.equals("chi") && computChoice.equals("fou")){
            gagnant = "IA";
        }else if (choice.equals("chi") && computChoice.equals("mi")){
            gagnant = "USER";
        }else if (choice.equals("fou") && computChoice.equals("chi")){
            gagnant = "IA";
        }else if (choice.equals("fou") && computChoice.equals("mi")){
            gagnant = "USER";
        }else if (choice.equals("mi") && computChoice.equals("chi")){
            gagnant = "IA";
        }else if (choice.equals("mi") && computChoice.equals("fou")){
            gagnant = "USER";
        }else{
            gagnant = "egalité";
        }

        System.out.println("le gagnant est : " + gagnant);

        // on bind la réponse vant le forward
        request.setAttribute("gagnant", gagnant);

        // redirection forward
        RequestDispatcher rq = null;
        rq = request.getRequestDispatcher("/WEB-INF/resultat.jsp");
        rq.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
