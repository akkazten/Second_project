package kz.javaee;

import DB.DbManager;
import DB.Items;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = 0L;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (Exception e) {

        }

        Items item = DbManager.getItem(id);
        if (item != null) {
            request.setAttribute("item", item);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int price = Integer.parseInt(request.getParameter("price"));
        Items item = DbManager.getItem(id);
        if (item != null) {

            item.setName(name);
            item.setAmount(amount);
            item.setPrice(price);

            if (DbManager.saveItem(item)) {
                response.sendRedirect("/edit?id=" + id + "&success");
            } else {
                response.sendRedirect("/edit?id=" + id + "&error");
            }

        } else {
            response.sendRedirect("/");
        }

    }
}
