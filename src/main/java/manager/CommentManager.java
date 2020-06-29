package manager;

import db.DBConnectionProvider;
import model.Comment;
import model.ToDo;
import model.ToDoStatus;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public boolean create(Comment comment) {
        String sql = "INSERT INTO comment(task_id,user_id,comment) VALUES(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, comment.getTaskId());
            statement.setLong(2, comment.getUserId());
            statement.setString(3, comment.getComment());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                comment.setId(rs.getLong(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Comment getCommentByID(long id) {
        String sql = "SELECT * FROM comment WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return getCommentFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM comment  WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();
        String sql = "SELECT * FROM comment";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comments.add(getCommentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public List<Comment> getAllCommentByUserIdAndTaskId(int userId, int taskId) {
        List<Comment> comments = new ArrayList<Comment>();
        String sql = "SELECT * FROM comment WHERE user_id = ? AND task_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.setLong(2, taskId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comments.add(getCommentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public boolean deleteComment(int id) {
        String sql = "DELETE FROM comment  WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Comment> getAllCommentByTaskId(int taskId) {
        List<Comment> comments = new ArrayList<Comment>();
        String sql = "SELECT * FROM comment WHERE task_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, taskId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comments.add(getCommentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    private Comment getCommentFromResultSet(ResultSet resultSet) {
        try {
            try {
                return Comment.builder()
                        .id(resultSet.getLong("id"))
                        .taskId(resultSet.getInt("task_id"))
                        .userId(resultSet.getInt("user_id"))
                        .comment(resultSet.getString("comment"))
                        .date(sdf.parse(resultSet.getString("date")))
                        .build();
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}



