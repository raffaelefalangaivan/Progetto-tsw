package control;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import utils.PasswordUtils;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/view/customer/signup.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username =
                request.getParameter("username");

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("CUSTOMER");

        try {

            if (userDAO.usernameExists(username)) {

                request.setAttribute(
                        "error",
                        "Username già utilizzato");

                request.getRequestDispatcher(
                        "/WEB-INF/view/customer/signup.jsp")
                        .forward(request, response);

                return;
            }

            if (userDAO.emailExists(email)) {

                request.setAttribute(
                        "error",
                        "Email già registrata");

                request.getRequestDispatcher(
                        "/WEB-INF/view/customer/signup.jsp")
                        .forward(request, response);

                return;
            }

            user.setPassword(
                    PasswordUtils.hashPassword(password));

            userDAO.save(user);

            response.sendRedirect(
                    request.getContextPath() + "/");

        } catch (SQLException e) {

            throw new ServletException(e);
        }
    }
}