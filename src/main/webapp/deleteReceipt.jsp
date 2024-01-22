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
    <title>Delete Receipt</title>
</head>
<body>
<h1>Are you sure you want to delete this receipt?</h1>
<span>ID: ${receipt.id}</span>
<span>Title: ${receipt.title}</span>
<span>Date: ${receipt.date}</span>
<span>Description: ${receipt.description}</span>
<span>Category: ${receipt.category}</span>
<spanp>Cost: ${receipt.price} SEK</spanp>

<form action="${pageContext.request.contextPath}/ReceiptServlet/deleteConfirmed" method="POST">
    <input type="hidden" name="id" value="${receipt.id}">
    <button type="submit">
        Confirm
    </button>
</form>

<form action="${pageContext.request.contextPath}/MenuServlet" method="GET">
    <button type="submit">
        Cancel
    </button>
</form>
</body>
</html>
