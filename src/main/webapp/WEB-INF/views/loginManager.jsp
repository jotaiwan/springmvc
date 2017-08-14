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
    <h2>Login</h2></div>
    <div>Back to <a href="http://localhost:8080">home</a></div>
    <div><a href="/login/add">add</a></div>
    <c:choose>
        <c:when test="${mode == 'add'}">
            <div>add the new login here..</div>
            <form:form method = "POST" action = "/login/save" modelAttribute="login">
                <table>
                    <tr>
                        <td><form:label path = "username">Username</form:label></td>
                        <td><form:input path = "username" /></td>
                    </tr>
                    <tr>
                        <td><form:label path = "password">Password</form:label></td>
                        <td><form:password path = "password" /></td>
                    </tr>
                    <tr>
                        <td colspan = "2">
                            <input type = "submit" value = "Submit"/>
                        </td>
                    </tr>
                </table>
            </form:form>
        </c:when>
        <c:when test="${mode == 'edit'}">
            <div>edit ${login.username} here..</div>
            <form:form method = "POST" action = "/login/update/${login.id}" modelAttribute="login">
                <table>
                    <tr>
                        <td><form:label path = "username">Username</form:label></td>
                        <td><form:input path = "username" /></td>
                    </tr>
                    <tr>
                        <td><form:label path = "password">Password</form:label></td>
                        <td><form:password path = "password" /></td>
                    </tr>
                    <tr>
                        <td colspan = "2">
                            <input type = "submit" value = "Submit"/>
                        </td>
                    </tr>
                </table>
            </form:form>
        </c:when>
        <c:otherwise>
            <c:forEach items="${logins}" var="login">
                <div><a href="/login/edit/${login.id}">${login.username}</a></div>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</body>
</html>
