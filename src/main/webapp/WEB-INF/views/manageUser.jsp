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
                                                    <span class="glyphicon glyphicon-envelope"></span>
                                                </div>
                                                <form:input path="emailAddress" cssClass="form-control" placeholder="Enter E-Mail" />
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
                    <div>edit ${login.username} here..</div>
                    <form:form method = "POST" action = "/manageuser/update/${login.id}" modelAttribute="login">
                        <table>
                            <tr>
                                <td><form:label cssclass="control-label" path = "username">Username</form:label></td>
                                <td><form:input cssClass="form-control" path = "username" /></td>
                            </tr>
                            <tr>
                                <td><form:label cssclass="control-label" path = "password">Password</form:label></td>
                                <td><form:password cssClass="form-control" path = "password" /></td>
                            </tr>
                            <tr>
                                <td colspan = "2">
                                    <input type = "submit" class="btn btn-primary  btn-md" value = "Submit"/>
                                </td>
                            </tr>
                        </table>
                    </form:form>
                </c:when>
                <c:otherwise>
                    <form action="/manageuser/all" method="post" id="removeUserForm" role="form" >
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
                                        ${login.emailAddress}
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
                    </form>

                </c:otherwise>
            </c:choose>
        </div>

    </body>
</html>
