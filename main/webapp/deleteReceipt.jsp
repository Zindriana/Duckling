<%--
  Created by IntelliJ IDEA.
  User: anyak
  Date: 2024-01-18
  Time: 09:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <body>
        <form action="${pageContext.request.contextPath}/ReceiptServlet/deleteReceipt" method="POST">
            <span>ID-number on the receipt to be deleted</span>
            <input type="number" name="id">
            <button>
                Confirm
            </button>
        </form>
    </body>
</html>
