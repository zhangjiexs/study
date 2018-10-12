<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/26
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>欢迎光临购物车</title>
    <jsp:useBean id="Goods" scope="session" class="com.uplooking.bigdata.vo.Goods" />
    <style type="text/css">
        td {
            color: #333;
            font-family: "微软雅黑", Verdana, sans-serif, "宋体";
            width: 150px;
            border-bottom-width: 1px;
            border-bottom-style: dashed;
            border-bottom-color: #999;
        }

        table {
            width: 400px;
            text-align: center;
            margin-right: auto;
            margin-left: auto;
            border: 2px solid #666;
        }

        h1 {
            color: #F66;
        }
    </style>
</head>

<body>
<div align="center">
    <h1>
        欢迎光临购物车
    </h1>
    <p>
        您的购物信息如下：
    </p>
    <table>


        <tr>
                <br>
                <form action="edit.jsp" method="post">
                    <input name="deleteName" type="hidden" value="">
                    <input name="delete" type="submit" value="移除">
                </form>

            </td>
        </tr>
    </table>
    <p>
        <input type="button" name="goon" value="继续购物"
               onClick="javascript:window.location='list.html'">
        <input type="button" name="buy" value="确定订单"
               onClick="javascript:window.location='success.jsp'">
    </p>
</div>
</body>
</html>
