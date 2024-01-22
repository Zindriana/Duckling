package org.example.ducklinginc.control;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.ducklinginc.model.User;
import org.example.ducklinginc.repositories.ReceiptDatabase;
import org.example.ducklinginc.repositories.UserDatabase;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    UserDatabase userDatabase = new UserDatabase();
    ReceiptDatabase receiptDatabase = new ReceiptDatabase();
    Connection conn = userDatabase.getConnection();
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session;
        resp.setContentType("text/html");
        userDatabase.createUsers();
        receiptDatabase.createReceiptsTable();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String sql = "SELECT username, password FROM users WHERE username=? AND password=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            boolean userExist = false;

            if(rs.next()) {
                userExist = true;
                session = req.getSession(true);
                session.setAttribute("name", username);
                resp.sendRedirect("/MenuServlet");
            }
            if(!userExist){
                resp.sendRedirect("/loginFail.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}