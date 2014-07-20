package controllers.auth;

import daos.UserDAO;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/login", "/auth/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletContext().getContextPath();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        EntityManager em = (EntityManager) request.getAttribute("EntityManager");
        UserDAO userDAO = new UserDAO(em);
        
        if (userDAO.authenticate(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("currentuser", username);
            
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
