package servlet;

import manager.ToDoManager;
import manager.ToDoManager;
import model.ToDoStatus;
import model.User;
import model.UserStatus;
import model.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeToDoStatus")
public class ChangeToDoStatus extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int toDoId = Integer.parseInt((req.getParameter("toDoId")));
        ToDoStatus todoStatus = ToDoStatus.valueOf(req.getParameter("status"));

        ToDoManager toDoManager = new ToDoManager();
        toDoManager.update(toDoId,todoStatus);
        if (user.getStatus() == UserStatus.MANAGER){
            resp.sendRedirect("/managerHome");
        }else {
            resp.sendRedirect("/userHome");
        }

    }
}
