<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' />" rel="stylesheet"></link>

</head>

<body>

<div class="generic-container">
    <div class="form-signin">
        <div class="well lead">User Registration Form</div>
        <form:form method="POST" modelAttribute="user" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>

            <div class="container-fluid">
                <div class="row">
                    <h2 class="text-center" style="color: #f0ad4e;"> <Strong> Signup </Strong></h2> <hr />
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="form-group">
                                <div class="input-group">
                                    <label for="firstName" class="control-label">First name</label>
                                    <form:input path="firstName" cssClass="form-control" placeholder="e.g. Harris"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="form-group">
                                <div class="input-group">
                                    <label for="firstName" class="control-label">Last name</label>
                                    <form:input path = "lastName" cssClass="form-control" placeholder="e.g. Potter" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="form-group">
                                <div class="input-group">
                                    <label for="firstName" class="control-label">Email address</label>
                                    <form:input path="emailAddress" cssClass="form-control" placeholder="e.g. test@test.com" />
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
            </div>

            <%--
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="firstName">First Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="firstName" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="lastName">Last Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="lastName" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userId">Email</label>
                    <div class="col-md-7">
                        <form:input type="text" path="userId" id="userId" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="userId" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group floatRight">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/user/list' />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/user/list' />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            --%>
            <c:if test="${edit}">
                <span class="well pull-left">
                    <a href="<c:url value='/user/add-document-${user.id}' />">Click here to upload/manage your documents</a>
                </span>
            </c:if>



        </form:form>
    </div>
</div>
</body>
</html>