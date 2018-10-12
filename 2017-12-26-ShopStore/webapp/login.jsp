<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/26
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<html>
<head>
    <title>
        用户登录
    </title>
</head>
<div align="center">
<body bgcolor="#e3e3e3">

    <form action="list.html" method="post">
        <table>
            <caption>用户登录</caption>
            <tr><td>用户名:</td><td><input type="text" name="username" size="20"/></td></tr>
            <tr><td>密码:</td><td><input type="text" name="pwd" size="20"/></td></tr>
            <tr><td><input type="submit" value="登录"/><td><input type="reset" value="重置"/>
        </table>
    </form>
    如果您还没有注册，请单击<a href="register.jsp">这里</a>注册！
</body>
</div>
</html>