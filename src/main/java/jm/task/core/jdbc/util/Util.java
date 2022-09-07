package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/user";
    private static final String NAME = "root";
    private static final String PASSWORD = "1";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static SessionFactory getSessionFactory () {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.connection.username", NAME)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.dialect", DIALECT)
                .setProperty("hibernate.driver_class", DRIVER);

        configuration.addAnnotatedClass(User.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(registry);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }
    public static Statement getStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
}

