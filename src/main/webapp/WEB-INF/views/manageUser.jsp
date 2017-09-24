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
        <link rel="stylesheet" href="/resources/css/user.css">

        <script src="//code.jquery.com/jquery-1.12.4.js"></script>

        <script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <script type="text/javascript" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" />


        <script>
            $(document).ready(function(){
                $('#myTable').dataTable({
                    "paging":   false,
                    "ordering": false,
                    "info":     false
                });
            });
        </script>

    </head>
    <body>

    <div class="generic-container" id="manageuser">
            <c:choose>
                <c:when test="${mode == 'add'}">
                    <c:import url="/WEB-INF/views/user/registerUser.jsp" />
                </c:when>
                <c:when test="${mode == 'edit'}">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6 col-md-offset-3">
                                <div class="panel panel-login">
                                    <div class="panel-heading">
                                        <c:choose>
                                            <c:when test="${view == 'login'}">
                                                <h3 class="modal-title btn btn-success btn-lg" id="myModalLabel">
                                                    <span class="glyphicon glyphicon-lock"></span> Edit Login</h3>
                                            </c:when>
                                            <c:when test="${view == 'account'}">
                                                <h3 class="modal-title btn btn-success btn-lg" id="myModalLabel">
                                                    <span class="glyphicon glyphicon-user"></span> Edit User</h3>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <c:choose>
                                                    <c:when test="${view == 'login'}">
                                                        <c:import url="/WEB-INF/views/user/editLogin.jsp" />
                                                    </c:when>
                                                    <c:when test="${view == 'account'}">
                                                        <c:import url="/WEB-INF/views/user/editAccount.jsp" />
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:when test="${mode == 'document'}">
                    <c:import url="/WEB-INF/views/user/document.jsp"/>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty alertType}">
                        <div class="alert alert-${alertType}">
                                ${alertMessage}
                        </div>
                    </c:if>
                    <form:form action="/user/all" method="post" modelAttribute="users" id="userInfo" role="form">
                        <div class="panel panel-default">
                            <div class="panel-heading"><span class="lead">List of Users </span></div>
                            <div class="bootstrap-table">
                            <table id="myTable" class="table table-striped ">
                                <thead>
                                <tr>
                                    <th>User name</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Email Address</th>
                                    <th width="50" data-defaultsort="disabled"></th>
                                    <th width="50" data-defaultsort="disabled"></th>
                                    <th width="50" data-defaultsort="disabled"></th>
                                    <th width="50" data-defaultsort="disabled"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${users}" var="user" varStatus="status">
                                    <tr>
                                        <td>${user.userName}</td>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td>${user.emailAddress}</td>
                                        <th><a href="<c:url value='/user/document-${user.id}' />" class="btn btn-primary">
                                            <span class="glyphicon glyphicon-pencil"/></a></th>
                                        <td><a href="<c:url value='/user/login/edit/${user.id}' />" class="btn btn-warning">
                                            <span class="glyphicon glyphicon-lock"/></a>
                                        </td>
                                        <td><a href="<c:url value='/user/account/edit/${user.id}' />" class="btn btn-success">
                                            <span class="glyphicon glyphicon-user"/></a>
                                        </td>
                                        <td><a href="/user/delete/${user.id}" class="btn btn-danger">
                                            <span class="glyphicon glyphicon-trash"/>
                                        </a>
                                        </td>
                                        <%--
                                        <td><a href="#" id="remove" class="btn btn-danger"
                                               onclick="$(document.getElementById('userInfo')).attr('action', '/user/delete/${user.id}');
                                                       document.getElementById('userInfo').submit();">
                                                <span class="glyphicon glyphicon-trash"/>
                                            </a>
                                        </td>
                                        --%>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            </div>

                        </div>
                    </form:form>
                </c:otherwise>
            </c:choose>
        </div>

        <c:if test="${mode != 'add'}" >
            <div class="well">
                <a href="<c:url value='/user/register/1' />">Add New User</a>
            </div>
        </c:if>
    </body>
</html>
