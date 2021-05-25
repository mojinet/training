package fr.modji.TP5_SuivisDesRepas.servlet;

import fr.modji.TP5_SuivisDesRepas.bll.AlimentsManager;
import fr.modji.TP5_SuivisDesRepas.bll.RepasManager;
import fr.modji.TP5_SuivisDesRepas.bo.Repas;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletVisualisationRepas", value = "/visualisationRepas")
public class ServletVisualisationRepas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepasManager repasManager = new RepasManager();

        // recupere la liste de tout les repas
        List<Repas> repas = null;
        try {
            repas = repasManager.getRepas();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher rd = null;
        // envois la liste des aliments à la jsp
        request.setAttribute("repas",repas);
        rd = request.getRequestDispatcher("/WEB-INF/page/visualisationRepas.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
