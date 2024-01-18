package org.example.ducklinginc.repositories;

import jakarta.servlet.http.HttpSession;
import org.example.ducklinginc.model.Receipt;
import org.example.ducklinginc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDatabase {
    UserDatabase userDatabase = new UserDatabase();
    Connection conn = userDatabase.getConnection();
    List<Receipt> receipts = new ArrayList<>();

    public void createReceipts(){

        String sql = "CREATE TABLE IF NOT EXISTS receipts (id INT(4) AUTO_INCREMENT PRIMARY KEY, title varchar(32), date date, description varchar(255), category varchar(16), price INT(8), owner varchar(32))";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Receipt> getAllReceipts(String username){
        String sql = "SELECT id, title, date, description, category, price, owner  FROM receipts WHERE owner = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Receipt receipt = new Receipt();

                receipt.setId(rs.getInt("id"));
                receipt.setTitle(rs.getString("title"));
                receipt.setDate(new java.util.Date(rs.getDate("date").getTime()));
                receipt.setDescription(rs.getString("description"));
                receipt.setCategory(rs.getString("category"));
                receipt.setPrice(rs.getInt("price"));
                receipt.setOwner(rs.getString("owner"));

                receipts.add(receipt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receipts;
    }
}
