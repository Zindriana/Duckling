package org.example.ducklinginc.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.ducklinginc.model.Receipt;
import org.example.ducklinginc.repositories.UserDatabase;
import org.example.ducklinginc.service.ReceiptService;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ReceiptServlet/*")
public class ReceiptServlet extends HttpServlet {

    UserDatabase userDatabase = new UserDatabase();
    Connection conn = userDatabase.getConnection();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    ReceiptService receiptService = new ReceiptService();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("name");
        switch (req.getPathInfo()) {
            case "/addReceipt":
                createReceipt(req, resp);
                break;
            case "/editReceipt":
                try {
                    editReceipt(req, resp);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/editConfirmed":
                try {
                    edit(req, resp);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/deleteReceipt":
                try {
                    deleteReceipt(req, resp);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/deleteConfirmed":
                try {
                    delete(req, resp);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
    private void createReceipt(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(true);
        String title = req.getParameter("title");
        Date date;
        try {
            date = dateFormatter.parse(req.getParameter("date"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        int price = Integer.parseInt(req.getParameter("price"));
        String owner = (String) session.getAttribute("name");

        receiptService.createReceipt(title, date, description, category, price, owner);

        resp.sendRedirect(req.getContextPath() + "/MenuServlet");
    }

    private void deleteReceipt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("name");
        int id = Integer.parseInt(req.getParameter("id"));
        Receipt receipt = receiptService.getReceiptById(id, username);
        if(receipt == null){
            resp.sendRedirect(req.getContextPath() + "/MenuServlet");
        } else {
            req.setAttribute("receipt", receipt);
            req.getRequestDispatcher("/deleteReceipt.jsp").forward(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("name");
        int id = Integer.parseInt(req.getParameter("id"));
        receiptService.delete(id, username);
        resp.sendRedirect(req.getContextPath() + "/MenuServlet");
    }

    private void editReceipt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("name");
        int id = Integer.parseInt(req.getParameter("id"));
        Receipt receipt = receiptService.getReceiptById(id, username);
        if(receipt == null){
            resp.sendRedirect(req.getContextPath() + "/MenuServlet");
        } else {
            req.setAttribute("id", receipt.getId());
            req.setAttribute("title", receipt.getTitle());
            String formattedDate = dateFormatter.format(receipt.getDate());
            req.setAttribute("date", formattedDate);
            req.setAttribute("description", receipt.getDescription());
            req.setAttribute("category", receipt.getCategory());
            req.setAttribute("price", receipt.getPrice());
            req.setAttribute("owner", username);
            req.getRequestDispatcher("/editReceipt.jsp").forward(req, resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("name");
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String date =  req.getParameter("date");;
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        int price = Integer.parseInt(req.getParameter("price"));
        req.setAttribute("id", id);
        req.setAttribute("title", title);
        req.setAttribute("date", date);
        req.setAttribute("description", description);
        req.setAttribute("category", category);
        req.setAttribute("price", price);
        receiptService.edit(id, title, date, description, category, price, username);
        resp.sendRedirect(req.getContextPath() + "/MenuServlet");
    }
}
