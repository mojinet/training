package fr.modji.TP5_SuivisDesRepas;

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

@WebServlet(name = "ServletVisualisationRepas", value = "/visualisationRepas")
public class ServletVisualisationRepas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
            Connection cnx = ds.getConnection();
            System.out.println(cnx.isClosed()?"closed !" : "open :D");

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/WEB-INF/page/visualisationRepas.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
