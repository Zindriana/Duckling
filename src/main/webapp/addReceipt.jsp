<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Receipt</title>
</head>
    <body>
        <form action="${pageContext.request.contextPath}/ReceiptServlet/addReceipt" method="POST">
            <span>Title</span>
            <input type="text" name="title">
            <span>Date</span>
            <input type="date" name="date">
            <span>Description</span>
            <textarea type="text" name="description"></textarea>
            <span>Category</span>
            <select name="category">
                <option value="travel">Travel</option>
                <option value="lunch meeting">Lunch-meeting</option>
                <option value="overtime">Overtime</option>
                <option value="misc">Miscellaneous</option>
            </select>
            <span>Price</span>
            <input type="number" name="price">
            <button>
                Submit
            </button>
        </form>
    </body>
</html>
