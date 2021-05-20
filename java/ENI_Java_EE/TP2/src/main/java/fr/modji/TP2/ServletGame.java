package fr.modji.TP2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGame", value = "/ServletGame")
public class ServletGame extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int randNumber = (int) (Math.random() * 10);
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
