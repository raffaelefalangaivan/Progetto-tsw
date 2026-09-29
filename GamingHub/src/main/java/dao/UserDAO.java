package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.User;
import utils.DataSourceManager;

public class UserDAO {

    private final DataSource dataSource;

    public UserDAO() {
        this.dataSource = DataSourceManager.getDataSource();
    }

    public void save(User user) throws SQLException {

        String sql =
                "INSERT INTO users(username,email,password,role) " +
                "VALUES(?,?,?,?)";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());

            statement.executeUpdate();
        }
    }
    public boolean emailExists(String email)
            throws SQLException {

        String sql =
                "SELECT id FROM users WHERE email = ?";

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);

            ResultSet resultSet =
                    statement.executeQuery();

            return resultSet.next();
        }
    }
    public boolean usernameExists(String username)
            throws SQLException {

        String sql =
                "SELECT id FROM users WHERE username = ?";

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, username);

            ResultSet resultSet =
                    statement.executeQuery();

            return resultSet.next();
        }
    }
}