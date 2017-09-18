<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${not empty alertType}">
    <div class="alert alert-${alertType}">
            ${alertMessage}
    </div>
</c:if>

<form action="/user/all" method="post" id="removeUserForm" role="form" >

    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Users </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>User name</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email Address</th>
                    <th width="50"></th>
                    <th width="50"></th>
                    <th width="50"></th>
                    <th width="50"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${logins}" var="login">
                    <tr>
                        <td>${login.username}</td>
                        <td>${login.user.firstName}</td>
                        <td>${login.user.lastName}</td>
                        <td>${login.user.emailAddress}</td>
                        <th><a href="<c:url value='/user/document-${login.user.id}' />" class="btn btn-success">
                            <span class="glyphicon glyphicon-pencil"/></a></th>
                        <td><a href="<c:url value='/user/login/edit/${login.user.id}' />" class="btn btn-success">
                            <span class="glyphicon glyphicon-lock"/></a>
                        </td>
                        <td><a href="<c:url value='/user/account/edit/${login.user.id}' />" class="btn btn-success">
                            <span class="glyphicon glyphicon-user"/></a>
                        </td>
                        <td><a href="#" id="remove" class="btn btn-danger"
                               onclick="$(document.getElementById('removeUserForm')).attr('action', '/user/delete/${login.user.id}');
                                       document.getElementById('removeUserForm').submit();">
                            <span class="glyphicon glyphicon-trash"/>
                        </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%--
    <div>Total of users found.</div>
    <table  class="table table-striped">
        <c:forEach items="${logins}" var="login" >
            <c:set var="classSucess" value=""/>
            <c:if test ="${idUser == login.id}">
                <c:set var="classSucess" value="info"/>
            </c:if>
            <tr class="${classSucess}">
                <td>
                    <a href="/manageuser/edit/${login.id}">${login.username}</a>
                </td>
                <td>
                    ${login.user.firstName}
                </td>
                <td>
                    ${login.user.lastName}
                </td>
                <td>
                    ${login.user.emailAddress}
                </td>
                <td><a href="#" id="remove"
                    onclick="$(document.getElementById('removeUserForm')).attr('action', '/manageuser/delete/${login.id}');
                        document.getElementById('removeUserForm').submit();">
                    <span class="glyphicon glyphicon-trash"/>
                </a>

                </td>
            </tr>
        </c:forEach>
    </table>
    --%>
</form>
