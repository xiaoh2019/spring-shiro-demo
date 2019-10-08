<%--
  Created by IntelliJ IDEA.
  User: 29241
  Date: 2019/9/3
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
登录页面

<form action="/loginIn" method="post">
    username:<input type="text" name="username"><br>
    password:<input type="text" name="password">${requestScope.msg}<br>
    <input type="submit" value="登录">
</form>
</body>
</html>
