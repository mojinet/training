package fr.modji.TP5_SuivisDesRepas.servlet;

import fr.modji.TP5_SuivisDesRepas.bll.AlimentsManager;
import fr.modji.TP5_SuivisDesRepas.bll.RepasManager;
import fr.modji.TP5_SuivisDesRepas.bo.Aliments;
import fr.modji.TP5_SuivisDesRepas.bo.Repas;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "ServletAjoutRepas", value = "/ajoutRepas")
public class ServletAjoutRepas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/page/ajoutRepas.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // recupere les entrée fournis par l'utilisateur
        String inputDate = request.getParameter("date");
        String inputTime = request.getParameter("time");
        String[] aliments = request.getParameter("repas").split(",");

        // instancie nos manager
        RepasManager repasManager = new RepasManager();
        AlimentsManager alimentsManager = new AlimentsManager();

        // insert en base de données le repas et recupere son id
        try {
            int idRepas = repasManager.insert(new Repas(inputDate,inputTime));
            alimentsManager.setAliments(idRepas,aliments);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/visualisationRepas");
        rd.forward(request,response);
    }
}
