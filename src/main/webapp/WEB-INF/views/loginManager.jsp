<%--
  Created by IntelliJ IDEA.
  User: jotaiwan
  Date: 30/07/2017
  Time: 8:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>SpringMVC</title>
</head>
<body>
    <h2>Login</h2>
    <div><a href="/login/add">add</a></div>
    <c:choose>
        <c:when test="${mode == 'add'}">
            <div>add the new login here..</div>
        </c:when>
        <c:when test="${mode == 'edit'}">
            <div>edit ${login.username} here..</div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${logins}" var="login">
                <div><a href="/login/${login.id}/edit">${login.username}</a></div>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</body>
</html>
