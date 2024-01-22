package org.example.ducklinginc.repositories;

import org.example.ducklinginc.model.Receipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceiptDatabase {
    UserDatabase userDatabase = new UserDatabase();
    Connection conn = userDatabase.getConnection();

    public void createReceiptsTable(){

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
        List<Receipt> receipts = new ArrayList<>();
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

    public void createReceipt(String title, Date date, String description, String category, int price, String owner){
        String sql =
                "INSERT INTO receipts (title, date, description, category, price, owner) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setDate(2, new java.sql.Date(date.getTime()));
            pstmt.setString(3, description);
            pstmt.setString(4, category);
            pstmt.setInt(5, price);
            pstmt.setString(6, owner);

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Receipt getReceiptByID(int id){
        Receipt receipt = new Receipt();
        String sql = "SELECT id, title, date, description, category, price FROM receipts WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                receipt.setId(rs.getInt("id"));
                receipt.setTitle(rs.getString("title"));
                receipt.setDate(new java.util.Date(rs.getDate("date").getTime()));
                receipt.setDescription(rs.getString("description"));
                receipt.setCategory(rs.getString("category"));
                receipt.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receipt;
    }
    public void deleteReceipt(int id){
        String sql = "DELETE FROM receipts WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public void editReceipt(int id, String title, String date, String description, String category, int price, String username){
        String sql = "UPDATE receipts SET title = ?, date = ?, description = ?, category = ?, price = ? WHERE id = ? AND owner = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, date);
            pstmt.setString(3, description);
            pstmt.setString(4, category);
            pstmt.setInt(5, price);
            pstmt.setInt(6, id);
            pstmt.setString(7, username);

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
