package fr.modji.TemplateEE.servlet;

import fr.modji.TemplateEE.bll.ExempleManager;
import fr.modji.TemplateEE.bo.Exemple;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletExemple", value = "/ExempleBdd")
public class ServletExemple extends HttpServlet {

    private ExempleManager exempleManager;

    /**
     * Initialise une instance d'exempleManager qui s'occupe de faire la liaison entre les couches BO <-> DAL
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        exempleManager = new ExempleManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Si l'utilisateur à demander une supression
        if (request.getParameter("del") != null){
            try {
                this.exempleManager.delete(Integer.parseInt(request.getParameter("del")));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        // Recupere la liste de tout les exemples
        List<Exemple> exemples = null;
        try {
            exemples = this.exempleManager.selectAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Envois les instances d'Exemple de la base de données à la jsp
        request.setAttribute("exemples", exemples);

        // bind le titre et le content
        String pageTitle = "Le titre de ma page";
        String pageLink = "/WEB-INF/jsp/exemple.jsp";
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("pageLink",pageLink);

        // forward sur template
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/jsp/template.jsp");
        rd.forward(request,response);
    }

    /**
     * Appeler lors de l'ajout d'un exemple en base de données
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Insere la nouvelle entrée en base de données
        String nom = request.getParameter("nom");               // Recupere l'input "name" du formulaire
        Exemple exemple = new Exemple(nom);                        // Créer une instance d'exemple avec le nom
        try {
            this.exempleManager.insert(exemple);                   // Insert en base de données
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // redirige sur le doGet
        doGet(request,response);
    }
}
