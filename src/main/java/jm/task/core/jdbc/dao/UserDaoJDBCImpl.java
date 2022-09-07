package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS user.users ("
            + "id BIGINT NOT NULL AUTO_INCREMENT, "
            + "name VARCHAR(45) NOT NULL, "
            + "lastName VARCHAR(45) NOT NULL, "
            + "age TINYINT NOT NULL, "
            + "PRIMARY KEY (id)) ";

    private static final String SQL_DROP = "DROP TABLE IF EXISTS user.users ";

    private static final String SQL_SAVE = "INSERT INTO user.users "
            + "(name, lastName, age) "
            + "VALUES "
            + "(?,?,?) ";

    private static final String SQL_REMOVE = "DELETE FROM user.users WHERE id = ?";

    private static final String SQL_GET = "SELECT * FROM user.users";

    private static final String SQL_CLEAN = "DELETE FROM user.users";

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = Util.getStatement(connection)) {
            statement.execute(SQL_CREATE);
            System.out.println("...Таблица Users создана...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = Util.getStatement(connection)) {
            statement.execute(SQL_DROP);
            System.out.println("...Таблица Users удалена...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

        public void saveUser (String name, String lastName, byte age) {
            try (Connection connection = Util.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(SQL_SAVE);
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.executeUpdate();
                System.out.println("User с именем – " + name + " добавлен в базу данных");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void removeUserById (long id) {
            try (Connection connection = Util.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(SQL_REMOVE);
                statement.setLong(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public List<User> getAllUsers () {
            List<User> list = new ArrayList<>();

            try (Connection connection = Util.getConnection();
                 Statement statement = Util.getStatement(connection)) {
                ResultSet resultSet = statement.executeQuery(SQL_GET);

                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    list.add(user);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return list;
        }

        public void cleanUsersTable () {
            try (Connection connection = Util.getConnection();
                 Statement statement = Util.getStatement(connection)) {
                statement.execute(SQL_CLEAN);
                System.out.println("...Таблица Users очищена...");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

