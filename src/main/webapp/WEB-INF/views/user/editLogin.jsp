<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form id="login-form" modelAttribute="login" action="/manageuser/login/update/${login.id}" method="post" role="form">
    <form:hidden path="username"/>
    <div class="form-group" >
        <label for="currentPassword" class="control-label">Current Password</label>
        <form:password cssClass="form-control" path="currentPassword" placeholder="Current Password" disabled="disabled" />
        <span class="help-block text-danger"><form:errors class="text-danger" path="currentPassword" /></span>
    </div>
    <div class="form-group" >
        <label for="password" class="control-label">New Password</label>
        <form:password cssClass="form-control" path="password" placeholder="New Password" disabled="disabled" />
        <span class="help-block"><form:errors class="text-danger" path="password" /></span>
    </div>
    <div class="form-group" >
        <label for="confirmPassword" class="control-label">Confirm Password</label>
        <form:password cssClass="form-control" path="confirmPassword" placeholder="Confirm Password" disabled="disabled" />
        <span class="help-block text-danger"><form:errors class="text-danger" path="confirmPassword" /></span>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
                <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Update">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-lg-12">
                <div class="text-center">
                    <a href="https://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a>
                </div>
            </div>
        </div>
    </div>
</form:form>






<%--
<form:form method = "POST" action = "/manageuser/update/${login.id}" modelAttribute="login" cssClass="form-account-wide">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <form:hidden path="username" />
                <h3 class="modal-title btn btn-success btn-lg" id="myModalLabel">
                    <span class="glyphicon glyphicon-user"></span>
                    <c:out value="${login.username}" /></h3>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-6">
                        <form:hidden path="passwordReset" id="isPasswordReset"/>
                        <div class="well <c:out value="${login.passwordReset == true ? '' : 'collapse'}"/>" id="password" data-toggle="password">
                            <div class="form-group" >
                                <label for="currentPassword" class="control-label">Current Password</label>
                                <form:password cssClass="form-control" path="currentPassword" placeholder="Current Password" disabled="disabled" />
                                <span class="help-block"></span>
                                <form:errors path="currentPassword" />
                            </div>
                            <div class="form-group" >
                                <label for="password" class="control-label">New Password</label>
                                <form:password cssClass="form-control" path="password" placeholder="New Password" disabled="disabled" />
                                <span class="help-block"></span>
                            </div>
                            <div class="form-group" >
                                <label for="confirmPassword" class="control-label">Confirm Password</label>
                                <form:password cssClass="form-control" path="confirmPassword" placeholder="Confirm Password" disabled="disabled" />
                                <span class="help-block"></span>
                            </div>

                        </div>
                    </div>

                    <div id="userInfo" class="<c:out value="${login.passwordReset == true ? 'col-xs-6': 'col-xs-12'}"/>">
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
                        <a href="#" data-toggle="collapse" id="includePasswordChange">Include Password Reset?</a>
                        <button type="submit" class="btn btn-primary">Save Change</button>
                    </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form:form>
--%>