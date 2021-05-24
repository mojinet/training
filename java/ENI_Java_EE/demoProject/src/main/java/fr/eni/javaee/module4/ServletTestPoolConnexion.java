package fr.eni.javaee.module4;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "ServletTestPoolConnexion", value = "/ServletTestPoolConnexion")
public class ServletTestPoolConnexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            Context context = new InitialContext();
            //recherche de la data source
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
            // demande une connexion : la méthode getConnection met la demande en attente tant qu'il n'y as pas de connexion disponible dans le pool (de ticket)
            Connection cnx = dataSource.getConnection();
            out.print("la connexion est " + (cnx.isClosed() ? "fermé" : "ouverte"));
            // Libere la connexion quand on n'en as plus besoin
            cnx.close(); // la connexion n'est pas fermé mais remise dans la pool


        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("Une erreur est survenu lors de l'utilisation de la base de données " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
