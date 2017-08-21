<%--
  Created by IntelliJ IDEA.
  User: jotaiwan
  Date: 12/08/2017
  Time: 5:10 PM
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
    <h2>Product Page</h2>
    <div>Back to <a href="http://localhost:8080">home</a></div>

    <div><c:out value="${total}"/></div>
</body>
</html>
