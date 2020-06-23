package servlet;

import manager.ToDoManager;
import manager.UserManager;
import model.ToDo;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/userHome")
public class UserHomeServlet extends HttpServlet {

    UserManager userManager = new UserManager();
    ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            User user = (User) req.getSession().getAttribute("user");
            List<ToDo> toDos = toDoManager.getAllToDosByUserID(user.getId());
            req.setAttribute("tasks", toDos);
            req.getRequestDispatcher("/WEB-INF/userHome.jsp").forward(req, resp);


    }
}
