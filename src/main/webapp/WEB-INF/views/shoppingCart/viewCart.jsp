<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jotaiwan
  Date: 7/08/2017
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMvc</title>
</head>
<body>
    <h2>Shopping cart</h2>

    <c:forEach items="${products}" var="product">
        <div><a href="/shoppingCart/add/${product.id}">${product.code}</a>, ${product.name}</div>
    </c:forEach>
</body>
</html>
