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
import models.UserModel;

@WebServlet(name = "Users", urlPatterns = {"/admin/users"})
public class UsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = (EntityManager) request.getAttribute("EntityManager");
        UserDAO userDAO = new UserDAO(em);
        
        if (request.getParameter("id") != null) {
            Long id = Long.valueOf(request.getParameter("id"));
            UserModel user = userDAO.getUser(id);
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("/admin/users/user.jsp");
            rd.include(request, response);
        } else {
            List<UserModel> users = userDAO.getUsers();
            request.setAttribute("users", users);
            
            Long numberOfUsers = userDAO.getNumbersOfUsers();
            request.setAttribute("number_of_users", numberOfUsers);
            
            RequestDispatcher rd = request.getRequestDispatcher("/admin/users/users.jsp");
            rd.include(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        EntityManager em = (EntityManager) request.getAttribute("EntityManager");
        UserDAO userDAO = new UserDAO(em);
        userDAO.createUser(username, password);

        String path = request.getContextPath();
        path += "/admin/users";
        response.sendRedirect(path);
    }

    @Override
    public String getServletInfo() {
        return "Users Servlet";
    }
}
