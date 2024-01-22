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
        <form action="${pageContext.request.contextPath}/ReceiptServlet/editReceipt" method="POST">
            <span>Title</span>
            <input type="text" name="title" value=""${title}"">
            <span>Date</span>
            <input type="date" name="date" value=""${date}"">
            <span>Description</span>
            <textarea type="text" name="description">${description}""</textarea>
            <span>Category</span>
            <select name="category">
                <option ${category == 'travel' ? 'selected' : ''} value="travel">Travel</option>
                <option ${category == 'lunch meeting' ? 'selected' : ''} value="lunch meeting">Lunch-meeting</option>
                <option ${category == 'overtime' ? 'selected' : ''} value="overtime">Overtime</option>
                <option ${category == 'misc' ? 'selected' : ''} value="misc">Miscellaneous</option>
            </select>
            <span>Price</span>
            <input type="number" name="price" value=""${price}"">
            <button>
                Submit changes
            </button>
        </form>
    </body>
</html>
