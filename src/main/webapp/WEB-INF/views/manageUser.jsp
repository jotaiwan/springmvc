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
        <script>
            $(document).ready(function () {
                $('#password').on('hidden.bs.collapse', function () {
                    $.when( this ).done(function() {
                        $("#userInfo").removeClass("col-xs-6").addClass("col-xs-12");
                        $("input[type='password']").attr("disabled", true);
                    });
                });
                $('#password').on('show.bs.collapse', function() {
                    $("#userInfo").removeClass("col-xs-12").addClass("col-xs-6");
                    $("input[type='password']").attr("disabled", false);
                })
                $('#includePasswordChange').on('click', function() {
                    $("#isPasswordReset").val(!$("#isPasswordReset").val());
                });
            });

        </script>
    </head>
    <body>
        <div class="generic-container" id="manageuser">
            <c:choose>
                <c:when test="${mode == 'add'}">
                    <form:form method = "POST" action = "/manageuser/save" modelAttribute="login" cssClass="form-signin">
                        <div class="container-fluid">
                            <div class="row">
                                <h2 class="text-center" style="color: #f0ad4e;"> <Strong> Signup </Strong></h2> <hr />
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <div class="input-group-addon iga1">
                                                    <span class="glyphicon glyphicon-user"></span>
                                                </div>
                                                <form:input path="username" cssClass="form-control" placeholder="Enter User Name"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <div class="input-group-addon iga1">
                                                    <span class="glyphicon glyphicon-lock"></span>
                                                </div>
                                                <form:password path = "password" cssClass="form-control" placeholder="Enter Password" />
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <hr>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-lg btn-block btn-warning"> Register</button>
                                        </div>
                                    </div>
                                </div>
                            </div> <!-- row -->
                        </div> <!-- container-fluid -->
                    </form:form>
                </c:when>
                <c:when test="${mode == 'edit'}">
                    <form:form method = "POST" action = "/manageuser/update/${login.id}" modelAttribute="login" cssClass="form-account-wide">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h3 class="modal-title btn btn-success btn-lg" id="myModalLabel">
                                        <span class="glyphicon glyphicon-user"></span> <c:out value="${login.username}" /></h3>
                                </div>
                                <div class="modal-body">
                                    <div class="row">

                                        <div class="col-xs-6">
                                            <form:input path="passwordReset" id="isPasswordReset" value="false"/>
                                            <div class="well collapse" id="password" data-toggle="password">
                                                <div class="form-group" >
                                                    <label for="currentPassword" class="control-label">Current Password</label>
                                                    <input type="password" class="form-control" name="currentPassword" placeholder="Current Password" value="" required="" disabled title="Please enter your password">
                                                    <span class="help-block"></span>
                                                </div>
                                                <div class="form-group" >
                                                    <label for="password" class="control-label">New Password</label>
                                                    <input type="password" class="form-control" name="password" placeholder="New Password" value="" disabled required="" title="Please enter your password">
                                                    <span class="help-block"></span>
                                                </div>
                                                <div class="form-group" >
                                                    <label for="confirmPassword" class="control-label">Confirm Password</label>
                                                    <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password" disabled required="" value="" title="Please enter your password">
                                                    <span class="help-block"></span>
                                                </div>
                                                <div id="loginErrorMsg" class="alert alert-error hide">Wrong username or password</div>
                                            </div>
                                        </div>

                                        <div id="userInfo" class="col-xs-12">
                                            <div class="well">
                                                <form:hidden path="user.id" />
                                                <div class="form-group">
                                                    <label for="username" class="control-label">First name</label>
                                                    <form:input path = "user.firstName" cssClass="form-control" placeholder="Enter First Name" />
                                                    <span class="help-block"></span>
                                                </div>
                                                <div class="form-group">
                                                    <label for="password" class="control-label">Last name</label>
                                                    <form:input path = "user.lastName" cssClass="form-control" placeholder="Enter Last Name" />
                                                    <span class="help-block"></span>
                                                </div>
                                                <div class="form-group">
                                                    <label for="password" class="control-label">Email Address</label>
                                                    <form:input path = "user.emailAddress" cssClass="form-control" placeholder="Enter Email Address" />
                                                    <span class="help-block"></span>
                                                </div>
                                                <div id="loginErrorMsg" class="alert alert-error hide">Wrong username or password</div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-xs-7 pull-right">
                                        <p class="pull-right">
                                            <a href="#password" data-toggle="collapse" id="includePasswordChange">Include Password Reset?</a>
                                            <button type="submit" class="btn btn-primary">Save Change</button>
                                        </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </c:when>
                <c:otherwise>
                    <form action="/manageuser/all" method="post" id="removeUserForm" role="form" >

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
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${logins}" var="login">
                                        <tr>
                                            <td>${login.username}</td>
                                            <td>${login.user.firstName}</td>
                                            <td>${login.user.lastName}</td>
                                            <td>${login.user.emailAddress}</td>
                                            <td><a href="<c:url value='/manageuser/edit/${login.user.id}' />" class="btn btn-success">
                                                <span class="glyphicon glyphicon-pencil"/></a>
                                            </td>
                                            <td><a href="#" id="remove" class="btn btn-danger"
                                                   onclick="$(document.getElementById('removeUserForm')).attr('action', '/manageuser/delete/${login.user.id}');
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

                </c:otherwise>
            </c:choose>
        </div>

    </body>
</html>
