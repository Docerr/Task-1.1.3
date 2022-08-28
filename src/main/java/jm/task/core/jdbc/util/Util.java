package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String NAME = "root";
    private static final String PASSWORD = "1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }
    public static Statement getStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
}
