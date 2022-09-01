package kz.javaee;
import DB.DbManager;
import DB.Items;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Items item = DbManager.getItem(id);

            if (item != null) {
                if (DbManager.deleteItem(item)) {
                    response.sendRedirect("/");
                } else {
                    response.sendRedirect("/edit?id=" + id + "&error");
                }

            } else {
                response.sendRedirect("/");
            }
        }
    }

