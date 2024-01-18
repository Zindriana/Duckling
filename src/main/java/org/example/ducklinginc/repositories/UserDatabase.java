package org.example.ducklinginc.repositories;

import org.example.ducklinginc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    private Connection conn;
    List<User> users = new ArrayList<>();

    public UserDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ducklingInc?createDatabaseIfNotExist=true", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createUsers(){

        String sql = "CREATE TABLE IF NOT EXISTS users (id INT(4) AUTO_INCREMENT PRIMARY KEY, username varchar(32), password varchar(16))";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "TRUNCATE TABLE users";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "INSERT INTO users (username, password) VALUES ('Anja', 'A123'), ('Batman', 'B123'), ('Yves', 'Y123'), ('Zandra', 'Z123')";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers(){
        String sql = "SELECT username, password FROM users";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                User user = new User();

                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public Connection getConnection() {
        return this.conn;
    }
}
