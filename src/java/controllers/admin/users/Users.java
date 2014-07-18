package controllers.admin.users;

import daos.UserDAO;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

@WebServlet(name = "Users", urlPatterns = {"/admin/users"})
public class Users extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = (EntityManager) request.getAttribute("EntityManager");
        UserDAO userDAO = new UserDAO(em);
        List<User> users = userDAO.getUsers();
        
        request.setAttribute("users", users);
        RequestDispatcher rd = request.getRequestDispatcher("/admin/users/users.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Users Servlet";
    }

}
