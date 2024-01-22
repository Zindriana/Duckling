package org.example.ducklinginc.service;

import org.example.ducklinginc.model.Receipt;
import org.example.ducklinginc.repositories.ReceiptDatabase;
import org.example.ducklinginc.repositories.UserDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceiptService {

    ReceiptDatabase receiptDatabase = new ReceiptDatabase();

    UserDatabase userDatabase = new UserDatabase();
    Connection conn = userDatabase.getConnection();

    public void createReceipt(String title, Date date, String description, String category, int price, String owner) {
        receiptDatabase.createReceipt(title, date, description, category
                , price, owner);
    }

    public Receipt getReceiptById(int id, String username) {
        String sql = "SELECT id, owner FROM receipts WHERE id=? AND owner=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, username);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                return receiptDatabase.getReceiptByID(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void delete(int id, String username){
        String sql = "SELECT id, owner FROM receipts WHERE id=? AND owner=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, username);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                receiptDatabase.deleteReceipt(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
