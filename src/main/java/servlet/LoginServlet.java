package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    UserManager userManager = new UserManager();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        StringBuilder msg = new StringBuilder();

        if (email == null || email.length() == 0) {
            msg.append("email field is required <br>");
        }
        if (password == null || password.length() == 0) {
            msg.append("password field is required <br>");
        }
        if (msg.toString().equals("")) {
            User user = userManager.getUserByEmailAndPassword(email, password);
            if (user!=null){
                req.getSession().setAttribute("user",user);
                resp.sendRedirect("/home");
                return;
            }else {
                msg.append("User does not exists");
                req.getSession().setAttribute("msg", msg.toString());
                resp.sendRedirect("/");
            }

        } else {
            req.getSession().setAttribute("msg", msg.toString());
            resp.sendRedirect("/");
        }

    }
}
