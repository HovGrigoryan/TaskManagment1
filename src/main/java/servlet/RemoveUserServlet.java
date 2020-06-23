package servlet;

import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/removeUser")
public class RemoveUserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        int userID = Integer.parseInt(id);
        UserManager userManager = new UserManager();
        userManager.removeUserById(userID);
        resp.sendRedirect("/adminHome");
    }
}
