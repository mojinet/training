package fr.modji.TP6_PreferenceUsageApplication.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletTest", value = "/")
public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // gere le cookie nombre de visite
        int nbVisite = getMyCookie(request,response);

        // Initialise la couleur enreistrer dans la session à black
        HttpSession session = request.getSession();
        if (session.getAttribute("color") == null){
            session.setAttribute("color","black");
        }

        // parametre du contexte d'application
        ServletContext application = this.getServletContext();
        List<String> GColor = new ArrayList<String>();
        GColor.add("black"); GColor.add("green"); GColor.add("red"); GColor.add("blue");
        application.setAttribute("color", GColor);

        // setAttribute
        request.setAttribute("nbVisite",nbVisite+"");
        // forward
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/page/index.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // gere le cookie nombre de visite
        int nbVisite = getMyCookie(request,response);

        // recupere le choix de l'utilisateur
        String color = request.getParameter("color");

        // qu'ont ajoute a la session
        HttpSession session = request.getSession();
        session.setAttribute("color",color);

        // setAttribute
        request.setAttribute("resultat",nbVisite+"");
        // forward
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/page/index.jsp");
        rd.forward(request,response);
    }

    private int getMyCookie(HttpServletRequest request, HttpServletResponse response) {
        int nb = 1;

        // recupere tout les cookies
        Cookie[] cookies = request.getCookies();
        // recherche si le cookie nbVisite exsite deja
        Cookie nbVisite = null;
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("nbVisite")){
                nb = Integer.parseInt(cookie.getValue())+1;
                cookie.setValue(nb+"");
                nbVisite = cookie;
                response.addCookie(nbVisite);
            }
        }

        // créer le cookie si il n'existe pas déja
        if (nbVisite == null){
            nbVisite = new Cookie("nbVisite",1+"");
            nbVisite.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(nbVisite);
        }
        return nb;
    }

}
