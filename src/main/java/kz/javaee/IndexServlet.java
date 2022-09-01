package kz.javaee;

import DB.DbManager;
import DB.Items;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Items> items = DbManager.getItems();
        PrintWriter out = response.getWriter();

        out.println("<h1> Items list </h1>");
        for (Items it : items){
            out.println("<h3> "+ it.getId()+it.getName()+ it.getAmount()+ it.getPrice()+ "</h3>");

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
