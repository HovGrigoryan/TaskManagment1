package servlet;

import manager.CommentManager;
import manager.ToDoManager;
import model.Comment;
import model.ToDo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/comment")
public class CommentServlet extends HttpServlet {

    ToDoManager toDoManager = new ToDoManager();
    CommentManager commentManager = new CommentManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int todoID = Integer.parseInt(id);
        ToDo byID = toDoManager.getByID(todoID);
        List<Comment> allCommentByTaskId = commentManager.getAllCommentByTaskId(todoID);
        if (allCommentByTaskId.size() != 0) {
            req.setAttribute("toDoComments", allCommentByTaskId);
        }
        req.setAttribute("toDo", byID);
        req.getRequestDispatcher("WEB-INF/comment.jsp").forward(req, resp);
    }
}
