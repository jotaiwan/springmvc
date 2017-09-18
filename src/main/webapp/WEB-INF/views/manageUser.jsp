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
                    ${login.username}
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
