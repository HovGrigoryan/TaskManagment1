package servlet;

import manager.ToDoManager;
import manager.UserManager;
import model.User;
import model.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/adminHome")
public class AdminHomeServlet extends HttpServlet {
    private UserManager userManager = new UserManager();
    private ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<User> allUsers = userManager.getAllUsers();
        if (user==null || user.getStatus() != UserStatus.MANAGER){
            resp.sendRedirect("/index.jsp");
        }else {
            req.setAttribute("users",allUsers);
            req.setAttribute("tasks",toDoManager.getAll());
            req.getRequestDispatcher("WEB-INF/adminHome.jsp").forward(req,resp);
        }
    }
}
