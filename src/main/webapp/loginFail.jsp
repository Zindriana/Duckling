<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>
    <%= "Budget Ducklings inc" %>
</h1>
<br/>
<h2>Wrong user info, try again</h2>
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
