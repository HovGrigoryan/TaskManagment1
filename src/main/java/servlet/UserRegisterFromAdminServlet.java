package servlet;

import manager.UserManager;
import model.User;
import model.UserStatus;
import model.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/UserRegisterFromAdmin")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50 * 5)
public class UserRegisterFromAdminServlet extends HttpServlet {

    private final String UPLOAD_DIR = "C:\\Users\\Hov\\Desktop\\GIT JAVA\\TaskManagment\\upload";


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        UserManager userManager = new UserManager();
        User user = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .status(UserStatus.valueOf(type))
                .build();
        for (Part part : req.getParts()) {
            if (getFileName(part) != null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String FullFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(FullFileName);
                user.setPictureUrl(fileName);
            }
        }
        userManager.register(user);
        resp.sendRedirect("/adminHome");
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }

}
