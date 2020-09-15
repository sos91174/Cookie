<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/10
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="http://localhost:8080/13_cookie_session/loginServlet" method="get">
        用戶名：<input type="text" name="username" value="${cookie.username.value}"> <br>
        密碼：<input type="password" name="password"> <br>
        <input type="submit" value="登入">
    </form>
</body>
</html>
