package org.example.ducklinginc.control;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.ducklinginc.model.Receipt;
import org.example.ducklinginc.repositories.ReceiptDatabase;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

    ReceiptDatabase receiptDatabase = new ReceiptDatabase();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("name");
        receiptDatabase.getAllReceipts(username);
        resp.setContentType("text/html");

        out.println("<form action=\"//addReceipt.jsp\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"New Receipt\">");
        out.println("</form>");

        out.println("<form action=\"//editReceipt.jsp\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"Edit Receipt\">");
        out.println("</form>");

        out.println("<form action=\"//deleteReceipt.jsp\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"Delete Receipt\">");
        out.println("</form>");

        out.println("<form action=\"/LogoutServlet\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"Log out\">");
        out.println("</form>");
    }
}