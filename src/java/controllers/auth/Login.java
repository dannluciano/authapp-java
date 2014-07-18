package controllers.auth;

import daos.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

@WebServlet(name = "Login", urlPatterns = {"/login", "/auth/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = (EntityManager) request.getAttribute("EntityManager");
        UserDAO userDAO = new UserDAO(em);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String path = request.getServletContext().getContextPath();
        User u = userDAO.getUserWithUsernameAndPassword(username, password);
        if (u != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", "admin");
            
            path += "/admin/dashboard.jsp";
            response.sendRedirect(path);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
            rd.include(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet";
    }

}
