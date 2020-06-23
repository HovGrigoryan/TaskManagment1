package servlet;

import manager.ToDoManager;
import model.ToDo;
import model.ToDoStatus;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/addTodo")
public class AddToDoServlet extends HttpServlet {

    ToDoManager toDoManager = new ToDoManager();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String deadline = req.getParameter("deadline");
        String status = req.getParameter("status");
        String userID = req.getParameter("userID");
        ToDo todo = null;
        try {
            try {
                todo = ToDo.builder()
                        .title(title)
                        .deadline(sdf.parse(deadline))
                        .status(ToDoStatus.valueOf(status) )
                        .userId(Integer.parseInt(userID))
                        .build();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toDoManager.create(todo);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/adminHome");
    }
}
