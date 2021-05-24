package fr.modji.TP5_SuivisDesRepas;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String[] aliment = request.getParameter("repas").split(",");

        System.out.println(date + " " + time + " " + aliment[1]);

        RequestDispatcher rd = null;
        // todo vers la servlet et non directement sur la jsp
        rd = request.getRequestDispatcher("/WEB-INF/page/visualisationRepas.jsp");
        rd.forward(request,response);
    }
}
