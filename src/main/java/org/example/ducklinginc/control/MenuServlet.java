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
import java.util.List;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

    ReceiptDatabase receiptDatabase = new ReceiptDatabase();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("name");
        List<Receipt> receiptlist = receiptDatabase.getAllReceipts(username);
        for(Receipt receipt: receiptlist){
            out.println("<br>" + receipt.getId() + ") Titel: " + receipt.getTitle() + " Date: " + receipt.getDate() + " Description: " + receipt.getDescription() + " Category: " + receipt.getCategory() + " Cost: " + receipt.getPrice()+"SEK <br>");
        }

        out.println(" <br> <form action=\"/addReceipt.jsp\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"New Receipt\">");
        out.println("</form>");

        out.println("<br> <form action=\"/ReceiptServlet/editReceipt\" method=\"post\">");
        out.println("<label>Choose the number for the receipt you want to edit</label> <br>");
        out.println("<input type=\"number\" name=\"id\" value=\"1\" min=\"1\">");
        out.println("<input type=\"submit\" value=\"Edit Receipt\">");
        out.println("</form>");

        out.println("<form action=\"/ReceiptServlet/deleteReceipt\" method=\"post\">");
        out.println("<label>Choose the number for the receipt you want to delete</label> <br>");
        out.println("<input type=\"number\" name=\"id\" value=\"1\" min=\"1\">");
        out.println("<input type=\"submit\" value=\"Delete Receipt\">");
        out.println("</form>");

        out.println("<form action=\"/LogoutServlet\" method=\"post\">");
        out.println("<input type=\"submit\" value=\"Log out\">");
        out.println("</form>");
    }
}