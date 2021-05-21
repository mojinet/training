package fr.eni.javaee.module3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletJSP", value = "/module3/ServletJSP")
public class ServletJSP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // on initialise notre RequestDispatcher
        RequestDispatcher rd = null;
        // on lie notre jsp à notre servlet
        rd = request.getRequestDispatcher("/WEB-INF/modules/module3/demonstration/majspdansservlet.jsp");
        // on fournis le request et response
        rd.forward(request,response);
        // Ne rien écrire derriere le forrward
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
