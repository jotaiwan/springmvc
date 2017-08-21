<%--
  Created by IntelliJ IDEA.
  User: jotaiwan
  Date: 30/07/2017
  Time: 12:58 PM
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
        <div class="tab-content" id="employee">
            <c:forEach items="${employees}" var="employee">
                <div><a href="/employee/edit/${employee.id}">${employee.id}</a>, ${employee.name}</div>
            </c:forEach>
        </div>
    </body>
</html>
