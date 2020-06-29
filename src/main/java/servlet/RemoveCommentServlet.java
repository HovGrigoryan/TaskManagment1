package servlet;

import manager.CommentManager;
import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/removeComment")
public class RemoveCommentServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String taskIdStr = req.getParameter("taskID");

        int userID = Integer.parseInt(id);
        int taskID = Integer.parseInt(taskIdStr);
        CommentManager commentManager = new CommentManager();
        commentManager.deleteComment(userID);
        resp.sendRedirect("/comment?id="+taskID);
    }
}
