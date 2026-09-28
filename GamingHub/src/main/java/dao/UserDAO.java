package dao;

import java.sql.Connection;
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
}