package manager;


import db.DBConnectionProvider;
import model.ToDo;
import model.ToDoStatus;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ToDoManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public boolean create(ToDo todo) {
        String sql = "INSERT INTO task(title,deadline,status,user_id) VALUES(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, todo.getTitle());
            if (todo.getDeadline() != null) {
                statement.setString(2, sdf.format(todo.getDeadline()));
            } else {
                statement.setString(2, null);

            }
            statement.setString(3, todo.getStatus().name());
            statement.setLong(4, todo.getUserId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                todo.setId(rs.getLong(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ToDo getByID(long id) {
        String sql = "SELECT * FROM task WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return getToDOFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(long id, ToDoStatus status) {
        String sql = "UPDATE task SET status = '" + status.name() + "' WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM task  WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ToDo>  getAll() {
        List<ToDo> toDos = new ArrayList<ToDo>();
        String sql = "SELECT * FROM task";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                toDos.add(getToDOFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDos;
    }

    public List<ToDo> getAllToDosByUserAndStatus(Long userId, ToDoStatus status) {
        List<ToDo> toDos = new ArrayList<ToDo>();
        String sql = "SELECT * FROM task WHERE user_id = ? AND status = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.setString(2, status.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                toDos.add(getToDOFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDos;
    }

    public List<ToDo> getAllToDosByUserID(long userId) {
        List<ToDo> toDos = new ArrayList<ToDo>();
        String sql = "SELECT * FROM task WHERE user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                toDos.add(getToDOFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDos;
    }

    private ToDo getToDOFromResultSet(ResultSet resultSet) {
        try {
            try {
                return ToDo.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .deadline(sdf.parse(resultSet.getString("deadline")))
                        .status(ToDoStatus.valueOf(resultSet.getString("status")))
                        .userId(resultSet.getInt("user_id"))
                        .createdDate(sdf.parse(resultSet.getString("created_date")))
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
