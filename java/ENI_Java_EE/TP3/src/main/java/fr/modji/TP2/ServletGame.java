package fr.modji.TP2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "ServletGame",
        urlPatterns = "/ServletGame",
        initParams = {
                @WebInitParam(description = "Le parametre du nombre MAX", name = "MAX_VALUE", value = "10")
        }
)
public class ServletGame extends HttpServlet {

    private int MAX_VALUE;

    @Override
    public void init() throws ServletException{
        this.MAX_VALUE = Integer.parseInt(this.getInitParameter("MAX_VALUE"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int randNumber = (int) (Math.random() * this.MAX_VALUE);
        System.out.println(randNumber);
        String userEntry = request.getParameter("dataEntry");
        System.out.println(userEntry);
        if (randNumber == Integer.parseInt(userEntry)){
            response.sendRedirect("/TP2_war_exploded/victoire.html");
        }else{
            response.sendRedirect("/TP2_war_exploded/defaite.html");
        }
        out.close();
    }
}
