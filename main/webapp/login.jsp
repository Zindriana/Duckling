<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1>
        <%= "Budget Ducklings inc" %>
    </h1>
    <br/>
    <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
        <h2>
            <span>Username</span>
            <input type="text" name="username" placeholder="Username">
        </h2>
        <h2>
            <span>Password</span>
            <input type="password" name="password" placeholder="Password">
        </h2>
        <button> Log in </button>
    </form>
</body>
</html>