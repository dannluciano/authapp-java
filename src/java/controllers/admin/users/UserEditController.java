
package controllers.admin.users;

import daos.UserDAO;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.UserModel;

@WebServlet(name = "UserController", urlPatterns = {"/admin/users/edit"})
public class UserEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = (EntityManager) request.getAttribute("EntityManager");
        UserDAO userModelDAO = new UserDAO(em);
        
        if (request.getParameter("id") != null) {
            Long id = Long.valueOf(request.getParameter("id"));
            UserModel user = userModelDAO.getUser(id);
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("/admin/users/edit.jsp");
            rd.include(request, response);
        } else {
            String path = request.getServletContext().getContextPath();
            path += "/admin/users";
            response.sendRedirect(path);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Long id = Long.valueOf(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        EntityManager em = (EntityManager) request.getAttribute("EntityManager");
        UserDAO userDAO = new UserDAO(em);
        userDAO.updateUserModel(id, username, password);
        
        String path = request.getServletContext().getContextPath();
        path += "/admin/users";
        response.sendRedirect(path);
    }
    @Override
    public String getServletInfo() {
        return "User Edit Servlet";
    }

}
