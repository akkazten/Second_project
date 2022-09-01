package kz.javaee;

import DB.DbManager;
import DB.Items;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String name = request.getParameter("user_name");
        String surname = request.getParameter("user_surname");
        int age = Integer.parseInt(request.getParameter("user_age"));
        System.out.println("You entered " + name + surname+ age+ " years old");
   */
        ArrayList<Items> items = DbManager.getItems();
        request.setAttribute("tovary",items);
        request.getRequestDispatcher("/home.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
