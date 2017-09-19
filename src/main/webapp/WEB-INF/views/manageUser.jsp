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

        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css" />
        <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function(){
                $('#myTable').dataTable();
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

                    <c:import url="/WEB-INF/views/user/all.jsp"/>
                </c:otherwise>
            </c:choose>
        </div>

        <c:if test="${mode != 'add'}" >
            <div class="well">
                <a href="<c:url value='/user/register' />">Add New User</a>
            </div>
        </c:if>
    </body>
</html>
