package fr.modji.TemplateEE.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Gere les erreur 4XX et 5XX définie dans web.xml
 */
@WebServlet(name = "ServletError", value = "/ServletError")
public class ServletError extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // bind le titre et le content
        String pageTitle = "Erreur : " + response.getStatus();                               // le titre de la page
        String pageLink = "/WEB-INF/jsp/errorPage/" + response.getStatus() + ".jsp";         // le lien vers le fichier à inclure
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("pageLink",pageLink);

        // forward sur template
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/jsp/template.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
