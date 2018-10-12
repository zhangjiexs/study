<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/28
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品列表</title>
    <style type="text/css">
        body{
            margin: 0px;
            padding: 0px;
            background: url("/images/bg.jpg");
        }
        * {
            margin: 0px;
            padding: 0px;
        }

        .wrap {
            width: 100%;
            height: 100%;
            outline: 1px solid red;
            position: relative;
        }

        .inner {
            width: 200px;
            height: 300px;
            position: absolute;
            top: 50%;
            margin-top: -150px;
            left: 50%;
            margin-left: -100px;
        }

        .inner > select {
            padding: 6px;
        }

        .inner > input {
            padding: 4px;

        }

    </style>
</head>
<body>
<div class="wrap">
    当前用户:<span style="color: red" >${sessionScope.username}</span>
    <div class="inner">
        <select id="sel">
            <c:forEach items="${requestScope.shops}" var="shop">
                <option value="${pageScope.shop.name}"> ${pageScope.shop.name}</option>
            </c:forEach>
        </select>
        <input type="button" value="购买商品" id="buy">
    </div>
</div>


<script type="text/javascript">
    var buy = document.getElementById("buy");
    var sel = document.getElementById("sel");
    buy.onclick = function () {
        var url = "/buyshop";
        var xhr = new XMLHttpRequest()
        xhr.open("get", url, true)
        xhr.send()
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                alert("购买成功,请继续购买或者切换用户继续购买");
            }
        }
    }
</script>
</body>
</html>
