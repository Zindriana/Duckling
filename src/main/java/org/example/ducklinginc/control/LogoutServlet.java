package org.example.ducklinginc.control;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.ducklinginc.repositories.ReceiptDatabase;

import java.io.IOException;
import java.io.PrintWriter;


    @WebServlet("/LogoutServlet")
    public class LogoutServlet extends HttpServlet {

        public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            HttpSession session = req.getSession();
            session.setAttribute("name", null);
            resp.sendRedirect("/login.jsp");
        }
    }
