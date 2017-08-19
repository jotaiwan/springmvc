<%--
  Created by IntelliJ IDEA.
  User: jotaiwan
  Date: 7/08/2017
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>SpringMvc</title>
    </head>
    <body>
        <div class="tab-content" id="shoppingcart">
            <h2>Shopping cart ${cartItems}</h2>

            <c:if test="${cartItems > 0}">
                You have ${cartItems} in the shoppint cart.
            </c:if>

            <c:forEach items="${products}" var="product">
                <div><a href="/shoppingCart/add/${product.id}">${product.code}</a>, ${product.name}</div>
            </c:forEach>
        </div>
    </body>
</html>
