<%--
  Created by IntelliJ IDEA.
  User: 29241
  Date: 2019/9/3
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>list页面</h2>
<a href="/loginOut">退出</a><br>
<shiro:hasRole name="admin">你是管理人员</shiro:hasRole>
<br>
<shiro:hasRole name="general">你是普通人员</shiro:hasRole>
</body>
</html>
