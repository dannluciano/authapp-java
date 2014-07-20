package controllers.admin.users;

import daos.UserDAO;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserDeleteController", urlPatterns = {"/admin/users/delete"})
public class UserDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));

        EntityManager em = (EntityManager) request.getAttribute("EntityManager");
        UserDAO userDAO = new UserDAO(em);
        userDAO.deleteUserModel(id);
        
        String path = request.getServletContext().getContextPath();
        path += "/admin/users";
        response.sendRedirect(path);
    }

    @Override
    public String getServletInfo() {
        return "User Delete Servlet";
    }

}
