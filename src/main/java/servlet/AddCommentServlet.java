package servlet;

import manager.CommentManager;
import manager.ToDoManager;
import model.Comment;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {

    CommentManager commentManager = new CommentManager();
    ToDoManager toDoManager = new ToDoManager();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("toDoId");
        int todoID = Integer.parseInt(id);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String comment1 = req.getParameter("comment");
        Comment comment = null;
        try {
            comment = Comment.builder()
                    .taskId(todoID)
                    .userId(user.getId())
                    .comment(comment1)
                    .build();

            commentManager.create(comment);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/comment?id=" + todoID);
    }
}

